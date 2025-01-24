package org.example.configuration;

import org.example.dao.EmployeeInCompanyDao;
import org.example.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class SessionFactoryUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if(sessionFactory == null) {
            Configuration configuration = new Configuration();
            configuration.addAnnotatedClass(Apartment.class);
            configuration.addAnnotatedClass(BaseTaxes.class);
            configuration.addAnnotatedClass(Building.class);
            configuration.addAnnotatedClass(Company.class);
            configuration.addAnnotatedClass(Employee.class);
            configuration.addAnnotatedClass(EmployeeInCompany.class);
            configuration.addAnnotatedClass(Owner.class);
            configuration.addAnnotatedClass(PaidTax.class);
            configuration.addAnnotatedClass(Person.class);
            configuration.addAnnotatedClass(Resident.class);
            configuration.addAnnotatedClass(Tax.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }
        return sessionFactory;
    }
}
