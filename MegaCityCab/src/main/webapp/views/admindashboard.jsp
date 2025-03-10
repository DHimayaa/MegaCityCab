<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.DBConnection" %>
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
            <button class="btn">Manage Users</button> </a>
        </div>
        
        <div class="box">
            <h2>Cars Management</h2>
            <p>Add new cars to the system and manage existing vehicles effortlessly.</p>
            <a href="manageCars.jsp">
            <button class="btn">Manage Cars</button> </a>
            <a href="view_cars.jsp">
            <button class="btn">View Cars</button> </a>
        </div>
        
         <div class="box">
            <h2>Drivers Management</h2>
            <p>Register new drivers and oversee the details of current drivers with ease.</p>
             <a href="manageDrivers.jsp">
            <button class="btn">Manage Drivers</button> </a>
            <a href="view_drivers.jsp">
            <button class="btn">View Drivers</button> </a>
        </div>
        
          <div class="box">
                <h2>View Bookings</h2>
                <p>Access and monitor all customer bookings in one place.</p>
                <a href="viewbookings.jsp">
            <button class="btn">Go to Bookings</button> </a>
          </div>
    </div>
    
    <a href="/MegaCityCab/LogoutServlet">
    <button class="logout-btn">Logout</button> </a>
</body>
</html>





