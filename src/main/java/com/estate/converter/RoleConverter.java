package com.estate.converter;

import com.estate.dto.RoleDTO;
import com.estate.entity.RoleEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleConverter {


    private final ModelMapper modelMapper;

    public RoleDTO convertToDto(RoleEntity entity) {
        return modelMapper.map(entity, RoleDTO.class);
    }

    public RoleEntity convertToEntity(RoleDTO dto) {
        return modelMapper.map(dto, RoleEntity.class);
    }
}
