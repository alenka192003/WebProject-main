package com.example.webwork.except;

public class OfferNotFoundException extends RuntimeException {
    public OfferNotFoundException(String id) {
        super("Could not find offer " + id);
    }
}
