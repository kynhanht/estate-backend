package com.estate.service;

import com.estate.dto.UserDTO;
import com.estate.dto.request.UserPasswordRequest;
import com.estate.dto.request.UserProfileRequest;
import com.estate.dto.request.UserSearchRequest;
import com.estate.dto.respone.StaffResponse;
import com.estate.dto.respone.UserProfileResponse;
import com.estate.dto.respone.UserSearchResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface IUserService {

    Page<UserSearchResponse> searchUsers(UserSearchRequest request, Pageable pageable);

    UserProfileResponse findUserProfileById(Long id);

    UserDTO findUserById(Long id);

    UserDTO createUser(UserDTO dto);

    UserDTO updateUser(Long id, UserDTO dto);

    void updatePassword(Long id, UserPasswordRequest request);

    UserDTO resetPassword(Long id);

    UserProfileResponse updateUserProfile(Long id, UserProfileRequest request);

    void deleteUsers(List<Long> ids);

    Map<Long, String> getStaffs();

    List<StaffResponse> findStaffsByBuildingId(Long buildingId);

    List<StaffResponse> findStaffsByCustomerId(Long customerId);

}
