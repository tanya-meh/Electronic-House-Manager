package org.example.dao;

import jakarta.persistence.criteria.*;
import org.example.configuration.SessionFactoryUtil;
import org.example.dto.CompanyIncomeDto;
import org.example.entity.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public class CompanyDao {
    public static void createCompany(Company company) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(company);
            transaction.commit();
        }
    }

    public static void updateCompany(Company company) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(company);
            transaction.commit();
        }
    }

    public static void deleteCompany(Company company) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(company);
            transaction.commit();
        }
    }

    public static Company getCompanyById(long id) {
        Company company;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            company = session.get(Company.class, id);
            transaction.commit();
        }
        return company;
    }

    public static Set<EmployeeInCompany> getCompanyEmployeesInCompany(long id) {
        Company company;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            company = session.createQuery("select c from Company c join fetch c.employeesInCompany where c.id = :id", Company.class)
                    .setParameter("id", id)
                    .getSingleResult();
            transaction.commit();
        }
        return company.getEmployeesInCompany();
    }

    public static EmployeeInCompany getEmployeeInCompanyWithLeastBuildings(long id) {
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<EmployeeInCompany> cr = cb.createQuery(EmployeeInCompany.class);
            Root<EmployeeInCompany> root = cr.from(EmployeeInCompany.class);
            Join<EmployeeInCompany, Building> building = root.join("buildings", JoinType.LEFT);

            cr.select(root).groupBy(root.get("id")).orderBy(cb.asc(cb.count(building.get("id"))));

            Query<EmployeeInCompany> query = session.createQuery(cr);
            query.setMaxResults(1);
            EmployeeInCompany employeeInCompany = query.getSingleResult();

            return employeeInCompany;

        }
    }

    public static List<Object[]> filterCompaniesByIncome(BigDecimal minIncome, BigDecimal maxIncome, boolean ascending) {
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Object[]> cr = cb.createQuery(Object[].class);
            Root<Tax> root = cr.from(Tax.class);
            Join<Tax, Apartment> taxApartmentJoin = root.join("apartment");
            Join<Apartment, Building> apartmentBuildingJoin = taxApartmentJoin.join("building");
            Join<Building, EmployeeInCompany> buildingEmployeeInCompanyJoin = apartmentBuildingJoin.join("employeeInCompany");
            Join<EmployeeInCompany, Company> employeeInCompanyCompanyJoin = buildingEmployeeInCompanyJoin.join("company");

            cr.multiselect(
                    employeeInCompanyCompanyJoin.get("id"),
                    employeeInCompanyCompanyJoin.get("name"),
                    cb.sum(root.get("amount"))
            );

            Predicate payedTaxesCondition = cb.isNotNull(root.get("dateOfPayment"));
            Predicate incomeCondition = cb.between(cb.sum(root.get("amount")), minIncome, maxIncome);

            cr.where(cb.and(payedTaxesCondition, incomeCondition));
            cr.groupBy(employeeInCompanyCompanyJoin.get("id"), employeeInCompanyCompanyJoin.get("name"));
            cr.orderBy(ascending ? cb.asc(cb.sum(root.get("amount"))) : cb.desc(cb.sum(root.get("amount"))));

            List<Object[]> result = session.createQuery(cr).getResultList();
            return result;

        }
    }

}
