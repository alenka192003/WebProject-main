package com.example.webwork.repo;

import com.example.webwork.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Repository
public interface UsersRepository extends JpaRepository<Users, String> {

    Optional<Users> findByUserName(String userName);
    Optional<Users> findByEmail(String email);
    @Modifying
    @Transactional
    void deleteByUserName(String userName);
}
