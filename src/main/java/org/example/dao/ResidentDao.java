package org.example.dao;

import org.example.configuration.SessionFactoryUtil;
import org.example.entity.Apartment;
import org.example.entity.Owner;
import org.example.entity.Resident;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Set;

public class ResidentDao {
    public static void createResident(Resident resident) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(resident);
            transaction.commit();
        }
    }

    public static void updateResident(Resident resident) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(resident);
            transaction.commit();
        }
    }

    public static void deleteResident(Resident resident) {
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
            resident = session.createQuery("select r from Resident r join fetch r.apartments where r.id = :id", Resident.class)
                    .setParameter("id", id)
                    .getSingleResult();
            transaction.commit();
        }
        return resident.getApartments();
    }
}
