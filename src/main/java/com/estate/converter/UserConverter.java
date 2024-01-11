package com.estate.converter;

import com.estate.dto.UserDTO;
import com.estate.dto.respone.UserProfileResponse;
import com.estate.dto.respone.UserSearchResponse;
import com.estate.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserConverter {

    private final ModelMapper modelMapper;

    public UserDTO convertToDTO(UserEntity entity) {

        UserDTO dto =  modelMapper.map(entity, UserDTO.class);
        if (!entity.getRoles().isEmpty()) {
            dto.setRoleCode(entity.getRoles().get(0).getCode());
        }
        return dto;
    }

    public UserEntity convertToEntity(UserDTO dto) {
        return modelMapper.map(dto, UserEntity.class);
    }

    public UserSearchResponse convertToUserSearchResponse(UserEntity entity) {

        UserSearchResponse response = modelMapper.map(entity, UserSearchResponse.class);
        if (!entity.getRoles().isEmpty()) {
            response.setRoleCode(entity.getRoles().get(0).getCode());
        }
        return response;
    }

    public UserProfileResponse convertToUserProfileResponse(UserEntity entity) {

        UserProfileResponse response = modelMapper.map(entity, UserProfileResponse.class);
        if (!entity.getRoles().isEmpty()) {
            response.setRoleCode(entity.getRoles().get(0).getCode());
        }
        return response;
    }

}
