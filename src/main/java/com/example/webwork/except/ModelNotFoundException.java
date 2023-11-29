package com.example.webwork.except;

public class ModelNotFoundException extends RuntimeException {
    public ModelNotFoundException(String id) {
        super("Could not find model " + id);
    }
}
