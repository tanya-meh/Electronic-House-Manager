package org.example.dao;

import org.example.configuration.SessionFactoryUtil;
import org.example.entity.Apartment;
import org.example.entity.Building;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Set;

public class BuildingDao {
    public static void createBuilding(Building building) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(building);
            transaction.commit();
        }
    }

    public static void updateBuilding(Building building) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(building);
            transaction.commit();
        }
    }

    public static void deleteBuilding(Building building) {
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

}
