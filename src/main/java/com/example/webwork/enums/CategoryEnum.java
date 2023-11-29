package com.example.webwork.enums;

public enum CategoryEnum {
    Car ("Car", 0),
    Bus ("Bus", 1),
    Truck ("Truck", 2),
    Motorcycle ("Motorcycle", 3);
    private String type;
    private int number;

    CategoryEnum(String type, int number) {
        this.type = type;
        this.number = number;
    }
}
