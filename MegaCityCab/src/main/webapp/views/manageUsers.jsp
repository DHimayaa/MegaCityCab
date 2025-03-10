<%@ page import="java.sql.*" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="model.DBConnection" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard - Manage Users</title>
    <link rel="stylesheet" href="../css/Manage.css">
</head>
<body>
    <div class="container">
        <h2>Manage Users</h2>

        <!-- Add User Section -->
        <div class="form-container">
            <h3>Add User</h3>
            <form action="/MegaCityCab/AddUserServlet" method="POST">
                <label for="username">Username:</label>
                <input type="text" name="username" required>

                <label for="password">Password:</label>
                <input type="password" name="password" required>

                <label for="email">Email:</label>
                <input type="email" name="email" required>

                <label for="phone">Phone:</label>
                <input type="text" name="phone" required>

                <label for="role">Role:</label>
                <select name="role">
                    <option value="user">User</option>
                    <option value="admin">Admin</option>
                    <option value="Manager">Manager</option>
                </select>

                <button type="submit" class="btn primary-btn">Add User</button>
            </form>
        </div>

        <!-- Display Existing Users -->
        <div class="table-container">
            <h3>Existing Users</h3>
            <table>
                <thead>
                    <tr>
                        <th>Username</th>
                        <th>Email</th>
                        <th>Phone</th>
                        <th>Role</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <% 
                        // Fetch users from the database
                        String query = "SELECT * FROM users";
                        try (Connection connection = DBConnection.getConnection();
                             Statement statement = connection.createStatement();
                             ResultSet resultSet = statement.executeQuery(query)) {

                            while (resultSet.next()) {
                    %>
                        <tr>
                            <td><%= resultSet.getString("username") %></td>
                            <td><%= resultSet.getString("email") %></td>
                            <td><%= resultSet.getString("phone") %></td>
                            <td><%= resultSet.getString("role") %></td>
                            <td>
                                <a href="updateUser.jsp?id=<%= resultSet.getInt("user_id") %>" class="btn update-btn">Update</a>
                                <a href="deleteConfirmation.jsp?id=<%= resultSet.getInt("user_id") %>" class="btn delete-btn">Delete</a>
                            </td>
                        </tr>
                    <% 
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    %>
                </tbody>
            </table>
        </div>

        <button type="button" class="btn back-btn" onclick="window.location.href='managerdashboard.jsp'">Back</button>
    </div>
</body>
</html>
