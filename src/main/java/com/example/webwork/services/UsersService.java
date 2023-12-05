package com.example.webwork.services;


import com.example.webwork.dto.UsersDTO;
import com.example.webwork.dto.dtoss.*;
import com.example.webwork.models.Offer;
import com.example.webwork.models.Users;

import java.util.List;
import java.util.Optional;

public interface UsersService {
    UsersDTO registerUser(UsersDTO users);
    void addUser(AddUserDto userModel);

    List<ShowInfoUsers> allUsers();

    List<UsersDTO> getAll();

    Optional<UsersDTO> findById(String id);
    List<ShowInfoOffer> getOffersByUserName(String userName);
    void expel(String id);
    ShowInfoUsers userDetails(String userName);

    void removeUser(String userName);
    Users getUserDetails(String userName);
    void updateUser(String userName,String newUserName,String newFirstName, String newLastName, String newPassword, boolean newIsActive);

}
