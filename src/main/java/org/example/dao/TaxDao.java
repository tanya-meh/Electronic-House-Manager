package org.example.dao;

import jakarta.persistence.criteria.*;
import jakarta.validation.Valid;
import org.example.configuration.SessionFactoryUtil;
import org.example.dto.PayedTaxFullInformationDto;
import org.example.dto.TaxDto;
import org.example.entity.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class TaxDao {
    public static void createTax(@Valid Tax tax) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(tax);
            transaction.commit();
        }
    }

    public static void updateTax(@Valid Tax tax) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(tax);
            transaction.commit();
        }
    }

    public static void deleteTax(@Valid Tax tax) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(tax);
            transaction.commit();
        }
    }

    public static Tax getTaxById(long id) {
        Tax tax;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            tax = session.get(Tax.class, id);
            transaction.commit();
        }
        return tax;
    }

    public static PayedTaxFullInformationDto getTaxFullInformation(long taxId) {
        Object[] result;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Object[]> cr = cb.createQuery(Object[].class);

            Root<Tax> root = cr.from(Tax.class);
            Join<Tax, Apartment> taxApartmentJoin = root.join("apartment");
            Join<Apartment, Building> apartmentBuildingJoin = taxApartmentJoin.join("building");
            Join<Building, EmployeeInCompany> buildingEmployeeInCompanyJoin = apartmentBuildingJoin.join("employeeInCompany");
            Join<EmployeeInCompany, Company> employeeInCompanyCompanyJoin = buildingEmployeeInCompanyJoin.join("company");
            Join<EmployeeInCompany, Employee> employeeInCompanyEmployeeJoin = buildingEmployeeInCompanyJoin.join("employee");

            cr.multiselect(
                    employeeInCompanyCompanyJoin.get("id"),
                    employeeInCompanyCompanyJoin.get("name"),
                    employeeInCompanyEmployeeJoin.get("id"),
                    employeeInCompanyEmployeeJoin.get("name"),
                    apartmentBuildingJoin.get("id"),
                    taxApartmentJoin.get("id"),
                    root.get("id"),
                    root.get("amount"),
                    root.get("dateOfPayment")
            );

            cr.where(cb.equal(root.get("id"), taxId));

            result = session.createQuery(cr).getSingleResult();

        }
        return new PayedTaxFullInformationDto(
                (long) result[0],
                (String) result[1],
                (long) result[2],
                (String) result[3],
                (long) result[4],
                (long) result[5],
                (long) result[6],
                (BigDecimal) result[7],
                (LocalDate) result[8]
                );
    }
}
