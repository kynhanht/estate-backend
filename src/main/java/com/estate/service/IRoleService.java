package com.estate.service;

import com.estate.dto.RoleDTO;

import java.util.List;
import java.util.Map;

public interface IRoleService {
    List<RoleDTO> findAll();

    Map<String, String> getRoles();
}
