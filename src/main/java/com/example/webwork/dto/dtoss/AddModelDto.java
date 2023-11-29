package com.example.webwork.dto.dtoss;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;


import java.time.LocalDateTime;

public class AddModelDto {
    private String name;

    private String category;
    private int startYear;
    private int endYear;
    private LocalDateTime created;
    private LocalDateTime modified;

    @NotEmpty(message = "Model name must not be null or empty!")
    @Size(min = 2, max = 10, message = "Model name must be between 2 and 10 characters!")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
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