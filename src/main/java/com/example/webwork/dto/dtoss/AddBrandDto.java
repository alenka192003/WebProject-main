package com.example.webwork.dto.dtoss;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;


public class AddBrandDto {
    private String name;
    private LocalDateTime created;
    private LocalDateTime modified;

    @NotEmpty(message = "Brand name must not be null or empty!")
    @Size(min = 2, max = 10, message = "Brand name must be more 2 !")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
