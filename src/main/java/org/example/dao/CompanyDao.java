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
import java.util.stream.Collectors;

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


    public static List<CompanyIncomeDto> filterCompaniesByIncome(BigDecimal minIncome, BigDecimal maxIncome, boolean asc) {
        List<Object[]> results;
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

            Predicate havingPredicate = cb.conjunction();
            Predicate payedTaxesCondition = cb.isNotNull(root.get("dateOfPayment"));
            Expression<BigDecimal> incomeExpression = cb.sum(root.get("amount"));

            if(minIncome != null) {
                havingPredicate = cb.and(havingPredicate, cb.greaterThanOrEqualTo(incomeExpression, minIncome));
            }

            if(maxIncome != null) {
                havingPredicate = cb.and(havingPredicate, cb.lessThanOrEqualTo(incomeExpression, maxIncome));
            }

            cr.where(payedTaxesCondition);
            cr.groupBy(employeeInCompanyCompanyJoin.get("id"));
            cr.having(havingPredicate);
            cr.orderBy(asc ? cb.asc(incomeExpression) : cb.desc(incomeExpression));

            results = session.createQuery(cr).getResultList();
        }

        return results.stream()
                .map(result -> new CompanyIncomeDto(
                        (Long) result[0],
                        (String) result[1],
                        (BigDecimal) result[2]))
                .collect(Collectors.toList());
    }



}
