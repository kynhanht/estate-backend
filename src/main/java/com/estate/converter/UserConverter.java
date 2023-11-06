package com.estate.converter;

import com.estate.dto.UserDTO;
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
        return modelMapper.map(entity, UserDTO.class);
    }

    public UserEntity convertToEntity(UserDTO dto) {
        return modelMapper.map(dto, UserEntity.class);
    }

    public UserSearchResponse convertToUserSearchResponse(UserEntity userEntity) {

        UserSearchResponse response = modelMapper.map(userEntity, UserSearchResponse.class);
        if (!userEntity.getRoles().isEmpty()) {
            response.setRoleCode(userEntity.getRoles().get(0).getCode());
        }
        return response;
    }

}
