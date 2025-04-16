package org.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;

import java.util.Set;

@Entity
public class Owner extends Person {
    @ManyToMany
    private Set<Apartment> apartments;

    public Owner() {
        super();
    }

    public Owner(String name){
        super(name);
    }

    public Set<Apartment> getApartments() {
        return apartments;
    }

    public void setApartments(Set<Apartment> apartments) {
        this.apartments = apartments;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "apartments=" + apartments +
                "} " + super.toString();
    }
}

