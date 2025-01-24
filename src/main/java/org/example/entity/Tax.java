package org.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Tax extends BaseEntity{
    private LocalDate dateOfIssue;
    private BigDecimal amount;
    @ManyToOne
    private Apartment apartment;
    @OneToOne(mappedBy = "tax")
    private PaidTax paidTax;

    public Tax(){
    }

    public Tax(LocalDate dateOfIssue, BigDecimal amount, Apartment apartment){
        this.dateOfIssue = dateOfIssue;
        this.amount = amount;
        this.apartment = apartment;
    }

    public LocalDate getDateOfIssue() {
        return dateOfIssue;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public PaidTax getPaidTax() {
        return paidTax;
    }

    public void setDateOfIssue(LocalDate dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    public void setPaidTax(PaidTax paidTax) {
        this.paidTax = paidTax;
    }

    @Override
    public String
    toString() {
        return "Tax{" +
                "dateOfIssue=" + dateOfIssue +
                ", amount=" + amount +
                ", apartment=" + apartment +
                ", paidTax=" + paidTax +
                "} " + super.toString();
    }
}
