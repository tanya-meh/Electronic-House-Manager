package org.example.dto;

import org.example.entity.Building;

import java.math.BigDecimal;

public class CreateApartmentDto {
    private int number;
    private int floor;
    private BigDecimal area;

    public CreateApartmentDto(int number, int floor, BigDecimal area) {
        this.number = number;
        this.floor = floor;
        this.area = area;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }
}
