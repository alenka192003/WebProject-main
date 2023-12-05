package com.example.webwork.except;

public class UsersNotFoundException2 extends RuntimeException {
    public UsersNotFoundException2(String userName) {
        super("Could not find user " + userName);
    }
}
