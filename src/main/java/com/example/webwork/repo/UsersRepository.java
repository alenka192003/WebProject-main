package com.example.webwork.repo;

import com.example.webwork.enums.RoleEnum;
import com.example.webwork.models.Model;
import com.example.webwork.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, String> {

    Optional<Users> findByUserName(String userName);
    @Modifying
    @Transactional
    void deleteByUserName(String userName);
}
