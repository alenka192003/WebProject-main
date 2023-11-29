package com.example.webwork.services.Impl;

import com.example.webwork.dto.BrandDTO;
import com.example.webwork.dto.dtoss.AddBrandDto;
import com.example.webwork.dto.dtoss.AddUserDto;
import com.example.webwork.dto.dtoss.ShowDetailedBrandInfoDto;
import com.example.webwork.dto.dtoss.ShowInfoUsers;
import com.example.webwork.except.BrandConflictException;
import com.example.webwork.except.UsersConflictException;
import com.example.webwork.except.UsersNotFoundException;
import com.example.webwork.dto.UsersDTO;
import com.example.webwork.models.Brand;
import com.example.webwork.models.Users;
import com.example.webwork.repo.RoleRepository;
import com.example.webwork.repo.UsersRepository;
import com.example.webwork.services.UsersService;
import com.example.webwork.util.ValidationUtil;
import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsersServiceImpl implements UsersService {

    private final ModelMapper modelMapper;
    private UsersRepository userRepository;
    private final ValidationUtil validationUtil;
    @Autowired
    public UsersServiceImpl(ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public UsersDTO registerUser(UsersDTO users) {

        if(!this.validationUtil.isValid(users)) {
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

    @Override
    public UsersDTO update(UsersDTO users) {

        if(!this.validationUtil.isValid(users)) {
            this.validationUtil
                    .violations(users)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
            throw new IllegalArgumentException("не подходит");
        }

        if (userRepository.findById(users.getId()).isPresent()) {
            return modelMapper.map(userRepository.save(modelMapper.map(users, Users.class)), UsersDTO.class);
        } else {
            throw new UsersNotFoundException(users.getId());
        }
    }

    @Autowired
    public void setUsersRepository(UsersRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UsersDTO registerUser_1(AddUserDto user) {
        if (!this.validationUtil.isValid(user)) {
            this.validationUtil
                    .violations(user)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
            throw new IllegalArgumentException("не подходит");
        }
        Users b = modelMapper.map(user, Users.class);
        String userId = b.getId();
        if (userId == null || userRepository.findById(userId).isEmpty()) {
            return modelMapper.map(userRepository.save(b), UsersDTO.class);
        } else {
            throw new UsersConflictException("уже существует с таким id");
        }
    }

    public List<ShowInfoUsers> allUsers() {
        return userRepository.findAll().stream().map(users -> modelMapper.map(users, ShowInfoUsers.class))
                .collect(Collectors.toList());
    }
}
