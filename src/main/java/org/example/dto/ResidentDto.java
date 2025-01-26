package org.example.dto;

import org.example.entity.Apartment;

import java.time.LocalDate;
import java.util.Set;

public class ResidentDto {
    private long id;
    private String name;
    private int age;
    private boolean usesLift;
    private Set<Apartment> apartments;

    public ResidentDto(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public ResidentDto(String name, int age, boolean usesLift) {
        this.name = name;
        this.age = age;
        this.usesLift = usesLift;
    }

    public ResidentDto(long id, String name, int age, boolean usesLift) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.usesLift = usesLift;
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

    public int getAge() {
        return age;
    }

    public boolean usesLift() {
        return usesLift;
    }

    public void setUsesLift(boolean usesLift) {
        this.usesLift = usesLift;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Set<Apartment> getApartments() {
        return apartments;
    }

    public void setApartments(Set<Apartment> apartments) {
        this.apartments = apartments;
    }
}
