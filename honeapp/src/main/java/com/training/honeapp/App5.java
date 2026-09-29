package com.training.honeapp;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.training.model.DUser;

public class App5 {

    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();

        Query<DUser> query = session.createQuery("from DUser", DUser.class);
        List<DUser> list = query.list();

        list.forEach(user -> System.out.println(
                user.getId() + " | " + user.getUsername() + " | "
                + user.getFullName() + " | " + user.getEmail() + " | " + user.getRole()));

        session.close();
        sessionFactory.close();
    }
}
