package com.example.webwork.repo;

import com.example.webwork.dto.RoleDTO;
import com.example.webwork.enums.RoleEnum;
import com.example.webwork.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
    Role findByRoleEnum(RoleEnum roleEnum);

}
