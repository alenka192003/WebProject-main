package com.example.webwork.dto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

public class BrandDTO {
    private String id;
    @NotNull
    @NotEmpty
    @Length(min = 2, message = "Имя должно быть больше 2 символов!")
    private String name;
    private LocalDateTime created;
    private LocalDateTime modified;

    public BrandDTO(String id, String name, LocalDateTime created, LocalDateTime modified) {
        this.id = id;
        this.name = name;
        this.created = created;
        this.modified = modified;
    }

    protected BrandDTO() {};

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    @Override
    public String toString() {
        return "BrandDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", created=" + created +
                ", modified=" + modified +
                '}';
    }
}
