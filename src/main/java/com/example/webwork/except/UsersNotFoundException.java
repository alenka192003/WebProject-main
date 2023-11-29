package com.example.webwork.except;

public class UsersNotFoundException extends RuntimeException {
    public UsersNotFoundException(String id) {
        super("Could not find user " + id);
    }
}
