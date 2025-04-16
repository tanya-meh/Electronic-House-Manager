package org.example.dto;

public class CreateOwnerDto {
    private String name;

    public CreateOwnerDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
