package com.example.webwork.dto;

import com.example.webwork.enums.CategoryEnum;
import com.example.webwork.models.Brand;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

public class ModelDTO {
    private String id;
    private BrandDTO brand;
    @NotNull
    @NotEmpty
    @Length(min = 2, message = "Имя должно быть больше 2 символов!")

    private String name;
    private CategoryEnum categoryEnum;
    private String imageURL;
    private int startYear;
    private int endYear;
    private LocalDateTime created;
    private LocalDateTime modified;

    protected ModelDTO() {};

    public ModelDTO(String id, BrandDTO brand, String name, CategoryEnum categoryEnum, String imageURL, int startYear,
                    int endYear, LocalDateTime created, LocalDateTime modified) {
        this.id = id;
        this.brand = brand;
        this.name = name;
        this.categoryEnum = categoryEnum;
        this.imageURL = imageURL;
        this.startYear = startYear;
        this.endYear = endYear;
        this.created = created;
        this.modified = modified;
    }

    public ModelDTO(String id, Brand brand, String imageUrl, CategoryEnum categoryEnum, String name, int startYear, int endYear, LocalDateTime created, LocalDateTime modified) {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BrandDTO getBrandDto() {
        return brand;
    }

    public void setBrandDto(BrandDTO brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryEnum getCategoryEnum() {
        return categoryEnum;
    }

    public void setCategoryEnum(CategoryEnum categoryEnum) {
        this.categoryEnum = categoryEnum;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
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

    @Override
    public String toString() {
        return "ModelDto{" +
                "id=" + id +
                ", brand=" + brand +
                ", name='" + name + '\'' +
                ", categoryEnum=" + categoryEnum +
                ", imageURL='" + imageURL + '\'' +
                ", startYear=" + startYear +
                ", endYear=" + endYear +
                ", created=" + created +
                ", modified=" + modified +
                '}';
    }
}

// Инициализировать Enum's в скобочках
