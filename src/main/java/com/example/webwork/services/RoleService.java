package com.example.webwork.services;

import com.example.webwork.dto.RoleDTO;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    RoleDTO registerRole(RoleDTO role);

    List<RoleDTO> getAll();

    Optional<RoleDTO> findById(String id);

    void expel(String id);

    RoleDTO update(RoleDTO role);
}

