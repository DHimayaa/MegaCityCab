<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.DBConnection" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="../css/admin_dashboard.css">
</head>
<body>
    <h1>Admin Dashboard</h1>
    <p> Welcome Admin!!! </p>
    <div class="dashboard-container">
        <div class="box">
            <h2>User Management</h2>
            <p>Manage and oversee user accounts, ensuring proper access and permissions</p>
            <a href="manageUsers.jsp">
            <button class="btn">Manage Users</button> </a>
        </div>
        
        <div class="box">
            <h2>Cars Management</h2>
            <p>Here you can add new cars and view existing ones.</p>
            <a href="manageCars.jsp">
            <button class="btn">Manage Cars</button> </a>
            <a href="view_cars.jsp">
            <button class="btn">View Cars</button> </a>
        </div>
        
         <div class="box">
            <h2>Drivers Management</h2>
            <p>Here you can add new drivers and view existing ones.</p>
             <a href="manageDrivers.jsp">
            <button class="btn">Manage Drivers</button> </a>
            <a href="view_drivers.jsp">
            <button class="btn">View Drivers</button> </a>
        </div>
        
          <div class="box">
                <h3>View Bookings</h3>
                <p>Here you can view bookings.</p>
                <a href="viewbookings.jsp">
            <button class="btn">Go to Bookings</button> </a>
          </div>
    </div>
    
    <a href="/MegaCityCab/LogoutServlet">
    <button class="logout-btn">Logout</button> </a>
</body>
</html>

