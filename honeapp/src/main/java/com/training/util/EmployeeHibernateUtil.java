package com.training.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public final class EmployeeHibernateUtil {

    private static final SessionFactory SESSION_FACTORY = new Configuration()
            .configure("hibernate-employee.cfg.xml")
            .buildSessionFactory();

    private EmployeeHibernateUtil() {
    }

    public static SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }

    public static void shutdown() {
        SESSION_FACTORY.close();
    }
}
