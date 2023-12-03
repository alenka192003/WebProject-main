package com.example.webwork.config;

import com.example.webwork.enums.CategoryEnum;
import com.example.webwork.enums.EngineEnum;
import com.example.webwork.enums.RoleEnum;
import com.example.webwork.enums.TransmissionEnum;
import com.example.webwork.dto.*;
import com.example.webwork.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class DataInitializer implements CommandLineRunner {
    private final RoleService roleService;
    private final BrandService brandService;
    private final ModelService modelService;
    private final UsersService usersService;
    private final OfferService offerService;
    public DataInitializer(RoleService roleService, BrandService brandService, ModelService modelService,
                           UsersService usersService, OfferService offerService) {
        this.roleService = roleService;
        this.brandService = brandService;
        this.modelService = modelService;
        this.usersService = usersService;
        this.offerService = offerService;
    }

    @Override
    public void run(String... args){
        seedData();
    }

    private void seedData() {
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

        OfferDTO offer1 = new OfferDTO(null, model1, user2, "Заказ", EngineEnum.ELECTRIC, "http://kkkkkgfdds", 9, price1, TransmissionEnum.MANUAL, 2002, created1, modified2);
        OfferDTO offer2 = new OfferDTO(null, model2, user1, "Заказ 2", EngineEnum.GASOLINE, "http://kksdakd", 1, price2, TransmissionEnum.AUTOMATIC, 2002, created2, modified2);


        offerService.registerOffer(offer1);
        offerService.registerOffer(offer2);
    }
}