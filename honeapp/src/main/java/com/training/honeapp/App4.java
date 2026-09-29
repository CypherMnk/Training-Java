package com.training.honeapp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.training.model.DUser;

public class App4 {

    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        DUser user = session.get(DUser.class, 3);

        if (user != null) {
            session.delete(user);
            transaction.commit();
            System.out.println("User deleted successfully.");
        } else {
            transaction.rollback();
            System.out.println("User with ID 3 was not found.");
        }

        session.close();
        sessionFactory.close();
    }
}
