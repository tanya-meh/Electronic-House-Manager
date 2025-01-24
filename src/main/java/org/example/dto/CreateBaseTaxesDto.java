package org.example.dto;

import java.math.BigDecimal;

public class CreateBaseTaxesDto {
    private BigDecimal taxPerSquareMeter;
    private BigDecimal taxPerResident;
    private BigDecimal taxPerPet;

    public CreateBaseTaxesDto() {
    }

    public CreateBaseTaxesDto(BigDecimal taxPerSquareMeter, BigDecimal taxPerResident, BigDecimal taxPerPet) {
        this.taxPerSquareMeter = taxPerSquareMeter;
        this.taxPerResident = taxPerResident;
        this.taxPerPet = taxPerPet;
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
}
