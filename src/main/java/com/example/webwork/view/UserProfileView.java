package com.example.webwork.view;

public class UserProfileView {
    private String userName;
    private String email;

    public UserProfileView() {
    }

    public UserProfileView(String userName, String email) {
        this.userName = userName;
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUsername(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
