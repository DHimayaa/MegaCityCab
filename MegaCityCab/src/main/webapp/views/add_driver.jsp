<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Driver</title>
    <link rel="stylesheet" href="../css/add_driver.css">
</head>
<body>
    <div class="container">
        <div class="form-box">
            <h2>Add New Driver</h2>
            <form action="/MegaCityCab/AddDriverServlet" method="post">
                <div class="input-group">
                    <label for="driverName">Driver Name:</label>
                    <input type="text" id="driverName" name="driverName" required>
                </div>
                <div class="input-group">
                    <label for="licenseNumber">License Number:</label>
                    <input type="text" id="licenseNumber" name="licenseNumber" required>
                </div>
                <div class="input-group">
                    <label for="phone">Phone Number:</label>
                    <input type="text" id="phone" name="phone" required>
                </div>
                <div class="button-group">
                    <button type="submit" class="btn">Add Driver</button>
                    <button type="button" class="btn back-btn" onclick="window.location.href='managerdashboard.jsp'">Back</button>
                </div>
            </form>
        </div>
    </div>
    <% if(request.getParameter("error") != null) { %>
        <p style="color: red;"><%= request.getParameter("error") %></p>
    <% } %>
</body>
</html>



