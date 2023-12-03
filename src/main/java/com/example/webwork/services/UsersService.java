package com.example.webwork.services;


import com.example.webwork.dto.UsersDTO;
import com.example.webwork.dto.dtoss.AddUserDto;
import com.example.webwork.dto.dtoss.ShowDetailedBrandInfoDto;
import com.example.webwork.dto.dtoss.ShowInfoUsers;
import com.example.webwork.dto.dtoss.ShowModelInfoDto;

import java.util.List;
import java.util.Optional;

public interface UsersService {
    UsersDTO registerUser(UsersDTO users);
    void addUser(AddUserDto userModel);

    List<ShowInfoUsers> allUsers();

    List<UsersDTO> getAll();

    Optional<UsersDTO> findById(String id);

    void expel(String id);
    ShowInfoUsers userDetails(String userName);

    UsersDTO update(UsersDTO users);
    void removeUser(String userName);
}
