package org.example.dto;

import org.example.entity.Apartment;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TaxDto {
    private long id;
    private LocalDate dateOfIssue;
    private BigDecimal amount;
    private LocalDate dateOfPayment;
    private Apartment apartment;

    public TaxDto(long id, LocalDate dateOfIssue) {
        this.id = id;
        this.dateOfIssue = dateOfIssue;
    }

    public TaxDto(long id, LocalDate dateOfIssue, BigDecimal amount, LocalDate dateOfPayment, Apartment apartment) {
        this.id = id;
        this.dateOfIssue = dateOfIssue;
        this.amount = amount;
        this.dateOfPayment = dateOfPayment;
        this.apartment = apartment;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(LocalDate dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getDateOfPayment() {
        return dateOfPayment;
    }

    public void setDateOfPayment(LocalDate dateOfPayment) {
        this.dateOfPayment = dateOfPayment;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    @Override
    public String toString() {
        return "TaxDto{" +
                "id=" + id +
                ", dateOfIssue=" + dateOfIssue +
                ", amount=" + amount +
                ", dateOfPayment=" + dateOfPayment +
                ", apartmentId=" + apartment.getId() +
                '}';
    }
}
