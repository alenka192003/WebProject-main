package com.example.webwork.dto.dtoss;

import com.example.webwork.dto.RoleDTO;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UpdateUserDto {
    private String userName;
    private String firstName;
    private String lastName;
    private String password;
    @NotEmpty(message = "Name must not be null or empty!")
    @Size(min = 2, max = 10, message = "Name must be more 2 !")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    @Size(min = 2, message = "firstName must be more than 2!")
    @NotNull(message = "firstName must not be null or empty!")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    @Size(min = 2,message = "lastName must be more than 2!")
    @NotNull(message = "lastName must not be null or empty!")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    @NotEmpty(message = "password must not be null or empty!")
    @Size(min = 10, message = "password must be at least 1 characters!")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
