package org.example.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PayedTaxFullInformationDto {
    private long companyId;
    private String companyName;
    private long employeeId;
    private String employeeName;
    private long buildingId;
    private long apartmentId;
    private long taxId;
    private BigDecimal amount;
    private LocalDate dateOfPayment;

    public PayedTaxFullInformationDto(long companyId, String companyName, long employeeId, String employeeName, long buildingId, long apartmentId, long taxId, BigDecimal amount, LocalDate dateOfPayment) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.buildingId = buildingId;
        this.apartmentId = apartmentId;
        this.taxId = taxId;
        this.amount = amount;
        this.dateOfPayment = dateOfPayment;
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(long buildingId) {
        this.buildingId = buildingId;
    }

    public long getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(long apartmentId) {
        this.apartmentId = apartmentId;
    }

    public long getTaxId() {
        return taxId;
    }

    public void setTaxId(long taxId) {
        this.taxId = taxId;
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

    @Override
    public String toString() {
        return "PayedTaxFullInformationDto{" +
                "companyId=" + companyId +
                ", companyName='" + companyName + '\'' +
                ", employeeId=" + employeeId +
                ", employeeName='" + employeeName + '\'' +
                ", buildingId=" + buildingId +
                ", apartmentId=" + apartmentId +
                ", taxId=" + taxId +
                ", amount=" + amount +
                ", dateOfPayment=" + dateOfPayment +
                '}';
    }

    public String getLog() {
        return "PAYED TAX " +
                "Company ID: " + companyId +
                ", Company name: " + companyName +
                ", Employee ID: " + employeeId +
                ", Employee name: " + employeeName +
                ", Building ID: " + buildingId +
                ", Apartment ID: " + apartmentId +
                ", Tax ID: " + taxId +
                ", Amount: " + amount +
                ", Date of payment: " + dateOfPayment;
    }
}
