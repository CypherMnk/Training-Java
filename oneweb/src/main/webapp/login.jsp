<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          rel="stylesheet">
</head>

<body class="bg-light">

    <div class="container">
        <div class="row justify-content-center mt-5">
            <div class="col-md-5 col-lg-4">
                <div class="card shadow-sm">
                    <div class="card-body p-4">

        <h2 class="text-center mb-4">Login</h2>

        <% 
            String error = request.getParameter("error");

            if ("invalid".equals(error)) {
        %>
            <div class="alert alert-danger text-center">
                Invalid username or password
            </div>
        <%
            }
        %>

        <form action="login" method="post">

            <label for="username" class="form-label">Username</label>
            <input type="text"
                   id="username"
                   name="username"
                   class="form-control mb-3"
                   placeholder="Enter username"
                   required>

            <label for="password" class="form-label">Password</label>
            <input type="password"
                   id="password"
                   name="password"
                   class="form-control mb-3"
                   placeholder="Enter password"
                   required>

            <button type="submit" class="btn btn-primary w-100">Login</button>

        </form>

                    </div>
                </div>
            </div>
        </div>
    </div>

</body>
</html>
