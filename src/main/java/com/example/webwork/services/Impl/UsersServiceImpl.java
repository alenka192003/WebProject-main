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
import com.example.webwork.repo.OfferRepository;
import com.example.webwork.repo.RoleRepository;
import com.example.webwork.repo.UsersRepository;
import com.example.webwork.services.UsersService;
import com.example.webwork.util.ValidationUtil;
import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import java.util.stream.Collectors;

@Service
@EnableCaching
public class UsersServiceImpl implements UsersService {
    private final ModelMapper modelMapper;
    private static final Logger logger = LoggerFactory.getLogger(UsersServiceImpl.class);

    private RoleRepository roleRepository;
    private UsersRepository userRepository;
    private OfferRepository offerRepository;
    private final ValidationUtil validationUtil;

    @Autowired
    public UsersServiceImpl(ModelMapper modelMapper, RoleRepository roleRepository, ValidationUtil validationUtil, OfferRepository offerRepository) {
        this.roleRepository = roleRepository;
        this.offerRepository = offerRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public UsersDTO registerUser(UsersDTO users) {

        if (!this.validationUtil.isValid(users)) {
            this.validationUtil
                    .violations(users)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
            throw new IllegalArgumentException("не подходит");
        }

        Users u = modelMapper.map(users, Users.class);
        String userId = u.getId();
        if (u.getId() == null || userRepository.findById(userId).isEmpty()) {
            return modelMapper.map(userRepository.save(u), UsersDTO.class);
        } else {
            throw new UsersConflictException("уже существует с таким id");
        }
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
        System.out.println(userModel);
        /*userModel.setCreated(LocalDateTime.now());
        userModel.setModified(LocalDateTime.now());*/
        Users users = modelMapper.map(userModel, Users.class);
        users.setRole(roleRepository.findByRoleEnum(userModel.getRole()).orElse(null));
        users.setCreated(LocalDateTime.now());
        users.setModified(LocalDateTime.now());
        userRepository.saveAndFlush(users);
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
        logger.info("Updating user: {}", userName);
        Users existingUser = userRepository.findByUserName(userName).orElse(null);
        if (existingUser == null) {
            throw new UsersNotFoundException(userName);
        }

        // Update the fields
        existingUser.setFirstName(updateUserDto.getFirstName());
        existingUser.setLastName(updateUserDto.getLastName());
        existingUser.setPassword(updateUserDto.getPassword());
        existingUser.setModified(LocalDateTime.now());

        // Save the updated user
        userRepository.save(existingUser);
        logger.info("User {} updated successfully", userName);
    }

}


