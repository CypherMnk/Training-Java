package com.training.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.training.model.DUser;
import com.training.util.HibernateUtil;

public class UserDAOImpl implements UserDAO {

    @Override
    public void save(DUser user) {
        execute(session -> session.save(user));
    }

    @Override
    public DUser getById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(DUser.class, id);
        }
    }

    @Override
    public List<DUser> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from DUser", DUser.class).list();
        }
    }

    @Override
    public void update(DUser user) {
        execute(session -> session.update(user));
    }

    @Override
    public void delete(int id) {
        execute(session -> {
            DUser user = session.get(DUser.class, id);
            if (user != null) {
                session.delete(user);
            }
        });
    }

    private void execute(java.util.function.Consumer<Session> action) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                action.accept(session);
                transaction.commit();
            } catch (RuntimeException e) {
                transaction.rollback();
                throw e;
            }
        }
    }
}
