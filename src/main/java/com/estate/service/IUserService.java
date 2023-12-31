package com.estate.service;

import com.estate.dto.UserDTO;
import com.estate.dto.request.PasswordRequest;
import com.estate.dto.request.UserSearchRequest;
import com.estate.dto.respone.StaffResponse;
import com.estate.dto.respone.UserSearchResponse;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface IUserService {

    PageImpl<UserSearchResponse> searchUsers(UserSearchRequest request, Pageable pageable);

    UserDTO findUserByUserName(String userName);

    UserDTO findUserById(Long id);

    UserDTO createUser(UserDTO userDTO);

    UserDTO updateUser(Long id, UserDTO userDTO);

    void updatePassword(Long id, PasswordRequest passwordRequest);

    UserDTO resetPassword(Long id);

    UserDTO updateProfileOfUser(String userName, UserDTO userDTO);

    void deleteUsers(List<Long> ids);

    Map<Long, String> getStaffs();

    List<StaffResponse> findStaffsByBuildingId(Long buildingId);

    List<StaffResponse> findStaffByCustomerId(Long customerId);

}
