package org.example.dao;

import jakarta.persistence.criteria.*;
import jakarta.validation.Valid;
import org.example.configuration.SessionFactoryUtil;
import org.example.dto.CompanyEmployeeBuildingDto;
import org.example.dto.TaxDto;
import org.example.entity.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BuildingDao {
    public static void createBuilding(@Valid Building building) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(building);
            transaction.commit();
        }
    }

    public static void updateBuilding(@Valid Building building) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(building);
            transaction.commit();
        }
    }

    public static void deleteBuilding(@Valid Building building) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(building);
            transaction.commit();
        }
    }

    public static Building getBuildingById(long id) {
        Building building;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            building = session.get(Building.class, id);
            transaction.commit();
        }
        return building;
    }

    public static Set<Apartment> getBuildingApartments(long id) {
        Building building;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            building = session.createQuery("select b from Building b join fetch b.apartments where b.id = :id", Building.class)
                    .setParameter("id", id)
                    .getSingleResult();
            transaction.commit();
        }
        return building.getApartments();
    }

    public static long getNumberOfBuildingApartments(long id) {
        long numOfApartments;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            numOfApartments = session.createQuery("select count(a) from Building b join b.apartments a where b.id = :id", Long.class)
                    .setParameter("id", id)
                    .getSingleResult();
            transaction.commit();
        }
        return numOfApartments;
    }

    public static List<Resident> getBuildingResidents(long buildingId) {
        List<Object[]> results;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Object[]> cr = cb.createQuery(Object[].class);

            Root<Resident> root = cr.from(Resident.class);
            Join<Resident, Apartment> residentApartmentJoin = root.join("apartments");
            Join<Apartment, Building> apartmentBuildingJoin = residentApartmentJoin.join("building");

            cr.multiselect(
                    root.get("id"),
                    apartmentBuildingJoin.get("id")
            );

            cr.where(cb.equal(apartmentBuildingJoin.get("id"), buildingId));

            results = session.createQuery(cr).getResultList();
        }
        return results.stream()
                .map(result -> ResidentDao.getResidentById((long) result[0]))
                .collect(Collectors.toList());
    }

    public static List<Resident> getNumberOfBuildingResidents(long buildingId) {
        List<Object[]> results;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Object[]> cr = cb.createQuery(Object[].class);

            Root<Resident> root = cr.from(Resident.class);
            Join<Resident, Apartment> residentApartmentJoin = root.join("apartments");
            Join<Apartment, Building> apartmentBuildingJoin = residentApartmentJoin.join("building");

            cr.multiselect(
                    cb.count(root.get("id")),
                    apartmentBuildingJoin.get("id")
            );

            cr.where(cb.equal(apartmentBuildingJoin.get("id"), buildingId));

            results = session.createQuery(cr).getResultList();
        }
        return results.stream()
                .map(result -> ResidentDao.getResidentById((long) result[0]))
                .collect(Collectors.toList());
    }

    public static BigDecimal getBuildingSumUnpaidTaxes(long buildingId) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Object[]> cr = cb.createQuery(Object[].class);

            Root<Tax> root = cr.from(Tax.class);
            Join<Tax, Apartment> taxApartmentJoin = root.join("apartment");
            Join<Apartment, Building> apartmentBuildingJoin = taxApartmentJoin.join("building");

            cr.multiselect(
                    cb.sum(root.get("amount")),
                    apartmentBuildingJoin.get("id")
            );
            Predicate wherePredicate = cb.conjunction();
            wherePredicate = cb.and(wherePredicate, cb.isNull(root.get("dateOfPayment")));
            wherePredicate = cb.and(wherePredicate, cb.equal(apartmentBuildingJoin.get("id"), buildingId));

            cr.where(wherePredicate);

            return (BigDecimal) session.createQuery(cr).getSingleResult()[0];
        }

    }

    public static List<TaxDto> getBuildingUnpaidTaxes(long buildingId) {
        List<Object[]> results;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Object[]> cr = cb.createQuery(Object[].class);

            Root<Tax> root = cr.from(Tax.class);
            Join<Tax, Apartment> taxApartmentJoin = root.join("apartment");
            Join<Apartment, Building> apartmentBuildingJoin = taxApartmentJoin.join("building");

            cr.multiselect(
                    root.get("id"),
                    root.get("dateOfIssue"),
                    root.get("amount"),
                    taxApartmentJoin.get("id"),
                    apartmentBuildingJoin.get("id")
            );

            Predicate wherePredicate = cb.conjunction();
            wherePredicate = cb.and(wherePredicate, cb.isNull(root.get("dateOfPayment")));
            wherePredicate = cb.and(wherePredicate, cb.equal(apartmentBuildingJoin.get("id"), buildingId));

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

    public static BigDecimal getBuildingSumPaidTaxes(long buildingId) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Object[]> cr = cb.createQuery(Object[].class);

            Root<Tax> root = cr.from(Tax.class);
            Join<Tax, Apartment> taxApartmentJoin = root.join("apartment");
            Join<Apartment, Building> apartmentBuildingJoin = taxApartmentJoin.join("building");

            cr.multiselect(
                    cb.sum(root.get("amount")),
                    apartmentBuildingJoin.get("id")
            );
            Predicate wherePredicate = cb.conjunction();
            wherePredicate = cb.and(wherePredicate, cb.isNotNull(root.get("dateOfPayment")));
            wherePredicate = cb.and(wherePredicate, cb.equal(apartmentBuildingJoin.get("id"), buildingId));

            cr.where(wherePredicate);

            return (BigDecimal) session.createQuery(cr).getSingleResult()[0];
        }

    }

    public static List<TaxDto> getBuildingPaidTaxes(long buildingId) {
        List<Object[]> results;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Object[]> cr = cb.createQuery(Object[].class);

            Root<Tax> root = cr.from(Tax.class);
            Join<Tax, Apartment> taxApartmentJoin = root.join("apartment");
            Join<Apartment, Building> apartmentBuildingJoin = taxApartmentJoin.join("building");

            cr.multiselect(
                    root.get("id"),
                    root.get("dateOfIssue"),
                    root.get("amount"),
                    taxApartmentJoin.get("id"),
                    apartmentBuildingJoin.get("id")
            );

            Predicate wherePredicate = cb.conjunction();
            wherePredicate = cb.and(wherePredicate, cb.isNotNull(root.get("dateOfPayment")));
            wherePredicate = cb.and(wherePredicate, cb.equal(apartmentBuildingJoin.get("id"), buildingId));

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
