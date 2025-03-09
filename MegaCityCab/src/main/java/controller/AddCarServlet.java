package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.DBConnection;
import model.Car;
import model.User;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/AddCarServlet")
public class AddCarServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve car details from form
        String carModel = request.getParameter("carModel");
        String carNumber = request.getParameter("carNumber");
        String carType = request.getParameter("carType");
        int capacity = Integer.parseInt(request.getParameter("capacity"));

        // Get the user role from the session
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");  // Retrieve the User object from the session

        if (user == null) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "User not logged in.");
            return;
        }

        String role = user.getRole(); // Get the role from the User object

        // Query for adding the car to the database
        String query = "INSERT INTO cars (car_model, car_number, car_type, capacity) VALUES (?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, carModel);
            statement.setString(2, carNumber);
            statement.setString(3, carType);
            statement.setInt(4, capacity);

            int rowsInserted = statement.executeUpdate();

            // Redirect based on user role
            if (rowsInserted > 0) {
                if ("admin".equals(role)) {
                    response.sendRedirect("/MegaCityCab/views/manageCars.jsp");  // Admin view
                } else if ("manager".equals(role)) {
                    response.sendRedirect("/MegaCityCab/views/view_cars.jsp");  // Manager view
                } else {
                    response.sendError(HttpServletResponse.SC_FORBIDDEN, "Unauthorized access.");
                }
            } else {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error adding car.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error.");
        }
    }
}
