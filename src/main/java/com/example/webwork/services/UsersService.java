package com.example.webwork.services;


import com.example.webwork.dto.UsersDTO;
import com.example.webwork.dto.dtoss.AddUserDto;
import com.example.webwork.dto.dtoss.ShowDetailedBrandInfoDto;
import com.example.webwork.dto.dtoss.ShowInfoUsers;

import java.util.List;
import java.util.Optional;

public interface UsersService {
    UsersDTO registerUser(UsersDTO users);
    UsersDTO registerUser_1(AddUserDto user);

    List<ShowInfoUsers> allUsers();

    List<UsersDTO> getAll();

    Optional<UsersDTO> findById(String id);

    void expel(String id);

    UsersDTO update(UsersDTO users);
}
