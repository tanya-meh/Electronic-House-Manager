package org.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

import java.util.Set;

@Entity
public class Employee extends Person {
    @OneToMany(mappedBy = "employee")
    private Set<EmployeeInCompany> employeeInCompanies;

    public Employee() {
    }

    public Employee(String name) {
        super(name);
    }

    public Set<EmployeeInCompany> getEmployeeInCompanies() {
        return employeeInCompanies;
    }

    public void setEmployeeInCompanies(Set<EmployeeInCompany> employeeInCompanies) {
        this.employeeInCompanies = employeeInCompanies;
    }


}