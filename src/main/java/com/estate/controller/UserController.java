package com.estate.controller;

import com.estate.dto.UserDTO;
import com.estate.dto.request.UserPasswordRequest;
import com.estate.dto.request.UserProfileRequest;
import com.estate.dto.request.UserSearchRequest;
import com.estate.service.IRoleService;
import com.estate.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    private final IRoleService roleService;

    @GetMapping("/roles")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<?> getRoles() {
        return ResponseEntity.ok(roleService.getRoles());
    }


    @PostMapping("/search")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<?> searchUsers(@RequestBody UserSearchRequest request,
                                                                 @PageableDefault(size = 4, sort = "username", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(userService.searchUsers(request, pageable));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<?> getUserById(@PathVariable("id") long id) {
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<?> createUser(@RequestBody UserDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(dto));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.updateUser(id, userDTO));
    }

    @DeleteMapping
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<?> deleteUsers(@RequestBody List<Long> ids) {
        if (ids != null && !ids.isEmpty()) {
            userService.deleteUsers(ids);
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/staffs")
    public ResponseEntity<?> getStaffs() {
        return ResponseEntity.ok(userService.getStaffs());
    }


    @PutMapping("/{id}/change-password")
    public ResponseEntity<?> changeUserPassword(@PathVariable("id") Long id, @RequestBody UserPasswordRequest userPasswordRequest) {
        userService.updatePassword(id, userPasswordRequest);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/reset-password")
    public ResponseEntity<?> resetUserPassword(@PathVariable("id") long id) {
        return ResponseEntity.ok(userService.resetPassword(id));
    }

    @GetMapping("/{id}/profile")
    public ResponseEntity<?> getProfileUserByUserName(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.findUserProfileById(id));
    }

    @PutMapping("/{id}/profile")
    public ResponseEntity<?> updateProfileUser(@PathVariable("id") Long id, @RequestBody UserProfileRequest request) {
        return ResponseEntity.ok(userService.updateUserProfile(id, request));
    }
    
}
