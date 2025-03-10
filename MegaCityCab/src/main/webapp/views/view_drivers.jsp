<%@ page import="java.util.List, model.Driver, dao.DriverDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Driver Management</title>
    <link rel="stylesheet" href="../css/view_drivers.css">
</head>
<body>
    <div class="container">
        <h2>Drivers List</h2>
        
        <% if(request.getParameter("message") != null) { %>
            <p class="message"><%= request.getParameter("message") %></p>
        <% } %>
        
        <div class="table-container">
            <table>
                <thead>
                    <tr>
                        <th>Driver Name</th>
                        <th>License Number</th>
                        <th>Phone Number</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>
                    <% DriverDAO driverDAO = new DriverDAO();
                       List<Driver> drivers = driverDAO.getAllDrivers();
                       for (Driver driver : drivers) { %>
                    <tr>
                        <td><%= driver.getDriverName() %></td>
                        <td><%= driver.getLicenseNumber() %></td>
                        <td><%= driver.getPhone() %></td>
                        <td><%= driver.getStatus() %></td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
        </div>
        
        <button class="btn back-btn" onclick="window.location.href='managerdashboard.jsp'">Back</button>
    </div>
</body>
</html>