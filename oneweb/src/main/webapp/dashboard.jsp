<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.training.service.UserService" %>
<%@ page import="com.training.service.impl.UserServiceImpl" %>
<%
    String username = (String) session.getAttribute("username");
    if (username == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    int totalUsers = 0;
    try {
        UserService userService = new UserServiceImpl();
        totalUsers = userService.getAllUsers().size();
    } catch (Exception e) {
        request.setAttribute("userLoadError", "Unable to load user count.");
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Dashboard - LMS</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        html, body { height: 100%; }
        .wrapper { min-height: 100vh; display: flex; flex-direction: column; }
        .header { min-height: 60px; }
        .main-container { flex: 1; display: flex; }
        .sidebar { width: 240px; background-color: #212529; }
        .sidebar .nav-link { color: #ced4da; padding: 12px 20px; }
        .sidebar .nav-link:hover, .sidebar .nav-link.active { background-color: #0d6efd; color: white; }
        .content { flex: 1; padding: 25px; }
        .footer { min-height: 50px; }
        @media (max-width: 768px) { .sidebar { width: 200px; } .content { padding: 15px; } }
    </style>
</head>
<body class="bg-light">
    <div class="wrapper">
        <nav class="navbar navbar-dark bg-primary header">
            <div class="container-fluid">
                <a class="navbar-brand fw-bold" href="dashboard.jsp">LMS</a>
                <div class="d-flex align-items-center">
                    <span class="text-white me-3">Welcome, <%= username %></span>
                    <a href="logout.jsp" class="btn btn-outline-light btn-sm">Logout</a>
                </div>
            </div>
        </nav>

        <div class="main-container">
            <aside class="sidebar">
                <div class="p-3"><h6 class="text-white text-uppercase mb-0">Administration</h6></div>
                <ul class="nav flex-column">
                    <li class="nav-item"><a class="nav-link active" href="dashboard.jsp">Dashboard</a></li>
                    <li class="nav-item"><a class="nav-link" href="users">Manage Users</a></li>
                    <li class="nav-item"><a class="nav-link" href="#">Courses</a></li>
                    <li class="nav-item"><a class="nav-link" href="#">Students</a></li>
                    <li class="nav-item"><a class="nav-link" href="#">Reports</a></li>
                </ul>
            </aside>

            <main class="content">
                <div class="container-fluid">
                    <div class="mb-4">
                        <h2 class="fw-bold">Dashboard</h2>
                        <p class="text-muted">Welcome to the Learning Management System</p>
                    </div>
                    <div class="row g-4">
                        <div class="col-md-6 col-lg-3"><div class="card shadow-sm border-0"><div class="card-body"><h6 class="text-muted">Total Users</h6><h2 class="fw-bold"><%= totalUsers %></h2><a href="users" class="btn btn-sm btn-primary">Manage Users</a></div></div></div>
                        <div class="col-md-6 col-lg-3"><div class="card shadow-sm border-0"><div class="card-body"><h6 class="text-muted">Students</h6><h2 class="fw-bold">0</h2><a href="#" class="btn btn-sm btn-primary">View Students</a></div></div></div>
                        <div class="col-md-6 col-lg-3"><div class="card shadow-sm border-0"><div class="card-body"><h6 class="text-muted">Courses</h6><h2 class="fw-bold">0</h2><a href="#" class="btn btn-sm btn-primary">View Courses</a></div></div></div>
                        <div class="col-md-6 col-lg-3"><div class="card shadow-sm border-0"><div class="card-body"><h6 class="text-muted">Reports</h6><h2 class="fw-bold">0</h2><a href="#" class="btn btn-sm btn-primary">View Reports</a></div></div></div>
                    </div>
                    <div class="card shadow-sm border-0 mt-4"><div class="card-body"><h4>Welcome to LMS</h4><p class="text-muted mb-0">Use the navigation panel on the left to manage users, students, courses and reports.</p></div></div>
                </div>
            </main>
        </div>

        <footer class="footer bg-dark text-white d-flex align-items-center justify-content-center"><small>&copy; 2026 Learning Management System. All Rights Reserved.</small></footer>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
