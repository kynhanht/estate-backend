package com.estate.controller;

import com.estate.dto.AuthToken;
import com.estate.dto.UserDTO;
import com.estate.dto.request.PasswordRequest;
import com.estate.dto.request.UserSearchRequest;
import com.estate.dto.respone.StaffResponse;
import com.estate.dto.respone.UserSearchResponse;
import com.estate.security.JwtTokenProvider;
import com.estate.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenUtil;

    private final IUserService userService;

    @PostMapping("/authentication")
    public ResponseEntity<?> authentication(@RequestBody UserDTO userDTO) throws AuthenticationException {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userDTO.getUserName(),
                        userDTO.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);
        return ResponseEntity.ok(new AuthToken(token));
    }


    @PostMapping("/search")
    public ResponseEntity<PageImpl<UserSearchResponse>> searchStaffs(@RequestBody UserSearchRequest request,
                                                     @PageableDefault(size = 4 ,sort = "userName", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(userService.searchUsers(request, pageable));
    }

    @GetMapping("/staffs")
    public ResponseEntity<Map<Long, String>> getStaffs() {
        return ResponseEntity.ok(userService.getStaffs());
    }


    @GetMapping("/{buildingId}")
    public ResponseEntity<List<StaffResponse>> getStaffsByBuildingId(@PathVariable Long buildingId) {

        return ResponseEntity.ok(userService.findStaffsByBuildingId(buildingId));
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDTO> getUserByUsername(@PathVariable("username") String username) {
        return ResponseEntity.ok(userService.findUserByUserName(username));
    }


    @PostMapping
    public ResponseEntity<UserDTO> createUsers(@RequestBody UserDTO newUser) {
        return ResponseEntity.ok(userService.createUser(newUser));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUsers(@PathVariable("id") Long id, @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.updateUser(id, userDTO));
    }

    @PutMapping("/change-password/{id}")
    public ResponseEntity<String> changePasswordUser(@PathVariable("id") Long id, @RequestBody PasswordRequest passwordRequest) {
        userService.updatePassword(id, passwordRequest);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/password/{id}/reset")
    public ResponseEntity<UserDTO> resetPassword(@PathVariable("id") long id) {
        return ResponseEntity.ok(userService.resetPassword(id));
    }

    @PutMapping("/profile/{username}")
    public ResponseEntity<UserDTO> updateProfileOfUser(@PathVariable("username") String username, @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.updateProfileOfUser(username, userDTO));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUsers(@RequestBody List<Long> ids) {
        if (ids != null && !ids.isEmpty()) {
            userService.deleteUsers(ids);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{buildingId}/staffs")
    public ResponseEntity<List<StaffResponse>> loadStaff(@PathVariable Long buildingId) {

        return ResponseEntity.ok(userService.findStaffsByBuildingId(buildingId));
    }
}
