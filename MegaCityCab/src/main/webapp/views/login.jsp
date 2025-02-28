<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Login</title>
</head>
<link rel="stylesheet" href="../css/login.css">
<body>
<form action="/MegaCityCab/Loginservlet" method="post">
    <div class="login-container">
        <h2>Login</h2>
            <div class="input-group">
                <label for="username">Username</label>
                <input type="text" id="username" name="username" required>
            </div>
            <div class="input-group">
                <label for="password">Password</label>
                <input type="password" id="password" name="password" required>
            </div>
            <button type="submit" class="login-btn">Login</button>
            
            <% if (request.getParameter("error") != null) { %>
            <p style="color:red;">${param.error}</p>
        <% } %>
        <a href="register.jsp" class="register-link">Don't have an account? Register</a>
    </div>
    </form>
</body>
</html>