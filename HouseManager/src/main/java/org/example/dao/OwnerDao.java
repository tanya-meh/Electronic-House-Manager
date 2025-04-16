package org.example.dao;

import jakarta.validation.Valid;
import org.example.configuration.SessionFactoryUtil;
import org.example.entity.Apartment;
import org.example.entity.Building;
import org.example.entity.Owner;
import org.example.entity.Person;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Set;

public class OwnerDao {
    public static void createOwner(@Valid Owner owner) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(owner);
            transaction.commit();
        }
    }

    public static void updateOwner(@Valid Owner owner) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(owner);
            transaction.commit();
        }
    }

    public static void deleteOwner(@Valid Owner owner) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(owner);
            transaction.commit();
        }
    }

    public static Owner getOwnerById(long id) {
        Owner owner;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            owner = session.get(Owner.class, id);
            transaction.commit();
        }
        return owner;
    }

    public static Set<Apartment> getOwnerApartments(long id) {
        Owner owner;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            owner = session.createQuery("select o from Owner o left join fetch o.apartments where o.id = :id", Owner.class)
                    .setParameter("id", id)
                    .getSingleResult();
            transaction.commit();
        }
        return owner.getApartments();
    }
}
