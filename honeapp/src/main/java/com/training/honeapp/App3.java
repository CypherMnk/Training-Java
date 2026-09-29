package com.training.honeapp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.training.model.DUser;

public class App3 {

    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        DUser user = session.get(DUser.class, 2);

        if (user != null) {
            user.setFullName("Darshan B.M");
            transaction.commit();
            System.out.println("User updated successfully.");
        } else {
            transaction.rollback();
            System.out.println("User with ID 2 was not found.");
        }

        session.close();
        sessionFactory.close();
    }
}
