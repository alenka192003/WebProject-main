package com.example.webwork.except;

public class RoleConflictException extends RuntimeException {
    public RoleConflictException(String message) {
        super(message);
    }
}
