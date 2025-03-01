<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Register</title>
    <link rel="stylesheet" href="../css/register.css">
</head>
<body>
    <div class="register-container">
        <h2>Register</h2>
        <form action="/MegaCityCab/RegisterServlet" method="post">
            
            <div class="input-group">
                <label for="username">Username</label>
                <input type="text" id="username" name="username" required>
            </div>
            <div class="input-group">
                <label for="password">Password</label>
                <input type="password" id="password" name="password" required>
            </div>
            <div class="input-group">
                <label for="email">Email</label>
                <input type="email" id="email" name="email" required>
            </div>
            <div class="input-group">
                <label for="phone">Contact</label>
                <input type="text" id="phone" name="phone" required>
            </div>
            <div class="input-group">
                <label for="name">Full Name</label>
                <input type="text" id="name" name="name" required>
            </div>
            <div class="input-group">
                <label for="address">Address</label>
                <input type="text" id="address" name="address" required>
            </div>
            <div class="input-group">
                <label for="nic">NIC No</label>
                <input type="text" id="nic" name="nic" required>
            </div>
            
            <button type="submit" class="register-btn">Register</button>
        </form>
        <a href="login.jsp" class="login-link">Already have an account? Login</a>
    </div>
</body>
</html>