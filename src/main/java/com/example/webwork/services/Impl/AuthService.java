package com.example.webwork.services.Impl;

import com.example.webwork.dto.dtoss.UserRegistrationDto;
import com.example.webwork.enums.RoleEnum;
import com.example.webwork.models.Users;
import com.example.webwork.repo.RoleRepository;
import com.example.webwork.repo.UsersRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class AuthService {

    private UsersRepository userRepository;

    private RoleRepository roleRepository;


    private PasswordEncoder passwordEncoder;

    public AuthService(UsersRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public void register(UserRegistrationDto registrationDTO) {
        if (!registrationDTO.getPassword().equals(registrationDTO.getConfirmPassword())) {
            throw new RuntimeException("passwords.match");
        }

        Optional<Users> byEmail = this.userRepository.findByEmail(registrationDTO.getEmail());

        if (byEmail.isPresent()) {
            throw new RuntimeException("email.used");
        }

        var userRole = roleRepository.
                findByRoleEnum(RoleEnum.User).orElseThrow();

        Users user = new Users(
                registrationDTO.getUserName(),
                passwordEncoder.encode(registrationDTO.getPassword()),
                registrationDTO.getEmail()
        );

        user.setRole(userRole);

        this.userRepository.save(user);
    }
}
