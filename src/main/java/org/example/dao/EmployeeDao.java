package org.example.dao;

import org.example.configuration.SessionFactoryUtil;
import org.example.entity.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Set;

public class EmployeeDao {
    public static void createEmployee(Employee employee) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(employee);
            transaction.commit();
        }
    }

    public static void updateEmployee(Employee employee) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(employee);
            transaction.commit();
        }
    }

    public static void deleteEmployee(Employee employee) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(employee);
            transaction.commit();
        }
    }

    public static Employee getEmployeeById(long id) {
        Employee employee;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            employee = session.get(Employee.class, id);
            transaction.commit();
        }
        return employee;
    }

    public static Set<EmployeeInCompany> getEmployeeEmployeeInCompany(long id) {
        Employee employee;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            employee = session.createQuery("select e from Employee e join fetch e.employeeInCompanies where e.id = :id", Employee.class)
                    .setParameter("id", id)
                    .getSingleResult();
            transaction.commit();
        }
        return employee.getEmployeeInCompanies();
    }

}
