<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Manager Dashboard</title>
    <link rel="stylesheet" href="../css/dashboard.css">
</head>
<body>
    <h1>Manager Dashboard</h1>
    <p> Welcome Manager!!! </p>
    <div class="dashboard-container">
        <div class="box">
            <h2>Bookings Management</h2>
            <p>View and manage all bookings, ensuring smooth operations and coordination</p>
            <a href="viewBookings.jsp">
            <button class="btn">Go to Bookings</button> </a>
        </div>
        <div class="box">
            <h2>Staff Management</h2>
            <p>Oversee and manage your staff's schedules and performance for optimal productivity</p>
            <a href="manageStaff.jsp">
            <button class="btn">Go to Staff Management</button> </a>
        </div>
    </div>
    
    <a href="/MegaCityCab/LogoutServlet">
    <button class="logout-btn">Logout</button> </a>
</body>
</html>