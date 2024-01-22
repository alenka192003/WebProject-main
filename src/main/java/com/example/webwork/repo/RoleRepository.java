package com.example.webwork.repo;

import com.example.webwork.enums.RoleEnum;
import com.example.webwork.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    Optional<Role> findByRoleEnum(RoleEnum roleEnum);
}
