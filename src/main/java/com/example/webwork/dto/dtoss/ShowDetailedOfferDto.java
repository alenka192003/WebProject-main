package com.example.webwork.dto.dtoss;

import com.example.webwork.dto.ModelDTO;
import com.example.webwork.dto.UsersDTO;
import com.example.webwork.enums.EngineEnum;
import com.example.webwork.enums.TransmissionEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ShowDetailedOfferDto {
    private ModelDTO model;
    private UsersDTO users;
    private String description;
    private EngineEnum engineEnum;
    private String imageURL;
    private int mileage;
    private BigDecimal price;
    private TransmissionEnum transmissionEnum;
    private int year;
    private LocalDateTime created;
    private LocalDateTime modified;


    public ModelDTO getModel() {
        return model;
    }

    public void setModel(ModelDTO model) {
        this.model = model;
    }

    public UsersDTO getUsers() {
        return users;
    }

    public void setUsers(UsersDTO users) {
        this.users = users;
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

    public TransmissionEnum getTransmissionEnum() {
        return transmissionEnum;
    }

    public void setTransmissionEnum(TransmissionEnum transmissionEnum) {
        this.transmissionEnum = transmissionEnum;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }
}
