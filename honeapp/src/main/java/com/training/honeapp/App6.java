package com.training.honeapp;

import java.util.List;

import com.training.dao.UserDAO;
import com.training.dao.UserDAOImpl;
import com.training.model.DUser;
import com.training.util.HibernateUtil;

public class App6 {

    public static void main(String[] args) {
        UserDAO userDAO = new UserDAOImpl();

        DUser user = new DUser();
        user.setUsername("ram");
        user.setPassword("1234");
        user.setFullName("Ramakrishna");
        user.setEmail("ram@gmail.com");
        user.setRole("STUDENT");

        userDAO.save(user);
        System.out.println("User saved. Generated ID = " + user.getId());

        DUser dbUser = userDAO.getById(user.getId());
        System.out.println("User found: " + dbUser);

        List<DUser> users = userDAO.getAll();
        System.out.println("\nAll Users:");
        users.forEach(System.out::println);

        dbUser.setFullName("Ramakrishna Achalla");
        userDAO.update(dbUser);
        System.out.println("\nUser updated.");

        // userDAO.delete(dbUser.getId());
        // System.out.println("User deleted.");

        HibernateUtil.shutdown();
    }
}
