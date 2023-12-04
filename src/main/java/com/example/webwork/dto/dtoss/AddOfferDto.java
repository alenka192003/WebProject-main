package com.example.webwork.dto.dtoss;

import com.example.webwork.dto.ModelDTO;
import com.example.webwork.dto.UsersDTO;
import com.example.webwork.enums.EngineEnum;
import com.example.webwork.enums.TransmissionEnum;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AddOfferDto {
    @NotEmpty(message = "Model name must not be null or empty!")
    private String modelName;
    @NotEmpty(message = "User name must not be null or empty!")
    private String un;
    @NotEmpty(message = "Description name must not be null or empty!")
    private String description;
    @NotNull(message = "Engine must not be null or empty!")
    private EngineEnum engineEnum;
    @NotEmpty(message = "ImageURL must not be null or empty!")
    private String imageURL;
    @NotNull(message = "Mileage must not be null or empty!")
    private int mileage;
    @NotNull(message = "Price must not be null or empty!")
    private BigDecimal price;
    @NotNull(message = "Year must not be null or empty!")
    private int year;
    @NotNull(message = "Transmission must not be null or empty!")
    private TransmissionEnum transmissionEnum;

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getUn() {
        return un;
    }

    public void setUn(String un) {
        this.un = un;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EngineEnum getEngineEnum() {
        return engineEnum;
    }

    public void setEngineEnum(EngineEnum engineEnum) {
        this.engineEnum = engineEnum;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public TransmissionEnum getTransmissionEnum() {
        return transmissionEnum;
    }

    public void setTransmissionEnum(TransmissionEnum transmissionEnum) {
        this.transmissionEnum = transmissionEnum;
    }
}
