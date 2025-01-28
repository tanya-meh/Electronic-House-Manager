package org.example.dao;

import jakarta.validation.Valid;
import org.example.configuration.SessionFactoryUtil;
import org.example.dto.HiredEmployeeInCompanyDto;
import org.example.entity.Building;
import org.example.entity.EmployeeInCompany;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Set;

public class EmployeeInCompanyDao {
    public static void createEmployeeInCompany(@Valid EmployeeInCompany employeeInCompany) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(employeeInCompany);
            transaction.commit();
        }
    }

    public static void updateEmployeeInCompany(@Valid EmployeeInCompany employeeInCompany) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(employeeInCompany);
            transaction.commit();
        }
    }

    public static void deleteEmployeeInCompany(@Valid EmployeeInCompany employeeInCompany) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(employeeInCompany);
            transaction.commit();
        }
    }

    public static EmployeeInCompany getEmployeeInCompanyByEmployeeAndCompanyId(long employeeId, long companyId) {
        EmployeeInCompany employeeInCompany;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            employeeInCompany = session.createQuery("select ec from EmployeeInCompany ec where ec.employee.id = :employeeId and ec.company.id = :companyId", EmployeeInCompany.class)
                    .setParameter("employeeId", employeeId)
                    .setParameter("companyId", companyId)
                    .getSingleResult();
            transaction.commit();
        }
        return employeeInCompany;
    }

    public static Set<Building> getEmployeeInCompanyBuildings(long employeeId, long companyId) {
        EmployeeInCompany employeeInCompany;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            employeeInCompany = session.createQuery("select ec from EmployeeInCompany ec join fetch ec.buildings where ec.employee.id = :employeeId and ec.company.id = :companyId", EmployeeInCompany.class)
                    .setParameter("employeeId", employeeId)
                    .setParameter("companyId", companyId)
                    .getSingleResult();
            transaction.commit();
        }
        return employeeInCompany.getBuildings();
    }

    public static void createHireEmployeeInCompanyDto(HiredEmployeeInCompanyDto hiredEmployeeInCompanyDto) {
        EmployeeInCompany employeeInCompany = new EmployeeInCompany(
                hiredEmployeeInCompanyDto.getEmployee(),
                hiredEmployeeInCompanyDto.getCompany()
        );

        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(employeeInCompany);
            transaction.commit();
        }
    }

}
