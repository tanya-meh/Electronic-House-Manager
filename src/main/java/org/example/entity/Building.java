package org.example.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Set;

@Entity
public class Building extends BaseEntity{
    private String address;
    private int numberOfFloors;
    private int numberOfApartments;
    private BigDecimal builtUpArea;
    private BigDecimal commonArea;
    @OneToMany(mappedBy = "building")
    private Set<Apartment> apartments;
    @ManyToOne
    private EmployeeInCompany employeeInCompany;
    @OneToOne(mappedBy = "building")
    private BaseTaxes baseTaxes;

    public Building (){
    }

    public Building(String address, int numberOfFloors, int numberOfApartments, BigDecimal builtUpArea, BigDecimal commonArea) {
        this.address = address;
        this.numberOfFloors = numberOfFloors;
        this.numberOfApartments = numberOfApartments;
        this.builtUpArea = builtUpArea;
        this.commonArea = commonArea;
    }

    public String getAddress() {
        return address;
    }

    public int getNumberOfFloors() {
        return numberOfFloors;
    }

    public int getNumberOfApartments() {
        return numberOfApartments;
    }

    public BigDecimal getBuiltUpArea() {
        return builtUpArea;
    }

    public BigDecimal getCommonArea() {
        return commonArea;
    }

    public Set<Apartment> getApartments() {
        return apartments;
    }

    public EmployeeInCompany getEmployeeInCompany() {
        return employeeInCompany;
    }

    public BaseTaxes getBaseTaxes() {
        return baseTaxes;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setNumberOfFloors(int numberOfFloors) {
        this.numberOfFloors = numberOfFloors;
    }

    public void setNumberOfApartments(int numberOfApartments) {
        this.numberOfApartments = numberOfApartments;
    }

    public void setBuiltUpArea(BigDecimal builtUpArea) {
        this.builtUpArea = builtUpArea;
    }

    public void setCommonArea(BigDecimal commonArea) {
        this.commonArea = commonArea;
    }

    public void setApartments(Set<Apartment> apartments) {
        this.apartments = apartments;
    }

    public void setEmployeeInCompany(EmployeeInCompany employeeInCompany) {
        this.employeeInCompany = employeeInCompany;
    }

    public void setBaseTaxes(BaseTaxes baseTaxes) {
        this.baseTaxes = baseTaxes;
    }

    @Override
    public String toString() {
        return "Building{" +
                "id=" + super.getId() +
                ", address='" + address + '\'' +
                ", numberOfFloors=" + numberOfFloors +
                ", numberOfApartments=" + numberOfApartments +
                ", builtUpArea=" + builtUpArea +
                ", commonArea=" + commonArea +
                "} ";
    }
}
