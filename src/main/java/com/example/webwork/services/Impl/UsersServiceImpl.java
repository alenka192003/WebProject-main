package com.example.webwork.services.Impl;

import com.example.webwork.dto.dtoss.*;
import com.example.webwork.except.BrandNotFoundException;
import com.example.webwork.except.UsersConflictException;
import com.example.webwork.except.UsersNotFoundException;
import com.example.webwork.dto.UsersDTO;
import com.example.webwork.models.Brand;
import com.example.webwork.models.Model;
import com.example.webwork.models.Offer;
import com.example.webwork.models.Users;
import com.example.webwork.repo.ModelRepository;
import com.example.webwork.repo.OfferRepository;
import com.example.webwork.repo.RoleRepository;
import com.example.webwork.repo.UsersRepository;
import com.example.webwork.services.UsersService;
import com.example.webwork.util.ValidationUtil;
import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import java.util.stream.Collectors;

@Service
@EnableCaching
public class UsersServiceImpl implements UsersService {
    private final ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;

    private static final Logger LOG = LogManager.getLogger(Service.class);

    private RoleRepository roleRepository;
    private ModelRepository modelRepository;
    private UsersRepository userRepository;
    private OfferRepository offerRepository;
    private final ValidationUtil validationUtil;
    private AuthService authService;

    @Autowired
    public UsersServiceImpl(ModelMapper modelMapper, ModelRepository modelRepository,RoleRepository roleRepository, ValidationUtil validationUtil, OfferRepository offerRepository,AuthService authService) {
        this.roleRepository = roleRepository;
        this.modelRepository=modelRepository;
        this.offerRepository = offerRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.authService=authService;
    }

    @Override
    public List<UsersDTO> getAll() {return userRepository.findAll().stream().map((s) -> modelMapper.map(s, UsersDTO.class)).collect(Collectors.toList());}

    @Override
    public Optional<UsersDTO> findById(String id) {return Optional.ofNullable(modelMapper.map(userRepository.findById(id), UsersDTO.class));}
    @Override
    public void expel(String id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
        } else {
            throw new UsersNotFoundException(id);
        }
    }
    @Autowired
    public void setUsersRepository(UsersRepository userRepository) {
        this.userRepository = userRepository;
    }
    @CacheEvict(cacheNames = "users", allEntries = true)
    public void addUser(AddUserDto userModel) {
        authService.addUser(userModel);
    }



    @Cacheable("users")
    public List<ShowInfoUsers> allUsers() {
        return userRepository.findAll().stream().map(users -> modelMapper.map(users, ShowInfoUsers.class))
                .collect(Collectors.toList());
    }

    public Users getUser(String userName) {
        System.out.println(userName);
        return userRepository.findByUserName(userName)
                .orElseThrow(() -> new UsersNotFoundException(userName + " was not found!"));
    }


    @Override
    public ShowInfoUsers userDetails(String userName) {
        return modelMapper.map(userRepository.findByUserName(userName), ShowInfoUsers.class);
    }

    @Override
    public List<ShowInfoOffer> getOffersByUserName(String userName) {
        Users user = userRepository.findByUserName(userName).orElse(null);
        List<Offer> offers = offerRepository.findByUsersId(user.getId());
        return offers.stream()
                .map(offer -> modelMapper.map(offer, ShowInfoOffer.class))
                .collect(Collectors.toList());
    }

    public void removeUser(String userName) {
        userRepository.deleteByUserName(userName);
    }

    public Users getUserDetails(String userName) {
        return userRepository.findByUserName(userName).orElse(null);
    }
    @CacheEvict(value = "users", key = "#userName")
    @Override
    public void updateUser(String userName, UpdateUserDto updateUserDto) {
        LOG.info("Updating user: {}", userName);
        Users existingUser = userRepository.findByUserName(userName).orElse(null);
        if (existingUser == null) {
            throw new UsersNotFoundException(userName);
        }

        existingUser.setFirstName(updateUserDto.getFirstName());
        existingUser.setLastName(updateUserDto.getLastName());
        existingUser.setModified(LocalDateTime.now());

        userRepository.save(existingUser);
        LOG.info("User {} updated successfully", userName);

    }

    @Override
    public void addOfferUser(OfferAddDTO offerAddDTO) {
        // Создаем объект Offer из DTO
        Offer offer = new Offer();
        offer.setEngineEnum(offerAddDTO.getEngineEnum());

        offer.setUsers(userRepository.findByUserName(offerAddDTO.getUn()).orElse(null));
        offer.setModel(modelRepository.findByName(offerAddDTO.getModelName()).orElse(null));
        offer.setCreated(LocalDateTime.now());
        offer.setTransmissionEnum(offerAddDTO.getTransmissionEnum());
        offer.setYear(offerAddDTO.getYear());
        offer.setPrice(offerAddDTO.getPrice());
        offer.setMileage(offerAddDTO.getMileage());
        offer.setImageUrl(offerAddDTO.getImageURL());
        offer.setDescription(offerAddDTO.getDescription());
        offer.setEngineEnum(offerAddDTO.getEngineEnum());
        offerRepository.save(offer);
    }
    //доразобраться с оформить заказ не видит при сохранении id user+model

}


