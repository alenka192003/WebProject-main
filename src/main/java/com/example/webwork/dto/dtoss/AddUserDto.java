package com.example.webwork.dto.dtoss;

import com.example.webwork.dto.RoleDTO;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;


public class AddUserDto {
    private String userName;

    @NotEmpty(message = "Name must not be null or empty!")
    @Size(min = 2, max = 10, message = "Name must be more 2 !")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    private int password;
    
    @Size(min = 10, message = "password must be at least 1 characters!")
    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    private String firstName;
    @Min(value = 2, message = "firstName must be more than 2!")
    @NotNull(message = "firstName must not be null or empty!")
    public String getFirstName() {
        return firstName;
    }

    private String lastName;
    @Min(value = 2,message = "lastName must be more than 2!")
    @NotNull(message = "lastName must not be null or empty!")
    public String getLastName() {
        return lastName;
    }

    private RoleDTO role;
    public RoleDTO getRole(){
        return role;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setRole(RoleDTO role) {
        this.role = role;
    }
}
