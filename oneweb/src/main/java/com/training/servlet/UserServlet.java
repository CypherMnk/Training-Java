package com.training.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.training.model.User;
import com.training.service.UserService;
import com.training.service.impl.UserServiceImpl;

public class UserServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private UserService userService;

    @Override
    public void init() {
        userService = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!isLoggedIn(request, response)) {
            return;
        }

        try {
            if ("new".equals(request.getParameter("action"))) {
                showForm(request, response, new User(), false);
            } else if ("edit".equals(request.getParameter("action"))) {
                User user = userService.getUserById(Integer.parseInt(request.getParameter("id")));
                if (user == null) {
                    response.sendRedirect("users?error=notfound");
                    return;
                }
                showForm(request, response, user, true);
            } else {
                request.setAttribute("users", userService.getAllUsers());
                forward(request, response, "/WEB-INF/users.jsp");
            }
        } catch (NumberFormatException exception) {
            response.sendRedirect("users?error=invalid");
        } catch (ClassNotFoundException | SQLException exception) {
            throw new ServletException("Unable to load users.", exception);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!isLoggedIn(request, response)) {
            return;
        }

        String action = request.getParameter("action");
        try {
            if ("delete".equals(action)) {
                User user = new User();
                user.setUserId(Integer.parseInt(request.getParameter("id")));
                userService.delete(user);
                response.sendRedirect("users?message=deleted");
                return;
            }

            User user = userFromRequest(request);
            if ("update".equals(action)) {
                user.setUserId(Integer.parseInt(request.getParameter("id")));
                if (user.getPassword().isEmpty()) {
                    User existingUser = userService.getUserById(user.getUserId());
                    user.setPassword(existingUser.getPassword());
                }
                userService.update(user);
                response.sendRedirect("users?message=updated");
            } else {
                userService.save(user);
                response.sendRedirect("users?message=created");
            }
        } catch (NumberFormatException exception) {
            response.sendRedirect("users?error=invalid");
        } catch (ClassNotFoundException | SQLException exception) {
            request.setAttribute("error", "Operation failed. Username and email must be unique.");
            User user = userFromRequest(request);
            if ("update".equals(action) && request.getParameter("id") != null) {
                user.setUserId(Integer.parseInt(request.getParameter("id")));
            }
            showForm(request, response, user, "update".equals(action));
        }
    }

    private User userFromRequest(HttpServletRequest request) {
        User user = new User();
        user.setName(request.getParameter("name").trim());
        user.setRole(request.getParameter("role").trim());
        user.setUsername(request.getParameter("username").trim());
        user.setPassword(request.getParameter("password"));
        user.setEmail(request.getParameter("email").trim());
        user.setMobile(request.getParameter("mobile").trim());
        return user;
    }

    private boolean isLoggedIn(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("login.jsp");
            return false;
        }
        return true;
    }

    private void showForm(HttpServletRequest request, HttpServletResponse response, User user,
            boolean edit) throws ServletException, IOException {
        request.setAttribute("user", user);
        request.setAttribute("edit", edit);
        forward(request, response, "/WEB-INF/user-form.jsp");
    }

    private void forward(HttpServletRequest request, HttpServletResponse response, String page)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }
}
