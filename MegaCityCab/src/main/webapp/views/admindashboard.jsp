<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="../css/dashboard.css">
</head>
<body>
    <h1>Admin Dashboard</h1>
    <p> Welcome Admin!!! </p>
    <div class="dashboard-container">
        <div class="box">
            <h2>User Management</h2>
            <p>Manage and oversee user accounts, ensuring proper access and permissions</p>
            <a href="manageUsers.jsp">
            <button class="btn">Go to User Management</button> </a>
        </div>
        <div class="box">
            <h2>System Configuration</h2>
            <p>Modify system settings and configurations for optimal performance</p>
            <a href="systemSettings.jsp">
            <button class="btn">Go to System Settings</button> </a>
        </div>
    </div>
    
    <a href="/MegaCityCab/LogoutServlet">
    <button class="logout-btn">Logout</button> </a>
</body>
</html>
