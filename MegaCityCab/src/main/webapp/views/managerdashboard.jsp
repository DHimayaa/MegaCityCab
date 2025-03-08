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
            <p>Efficiently manage customer bookings, ensuring seamless trip coordination and satisfaction.</p>
            <a href="viewbookings.jsp">
            <button class="btn">View Bookings</button> </a>
        </div>
        
        <div class="box">
            <h2>Cars Management</h2>
            <p>Maintain a comprehensive vehicle database, ensuring optimal fleet utilization and performance.</p>
            <a href="add_car.jsp">
            <button class="btn">Add Cars</button> </a>
            <a href="view_cars.jsp">
            <button class="btn">View Cars</button> </a>
        </div>
    
        <div class="box">
            <h2>Drivers Management</h2>
            <p>Manage driver details to enhance coordination, ensure compliance, and improve service efficiency.</p>
             <a href="add_driver.jsp">
            <button class="btn">Add Drivers</button> </a>
            <a href="view_drivers.jsp">
            <button class="btn">View Drivers</button> </a>
        </div>
    </div>
    
    <a href="/MegaCityCab/LogoutServlet">
    <button class="logout-btn">Logout</button> </a>
</body>
</html>


