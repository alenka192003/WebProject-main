package com.example.webwork.enums;

public enum EngineEnum {
    GASOLINE ("GASOLINE", 0),
    DIESEL ("DIESEL", 1),
    ELECTRIC ("ELECTRIC", 2),
    HYBRID ("HYBRID", 3);
    private String type;
    private int number;

    EngineEnum(String type, int number) {
        this.type = type;
        this.number = number;
    }
}
