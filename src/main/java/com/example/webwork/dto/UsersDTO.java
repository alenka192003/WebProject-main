package com.example.webwork.dto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

public class UsersDTO {
    private String id;
    private RoleDTO role;
    @NotNull
    @NotEmpty
    @Length(min = 2, message = "Имя пользователя должно быть больше 2 символов!")
    private String userName;
    @Length(min = 10, message = "Пароль должен быть больше 10 символов!")
    private String password;
    @NotNull
    @NotEmpty
    @Length(min = 2, message = "Имя должно быть больше 2 символов!")
    private String firstName;
    @NotNull
    @NotEmpty
    @Length(min = 2, message = "Фамилия должна быть больше 2 символов!")
    private String lastName;
    private boolean isActive;
    private String imageURL;
    private LocalDateTime created;
    private LocalDateTime modified;

    protected UsersDTO() {};

    public UsersDTO(String id, RoleDTO role, String userName, String password, String firstName, String lastName,
                    boolean isActive, String imageURL, LocalDateTime created, LocalDateTime modified) {
        this.id = id;
        this.role = role;
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isActive = isActive;
        this.imageURL = imageURL;
        this.created = created;
        this.modified = modified;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RoleDTO getRole() {
        return role;
    }

    public void setRole(RoleDTO role) {
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }

    @Override
    public String toString() {
        return "UsersDto{" +
                "id=" + id +
                ", role=" + role +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", isActive=" + isActive +
                ", imageURL='" + imageURL + '\'' +
                ", created=" + created +
                ", modified=" + modified +
                '}';
    }
}
