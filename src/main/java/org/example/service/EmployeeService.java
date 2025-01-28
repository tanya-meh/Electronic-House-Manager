package org.example.service;

import org.example.dao.CompanyDao;
import org.example.dao.EmployeeDao;
import org.example.dto.*;
import org.example.entity.*;
import org.example.exception.IllegalAmountException;
import org.example.exception.MinimumGreaterThanMaximumException;

import java.math.BigDecimal;
import java.util.List;

public class EmployeeService {
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee(
                employeeDto.getName()
        );

        EmployeeDao.createEmployee(employee);

        return new EmployeeDto(employee.getId(), employee.getName());
    }

    public EmployeeDto createEmployee(PersonDto personDto) {
        Employee employee = new Employee();
        employee.setId(personDto.getId());
        employee.setName(personDto.getName());

        EmployeeDao.createEmployee(employee);

        return new EmployeeDto(
                employee.getId(),
                employee.getName()
        );
    }

    public void updateEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeDao.getEmployeeById(employeeDto.getId());
        if(employee != null) {
            employee.setName(employeeDto.getName());
        } else {
            throw new IllegalArgumentException("Employee not found");
        }
        EmployeeDao.updateEmployee(employee);
    }

    public void deleteEmployee(long id) {
        Employee employee = EmployeeDao.getEmployeeById(id);
        if(employee != null) {
            EmployeeDao.deleteEmployee(employee);
        } else {
            throw new IllegalArgumentException("Employee not found");
        }
    }

    public List<EmployeeNameNumBuildingsDto> filterCompanyEmployeesByNameAndNumberOfBuildings(long companyId, String name, long minNumBuildings, long maxNumBuildings) {
        if(CompanyDao.getCompanyById(companyId) == null){
            throw new IllegalArgumentException("Company not found");
        }

        if(minNumBuildings < 0 || maxNumBuildings < 0) {
            throw new IllegalAmountException("minNumBuildings and maxNumBuildings should not be negative");
        }

        if(minNumBuildings > maxNumBuildings) {
            throw new MinimumGreaterThanMaximumException("minNumBuildings is greater than maxNumBuildings");
        }
        return EmployeeDao.filterCompanyEmployeesByNameAndNumberOfBuildings(companyId, name, minNumBuildings, maxNumBuildings);
    }

    public BigDecimal getEmployeeSumUnpaidTaxes(long employeeId) {
        if(CompanyDao.getCompanyById(employeeId) == null){
            throw new IllegalArgumentException("Employee not found");
        }
        return EmployeeDao.getEmployeeSumUnpaidTaxes(employeeId);
    }

    public BigDecimal getEmployeeSumPaidTaxes(long employeeId) {
        if(CompanyDao.getCompanyById(employeeId) == null){
            throw new IllegalArgumentException("Employee not found");
        }
        return EmployeeDao.getEmployeeSumPaidTaxes(employeeId);
    }

    public List<TaxDto> getEmployeeUnpaidTaxes(long employeeId) {
        if(CompanyDao.getCompanyById(employeeId) == null){
            throw new IllegalArgumentException("Employee not found");
        }
        return EmployeeDao.getEmployeeUnpaidTaxes(employeeId);
    }

    public List<TaxDto> getEmployeePaidTaxes(long employeeId) {
        if(CompanyDao.getCompanyById(employeeId) == null){
            throw new IllegalArgumentException("Employee not found");
        }
        return EmployeeDao.getEmployeePaidTaxes(employeeId);
    }

}
