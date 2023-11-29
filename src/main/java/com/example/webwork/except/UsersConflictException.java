package com.example.webwork.except;

public class UsersConflictException extends RuntimeException {
    public UsersConflictException(String message) {
        super(message);
    }
}
