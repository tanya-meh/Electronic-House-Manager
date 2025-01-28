package org.example.service;

import org.example.dao.BuildingDao;
import org.example.dao.CompanyDao;
import org.example.dao.EmployeeDao;
import org.example.dao.EmployeeInCompanyDao;
import org.example.dto.*;
import org.example.entity.Building;
import org.example.entity.Company;
import org.example.entity.Employee;
import org.example.entity.EmployeeInCompany;
import org.example.exception.EntitiesNotConnectedException;
import org.example.exception.IllegalAmountException;
import org.example.exception.IllegalAmountOfMoneyException;
import org.example.exception.MinimumGreaterThanMaximumException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public class CompanyService {
    public CompanyDto createCompany(CompanyDto companyDto) {
        Company company = new Company(
                companyDto.getName()
        );

        CompanyDao.createCompany(company);

        return new CompanyDto(company.getId(), company.getName());
    }

    public void updateCompany(CompanyDto companyDto) {
        Company company = CompanyDao.getCompanyById(companyDto.getId());
        if(company != null) {
            company.setName(companyDto.getName());
        } else {
            throw new IllegalArgumentException("Company not found");
        }
        CompanyDao.updateCompany(company);
    }

    public void deleteCompany(long id) {
        Company company = CompanyDao.getCompanyById(id);
        if(company != null) {
            CompanyDao.deleteCompany(company);
        } else {
            throw new IllegalArgumentException("Company not found");
        }
    }

    public HiredEmployeeInCompanyDto hireEmployeeInCompany(long employeeId, long companyId) {
        Employee employee = EmployeeDao.getEmployeeById(employeeId);
        Company company = CompanyDao.getCompanyById(companyId);

        if(employee != null && company != null){
            EmployeeInCompany employeeInCompany = new EmployeeInCompany(employee, company);
            EmployeeInCompanyDao.createEmployeeInCompany(employeeInCompany);

            return new HiredEmployeeInCompanyDto(employeeInCompany.getEmployee(), employeeInCompany.getCompany());
        } else {
            throw new IllegalArgumentException("Employee or Company not found.");
        }
    }

    public EmployeeInCompany getEmployeeInCompanyWithLeastBuildings(long companyId) {
        Company company = CompanyDao.getCompanyById(companyId);
        if(company != null) {
            return CompanyDao.getEmployeeInCompanyWithLeastBuildings(companyId);
        }else {
            throw new IllegalArgumentException("Company not found.");
        }
    }

    public void assignBuildingToEmployeeInCompany(long buildingId, long companyId) {
        EmployeeInCompany employeeInCompany = this.getEmployeeInCompanyWithLeastBuildings(companyId);
        if(employeeInCompany != null) {
            BuildingService buildingService = new BuildingService();
            buildingService.setEmployeeInCompany(buildingId, employeeInCompany);
        }else {
            throw new IllegalArgumentException("Building or Company not found.");
        }
    }

    public void fireEmployeeInCompany(long employeeId, long companyId) {
        Company company = CompanyDao.getCompanyById(companyId);
        Employee employee = EmployeeDao.getEmployeeById(employeeId);
        EmployeeInCompany employeeInCompany;

        if(company != null && employee != null) {
            employeeInCompany = EmployeeInCompanyDao.getEmployeeInCompanyByEmployeeAndCompanyId(employeeId, companyId);
        } else {
            throw new IllegalArgumentException("Employee or Company not found.");
        }

        if(employeeInCompany != null) {
            //unlinking the buildings from the employeeInCompany
            Set<Building> buildings = EmployeeInCompanyDao.getEmployeeInCompanyBuildings(employeeId, companyId);
            buildings.forEach(building -> {
                        building.setEmployeeInCompany(null);
                        BuildingDao.updateBuilding(building);
                    });

            //deleting the employeeInCompany
            EmployeeInCompanyDao.deleteEmployeeInCompany(employeeInCompany);

            //reassigning the buildings to other employees in the company
            buildings.forEach(building -> {
                        assignBuildingToEmployeeInCompany(building.getId(), companyId);
            });
        } else {
            throw new EntitiesNotConnectedException("Employee not hired in Company");
        }
    }

    public List<CompanyIncomeDto> filterCompaniesByIncome(BigDecimal minIncome, BigDecimal maxIncome, boolean asc) {
        if(minIncome.compareTo(BigDecimal.ZERO) < 0 || maxIncome.compareTo(BigDecimal.ZERO) < 0 ){
            throw new IllegalAmountOfMoneyException("minIncome and maxIncome should not be negative numbers.");
        }
        if (minIncome.compareTo(maxIncome) > 0) {
            throw new MinimumGreaterThanMaximumException("minIncome greater than maxIncome.");
        }
        return CompanyDao.filterCompaniesByIncome(minIncome, maxIncome, asc);
    }

    public List<CompanyEmployeeBuildingDto> buildingsServicedByEmployeesInCompany(long companyId) {
        if(CompanyDao.getCompanyById(companyId) == null){
            throw new IllegalArgumentException("Company not found.");
        }
        return CompanyDao.buildingsServicedByEmployeesInCompany(companyId);
    }

    public List<CompanyEmployeeBuildingDto> numberOfBuildingsServicedByEmployeesInCompany(long companyId) {
        if(CompanyDao.getCompanyById(companyId) == null){
            throw new IllegalArgumentException("Company not found.");
        }
        return CompanyDao.numberOfBuildingsServicedByEmployeesInCompany(companyId);
    }

    public BigDecimal getCompanySumUnpaidTaxes(long companyId) {
        if(CompanyDao.getCompanyById(companyId) == null){
            throw new IllegalArgumentException("Company not found.");
        }
        return CompanyDao.getCompanySumUnpaidTaxes(companyId);
    }

    public BigDecimal getCompanySumPaidTaxes(long companyId) {
        if(CompanyDao.getCompanyById(companyId) == null){
            throw new IllegalArgumentException("Company not found.");
        }
        return CompanyDao.getCompanySumPaidTaxes(companyId);
    }

    public List<TaxDto> getCompanyUnpaidTaxes(long companyId) {
        if(CompanyDao.getCompanyById(companyId) == null){
            throw new IllegalArgumentException("Company not found.");
        }
        return CompanyDao.getCompanyUnpaidTaxes(companyId);
    }

    public List<TaxDto> getCompanyPaidTaxes(long companyId) {
        if(CompanyDao.getCompanyById(companyId) == null){
            throw new IllegalArgumentException("Company not found.");
        }
        return CompanyDao.getCompanyPaidTaxes(companyId);
    }
}
