package com.example.webwork.dto.dtoss;

import com.example.webwork.dto.BrandDTO;
import com.example.webwork.enums.CategoryEnum;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;


import java.time.LocalDateTime;

public class AddModelDto {
    private String name;
    private String brandName;
    private CategoryEnum category;
    private int startYear;
    private LocalDateTime created;
    private LocalDateTime modified;
    private int endYear;


    @NotEmpty(message = "Model name must not be null or empty!")
    @Size(min = 2, message = "Model name must be between 2 and 10 characters!")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryEnum category) {
        this.category = category;
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

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }


    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public LocalDateTime getModified() {
        return modified;
    }
}
