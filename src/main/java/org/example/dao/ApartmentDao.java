package org.example.dao;

import org.example.configuration.SessionFactoryUtil;
import org.example.entity.Apartment;
import org.example.entity.Owner;
import org.example.entity.Resident;
import org.example.entity.Tax;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
            apartment = session.createQuery("select a from Apartment a join fetch a.owners where a.id = :id", Apartment.class)
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
            apartment = session.createQuery("select a from Apartment a join fetch a.residents where a.id = :id", Apartment.class)
                    .setParameter("id", id)
                    .getSingleResult();
            transaction.commit();
        }
        return apartment.getResidents();
    }
}
