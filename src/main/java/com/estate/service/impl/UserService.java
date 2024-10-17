package com.estate.service.impl;

import com.estate.constant.ErrorMessageConstants;
import com.estate.constant.SystemConstants;
import com.estate.converter.UserConverter;
import com.estate.dto.UserDTO;
import com.estate.dto.request.UserPasswordRequest;
import com.estate.dto.request.UserProfileRequest;
import com.estate.dto.request.UserSearchRequest;
import com.estate.dto.respone.StaffResponse;
import com.estate.dto.respone.UserProfileResponse;
import com.estate.dto.respone.UserSearchResponse;
import com.estate.entity.AbstractEntity;
import com.estate.entity.RoleEntity;
import com.estate.entity.UserEntity;
import com.estate.entity.UserEntity_;
import com.estate.enums.SearchOperationEnum;
import com.estate.exception.InternalException;
import com.estate.exception.NotFoundException;
import com.estate.repository.RoleRepository;
import com.estate.repository.UserRepository;
import com.estate.service.IUserService;
import com.estate.specifications.SearchCriteria;
import com.estate.specifications.UserSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserConverter userConverter;


    @Override
    public Page<UserSearchResponse> searchUsers(UserSearchRequest request, Pageable pageable) {

        UserSpecification userSpecification = new UserSpecification();
        Specification<UserEntity> specification = Specification
                .where(userSpecification.byCommon(new SearchCriteria(UserEntity_.STATUS, SystemConstants.ACTIVE_STATUS, SearchOperationEnum.EQUAL)))
                .and(userSpecification.byCommon(new SearchCriteria(UserEntity_.FULL_NAME, request.getUsername(), SearchOperationEnum.CONTAINING))
                        .or(userSpecification.byCommon(new SearchCriteria(UserEntity_.USERNAME, request.getUsername(), SearchOperationEnum.CONTAINING))));

        Page<UserEntity> page = userRepository.findAll(specification, pageable);
        List<UserSearchResponse> responses = page
                .getContent()
                .stream()
                .map(userConverter::convertToUserSearchResponse)
                .collect(Collectors.toList());

        return new PageImpl<>(responses, page.getPageable(), page.getTotalElements());
    }

    @Override
    public UserProfileResponse findUserProfileById(Long id) {
        UserEntity entity = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessageConstants.USER_NOT_FOUND));
        return userConverter.convertToUserProfileResponse(entity);
    }

    @Override
    public UserDTO findUserById(Long id) {
        UserEntity entity = userRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessageConstants.USER_NOT_FOUND));
        List<RoleEntity> roles = entity.getRoles();
        UserDTO dto = userConverter.convertToDTO(entity);
        roles.forEach(item -> dto.setRoleCode(item.getCode()));
        return dto;
    }

    @Override
    @Transactional
    public UserDTO createUser(UserDTO newUser) {
        RoleEntity role = roleRepository
                .findOneByCode(newUser.getRoleCode())
                .orElseThrow(() -> new NotFoundException(ErrorMessageConstants.ROLE_NOT_FOUND));
        UserEntity userEntity = userConverter.convertToEntity(newUser);
        userEntity.setFullName(newUser.getFullName());
        userEntity.setRoles(Stream.of(role).collect(Collectors.toList()));
        userEntity.setStatus(SystemConstants.ACTIVE_STATUS);
        userEntity.setPassword(passwordEncoder.encode(SystemConstants.PASSWORD_DEFAULT));
        return userConverter.convertToDTO(userRepository.save(userEntity));
    }

    @Override
    @Transactional
    public UserDTO updateUser(Long id, UserDTO updateUser) {
        RoleEntity role = roleRepository
                .findOneByCode(updateUser.getRoleCode())
                .orElseThrow(() -> new NotFoundException(ErrorMessageConstants.ROLE_NOT_FOUND));
        UserEntity oldUser = userRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessageConstants.USER_NOT_FOUND));
        oldUser.setFullName(updateUser.getFullName());
        oldUser.setRoles(Stream.of(role).collect(Collectors.toList()));
        return userConverter.convertToDTO(userRepository.save(oldUser));
    }

    @Override
    @Transactional
    public void updatePassword(Long id, UserPasswordRequest userPasswordRequest) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessageConstants.USER_NOT_FOUND));
        if (passwordEncoder.matches(userPasswordRequest.getOldPassword(), user.getPassword())
                && userPasswordRequest.getNewPassword().equals(userPasswordRequest.getConfirmPassword())) {
            user.setPassword(passwordEncoder.encode(userPasswordRequest.getNewPassword()));
            userRepository.save(user);
        } else {
            throw new InternalException(ErrorMessageConstants.CHANGE_PASSWORD_FAIL);
        }
    }

    @Override
    @Transactional
    public UserDTO resetPassword(Long id) {
        UserEntity userEntity = userRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessageConstants.USER_NOT_FOUND));
        userEntity.setPassword(passwordEncoder.encode(SystemConstants.PASSWORD_DEFAULT));
        return userConverter.convertToDTO(userRepository.save(userEntity));
    }

    @Override
    @Transactional
    public UserProfileResponse updateUserProfile(Long id, UserProfileRequest request) {
        UserEntity entity = userRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessageConstants.USER_NOT_FOUND));
        entity.setFullName(request.getFullName());
        entity.setPhone(request.getPhone());
        entity.setEmail(request.getEmail());
        return userConverter.convertToUserProfileResponse(userRepository.save(entity));
    }

    @Override
    @Transactional
    public void deleteUsers(List<Long> ids) {
        ids.forEach(id -> {
            UserEntity userEntity = userRepository
                    .findById(id)
                    .orElseThrow(() -> new NotFoundException(ErrorMessageConstants.USER_NOT_FOUND));
            userEntity.setStatus(SystemConstants.INACTIVE_STATUS);
            userRepository.save(userEntity);
        });
    }

    @Override
    public Map<Long, String> getStaffs() {
        Map<Long, String> staffMap = new HashMap<>();
        List<UserEntity> staffs = userRepository.findByStatusAndRoles_Code(SystemConstants.ACTIVE_STATUS, SystemConstants.STAFF);
        for (UserEntity userEntity : staffs) {
            staffMap.put(userEntity.getId(), userEntity.getFullName());
        }
        return staffMap;
    }

    public Map<Long, String> findStaffsByCustomerId(Long customerId) {
        Map<Long, String> staffMap = new HashMap<>();
        List<UserEntity> staffs = userRepository.findByStatusAndCustomers_id(SystemConstants.ACTIVE_STATUS, customerId);
        for (UserEntity userEntity : staffs) {
            staffMap.put(userEntity.getId(), userEntity.getFullName());
        }
        return staffMap;
    }

    @Override
    public List<StaffResponse> findBuildingStaffs(Long buildingId) {
        // Get All staff who are active
        List<UserEntity> staffs = userRepository.findByStatusAndRoles_Code(SystemConstants.ACTIVE_STATUS, SystemConstants.STAFF);
        // Map to List<StaffResponse>
        return staffs.stream().map(staff -> {
                    StaffResponse staffResponse = new StaffResponse();
                    staffResponse.setStaffId(staff.getId());
                    staffResponse.setFullName(staff.getFullName());
                    staffResponse.setChecked(false);
                    // Map into List of buildingId
                    List<Long> buildingIds = staff.getBuildings()
                            .stream()
                            .map(AbstractEntity::getId)
                            .toList();
                    // Check List of buildingId contain request building
                    if (buildingIds.contains(buildingId)) staffResponse.setChecked(true);
                    return staffResponse;
                }
        ).collect(Collectors.toList());
    }

    @Override
    public List<StaffResponse> findCustomerStaffs(Long customerId) {
        // Get All staff who are active
        List<UserEntity> staffs = userRepository.findByStatusAndRoles_Code(SystemConstants.ACTIVE_STATUS, SystemConstants.STAFF);
        // Map to List<StaffResponse>
        return staffs.stream().map(staff -> {
                    StaffResponse staffResponse = new StaffResponse();
                    staffResponse.setStaffId(staff.getId());
                    staffResponse.setFullName(staff.getFullName());
                    staffResponse.setChecked(false);
                    // Map into List of buildingId
                    List<Long> customerIds = staff.getCustomers()
                            .stream()
                            .map(AbstractEntity::getId)
                            .toList();
                    // Check List of customerId contain request customerId
                    if (customerIds.contains(customerId)) staffResponse.setChecked(true);
                    return staffResponse;
                }
        ).collect(Collectors.toList());
    }
}
