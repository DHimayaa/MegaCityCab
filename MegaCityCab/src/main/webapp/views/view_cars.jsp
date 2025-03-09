<%@ page import="java.util.List, model.Car, dao.CarDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Car Management</title>
    <link rel="stylesheet" href="../css/view_cars.css">
</head>
<body>
    <div class="container">
        <h2>Cars List</h2>
        <table>
            <thead>
                <tr>
                    <th>Car Model</th>
                    <th>Car Number</th>
                    <th>Car Type</th>
                    <th>Capacity</th>
                    <th>Status</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    CarDAO carDAO = new CarDAO();
                    List<Car> cars = carDAO.getAllCars();
                    for (Car car : cars) { 
                %>
                <tr>
                    <td><%= car.getCarModel() %></td>
                    <td><%= car.getCarNumber() %></td>
                    <td><%= car.getCarType() %></td>
                    <td><%= car.getCapacity() %></td>
                    <td><%= car.getStatus() %></td>
                </tr>
                <% } %>
            </tbody>
        </table>
        <button class="back-button" onclick="window.location.href='managerdashboard.jsp'">Back</button>
    </div>
</body>
</html>
