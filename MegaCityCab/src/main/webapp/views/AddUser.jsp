<%@ page import="java.sql.*" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="model.DBConnection" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard - Manage Users</title>
</head>
<body>
    <h2>Manage Users</h2>
    
    <!-- Form to Add a New User -->
    <form action="addUser" method="POST">
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
        </select>
        
        <button type="submit">Add User</button>
    </form>
    
    <!-- Display Existing Users -->
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
                Connection connection = DBConnection.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);

                while (resultSet.next()) {
            %>
                <tr>
                    <td><%= resultSet.getString("username") %></td>
                    <td><%= resultSet.getString("email") %></td>
                    <td><%= resultSet.getString("phone") %></td>
                    <td><%= resultSet.getString("role") %></td>
                    <td>
                        <a href="updateUser?id=<%= resultSet.getInt("user_id") %>">Update</a> | 
                        <a href="deleteUser?id=<%= resultSet.getInt("user_id") %>">Delete</a>
                    </td>
                </tr>
            <% } %>
        </tbody>
    </table>
</body>
</html>
