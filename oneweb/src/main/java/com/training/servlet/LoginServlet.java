package com.training.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.training.dao.UserDao;
import com.training.dao.impl.UserDaoImpl;
import com.training.service.UserService;
import com.training.service.impl.UserServiceImpl;

public class LoginServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init() {
        userService = new UserServiceImpl();

        try {
            UserDao userDao = new UserDaoImpl();
            userDao.createTable();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Unable to create users table.", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            if (userService.isValidUser(username, password) != null) {
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                response.sendRedirect("dashboard.jsp");
            } else {
                response.sendRedirect("login.jsp?error=invalid");
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new IOException("Login database operation failed.", e);
        }
    }
}
