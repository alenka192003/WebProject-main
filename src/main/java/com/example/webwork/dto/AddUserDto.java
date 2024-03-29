package com.example.webwork.dto;

import com.example.webwork.enums.RoleEnum;
import com.example.webwork.except.UniqueUserName;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class AddUserDto {
    @UniqueUserName
    private String userName;
    @NotEmpty(message = "Name must not be null or empty!")
    @Size(min = 2, max = 10, message = "Name must be more 2 !")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    private String password;
    private String email;
    @NotEmpty(message = "password must not be null or empty!")
    @Size(min = 10, message = "password must be at least 1 characters!")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String firstName;
    @Size(min = 2, message = "firstName must be more than 2!")
    @NotNull(message = "firstName must not be null or empty!")
    public String getFirstName() {
        return firstName;
    }

    private String lastName;
    @Size(min = 2,message = "lastName must be more than 2!")
    @NotNull(message = "lastName must not be null or empty!")
    public String getLastName() {
        return lastName;
    }

    private RoleEnum role;
    public RoleEnum getRole(){
        return role;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "AddUserDto{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role=" + role +
                '}';
    }
}
