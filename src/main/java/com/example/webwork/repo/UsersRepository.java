package com.example.webwork.repo;

import com.example.webwork.enums.RoleEnum;
import com.example.webwork.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsersRepository extends JpaRepository<Users, String> {
}
