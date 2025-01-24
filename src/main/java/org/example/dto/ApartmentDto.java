package org.example.dto;

import org.example.entity.Building;
import org.example.entity.Owner;
import org.example.entity.Resident;
import org.example.entity.Tax;

import java.math.BigDecimal;
import java.util.Set;

public class ApartmentDto {
    private long id;
    private int number;
    private int floor;
    private BigDecimal area;
    private Building building;
    private Set<Owner> owners;
    private Set<Resident> residents;
    private int numberOfPets;
    private Set<Tax> taxes;

    public ApartmentDto(long id, int number, int floor, BigDecimal area) {
        this.id = id;
        this.number = number;
        this.floor = floor;
        this.area = area;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public Set<Owner> getOwners() {
        return owners;
    }

    public void setOwners(Set<Owner> owners) {
        this.owners = owners;
    }

    public Set<Resident> getResidents() {
        return residents;
    }

    public void setResidents(Set<Resident> residents) {
        this.residents = residents;
    }

    public int getNumberOfPets() {
        return numberOfPets;
    }

    public void setNumberOfPets(int numberOfPets) {
        this.numberOfPets = numberOfPets;
    }

    public Set<Tax> getTaxes() {
        return taxes;
    }

    public void setTaxes(Set<Tax> taxes) {
        this.taxes = taxes;
    }
}
