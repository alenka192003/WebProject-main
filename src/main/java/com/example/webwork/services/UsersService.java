package com.example.webwork.services;


import com.example.webwork.dto.*;
import com.example.webwork.models.Users;

import java.util.List;
import java.util.Optional;

public interface UsersService {
    void addUser(AddUserDto userModel);

    List<ShowInfoUsers> allUsers();

    List<UsersDTO> getAll();

    Optional<UsersDTO> findById(String id);
    List<ShowInfoOffer> getOffersByUserName(String userName);
    void expel(String id);
    ShowInfoUsers userDetails(String userName);

    void removeUser(String userName);
    Users getUserDetails(String userName);
    Users getUser(String userName);
    void updateUser(String userName, UpdateUserDto updateUserDto);
}
