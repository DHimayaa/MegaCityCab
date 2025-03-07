<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Car</title>
    <link rel="stylesheet" href="../css/add_car.css">
</head>
<body>
    <<body>
    <div class="container">
        <div class="form-box">
            <h2>Add New Car</h2>
            <form action="/MegaCityCab/AddCarServlet" method="post">
                <div class="input-group">
                    <label for="carModel">Car Model:</label>
                    <input type="text" id="carModel" name="carModel" required>
                </div>
                <div class="input-group">
                    <label for="carNumber">Car Number:</label>
                    <input type="text" id="carNumber" name="carNumber" required>
                </div>
                <div class="input-group">
                    <label for="carType">Car Type:</label>
                    <select id="carType" name="carType">
                        <option value="Sedan">Sedan</option>
                        <option value="SUV">SUV</option>
                        <option value="Mini">Mini</option>
                    </select>
                </div>
                <div class="input-group">
                    <label for="capacity">Capacity:</label>
                    <input type="number" id="capacity" name="capacity" required>
                </div>
                <div class="button-group">
                    <button type="submit" class="btn">Add Car</button>
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



