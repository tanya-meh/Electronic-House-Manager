package org.example.dao;

import jakarta.persistence.criteria.*;
import org.example.configuration.SessionFactoryUtil;
import org.example.entity.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Set;

public class ApartmentDao {
    public static void createApartment(Apartment apartment) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(apartment);
            transaction.commit();
        }
    }

    public static void updateApartment(Apartment apartment) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(apartment);
            transaction.commit();
        }
    }

    public static void deleteApartment(Apartment apartment) {
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

//    public static int getNumberOfResidentsOver7UseLiftInApartment(long id) {
//        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
//            CriteriaBuilder cb = session.getCriteriaBuilder();
//            CriteriaQuery<Resident> cr = cb.createQuery(Resident.class);
//            Root<Resident> root = cr.from(Resident.class);
//            Join<Resident, Apartment> a = root.join("buildings", JoinType.LEFT);
//
//            cr.select(root).groupBy(root.get("id")).orderBy(cb.asc(cb.count(building.get("id"))));
//
//            Query<EmployeeInCompany> query = session.createQuery(cr);
//            query.setMaxResults(1);
//            EmployeeInCompany employeeInCompany = query.getSingleResult();
//
//            return employeeInCompany;
//
//        }
//    }

    public static long countResidentsByCriteria(Long apartmentId) {
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Long> cq = cb.createQuery(Long.class);

            // Root for Apartment
            Root<Apartment> apartmentRoot = cq.from(Apartment.class);

            // Join Residents
            Join<Apartment, Resident> residentJoin = apartmentRoot.join("residents");

            // Build the predicates (criteria)
            Predicate apartmentPredicate = cb.equal(apartmentRoot.get("id"), apartmentId);
            Predicate agePredicate = cb.greaterThan(residentJoin.get("age"), 7);
            Predicate usesLiftPredicate = cb.equal(residentJoin.get("usesLift"), true);

            // Combine the predicates
            Predicate combinedPredicate = cb.and(apartmentPredicate, agePredicate, usesLiftPredicate);

            // Select the count
            cq.select(cb.count(residentJoin)).where(combinedPredicate);

            // Execute the query
            return session.createQuery(cq).getSingleResult();
        }
    }

}
