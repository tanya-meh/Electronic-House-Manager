package org.example.dto;

import org.example.entity.Apartment;
import org.example.entity.BaseTaxes;
import org.example.entity.EmployeeInCompany;

import java.math.BigDecimal;
import java.util.Set;

public class BuildingDto {
    private long id;
    private String address;
    private int numberOfFloors;
    private BigDecimal builtUpArea;
    private BigDecimal commonArea;
    private EmployeeInCompany employeeInCompany;
    private BaseTaxes baseTaxes;

    public BuildingDto(long id, String address, int numberOfFloors, BigDecimal builtUpArea, BigDecimal commonArea) {
        this.id = id;
        this.address = address;
        this.numberOfFloors = numberOfFloors;
        this.builtUpArea = builtUpArea;
        this.commonArea = commonArea;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public EmployeeInCompany getEmployeeInCompany() {
        return employeeInCompany;
    }

    public void setEmployeeInCompany(EmployeeInCompany employeeInCompany) {
        this.employeeInCompany = employeeInCompany;
    }

    public BaseTaxes getBaseTaxes() {
        return baseTaxes;
    }

    public void setBaseTaxes(BaseTaxes baseTaxes) {
        this.baseTaxes = baseTaxes;
    }
}
