package org.example.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Tax extends BaseEntity{
    @PastOrPresent(message = "Date of issue cannot be in the future!")
    @Column(name = "date_of_issue", nullable = false)
    private LocalDate dateOfIssue;
    @PositiveOrZero
    private BigDecimal amount;
    @PastOrPresent(message = "Date of payment cannot be in the future!")
    @Column(name = "date_of_payment")
    private LocalDate dateOfPayment;
    @ManyToOne(fetch = FetchType.LAZY)
    private Apartment apartment;

    public Tax(){
    }

    public Tax(LocalDate dateOfIssue){
        this.dateOfIssue = dateOfIssue;
    }

    public LocalDate getDateOfIssue() {
        return dateOfIssue;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDate getDateOfPayment() {
        return dateOfPayment;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setDateOfIssue(LocalDate dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setDateOfPayment(LocalDate dateOfPayment) {
        this.dateOfPayment = dateOfPayment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }


    @Override
    public String
    toString() {
        return "Tax{" +
                "dateOfIssue=" + dateOfIssue +
                ", amount=" + amount +
                ", apartment=" + apartment +
                "} " + super.toString();
    }
}
