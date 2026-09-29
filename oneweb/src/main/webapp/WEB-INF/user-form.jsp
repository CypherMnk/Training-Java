<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>${edit ? 'Edit User' : 'Add User'} - LMS</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
    <nav class="navbar navbar-dark bg-primary"><div class="container-fluid"><a class="navbar-brand fw-bold" href="dashboard.jsp">LMS</a></div></nav>
    <main class="container py-4" style="max-width: 720px;">
        <div class="card shadow-sm">
            <div class="card-body p-4">
                <h2 class="mb-4">${edit ? 'Edit User' : 'Add User'}</h2>
                <c:if test="${not empty error}"><div class="alert alert-danger"><c:out value="${error}"/></div></c:if>
                <form action="users" method="post">
                    <input type="hidden" name="action" value="${edit ? 'update' : 'create'}">
                    <c:if test="${edit}"><input type="hidden" name="id" value="${user.userId}"></c:if>
                    <div class="row g-3">
                        <div class="col-md-6"><label class="form-label">Name</label><input class="form-control" name="name" value="<c:out value='${user.name}'/>" required></div>
                        <div class="col-md-6"><label class="form-label">Role</label><input class="form-control" name="role" value="<c:out value='${user.role}'/>" placeholder="ADMIN or STUDENT" required></div>
                        <div class="col-md-6"><label class="form-label">Username</label><input class="form-control" name="username" value="<c:out value='${user.username}'/>" required></div>
                        <div class="col-md-6"><label class="form-label">Password</label><input type="password" class="form-control" name="password" placeholder="${edit ? 'Leave blank to keep current password' : 'Enter password'}" <c:if test="${not edit}">required</c:if>></div>
                        <div class="col-md-6"><label class="form-label">Email</label><input type="email" class="form-control" name="email" value="<c:out value='${user.email}'/>" required></div>
                        <div class="col-md-6"><label class="form-label">Mobile</label><input class="form-control" name="mobile" value="<c:out value='${user.mobile}'/>" required></div>
                    </div>
                    <div class="mt-4"><button class="btn btn-primary">Save User</button><a href="users" class="btn btn-secondary">Cancel</a></div>
                </form>
            </div>
        </div>
    </main>
</body>
</html>
