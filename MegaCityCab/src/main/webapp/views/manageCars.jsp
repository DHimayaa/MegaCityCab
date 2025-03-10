<%@ page import="java.sql.*" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="model.DBConnection" %>
<!DOCTYPE html>
<html>
<head>
    <title>Manage Cars</title>
    <link rel="stylesheet" href="../css/Manage.css">
</head>
<body>
    <div class="container">
    <h2>Manage Cars</h2>
    
    <!-- Add Cars Section -->
        <div class="form-container">
            <h3>Add Cars</h3>
        <form action="/MegaCityCab/AddCarServlet" method="post">

            <label for="carModel">Car Model:</label>
            <input type="text" id="carModel" name="carModel" required />

            <label for="carNumber">Car Number:</label>
            <input type="text" id="carNumber" name="carNumber" required />

            <label for="carType">Car Type:</label>
            <select id="carType" name="carType">
                <option value="Sedan">Sedan</option>
                <option value="SUV">SUV</option>
                <option value="Mini">Mini</option>
            </select>
            
            <label for="capacity">Capacity:</label>
            <input type="number" id="capacity" name="capacity" required />

                <button type="submit" class="btn primary-btn">Add Car</button>
            </form>
        </div>
        
    <!-- Display Existing Cars -->
    <h3>Existing Cars</h3>
    <table>
        <thead>
            <tr>
                <th>Car Model</th>
                <th>Car Number</th>
                <th>Car Type</th>
                <th>Capacity</th>
                <th>Status</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <%
                // Fetch cars from the database
                String query = "SELECT * FROM cars";
                Connection connection = DBConnection.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);

                while (resultSet.next()) {
            %>
                <tr>
                    <td><%= resultSet.getString("car_model") %></td>
                    <td><%= resultSet.getString("car_number") %></td>
                    <td><%= resultSet.getString("car_type") %></td>
                    <td><%= resultSet.getInt("capacity") %></td>
                    <td><%= resultSet.getString("status") %></td>
                    <td>
                        <!-- Link to update car -->
                        <a href="/MegaCityCab/UpdateCarServlet?car_id=<%= resultSet.getInt("car_id") %>">Update</a> | 
                        <!-- Link to delete car -->
                        <a href="/MegaCityCab/DeleteCarServlet?car_id=<%= resultSet.getInt("car_id") %>">Delete</a>
                    </td>
                </tr>
            <% } %>
        </tbody>
    </table>
        <button type="button" class="btn back-btn" onclick="window.location.href='managerdashboard.jsp'">Back</button>
    </div>
</body>
</html>
