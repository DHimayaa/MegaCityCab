<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="dao.BookingDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Booking" %>
<%@ page import="model.User" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View My Bookings</title>
    <link rel="stylesheet" href="../css/ViewMybookings.css"> 
</head>
<body>
    <div class="container">
        <h2>My Bookings</h2>

        <%
            User loggedInUser = (User) session.getAttribute("user");

            if (loggedInUser == null) {
        %>
            <p class="error">You must log in to view your bookings.</p>
        <%
            } else {
                BookingDAO bookingDAO = new BookingDAO();
                List<Booking> bookings = bookingDAO.getBookingsByUserId(loggedInUser.getUserId());

                if (bookings == null || bookings.isEmpty()) {
        %>
                <p class="info">No bookings found.</p>
        <%
                } else {
        %>
            <div class="table-container">
                <table>
                    <thead>
                        <tr>
                            <th>Booking ID</th>
                            <th>Customer Name</th>
                            <th>Pickup Location</th>
                            <th>Destination</th>
                            <th>Fare (Rs)</th>
                            <th>Status</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for (Booking b : bookings) { %>
                        <tr>
                            <td><%= b.getBookingId() %></td>
                            <td><%= loggedInUser.getUsername() %></td>
                            <td><%= b.getPickupLocation() %></td>
                            <td><%= b.getDestination() %></td>
                            <td><%= b.getFare() %></td>
                            <td class="<%= b.getStatus().equals("Pending") ? "pending" : "completed" %>">
                                <%= b.getStatus() %>
                            </td>
                            <td>
                                <% if ("Pending".equals(b.getStatus())) { %>
                                    <a class="btn complete" href="UpdateBookingServlet?bookingId=<%= b.getBookingId() %>&status=Completed">Complete</a>
                                    <a class="btn cancel" href="CancelBookingServlet?bookingId=<%= b.getBookingId() %>">Cancel</a>
                                <% } else { %>
                                    <span class="no-action">No Actions</span>
                                <% } %>
                            </td>
                        </tr>
                        <% } %>
                    </tbody>
                </table>
            </div>
        <%
                }
            }
        %>

        <button class="btn back-btn" onclick="window.location.href='homedashboard.jsp'">Back to Home</button>
    </div>
</body>
</html>