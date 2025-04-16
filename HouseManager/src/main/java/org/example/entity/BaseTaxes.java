package org.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

@Entity
public class BaseTaxes extends BaseEntity {
    @PositiveOrZero
    @Digits(integer = 3, fraction = 2, message = "Initial capital has to be with max 3 digits before and 2 digits after the decimal separator!")
    @Column(name = "tax_per_square_meter")
    private BigDecimal taxPerSquareMeter;
    @PositiveOrZero
    @Digits(integer = 3, fraction = 2, message = "Initial capital has to be with max 3 digits before and 2 digits after the decimal separator!")
    @Column(name = "tax_per_resident")
    private BigDecimal taxPerResident;
    @PositiveOrZero
    @Digits(integer = 3, fraction = 2, message = "Initial capital has to be with max 3 digits before and 2 digits after the decimal separator!")
    @Column(name = "tax_per_pet")
    private BigDecimal taxPerPet;
    @OneToOne
    private Building building;

    public BaseTaxes() {
    }

    public BaseTaxes(BigDecimal taxPerSquareMeter, BigDecimal taxPerResident, BigDecimal taxPerPet) {
        this.taxPerSquareMeter = taxPerSquareMeter;
        this.taxPerResident = taxPerResident;
        this.taxPerPet = taxPerPet;
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