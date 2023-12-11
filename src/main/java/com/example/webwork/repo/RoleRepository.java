package com.example.webwork.repo;

import com.example.webwork.dto.RoleDTO;
import com.example.webwork.enums.RoleEnum;
import com.example.webwork.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, String> {
    Optional<Role> findByRoleEnum(RoleEnum roleEnum);


}
