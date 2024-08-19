package com.estate.service.impl;

import com.estate.constant.ErrorMessageConstants;
import com.estate.constant.SystemConstants;
import com.estate.dto.CustomUserDetail;
import com.estate.entity.RoleEntity;
import com.estate.entity.UserEntity;
import com.estate.exception.NotFoundException;
import com.estate.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository
                .findOneByUserNameAndStatus(username, SystemConstants.ACTIVE_STATUS)
                .orElseThrow(() -> new NotFoundException(ErrorMessageConstants.USER_NOT_FOUND));

        List<GrantedAuthority> authorities = new ArrayList<>();
        for (RoleEntity role : userEntity.getRoles()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getCode()));
        }
        CustomUserDetail customUserDetail = new CustomUserDetail(username, userEntity.getPassword(), true, true, true, true, authorities);
        customUserDetail.setId(userEntity.getId());
        customUserDetail.setFullName(userEntity.getFullName());
        return customUserDetail;
    }
}
