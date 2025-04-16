package org.example.dao;

import jakarta.persistence.criteria.*;
import jakarta.validation.Valid;
import org.example.configuration.SessionFactoryUtil;
import org.example.dto.EmployeeNameNumBuildingsDto;
import org.example.dto.TaxDto;
import org.example.entity.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class EmployeeDao {
    public static void createEmployee(@Valid Employee employee) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(employee);
            transaction.commit();
        }
    }

    public static void updateEmployee(@Valid Employee employee) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(employee);
            transaction.commit();
        }
    }

    public static void deleteEmployee(@Valid Employee employee) {
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

    public static List<EmployeeNameNumBuildingsDto> filterCompanyEmployeesByNameAndNumberOfBuildings(long companyId, String name, long minNumBuildings, long maxNumBuildings) {
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
                    cb.count(root.get("id")),
                    employeeInCompanyCompanyJoin.get("id")
            );

            Predicate havingPredicate = cb.conjunction();
            Expression<Long> numBuildingsCount = cb.count(root.get("id"));

            havingPredicate = cb.and(havingPredicate, cb.equal(employeeInCompanyCompanyJoin.get("id"), companyId));
            havingPredicate = cb.and(havingPredicate, cb.greaterThanOrEqualTo(numBuildingsCount, minNumBuildings));
            havingPredicate = cb.and(havingPredicate, cb.lessThanOrEqualTo(numBuildingsCount, maxNumBuildings));
            havingPredicate = cb.and(havingPredicate, cb.like(root.get("name"), "%" + name + "%"));

            cr.groupBy(root.get("id"),
                    root.get("name"),
                    employeeInCompanyCompanyJoin.get("id"));

            cr.having(havingPredicate);

            results = session.createQuery(cr).getResultList();
        }

        return results.stream()
                .map(result -> new EmployeeNameNumBuildingsDto(
                        (long) result[0],
                        (String) result[1],
                        (long) result[2],
                        (long) result[3]))
                .collect(Collectors.toList());
    }

    public static BigDecimal getEmployeeSumUnpaidTaxes(long employeeId) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Object[]> cr = cb.createQuery(Object[].class);

            Root<Tax> root = cr.from(Tax.class);
            Join<Tax, Apartment> taxApartmentJoin = root.join("apartment");
            Join<Apartment, Building> apartmentBuildingJoin = taxApartmentJoin.join("building");
            Join<Building, EmployeeInCompany> buildingEmployeeInCompanyJoin = apartmentBuildingJoin.join("employeeInCompany");
            Join<EmployeeInCompany, Employee> employeeInCompanyEmployeeJoin = buildingEmployeeInCompanyJoin.join("employee");

            cr.multiselect(
                    cb.sum(root.get("amount")),
                    employeeInCompanyEmployeeJoin.get("id")
            );
            Predicate wherePredicate = cb.conjunction();
            wherePredicate = cb.and(wherePredicate, cb.isNull(root.get("dateOfPayment")));
            wherePredicate = cb.and(wherePredicate, cb.equal(employeeInCompanyEmployeeJoin.get("id"), employeeId));

            cr.where(wherePredicate);

            return (BigDecimal) session.createQuery(cr).getSingleResult()[0];
        }

    }

    public static List<TaxDto> getEmployeeUnpaidTaxes(long employeeId) {
        List<Object[]> results;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Object[]> cr = cb.createQuery(Object[].class);

            Root<Tax> root = cr.from(Tax.class);
            Join<Tax, Apartment> taxApartmentJoin = root.join("apartment");
            Join<Apartment, Building> apartmentBuildingJoin = taxApartmentJoin.join("building");
            Join<Building, EmployeeInCompany> buildingEmployeeInCompanyJoin = apartmentBuildingJoin.join("employeeInCompany");
            Join<EmployeeInCompany, Employee> employeeInCompanyEmployeeJoin = buildingEmployeeInCompanyJoin.join("employee");

            cr.multiselect(
                    root.get("id"),
                    root.get("dateOfIssue"),
                    root.get("amount"),
                    taxApartmentJoin.get("id"),
                    employeeInCompanyEmployeeJoin.get("id")
            );

            Predicate wherePredicate = cb.conjunction();
            wherePredicate = cb.and(wherePredicate, cb.isNull(root.get("dateOfPayment")));
            wherePredicate = cb.and(wherePredicate, cb.equal(employeeInCompanyEmployeeJoin.get("id"), employeeId));

            cr.where(wherePredicate);

            results = session.createQuery(cr).getResultList();

        }
        return results.stream()
                .map(result -> new TaxDto(
                        (long) result[0],
                        (LocalDate) result[1],
                        (BigDecimal) result[2],
                        (LocalDate) null,
                        (Apartment) ApartmentDao.getApartmentById((long)result[3])
                ))
                .collect(Collectors.toList());

    }

    public static BigDecimal getEmployeeSumPaidTaxes(long employeeId) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Object[]> cr = cb.createQuery(Object[].class);

            Root<Tax> root = cr.from(Tax.class);
            Join<Tax, Apartment> taxApartmentJoin = root.join("apartment");
            Join<Apartment, Building> apartmentBuildingJoin = taxApartmentJoin.join("building");
            Join<Building, EmployeeInCompany> buildingEmployeeInCompanyJoin = apartmentBuildingJoin.join("employeeInCompany");
            Join<EmployeeInCompany, Employee> employeeInCompanyEmployeeJoin = buildingEmployeeInCompanyJoin.join("employee");

            cr.multiselect(
                    cb.sum(root.get("amount")),
                    employeeInCompanyEmployeeJoin.get("id")
            );
            Predicate wherePredicate = cb.conjunction();
            wherePredicate = cb.and(wherePredicate, cb.isNotNull(root.get("dateOfPayment")));
            wherePredicate = cb.and(wherePredicate, cb.equal(employeeInCompanyEmployeeJoin.get("id"), employeeId));

            cr.where(wherePredicate);

            return (BigDecimal) session.createQuery(cr).getSingleResult()[0];
        }

    }

    public static List<TaxDto> getEmployeePaidTaxes(long employeeId) {
        List<Object[]> results;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Object[]> cr = cb.createQuery(Object[].class);

            Root<Tax> root = cr.from(Tax.class);
            Join<Tax, Apartment> taxApartmentJoin = root.join("apartment");
            Join<Apartment, Building> apartmentBuildingJoin = taxApartmentJoin.join("building");
            Join<Building, EmployeeInCompany> buildingEmployeeInCompanyJoin = apartmentBuildingJoin.join("employeeInCompany");
            Join<EmployeeInCompany, Employee> employeeInCompanyEmployeeJoin = buildingEmployeeInCompanyJoin.join("employee");

            cr.multiselect(
                    root.get("id"),
                    root.get("dateOfIssue"),
                    root.get("amount"),
                    taxApartmentJoin.get("id"),
                    employeeInCompanyEmployeeJoin.get("id")
            );

            Predicate wherePredicate = cb.conjunction();
            wherePredicate = cb.and(wherePredicate, cb.isNotNull(root.get("dateOfPayment")));
            wherePredicate = cb.and(wherePredicate, cb.equal(employeeInCompanyEmployeeJoin.get("id"), employeeId));

            cr.where(wherePredicate);

            results = session.createQuery(cr).getResultList();

        }
        return results.stream()
                .map(result -> new TaxDto(
                        (long) result[0],
                        (LocalDate) result[1],
                        (BigDecimal) result[2],
                        (LocalDate) null,
                        (Apartment) ApartmentDao.getApartmentById((long)result[3])
                ))
                .collect(Collectors.toList());

    }
}
