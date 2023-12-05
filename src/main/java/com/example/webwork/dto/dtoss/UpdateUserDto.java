package com.example.webwork.dto.dtoss;

import com.example.webwork.dto.RoleDTO;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UpdateUserDto {
    private String password;
    @NotEmpty(message = "password must not be null or empty!")
    @Size(min = 10, message = "password must be at least 1 characters!")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String newUserName;
    @Size(min = 2, message = "firstName must be more than 2!")
    @NotNull(message = "firstName must not be null or empty!")
    public String getnewUserName() {
        return newUserName;
    }

    private String newFirstName;
    @Size(min = 2, message = "firstName must be more than 2!")
    @NotNull(message = "firstName must not be null or empty!")
    public String getnewFirstName() {
        return newFirstName;
    }

    private String newLastName;
    @Size(min = 2,message = "lastName must be more than 2!")
    @NotNull(message = "lastName must not be null or empty!")
    public String getnewLastName() {
        return newLastName;
    }

    private boolean newIsActive;

    public boolean getIsActive() {return newIsActive;}

    public void setActive(boolean newIsActive) {newIsActive = newIsActive;}

    public void setnewFirstName(String newFirstName) {
        this.newFirstName = newFirstName;
    }

    public void setnewLastName(String newLastName) {
        this.newLastName = newLastName;
    }

}
