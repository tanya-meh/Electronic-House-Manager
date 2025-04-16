package org.example.dao;

import jakarta.persistence.PersistenceException;
import jakarta.persistence.criteria.*;
import jakarta.validation.Valid;
import org.example.configuration.SessionFactoryUtil;
import org.example.entity.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Set;

public class ApartmentDao {
    public static void createApartment(@Valid Apartment apartment) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(apartment);
            transaction.commit();
        }
    }

    public static void updateApartment(@Valid Apartment apartment) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(apartment);
            transaction.commit();
        }
    }

    public static void deleteApartment(@Valid Apartment apartment) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(apartment);
            transaction.commit();
        }
    }

    public static Apartment getApartmentById(long id) {
        Apartment apartment;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            apartment = session.get(Apartment.class, id);
            transaction.commit();
        }
        return apartment;
    }

    public static Set<Tax> getApartmentTaxes(long id) {
        Apartment apartment;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            apartment = session.createQuery("select a from Apartment a join fetch a.taxes where a.id = :id", Apartment.class)
                    .setParameter("id", id)
                    .getSingleResult();
            transaction.commit();
        }
        return apartment.getTaxes();
    }

    public static Set<Owner> getApartmentOwners(long id) {
        Apartment apartment;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            apartment = session.createQuery("select a from Apartment a left join fetch a.owners where a.id = :id", Apartment.class)
                    .setParameter("id", id)
                    .getSingleResult();
            transaction.commit();
        }
        return apartment.getOwners();
    }

    public static Set<Resident> getApartmentResidents(long id) {
        Apartment apartment;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            apartment = session.createQuery("select a from Apartment a left join fetch a.residents where a.id = :id", Apartment.class)
                    .setParameter("id", id)
                    .getSingleResult();
            transaction.commit();
        }
        return apartment.getResidents();
    }


    public static long countResidentsOver7UseLiftInApartment(Long apartmentId) {
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Long> cr = cb.createQuery(Long.class);

            Root<Apartment> root = cr.from(Apartment.class);
            Join<Apartment, Resident> apartmentResidentJoin = root.join("residents");

            Predicate apartmentPredicate = cb.equal(root.get("id"), apartmentId);
            Predicate agePredicate = cb.greaterThan(apartmentResidentJoin.get("age"), 7);
            Predicate usesLiftPredicate = cb.equal(apartmentResidentJoin.get("usesLift"), true);

            Predicate combinedPredicate = cb.and(apartmentPredicate, agePredicate, usesLiftPredicate);
            cr.select(cb.count(apartmentResidentJoin)).where(combinedPredicate);

            return session.createQuery(cr).getSingleResult();
        }
    }

}
