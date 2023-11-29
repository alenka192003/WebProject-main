package com.example.webwork.except;

public class RoleNotFoundException extends RuntimeException {
    public RoleNotFoundException(String id) {
        super("Could not find role " + id);
    }
}
