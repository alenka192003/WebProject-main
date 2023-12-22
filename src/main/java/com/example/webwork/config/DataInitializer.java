package com.example.webwork.config;

import com.example.webwork.dto.BrandDTO;
import com.example.webwork.dto.ModelDTO;
import com.example.webwork.dto.RoleDTO;
import com.example.webwork.dto.UsersDTO;
import com.example.webwork.enums.CategoryEnum;
import com.example.webwork.enums.RoleEnum;
import com.example.webwork.models.Role;
import com.example.webwork.models.Users;
import com.example.webwork.repo.RoleRepository;
import com.example.webwork.repo.UsersRepository;
import com.example.webwork.services.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {
    private final RoleService roleService;
    private final RoleRepository roleRepository;

    private final BrandService brandService;
    private final ModelService modelService;
    private final UsersService usersService;
    private final PasswordEncoder passwordEncoder;
    private final UsersRepository usersRepository;
    private final OfferService offerService;
    private final String defaultPassword;
    public DataInitializer(RoleService roleService, RoleRepository roleRepository, PasswordEncoder passwordEncoder, @Value("topsecret") String defaultPassword, BrandService brandService, ModelService modelService,
                           UsersService usersService, UsersRepository usersRepository, OfferService offerService) {
        this.roleService = roleService;
        this.roleRepository = roleRepository;
        this.brandService = brandService;
        this.passwordEncoder = passwordEncoder;
        this.modelService = modelService;
        this.usersService = usersService;
        this.usersRepository = usersRepository;
        this.offerService = offerService;
        this.defaultPassword = defaultPassword;
    }

    @Override
    public void run(String... args) {
        initRoles();
        initUsers();
    }

    private void seedData() {
    }


    private void initRoles() {
        if (roleRepository.count() == 0) {
            var adminRole = new Role(RoleEnum.Admin);
            var normalUserRole = new Role(RoleEnum.User);

            roleRepository.save(adminRole);
            roleRepository.save(normalUserRole);
        }
    }
    private void initAdmin(){
        var adminRole = roleRepository.findByRoleEnum(RoleEnum.Admin).orElseThrow();

        var adminUser = new Users("admin", "admin", "admin",passwordEncoder.encode(defaultPassword),"admin@example.com");
        adminUser.setRole(adminRole);

        usersRepository.save(adminUser);
    }
    private void initNormalUser(){
        var userRole = roleRepository.findByRoleEnum(RoleEnum.User).orElseThrow();

        var normalUser = new Users("user", "user","user", passwordEncoder.encode(defaultPassword),"user@example.com");
        normalUser.setRole(userRole);

        usersRepository.save(normalUser);
    }
    private void initUsers() {
        if (usersRepository.count() == 0) {
            initAdmin();
            initNormalUser();
        }
    }

}