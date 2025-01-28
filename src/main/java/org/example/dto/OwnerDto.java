package org.example.dto;

import org.example.entity.Apartment;

import java.util.Set;

public class OwnerDto {
    private long id;
    private String name;
    public OwnerDto(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
