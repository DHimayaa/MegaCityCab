<%@ page import="java.sql.*" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="model.DBConnection" %>
<!DOCTYPE html>
<html>
<head>
    <title>Manage Drivers</title>
    <link rel="stylesheet" href="../css/Manage.css">
    
</head>
<body>
    <div class="container">
    <h2>Manage Drivers</h2>

    <!-- Add Driver Section -->
    <div class="form-container">
    <h3>Add Driver</h3>
    <form action="/MegaCityCab/AddDriverServlet" method="POST">
        <label for="driver_name">Driver Name:</label>
        <input type="text" name="driver_name" required>
        
        <label for="license_number">License Number:</label>
        <input type="text" name="license_number" required>
        
        <label for="phone">Phone:</label>
        <input type="text" name="phone" required>
        
        <label for="status">Status:</label>
        <select name="status">
            <option value="Available">Available</option>
            <option value="Assigned">Assigned</option>
            <option value="Inactive">Inactive</option>
        </select>
        
        <button type="submit" class="btn primary-btn">Add Driver</button>
    </form>
    </div>
    
    <!-- Display Existing Drivers -->
    <h3>Existing Drivers</h3>
    <table border="1">
        <thead>
            <tr>
                <th>Driver Name</th>
                <th>License Number</th>
                <th>Phone</th>
                <th>Status</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <% 
                // Fetch drivers' data from the database
                String query = "SELECT * FROM drivers";
                Connection connection = DBConnection.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);

                if (resultSet != null) {
                    while (resultSet.next()) {
            %>
                <tr>
                    <td><%= resultSet.getString("driver_name") %></td>
                    <td><%= resultSet.getString("license_number") %></td>
                    <td><%= resultSet.getString("phone") %></td>
                    <td><%= resultSet.getString("status") %></td>
                    <td>
                        <a href="updateDriver.jsp?id=<%= resultSet.getInt("driver_id") %>">Update</a> | 
                        <a href="deleteDriver.jsp?id=<%= resultSet.getInt("driver_id") %>">Delete</a>
                    </td>
                </tr>
            <% 
                    }
                } else {
                    out.println("<tr><td colspan='5'>No drivers found.</td></tr>");
                }
            %>
        </tbody>
    </table>
    <button type="button" class="btn back-btn" onclick="window.location.href='managerdashboard.jsp'">Back</button>
    </div>
</body>
</html>
