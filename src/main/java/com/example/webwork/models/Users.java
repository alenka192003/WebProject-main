package com.example.webwork.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Users extends Base {

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<Offer> offers;
    @Column(name="userName", length = 255, nullable = false)
    private String userName;
    @Column(name="password", length = 255, nullable = false)
    private String password;
    @Column(name="firstName", length = 255)
    private String firstName;
    @Column(name="lastName", length = 255)
    private String lastName;

    private String email;
    private boolean isActive;
    @Column(name="imageURL", length = 255)
    private String imageURL;

    public Users() {};


    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
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

    public String getEmail() {
        return email;
    }

    public Users(String userName, String password, String email) {
        this();
        this.firstName=firstName;
        this.userName = userName;
        this.password = password;
        this.email = email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    @Override
    public String toString() {
        return "Users{" +
                "role=" + role +
                ", offers=" + offers +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", isActive=" + isActive +
                ", imageURL='" + imageURL + '\'' +
                '}';
    }
}
