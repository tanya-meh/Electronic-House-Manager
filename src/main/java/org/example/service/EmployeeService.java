package org.example.service;

import org.example.dao.EmployeeDao;
import org.example.dao.OwnerDao;
import org.example.dto.EmployeeDto;
import org.example.dto.OwnerDto;
import org.example.dto.PersonDto;
import org.example.entity.Employee;
import org.example.entity.Owner;

import java.util.Set;

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
//        employee.setId(personDto.getId());
//        employee.setName(personDto.getName());

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
        }
        EmployeeDao.updateEmployee(employee);
    }

    public void deleteEmployee(long id) {
        Employee employee = EmployeeDao.getEmployeeById(id);
        if(employee != null) {
            EmployeeDao.deleteEmployee(employee);
        }
    }

}
