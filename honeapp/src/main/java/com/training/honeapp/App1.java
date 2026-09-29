package com.training.honeapp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.training.model.DUser;

public class App1 {

    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .configure()
                .buildSessionFactory();

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        DUser user = new DUser();
        user.setUsername("darshan");
        user.setPassword("darshan123");
        user.setFullName("Darshan K");
        user.setEmail("darshan@dover.com");
        user.setRole("EMPLOYEE");

        session.save(user);
        transaction.commit();

        session.close();
        sessionFactory.close();

        System.out.println("User saved successfully.");
    }
}
