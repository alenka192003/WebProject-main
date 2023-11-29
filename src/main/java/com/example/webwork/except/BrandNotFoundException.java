package com.example.webwork.except;

public class BrandNotFoundException extends RuntimeException{
    public BrandNotFoundException(String id) {
        super("Could not find brand " + id);
    }
}
