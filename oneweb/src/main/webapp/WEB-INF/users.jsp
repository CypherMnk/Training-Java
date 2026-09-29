<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Manage Users - LMS</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
    <nav class="navbar navbar-dark bg-primary">
        <div class="container-fluid">
            <a class="navbar-brand fw-bold" href="dashboard.jsp">LMS</a>
            <div><span class="text-white me-3">Welcome, <c:out value="${sessionScope.username}"/></span><a href="logout.jsp" class="btn btn-outline-light btn-sm">Logout</a></div>
        </div>
    </nav>

    <main class="container py-4">
        <div class="d-flex justify-content-between align-items-center mb-3">
            <h2 class="mb-0">Manage Users</h2>
            <div>
                <a href="users?action=new" class="btn btn-primary">Add User</a>
                <a href="dashboard.jsp" class="btn btn-secondary">Dashboard</a>
            </div>
        </div>

        <c:if test="${param.message eq 'created'}"><div class="alert alert-success">User added successfully.</div></c:if>
        <c:if test="${param.message eq 'updated'}"><div class="alert alert-success">User updated successfully.</div></c:if>
        <c:if test="${param.message eq 'deleted'}"><div class="alert alert-success">User deleted successfully.</div></c:if>
        <c:if test="${param.error eq 'notfound'}"><div class="alert alert-warning">User was not found.</div></c:if>
        <c:if test="${param.error eq 'invalid'}"><div class="alert alert-danger">Invalid user request.</div></c:if>

        <div class="card shadow-sm">
            <div class="table-responsive">
                <table class="table table-striped align-middle mb-0">
                    <thead class="table-dark">
                        <tr><th>ID</th><th>Name</th><th>Role</th><th>Username</th><th>Email</th><th>Mobile</th><th>Actions</th></tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${users}" var="user">
                            <tr>
                                <td><c:out value="${user.userId}"/></td>
                                <td><c:out value="${user.name}"/></td>
                                <td><c:out value="${user.role}"/></td>
                                <td><c:out value="${user.username}"/></td>
                                <td><c:out value="${user.email}"/></td>
                                <td><c:out value="${user.mobile}"/></td>
                                <td class="text-nowrap">
                                    <a href="users?action=edit&id=${user.userId}" class="btn btn-sm btn-warning">Edit</a>
                                    <form action="users" method="post" class="d-inline" onsubmit="return confirm('Delete this user?');">
                                        <input type="hidden" name="action" value="delete">
                                        <input type="hidden" name="id" value="${user.userId}">
                                        <button type="submit" class="btn btn-sm btn-danger">Delete</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                        <c:if test="${empty users}">
                            <tr><td colspan="7" class="text-center text-muted">No users found. Click Add User to create one.</td></tr>
                        </c:if>
                    </tbody>
                </table>
            </div>
        </div>
    </main>
</body>
</html>
