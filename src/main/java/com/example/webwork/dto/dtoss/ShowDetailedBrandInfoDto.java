package com.example.webwork.dto.dtoss;

import java.time.LocalDateTime;

public class ShowDetailedBrandInfoDto {
    private String name;
    private LocalDateTime created;
    private LocalDateTime modified;

    public String getName() {
        return name;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }
}
