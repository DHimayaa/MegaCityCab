<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="../css/login.css">
</head>
<body>
    <form action="/MegaCityCab/Loginservlet" method="post">
        <h2>Login</h2>
        <label>Username:</label>
        <input type="text" name="username" required>
        
        <label>Password:</label>
        <input type="password" name="password" required>
        
        <button type="submit">Login</button>
        
        <% if (request.getParameter("error") != null) { %>
            <p style="color:red;">${param.error}</p>
        <% } %>
        <a href="register.jsp">Don't have an account? Register here</a>
    </form>
    
</body>
</html>
