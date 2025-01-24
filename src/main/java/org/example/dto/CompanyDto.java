package org.example.dto;

import org.example.entity.EmployeeInCompany;

import java.util.Set;

public class CompanyDto {
    private long id;
    private String name;
    private Set<EmployeeInCompany> employeesInCompany;

    public CompanyDto() {
    }

    public CompanyDto(String name) {
        this.name = name;
    }

    public CompanyDto(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<EmployeeInCompany> getEmployeesInCompany() {
        return employeesInCompany;
    }

    public void setEmployeesInCompany(Set<EmployeeInCompany> employeesInCompany) {
        this.employeesInCompany = employeesInCompany;
    }
}
