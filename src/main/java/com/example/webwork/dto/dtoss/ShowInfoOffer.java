package com.example.webwork.dto.dtoss;

import com.example.webwork.dto.ModelDTO;
import com.example.webwork.enums.EngineEnum;
import com.example.webwork.enums.TransmissionEnum;

import java.math.BigDecimal;

public class ShowInfoOffer {
    private ModelDTO model;
    private String description;
    private EngineEnum engineEnum;
    private String imageURL;
    private int mileage;
    private BigDecimal price;
    private TransmissionEnum transmissionEnum;

    public ModelDTO getModel() {
        return model;
    }

    public void setModel(ModelDTO model) {
        this.model = model;
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
}
