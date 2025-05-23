package org.example.dao;

import jakarta.validation.Valid;
import org.example.configuration.SessionFactoryUtil;
import org.example.entity.BaseTaxes;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class BaseTaxesDao {
    public static void createBaseTaxes(@Valid BaseTaxes baseTaxes) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(baseTaxes);
            transaction.commit();
        }
    }

    public static void updateBaseTaxes(@Valid BaseTaxes baseTaxes) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(baseTaxes);
            transaction.commit();
        }
    }

    public static void deleteBaseTaxes(@Valid BaseTaxes baseTaxes) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(baseTaxes);
            transaction.commit();
        }
    }

    public static BaseTaxes getBaseTaxesById(long id) {
        BaseTaxes baseTaxes;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            baseTaxes = session.get(BaseTaxes.class, id);
            transaction.commit();
        }
        return baseTaxes;
    }
}
