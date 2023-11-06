package com.estate.service.impl;

import com.estate.converter.RoleConverter;
import com.estate.dto.RoleDTO;
import com.estate.entity.RoleEntity;
import com.estate.repository.RoleRepository;
import com.estate.service.IRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RoleService implements IRoleService {

    private final RoleRepository roleRepository;

    private final RoleConverter roleConverter;

    public List<RoleDTO> findAll() {
        List<RoleEntity> roleEntities = roleRepository.findAll();
        List<RoleDTO> list = new ArrayList<>();
        roleEntities.forEach(item -> {
            RoleDTO roleDTO = roleConverter.convertToDto(item);
            list.add(roleDTO);
        });
        return list;
    }

    @Override
    public Map<String, String> getRoles() {
        Map<String, String> roles = new HashMap<>();
        List<RoleEntity> roleEntities = roleRepository.findAll();
        roleEntities.forEach(entity -> roles.put(entity.getCode(), entity.getName()));
        return roles;
    }
}
