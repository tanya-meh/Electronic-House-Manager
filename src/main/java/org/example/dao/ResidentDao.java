package org.example.dao;

import jakarta.persistence.criteria.*;
import jakarta.validation.Valid;
import org.example.configuration.SessionFactoryUtil;
import org.example.dto.EmployeeNameNumBuildingsDto;
import org.example.dto.ResidentNameAgeBuildingDto;
import org.example.entity.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ResidentDao {
    public static void createResident(@Valid Resident resident) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(resident);
            transaction.commit();
        }
    }

    public static void updateResident(@Valid Resident resident) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(resident);
            transaction.commit();
        }
    }

    public static void deleteResident(@Valid Resident resident) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(resident);
            transaction.commit();
        }
    }

    public static Resident getResidentById(long id) {
        Resident resident;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            resident = session.get(Resident.class, id);
            transaction.commit();
        }
        return resident;
    }

    public static Set<Apartment> getResidentApartments(long id) {
        Resident resident;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            resident = session.createQuery("select r from Resident r left join fetch r.apartments where r.id = :id", Resident.class)
                    .setParameter("id", id)
                    .getSingleResult();
            transaction.commit();
        }
        return resident.getApartments();
    }

    public static List<ResidentNameAgeBuildingDto> filterResidentsInBuildingByNameAndAge(long buildingId, String name, int minAge, int maxAge) {
        List<Object[]> results;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Object[]> cr = cb.createQuery(Object[].class);

            Root<Resident> root = cr.from(Resident.class);
            Join<Resident, Apartment> residentApartmentJoin = root.join("apartments");
            Join<Apartment, Building> apartmentBuildingJoin = residentApartmentJoin.join("building");

            cr.multiselect(
                    root.get("id"),
                    root.get("name"),
                    root.get("age"),
                    apartmentBuildingJoin.get("id")
            );

            Predicate wherePredicate = cb.conjunction();

            wherePredicate = cb.and(wherePredicate, cb.equal(apartmentBuildingJoin.get("id"), buildingId));
            wherePredicate = cb.and(wherePredicate, cb.greaterThanOrEqualTo(root.get("age"), minAge));
            wherePredicate = cb.and(wherePredicate, cb.lessThanOrEqualTo(root.get("age"), maxAge));
            wherePredicate = cb.and(wherePredicate, cb.like(root.get("name"), "%" + name + "%"));

            cr.where(wherePredicate);

            results = session.createQuery(cr).getResultList();
        }

        return results.stream()
                .map(result -> new ResidentNameAgeBuildingDto(
                        (long) result[0],
                        (String) result[1],
                        (int) result[2],
                        (long) result[3]))
                .collect(Collectors.toList());
    }

    public static List<ResidentNameAgeBuildingDto> sortResidentsInBuildingByNameAndAge(long buildingId, boolean nameAsc, boolean ageAsc) {
        List<Object[]> results;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Object[]> cr = cb.createQuery(Object[].class);

            Root<Resident> root = cr.from(Resident.class);
            Join<Resident, Apartment> residentApartmentJoin = root.join("apartments");
            Join<Apartment, Building> apartmentBuildingJoin = residentApartmentJoin.join("building");

            cr.multiselect(
                    root.get("id"),
                    root.get("name"),
                    root.get("age"),
                    apartmentBuildingJoin.get("id")
            );

            cr.where(cb.equal(apartmentBuildingJoin.get("id"), buildingId));
            cr.orderBy((ageAsc ? cb.asc(root.get("age")) : cb.desc(root.get("age"))), (nameAsc ? cb.asc(root.get("name")) : cb.desc(root.get("name"))));

            results = session.createQuery(cr).getResultList();
        }

        return results.stream()
                .map(result -> new ResidentNameAgeBuildingDto(
                        (long) result[0],
                        (String) result[1],
                        (int) result[2],
                        (long) result[3]))
                .collect(Collectors.toList());
    }
}
