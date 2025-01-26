package org.example.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CreateTaxDto {
    private LocalDate dateOfIssue;

    public CreateTaxDto(LocalDate dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public LocalDate getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(LocalDate dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }
}
