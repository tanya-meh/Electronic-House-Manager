package org.example.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.util.Set;

@Entity
public class Apartment extends BaseEntity{
    @Positive
    private int number;
    @Positive
    private int floor;
    @Positive
    private BigDecimal area;
    @ManyToOne
    private Building building;
    @ManyToMany(mappedBy = "apartments")
    private Set<Owner> owners;
    @ManyToMany(mappedBy = "apartments")
    private Set<Resident> residents;
    @PositiveOrZero
    @Column(name="number_of_pets")
    private int numberOfPets;
    @OneToMany(mappedBy = "apartment")
    private Set<Tax> taxes;

    public Apartment() {
    }

    public Apartment(int number, int floor, BigDecimal area) {
        this.number = number;
        this.floor = floor;
        this.area = area;
    }

    public int getNumber() {
        return number;
    }

    public int getFloor() {
        return floor;
    }

    public BigDecimal getArea() {
        return area;
    }

    public Building getBuilding() {
        return building;
    }

    public Set<Owner> getOwners() {
        return owners;
    }

    public Set<Resident> getResidents() {
        return residents;
    }

    public int getNumberOfPets() {
        return numberOfPets;
    }

    public Set<Tax> getTaxes() {
        return taxes;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public void setOwners(Set<Owner> owners) {
        this.owners = owners;
    }

    public void setResidents(Set<Resident> residents) {
        this.residents = residents;
    }

    public void setNumberOfPets(int numberOfPets) {
        this.numberOfPets = numberOfPets;
    }

    public void setTaxes(Set<Tax> taxes) {
        this.taxes = taxes;
    }

    @Override
    public String toString() {
        return "Apartment{" +
                "number=" + number +
                ", area=" + area +
                ", building=" + building +
                ", owners=" + owners +
                ", residents=" + residents +
                ", numberOfPets=" + numberOfPets +
                ", taxes=" + taxes +
                "} " + super.toString();
    }
}
