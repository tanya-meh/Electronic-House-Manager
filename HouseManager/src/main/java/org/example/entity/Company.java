package org.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

import java.util.Set;

@Entity
public class Company extends BaseEntity{
    @NotBlank
    private String name;
    @OneToMany(mappedBy = "company")
    private Set<EmployeeInCompany> employeesInCompany;

    public Company() {
    }

    public Company(String name) {
        this.name = name;
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


    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                "} " + super.toString();
    }
}
