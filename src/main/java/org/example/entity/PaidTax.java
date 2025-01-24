package org.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

import java.time.LocalDate;

@Entity
public class PaidTax  extends BaseEntity{
    private LocalDate dateOfPayment;
    @OneToOne
    private Tax tax;

    public PaidTax(){
    }

    public PaidTax(LocalDate dateOfPayment, Tax tax){
        this.dateOfPayment = dateOfPayment;
        this.tax = tax;
    }

    public LocalDate getDateOfPayment() {
        return dateOfPayment;
    }

    public Tax getTax() {
        return tax;
    }

    public void setDateOfPayment(LocalDate dateOfPayment) {
        this.dateOfPayment = dateOfPayment;
    }

    public void setTax(Tax tax) {
        this.tax = tax;
    }

    @Override
    public String toString() {
        return "PaidTax{" +
                "dateOfPayment=" + dateOfPayment +
                ", tax=" + tax +
                "} " + super.toString();
    }
}
