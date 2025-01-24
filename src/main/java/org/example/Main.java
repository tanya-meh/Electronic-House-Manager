package org.example;

import org.example.configuration.SessionFactoryUtil;
import org.example.dao.EmployeeInCompanyDao;
import org.example.dto.*;
import org.example.service.ApartmentService;
import org.example.service.BuildingService;
import org.example.service.CompanyService;
import org.example.service.EmployeeService;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        SessionFactoryUtil.getSessionFactory().openSession();

        CompanyService companyService = new CompanyService();
        EmployeeService employeeService = new EmployeeService();
        BuildingService buildingService = new BuildingService();
        ApartmentService apartmentService = new ApartmentService();

        CompanyDto companyDto = companyService.createCompany(new CompanyDto("Domoupravitel 1"));

        EmployeeDto employee1 = employeeService.createEmployee(new EmployeeDto("Ivan Georgiev"));
        EmployeeDto employee2 = employeeService.createEmployee(new EmployeeDto("Ivan Georgiev"));

        System.out.println(companyService.hireEmployeeInCompany(employee1.getId(), companyDto.getId()));
        System.out.println(companyService.hireEmployeeInCompany(employee2.getId(), companyDto.getId()));

        BuildingDto building1 = buildingService.createBuilding(new CreateBuildingDto("Montevideo 25", 12, 40, BigDecimal.valueOf(50000), BigDecimal.valueOf(10000)));

        ApartmentDto apartment1 = apartmentService.createApartment(new CreateApartmentDto(1,1,BigDecimal.valueOf(90)));
        apartmentService.addApartmentToBuilding(apartment1.getId(), building1.getId());
        companyService.assignBuildingToEmployeeInCompany(building1.getId(), companyDto.getId());

        BuildingDto building2 = buildingService.createBuilding(new CreateBuildingDto("Makedonia 87", 8, 32, BigDecimal.valueOf(20000), BigDecimal.valueOf(6000)));
        companyService.assignBuildingToEmployeeInCompany(building2.getId(), companyDto.getId());

        BuildingDto building3 = buildingService.createBuilding(new CreateBuildingDto("Pere Toshev 89", 6, 17, BigDecimal.valueOf(15000), BigDecimal.valueOf(5000)));
        companyService.assignBuildingToEmployeeInCompany(building3.getId(), companyDto.getId());

//        companyService.fireEmployeeInCompany(employee2.getId(), companyDto.getId());

    }
}