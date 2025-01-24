package org.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;

import java.time.LocalDate;
import java.util.Set;

@Entity
public class Resident extends Person {
    private LocalDate birthDate;
    private boolean usesLift;
    @ManyToMany
    private Set<Apartment> apartments;

    public Resident() {
        super();
    }

    public Resident(String name, LocalDate birthdate, boolean usesLift){
        super(name);
        this.birthDate = birthdate;
        this.usesLift = usesLift;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public boolean isUsesLift() {
        return usesLift;
    }

    public Set<Apartment> getApartments() {
        return apartments;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
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
                "birthDate=" + birthDate +
                ", usesLift=" + usesLift +
                ", apartments=" + apartments +
                "} " + super.toString();
    }
}

