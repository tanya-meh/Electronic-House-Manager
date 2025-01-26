package org.example.dao;

import org.example.configuration.SessionFactoryUtil;
import org.example.entity.Tax;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TaxDao {
    public static void createTax(Tax tax) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(tax);
            transaction.commit();
        }
    }

    public static void updateTax(Tax tax) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(tax);
            transaction.commit();
        }
    }

    public static void deleteTax(Tax tax) {
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
}
