package org.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;


import java.util.Set;

@Entity
public class Resident extends Person {
    @PositiveOrZero
    private int age;
    @Column(name = "uses_lift", nullable = false)
    private boolean usesLift;
    @ManyToMany
    private Set<Apartment> apartments;

    public Resident() {
        super();
    }

    public Resident(String name, int age, boolean usesLift){
        super(name);
        this.age = age;
        this.usesLift = usesLift;
    }

    public int getAge() {
        return age;
    }

    public boolean usesLift() {
        return usesLift;
    }

    public Set<Apartment> getApartments() {
        return apartments;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setUsesLift(boolean usesLift) {
        this.usesLift = usesLift;
    }

    public void setApartments(Set<Apartment> apartments) {
        this.apartments = apartments;
    }

    @Override
    public String toString() {
        return "Resident{" +
                "birthDate=" + age +
                ", usesLift=" + usesLift +
                ", apartments=" + apartments +
                "} " + super.toString();
    }
}

