package org.example.dto;

import org.example.entity.Building;

import java.math.BigDecimal;

public class BaseTaxesDto {
    private long id;
    private BigDecimal taxPerSquareMeter;
    private BigDecimal taxPerResident;
    private BigDecimal taxPerPet;
    private Building building;

    public BaseTaxesDto(long id, BigDecimal taxPerSquareMeter, BigDecimal taxPerResident, BigDecimal taxPerPet) {
        this.id = id;
        this.taxPerSquareMeter = taxPerSquareMeter;
        this.taxPerResident = taxPerResident;
        this.taxPerPet = taxPerPet;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getTaxPerSquareMeter() {
        return taxPerSquareMeter;
    }

    public void setTaxPerSquareMeter(BigDecimal taxPerSquareMeter) {
        this.taxPerSquareMeter = taxPerSquareMeter;
    }

    public BigDecimal getTaxPerResident() {
        return taxPerResident;
    }

    public void setTaxPerResident(BigDecimal taxPerResident) {
        this.taxPerResident = taxPerResident;
    }

    public BigDecimal getTaxPerPet() {
        return taxPerPet;
    }

    public void setTaxPerPet(BigDecimal taxPerPet) {
        this.taxPerPet = taxPerPet;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }
}
