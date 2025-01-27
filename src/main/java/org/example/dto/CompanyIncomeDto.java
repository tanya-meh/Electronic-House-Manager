package org.example.dto;

import java.math.BigDecimal;

public class CompanyIncomeDto {
    private long companyId;
    private String companyName;
    private BigDecimal income;

    public CompanyIncomeDto(long companyId, String companyName, BigDecimal income) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.income = income;
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

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    @Override
    public String toString() {
        return "CompanyIncomeDto{" +
                "companyId=" + companyId +
                ", companyName='" + companyName + '\'' +
                ", income=" + income +
                '}';
    }
}
