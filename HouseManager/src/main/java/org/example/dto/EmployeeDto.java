package org.example.dto;

public class EmployeeDto {
    private long id;
    private String name;

    public EmployeeDto(String name) {
        this.name = name;
    }

    public EmployeeDto(long id, String name) {
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
