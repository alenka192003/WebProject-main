package com.example.webwork.enums;

public enum TransmissionEnum {
    MANUAL ("MANUAL", 0),
    AUTOMATIC ("AUTOMATIC", 1);
    private String type;
    private int number;

    TransmissionEnum(String type, int number) {
        this.type = type;
        this.number = number;
    }
}
