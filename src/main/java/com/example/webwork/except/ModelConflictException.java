package com.example.webwork.except;

public class ModelConflictException extends RuntimeException {
    public ModelConflictException(String message) {
        super(message);
    }
}
