package org.example.dto;

import org.example.entity.Apartment;
import org.example.entity.Company;
import org.example.entity.Employee;
import org.example.entity.EmployeeInCompany;

import java.math.BigDecimal;
import java.util.Set;

public class CreateBuildingDto {
    private String address;
    private int numberOfFloors;
    private BigDecimal builtUpArea;
    private BigDecimal commonArea;
    public CreateBuildingDto() {
    }

    public CreateBuildingDto(String address, int numberOfFloors, BigDecimal builtUpArea, BigDecimal commonArea) {
        this.address = address;
        this.numberOfFloors = numberOfFloors;
        this.builtUpArea = builtUpArea;
        this.commonArea = commonArea;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getNumberOfFloors() {
        return numberOfFloors;
    }

    public void setNumberOfFloors(int numberOfFloors) {
        this.numberOfFloors = numberOfFloors;
    }

    public BigDecimal getBuiltUpArea() {
        return builtUpArea;
    }

    public void setBuiltUpArea(BigDecimal builtUpArea) {
        this.builtUpArea = builtUpArea;
    }

    public BigDecimal getCommonArea() {
        return commonArea;
    }

    public void setCommonArea(BigDecimal commonArea) {
        this.commonArea = commonArea;
    }


}
