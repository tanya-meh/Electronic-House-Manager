package org.example.dto;

import org.example.entity.Company;
import org.example.entity.Employee;

public class HiredEmployeeInCompanyDto {
    private Employee employee;
    private Company company;

    public HiredEmployeeInCompanyDto(Employee employee, Company company) {
        this.employee = employee;
        this.company = company;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
