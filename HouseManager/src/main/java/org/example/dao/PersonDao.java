package org.example.dao;

import jakarta.validation.Valid;
import org.example.configuration.SessionFactoryUtil;
import org.example.entity.Person;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PersonDao {
    public static void createPerson(@Valid Person Person) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(Person);
            transaction.commit();
        }
    }

    public static void updatePerson(@Valid Person Person) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(Person);
            transaction.commit();
        }
    }

    public static void deletePerson(@Valid Person Person) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(Person);
            transaction.commit();
        }
    }

    public static Person getPersonById(long id) {
        Person Person;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Person = session.get(Person.class, id);
            transaction.commit();
        }
        return Person;
    }
}
