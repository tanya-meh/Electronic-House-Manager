package org.example.dao;

import jakarta.persistence.criteria.*;
import org.example.configuration.SessionFactoryUtil;
import org.example.dto.EmployeeNameNumBuildingsDto;
import org.example.entity.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    public static List<EmployeeNameNumBuildingsDto> filterCompanyEmployeesByNameAndNumberOfBuildings(long companyId, long minNumBuildings, long maxNumBuildings) {
        List<Object[]> results;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Object[]> cr = cb.createQuery(Object[].class);

            Root<Employee> root = cr.from(Employee.class);
            Join<Employee, EmployeeInCompany> employeeEmployeeInCompanyJoin = root.join("employeeInCompanies");
            Join<EmployeeInCompany, Company> employeeInCompanyCompanyJoin = employeeEmployeeInCompanyJoin.join("company");
            Join<EmployeeInCompany, Building> buildingEmployeeInCompanyJoin = employeeEmployeeInCompanyJoin.join("buildings");

            cr.multiselect(
                    root.get("id"),
                    root.get("name"),
                    cb.count(buildingEmployeeInCompanyJoin.get("id")),
                    employeeInCompanyCompanyJoin.get("id")
            );

            Predicate havingPredicate = cb.conjunction();
            Expression<Long> numBuildingsCount = cb.count(buildingEmployeeInCompanyJoin.get("id"));

            havingPredicate = cb.and(havingPredicate, cb.greaterThanOrEqualTo(numBuildingsCount, minNumBuildings));

            havingPredicate = cb.and(havingPredicate, cb.lessThanOrEqualTo(numBuildingsCount, maxNumBuildings));

            cr.groupBy(root.get("id"),
                    root.get("name"),
                    employeeInCompanyCompanyJoin.get("id"));

            cr.having(cb.equal(employeeInCompanyCompanyJoin.get("id"), companyId));

            results = session.createQuery(cr).getResultList();
        }

        return results.stream()
                .map(result -> new EmployeeNameNumBuildingsDto(
                        (Long) result[0],
                        (String) result[1],
                        (Long) result[3]))
                .collect(Collectors.toList());
    }

}
