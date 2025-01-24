package org.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Id;

import java.util.Set;

@Entity
public class EmployeeInCompany{
    @Id
    @ManyToOne
    Employee employee;
    @Id
    @ManyToOne
    Company company;

    @OneToMany(mappedBy = "employeeInCompany")
    private Set<Building> buildings;

    public EmployeeInCompany() {
    }

    public EmployeeInCompany(Employee employee, Company company) {
        this.employee = employee;
        this.company = company;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Set<Building> getBuildings() {
        return buildings;
    }

    public void setBuildings(Set<Building> buildings) {
        this.buildings = buildings;
    }
}
