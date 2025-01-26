package org.example.service;

import org.example.dao.BuildingDao;
import org.example.dao.CompanyDao;
import org.example.dao.EmployeeDao;
import org.example.dao.EmployeeInCompanyDao;
import org.example.dto.CompanyDto;
import org.example.dto.HiredEmployeeInCompanyDto;
import org.example.entity.Building;
import org.example.entity.Company;
import org.example.entity.Employee;
import org.example.entity.EmployeeInCompany;

import java.util.Set;

public class CompanyService {
    public CompanyDto createCompany(CompanyDto companyDto) {
        Company company = new Company(
                companyDto.getName(),
                companyDto.getEmployeesInCompany()
        );

        CompanyDao.createCompany(company);

        return new CompanyDto(company.getId(), company.getName());
    }

    public void updateCompany(CompanyDto companyDto) {
        Company company = CompanyDao.getCompanyById(companyDto.getId());
        if(company != null) {
            company.setName(companyDto.getName());
            company.setEmployeesInCompany(companyDto.getEmployeesInCompany());
        }
        CompanyDao.updateCompany(company);
    }

    public void deleteCompany(long id) {
        Company company = CompanyDao.getCompanyById(id);
        if(company != null) {
            CompanyDao.deleteCompany(company);
        }
    }

    public HiredEmployeeInCompanyDto hireEmployeeInCompany(long employeeId, long companyId) {
        Employee employee = EmployeeDao.getEmployeeById(employeeId);
        Company company = CompanyDao.getCompanyById(companyId);

        if(employee != null && company != null){
            EmployeeInCompany employeeInCompany = new EmployeeInCompany(employee, company);
            EmployeeInCompanyDao.createEmployeeInCompany(employeeInCompany);

            return new HiredEmployeeInCompanyDto(employeeInCompany.getEmployee(), employeeInCompany.getCompany());
        }

        return null;
    }

    public EmployeeInCompany getEmployeeInCompanyWithLeastBuildings(long companyId) {
        Company company = CompanyDao.getCompanyById(companyId);
        if(company != null) {
            return CompanyDao.getEmployeeInCompanyWithLeastBuildings(companyId);
        }
        return null;
    }

    public void assignBuildingToEmployeeInCompany(long buildingId, long companyId) {
        EmployeeInCompany employeeInCompany = this.getEmployeeInCompanyWithLeastBuildings(companyId);
        if(employeeInCompany != null) {
            BuildingService buildingService = new BuildingService();
            buildingService.setEmployeeInCompany(buildingId, employeeInCompany);
        }
    }

    public void fireEmployeeInCompany(long employeeId, long companyId) {
        System.out.println("firing employee in company");
        Company company = CompanyDao.getCompanyById(companyId);
        Employee employee = EmployeeDao.getEmployeeById(employeeId);
        EmployeeInCompany employeeInCompany;

        if(company != null && employee != null) {
            employeeInCompany = EmployeeInCompanyDao.getEmployeeInCompanyByEmployeeAndCompanyId(employeeId, companyId);
            System.out.println("employee in company " + employeeInCompany);
        } else {
            return;
        }

        if(employeeInCompany != null) {
            //unlinking the buildings from the employeeInCompany
            System.out.println("unlinking");
            Set<Building> buildings = EmployeeInCompanyDao.getEmployeeInCompanyBuildings(employeeId, companyId);
            buildings.forEach(building -> {
                        building.setEmployeeInCompany(null);
                        BuildingDao.updateBuilding(building);
                    });

            //deleting the employeeInCompany
            System.out.println("deleting");
            EmployeeInCompanyDao.deleteEmployeeInCompany(employeeInCompany);

            //reassigning the buildings to other employees in the company
            System.out.println("reassigning");
            buildings.forEach(building -> {
                        assignBuildingToEmployeeInCompany(building.getId(), companyId);
                    });
        }
    }
/*SELECT sum(house_manager.tax.amount) FROM house_manager.tax join house_manager.apartment on house_manager.tax.apartment_id = house_manager.apartment.id
join house_manager.building on house_manager.apartment.building_id = house_manager.building.id
join house_manager.company on house_manager.building.employeeInCompany_company_id
where house_manager.tax.dateOfPayment is not null
;*/

}
