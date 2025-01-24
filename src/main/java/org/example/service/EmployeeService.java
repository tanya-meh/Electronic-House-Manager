package org.example.service;

import org.example.dao.EmployeeDao;
import org.example.dto.EmployeeDto;
import org.example.entity.Employee;

import java.util.Set;

public class EmployeeService {
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee(
                employeeDto.getName()
        );

        EmployeeDao.createEmployee(employee);

        return new EmployeeDto(employee.getId(), employee.getName());
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
