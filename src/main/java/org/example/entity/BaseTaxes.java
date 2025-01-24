package org.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

import java.math.BigDecimal;

@Entity
public class BaseTaxes extends BaseEntity {
    private BigDecimal taxPerSquareMeter;
    private BigDecimal taxPerResident;
    private BigDecimal taxPerPet;
    @OneToOne
    private Building building;

    public BaseTaxes() {
    }

    public BaseTaxes(BigDecimal taxPerSquareMeter, BigDecimal taxPerResident, BigDecimal taxPerPet, Building building) {
        this.taxPerSquareMeter = taxPerSquareMeter;
        this.taxPerResident = taxPerResident;
        this.taxPerPet = taxPerPet;
        this.building = building;
    }

    public BigDecimal getTaxPerSquareMeter() {
        return taxPerSquareMeter;
    }

    public BigDecimal getTaxPerResident() {
        return taxPerResident;
    }

    public BigDecimal getTaxPerPet() {
        return taxPerPet;
    }

    public Building getBuilding() {
        return building;
    }

    public void setTaxPerSquareMeter(BigDecimal taxPerSquareMeter) {
        this.taxPerSquareMeter = taxPerSquareMeter;
    }

    public void setTaxPerResident(BigDecimal taxPerResident) {
        this.taxPerResident = taxPerResident;
    }

    public void setTaxPerPet(BigDecimal taxPerPet) {
        this.taxPerPet = taxPerPet;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    @Override
    public String toString() {
        return "BaseTaxes{" +
                "taxPerSquareMeter=" + taxPerSquareMeter +
                ", taxPerResident=" + taxPerResident +
                ", taxPerPet=" + taxPerPet +
                ", building=" + building +
                "} " + super.toString();
    }
}