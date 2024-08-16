package com.estate.controller;

import com.estate.dto.UserDTO;
import com.estate.dto.request.UserPasswordRequest;
import com.estate.dto.request.UserProfileRequest;
import com.estate.dto.request.UserSearchRequest;
import com.estate.dto.respone.StaffResponse;
import com.estate.dto.respone.UserProfileResponse;
import com.estate.dto.respone.UserSearchResponse;
import com.estate.service.IRoleService;
import com.estate.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    private final IRoleService roleService;

    @GetMapping("/roles")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<Map<String, String>> getRoles() {
        return ResponseEntity.ok(roleService.getRoles());
    }


    @PostMapping("/search")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<Page<UserSearchResponse>> searchStaffs(@RequestBody UserSearchRequest request,
                                                                 @PageableDefault(size = 4, sort = "userName", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(userService.searchUsers(request, pageable));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") long id) {
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(dto));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<UserDTO> updateUser(@PathVariable("id") Long id, @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.updateUser(id, userDTO));
    }

    @DeleteMapping
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<Void> deleteUsers(@RequestBody List<Long> ids) {
        if (ids != null && !ids.isEmpty()) {
            userService.deleteUsers(ids);
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/staffs")
    public ResponseEntity<Map<Long, String>> getStaffs() {
        return ResponseEntity.ok(userService.getStaffs());
    }

    @PutMapping("/{id}/change-password")
    public ResponseEntity<String> changeUserPassword(@PathVariable("id") Long id, @RequestBody UserPasswordRequest userPasswordRequest) {
        userService.updatePassword(id, userPasswordRequest);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/reset-password")
    public ResponseEntity<UserDTO> resetUserPassword(@PathVariable("id") long id) {
        return ResponseEntity.ok(userService.resetPassword(id));
    }

    @GetMapping("/profile/{userName}")
    public ResponseEntity<UserProfileResponse> getProfileUserByUserName(@PathVariable("userName") String userName) {
        return ResponseEntity.ok(userService.findUserByUserName(userName));
    }

    @PutMapping("/profile/{userName}")
    public ResponseEntity<UserProfileResponse> updateProfileUser(@PathVariable("userName") String username, @RequestBody UserProfileRequest request) {
        return ResponseEntity.ok(userService.updateUserProfile(username, request));
    }

    @GetMapping("/{buildingId}/building-staffs")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<List<StaffResponse>> loadStaffByBuildingId(@PathVariable Long buildingId) {

        return ResponseEntity.ok(userService.findStaffsByBuildingId(buildingId));
    }

    @GetMapping("/{customerId}/customer-staffs")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<List<StaffResponse>> loadStaffsByCustomerId(@PathVariable Long customerId) {

        return ResponseEntity.ok(userService.findStaffsByCustomerId(customerId));
    }
}
