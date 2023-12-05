package com.example.webwork.services.Impl;

import com.example.webwork.dto.BrandDTO;
import com.example.webwork.dto.dtoss.*;
import com.example.webwork.except.BrandConflictException;
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import java.util.stream.Collectors;

@Service
public class UsersServiceImpl implements UsersService {


    private final ModelMapper modelMapper;
    private UsersRepository userRepository;
    private OfferRepository offerRepository;
    private final ValidationUtil validationUtil;

    @Autowired
    public UsersServiceImpl(ModelMapper modelMapper, ValidationUtil validationUtil, OfferRepository offerRepository) {
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
    public List<UsersDTO> getAll() {
        return userRepository.findAll().stream().map((s) -> modelMapper.map(s, UsersDTO.class)).collect(Collectors.toList());
    }

    @Override
    public Optional<UsersDTO> findById(String id) {
        return Optional.ofNullable(modelMapper.map(userRepository.findById(id), UsersDTO.class));
    }

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

    public void addUser(AddUserDto userModel) {

        Users users = modelMapper.map(userModel, Users.class);
        users.setCreated(LocalDateTime.now());
        users.setModified(LocalDateTime.now());
        userRepository.saveAndFlush(users);
    }

    public List<ShowInfoUsers> allUsers() {
        return userRepository.findAll().stream().map(users -> modelMapper.map(users, ShowInfoUsers.class))
                .collect(Collectors.toList());
    }

    @Override
    public ShowInfoUsers userDetails(String userName) {
        return modelMapper.map(userRepository.findByUserName(userName), ShowInfoUsers.class);
    }

    @Override
    public List<ShowInfoOffer> getOffersByUserName(String userName) {
        Users user = userRepository.findByUserName(userName);

        List<Offer> offers = offerRepository.findByUsers(user);
        return offers.stream()
                .map(model -> modelMapper.map(offers, ShowInfoOffer.class))
                .collect(Collectors.toList());
    }

    public void removeUser(String userName) {
        userRepository.deleteByUserName(userName);
    }

    public Users getUserDetails(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Transactional
    public void updateUser(String userName,String newUserName,String newFirstName, String newLastName, String newPassword, boolean newIsActive) {
        Users user = userRepository.findByUserName(userName);

        if (user != null) {
            user.setUserName(newUserName);
            user.setFirstName(newFirstName);
            user.setLastName(newLastName);
            user.setPassword(newPassword);
            user.setActive(newIsActive);
            user.setModified(LocalDateTime.now());
            userRepository.save(user);
        }


    }
}


