<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="model.User" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html>
<head>
    <title>User Dashboard</title>
    <link rel="stylesheet" href="../css/dashboard.css">
</head>
<body>
    <%
        // Retrieve the user object from the existing session (No need to declare session again)
        User user = (User) request.getSession(false).getAttribute("user"); // Get the user object from the session
        
        // If the user is not logged in, redirect to the login page
        if (user == null) {
            response.sendRedirect("views/login.jsp?error=Please%20login%20first");
            return;
        }
    %>

    
    <h1>Hello!! <%= user.getUsername() %>!</h1> <!-- Display the logged-in user's username -->
    <p>Welcome to your Dashboard</p>
    

    <div class="dashboard-container">
        <div class="box">
            <h2>Book a Ride</h2>
            <p>Request a cab instantly with your preferred options</p>
            <a href="booking.jsp">
            <button class="btn">Go to Booking</button> </a>
        </div>
        <div class="box">
            <h2>Previous Bookings</h2>
            <p>View and manage your past ride details</p>
            <a href="viewbookings.jsp">
            <button class="btn">Go to Your Bookings</button> </a>
        </div>
    </div>
    
    <a href="/MegaCityCab/LogoutServlet">
    <button class="logout-btn">Logout</button> </a>
</body>
</html>


