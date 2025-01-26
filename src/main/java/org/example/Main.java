package org.example;

import org.example.configuration.SessionFactoryUtil;
import org.example.dao.CompanyDao;
import org.example.dao.EmployeeInCompanyDao;
import org.example.dto.*;
import org.example.entity.BaseTaxes;
import org.example.service.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        SessionFactoryUtil.getSessionFactory().openSession();

        CompanyService companyService = new CompanyService();
        EmployeeService employeeService = new EmployeeService();
        BuildingService buildingService = new BuildingService();
        ApartmentService apartmentService = new ApartmentService();
        ResidentService residentService = new ResidentService();
        BaseTaxesService baseTaxesService = new BaseTaxesService();
        OwnerService ownerService = new OwnerService();
        TaxService taxService = new TaxService();


        //company 1
        CompanyDto company1 = companyService.createCompany(new CompanyDto("Domoupravitel 1"));

        EmployeeDto employee1 = employeeService.createEmployee(new EmployeeDto("Ivan Georgiev"));
        EmployeeDto employee2 = employeeService.createEmployee(new EmployeeDto("Petar Kolev"));

        //hiring employees to company 1
        companyService.hireEmployeeInCompany(employee1.getId(), company1.getId());
        companyService.hireEmployeeInCompany(employee2.getId(), company1.getId());

            //building 1
        BuildingDto building1 = buildingService.createBuilding(new CreateBuildingDto("Montevideo 25", 12, BigDecimal.valueOf(50000), BigDecimal.valueOf(10000)));
            //building 1 is serviced by company 1
        companyService.assignBuildingToEmployeeInCompany(building1.getId(), company1.getId());

            //setting base taxes for building 1
        BaseTaxesDto baseTaxesBuilding1 = baseTaxesService.createBaseTaxes(new CreateBaseTaxesDto(BigDecimal.valueOf(2.5), BigDecimal.valueOf(10), BigDecimal.valueOf(5)));
        baseTaxesService.applyBaseTaxesToBuilding(baseTaxesBuilding1.getId(), building1.getId());

                //apartment 1
        ApartmentDto apartment1 = apartmentService.createApartment(new CreateApartmentDto(1,1,BigDecimal.valueOf(90)));
                //adding apartment to building
        buildingService.addApartmentToBuilding(apartment1.getId(), building1.getId());

                //apartment 2
        ApartmentDto apartment2 = apartmentService.createApartment(new CreateApartmentDto(2,1,BigDecimal.valueOf(65)));
                //adding apartment to building
        buildingService.addApartmentToBuilding(apartment2.getId(), building1.getId());

                //apartment 3
        ApartmentDto apartment3 = apartmentService.createApartment(new CreateApartmentDto(3,2,BigDecimal.valueOf(90)));
                //adding apartment to building
        buildingService.addApartmentToBuilding(apartment3.getId(), building1.getId());

        //owner 1
        OwnerDto owner1 = ownerService.createOwner(new CreateOwnerDto("Maria Ivanova"));
        //setting owner 1 as owner of apartment 1 and apartment 2
        apartmentService.addApartmentOwner(owner1.getId(), apartment1.getId());
        apartmentService.addApartmentOwner(owner1.getId(), apartment2.getId());

        //owner 2
        OwnerDto owner2 = ownerService.createOwner(new CreateOwnerDto("Martin Ivanov"));
        //setting owner 2 as owner of apartment 2 and apartment 3
        apartmentService.addApartmentOwner(owner2.getId(), apartment2.getId());
        apartmentService.addApartmentOwner(owner2.getId(), apartment3.getId());

        //resident 1
        ResidentDto resident1 = residentService.createResident(new ResidentDto("Kalina Kirilova", 22, false));
        //lives in apartment 2
        apartmentService.addResidentToApartment(resident1.getId(), apartment2.getId());

        //resident 2
        ResidentDto resident2 = residentService.createResident(new ResidentDto("Rumen Rumenov", 6, false));
        //lives in apartment 2
        apartmentService.addResidentToApartment(resident2.getId(), apartment2.getId());

        //with two pets
        apartment1.setNumberOfPets(2);
        apartmentService.updateApartment(apartment1);

        //resident 3
        ResidentDto resident3 = residentService.createResident(new ResidentDto("Viktor Viktorov", 5, true));
        //lives in apartment 3
        apartmentService.addResidentToApartment(resident3.getId(), apartment3.getId());

        //resident 4
        ResidentDto resident4 = residentService.createResident(new ResidentDto("Asen Asenov", 45, true));
        //lives in apartment 3
        apartmentService.addResidentToApartment(resident4.getId(), apartment3.getId());


            //building 2
        BuildingDto building2 = buildingService.createBuilding(new CreateBuildingDto("Linkoln 40", 6, BigDecimal.valueOf(6000), BigDecimal.valueOf(500)));
            //building 2 is serviced by company 1
        companyService.assignBuildingToEmployeeInCompany(building2.getId(), company1.getId());

            //setting base taxes for building 2
        BaseTaxesDto baseTaxesBuilding2 = baseTaxesService.createBaseTaxes(new CreateBaseTaxesDto(BigDecimal.valueOf(1.8), BigDecimal.valueOf(5), BigDecimal.valueOf(3)));
        baseTaxesService.applyBaseTaxesToBuilding(baseTaxesBuilding2.getId(), building2.getId());

        //apartment 4
        ApartmentDto apartment4 = apartmentService.createApartment(new CreateApartmentDto(2,3,BigDecimal.valueOf(110)));
        //adding apartment to building
        buildingService.addApartmentToBuilding(apartment4.getId(), building2.getId());
        //adding owner
        apartmentService.addApartmentOwner(owner1.getId(), apartment4.getId());
        //adding resident
        apartmentService.addResidentToApartment(resident1.getId(), apartment4.getId());
        //adding a pet
        apartment4.setNumberOfPets(1);
        apartmentService.updateApartment(apartment4);

        //apartment 5
        ApartmentDto apartment5 = apartmentService.createApartment(new CreateApartmentDto(4,3,BigDecimal.valueOf(95)));
        //adding apartment to building
        buildingService.addApartmentToBuilding(apartment5.getId(), building2.getId());

        //apartment 6
        ApartmentDto apartment6 = apartmentService.createApartment(new CreateApartmentDto(6,4,BigDecimal.valueOf(90)));
        //adding apartment to building
        buildingService.addApartmentToBuilding(apartment6.getId(), building2.getId());

        //owner 3
        OwnerDto owner3 = ownerService.createOwner(new CreateOwnerDto("Jivko Jivkov"));
        //owns apartment 4 and 5
        apartmentService.addApartmentOwner(owner3.getId(), apartment4.getId());
        apartmentService.addApartmentOwner(owner3.getId(), apartment5.getId());

        //Owner 4
        OwnerDto owner4 = ownerService.createOwner(new CreateOwnerDto("Nikola Nikolov"));
        //owns apartment 6
        apartmentService.addApartmentOwner(owner4.getId(), apartment6.getId());


        //company 2
        CompanyDto company2 = companyService.createCompany(new CompanyDto("Home management"));

        EmployeeDto employee3 = employeeService.createEmployee(new EmployeeDto("Kaloyan Kaloyanov"));
        EmployeeDto employee4 = employeeService.createEmployee(new EmployeeDto("Mihail Mihailov"));

        //hiring employees to company 2
        companyService.hireEmployeeInCompany(employee1.getId(), company2.getId());//working in company 1 and 2
        companyService.hireEmployeeInCompany(employee3.getId(), company2.getId());
        companyService.hireEmployeeInCompany(employee4.getId(), company2.getId());

        //building 3
        BuildingDto building3 = buildingService.createBuilding(new CreateBuildingDto("Lyublyana 35", 3, BigDecimal.valueOf(1100), BigDecimal.valueOf(800)));
        //building 3 is serviced by company 2
        companyService.assignBuildingToEmployeeInCompany(building3.getId(), company2.getId());

        //setting base taxes for building 3
        BaseTaxesDto baseTaxesBuilding3 = baseTaxesService.createBaseTaxes(new CreateBaseTaxesDto(BigDecimal.valueOf(2), BigDecimal.valueOf(7), BigDecimal.valueOf(6)));
        baseTaxesService.applyBaseTaxesToBuilding(baseTaxesBuilding1.getId(), building1.getId());

        //apartment7
        ApartmentDto apartment7 = apartmentService.createApartment(new CreateApartmentDto(1,1,BigDecimal.valueOf(90)));
        //adding apartment to building
        buildingService.addApartmentToBuilding(apartment7.getId(), building3.getId());

        //apartment 8
        ApartmentDto apartment8 = apartmentService.createApartment(new CreateApartmentDto(2,1,BigDecimal.valueOf(85)));
        //adding apartment to building
        buildingService.addApartmentToBuilding(apartment8.getId(), building3.getId());

        //owner 5
        OwnerDto owner5 = ownerService.createOwner(new CreateOwnerDto("Ognyan Ognyanov"));
        //setting owner 5 as owner of apartment 7 and apartment 8
        apartmentService.addApartmentOwner(owner5.getId(), apartment7.getId());
        apartmentService.addApartmentOwner(owner5.getId(), apartment8.getId());

        //resident 5
        ResidentDto resident5 = residentService.createResident(new ResidentDto("Silviya Svilenova", 40, false));
        //lives in apartment 7
        apartmentService.addResidentToApartment(resident5.getId(), apartment7.getId());

        //////////////////////////////////////////////

        //company 3
        CompanyDto company3 = companyService.createCompany(new CompanyDto("Building manager"));

        EmployeeDto employee5 = employeeService.createEmployee(new EmployeeDto("Vasil Vasilev"));

        //hiring employees to company 3
        companyService.hireEmployeeInCompany(employee5.getId(), company3.getId());

        //building 4
        BuildingDto building4 = buildingService.createBuilding(new CreateBuildingDto("Boryana 15", 2, BigDecimal.valueOf(1000), BigDecimal.valueOf(350)));
        //building 4 is serviced by company 3
        companyService.assignBuildingToEmployeeInCompany(building4.getId(), company3.getId());

        //setting base taxes for building 4
        BaseTaxesDto baseTaxesBuilding4 = baseTaxesService.createBaseTaxes(new CreateBaseTaxesDto(BigDecimal.valueOf(3), BigDecimal.valueOf(12), BigDecimal.valueOf(7)));
        baseTaxesService.applyBaseTaxesToBuilding(baseTaxesBuilding4.getId(), building4.getId());

        //apartment 9
        ApartmentDto apartment9 = apartmentService.createApartment(new CreateApartmentDto(1,1,BigDecimal.valueOf(80)));
        //adding apartment to building
        buildingService.addApartmentToBuilding(apartment9.getId(), building4.getId());

        //apartment 10
        ApartmentDto apartment10 = apartmentService.createApartment(new CreateApartmentDto(2,1,BigDecimal.valueOf(90)));
        //adding apartment to building
        buildingService.addApartmentToBuilding(apartment10.getId(), building4.getId());


        //
        TaxDto tax1 = taxService.createTax(new CreateTaxDto(LocalDate.now()));
        taxService.applyTaxToApartment(tax1.getId(), apartment1.getId());
        taxService.payTax(tax1.getId());

        TaxDto tax2 = taxService.createTax(new CreateTaxDto(LocalDate.now()));
        taxService.applyTaxToApartment(tax2.getId(), apartment2.getId());
        taxService.payTax(tax2.getId());

        TaxDto tax3 = taxService.createTax(new CreateTaxDto(LocalDate.now()));
        taxService.applyTaxToApartment(tax3.getId(), apartment3.getId());
        taxService.payTax(tax3.getId());

        TaxDto tax4 = taxService.createTax(new CreateTaxDto(LocalDate.now()));
        taxService.applyTaxToApartment(tax4.getId(), apartment4.getId());
        taxService.payTax(tax4.getId());

        TaxDto tax5 = taxService.createTax(new CreateTaxDto(LocalDate.now()));
        taxService.applyTaxToApartment(tax5.getId(), apartment5.getId());

        TaxDto tax6 = taxService.createTax(new CreateTaxDto(LocalDate.now()));
        taxService.applyTaxToApartment(tax6.getId(), apartment6.getId());
        taxService.payTax(tax6.getId());

        TaxDto tax7 = taxService.createTax(new CreateTaxDto(LocalDate.now()));
        taxService.applyTaxToApartment(tax7.getId(), apartment7.getId());

        TaxDto tax8 = taxService.createTax(new CreateTaxDto(LocalDate.now()));
        taxService.applyTaxToApartment(tax8.getId(), apartment8.getId());
        taxService.payTax(tax8.getId());

        TaxDto tax9 = taxService.createTax(new CreateTaxDto(LocalDate.now()));
        taxService.applyTaxToApartment(tax9.getId(), apartment9.getId());

        TaxDto tax10 = taxService.createTax(new CreateTaxDto(LocalDate.now()));
        taxService.applyTaxToApartment(tax10.getId(), apartment10.getId());
        taxService.payTax(tax10.getId());


//        System.out.println(CompanyDao.filterCompaniesByIncome(BigDecimal.ZERO,BigDecimal.valueOf(300), false));
    }
}