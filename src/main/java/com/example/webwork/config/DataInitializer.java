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
    public void run(String... args){
        initRoles();
        initUsers();
    }

    private void seedData() {
/*

        RoleDTO role1 = new RoleDTO(null, RoleEnum.Admin);
        RoleDTO role2 = new RoleDTO(null, RoleEnum.User);
        role1 = roleService.registerRole(role1);
        role2 = roleService.registerRole(role2);

        LocalDateTime created1 = LocalDateTime.now();
        LocalDateTime created2 = LocalDateTime.of(1909, 12, 19, 1, 13, 16);
        LocalDateTime modified1 = LocalDateTime.of(1923, 1, 1, 3, 14, 17);
        LocalDateTime modified2 = LocalDateTime.of(1022, 2, 11, 8, 15, 18);

        BrandDTO brand1 = new BrandDTO(null, "Lada", created1, modified1);
        BrandDTO brand2 = new BrandDTO(null, "ВАЗ", created2, modified2);
        brand1 = brandService.registerBrand(brand1);
        brand2 = brandService.registerBrand(brand2);

        ModelDTO model1 = new ModelDTO(null, brand2, "PUPG", CategoryEnum.Motorcycle, "https://kkkk", 2018, 2023, created1, modified2);
        ModelDTO model2 = new ModelDTO(null, brand1, "икисииксибокус", CategoryEnum.Bus, "https://k11h1hh", 2000, 2094, created2, modified2);

        model1 = modelService.registerModel(model1);
        model2 = modelService.registerModel(model2);

        UsersDTO user1 = new UsersDTO(null, role1, "alena", "1234560340", "alena", "Brezhneva", true, "http://kkkdad.ru", created1, modified1);
        UsersDTO user2 = new UsersDTO(null, role2, "Alena2", "1234567890", "alena", "Brezhneva2", true, "http://kjfjjfj.ru", created2, modified2);

        user1 = usersService.registerUser(user1);
        user2 = usersService.registerUser(user2);

        BigDecimal price1 = new BigDecimal(100);
        BigDecimal price2 = new BigDecimal(1);

        OfferDTO offer1 = new OfferDTO(null, model1, user2, "Заказ", EngineEnum.ELECTRIC, "http://kkkkkgfdds", 9, price1, TransmissionEnum.MANUAL, 2002);
        OfferDTO offer2 = new OfferDTO(null, model2, user1, "Заказ 2", EngineEnum.GASOLINE, "http://kksdakd", 1, price2, TransmissionEnum.AUTOMATIC, 2002);


        offerService.registerOffer(offer1);
        offerService.registerOffer(offer2);*/
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

        var adminUser = new Users("admin", passwordEncoder.encode(defaultPassword), "admin@example.com");
        adminUser.setRole(adminRole);

        usersRepository.save(adminUser);
    }
    private void initNormalUser(){
        var userRole = roleRepository.findByRoleEnum(RoleEnum.User).orElseThrow();

        var normalUser = new Users("user", passwordEncoder.encode(defaultPassword), "user@example.com");
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