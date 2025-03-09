package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.DBConnection;
import model.User;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/AddDriverServlet")
public class AddDriverServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve driver details from form
        String driverName = request.getParameter("driver_name");
        String licenseNumber = request.getParameter("license_number");
        String phone = request.getParameter("phone");
        String status = request.getParameter("status");

        // Get the user role from the session
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");  // Retrieve the User object from the session

        if (user == null) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "User not logged in.");
            return;
        }

        String role = user.getRole(); // Get the role from the User object

        // Query for adding the driver to the database
        String query = "INSERT INTO drivers (driver_name, license_number, phone, status) VALUES (?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, driverName);
            statement.setString(2, licenseNumber);
            statement.setString(3, phone);
            statement.setString(4, status);

            int rowsInserted = statement.executeUpdate();

            // Redirect based on user role
            if (rowsInserted > 0) {
                if ("admin".equals(role)) {
                    response.sendRedirect("/MegaCityCab/views/manageDrivers.jsp");
                } else if ("manager".equals(role)) {
                    response.sendRedirect("/MegaCityCab/views/view_drivers.jsp");
                } else {
                    response.sendError(HttpServletResponse.SC_FORBIDDEN, "Unauthorized access.");
                }
            } else {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error adding driver.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error.");
        }
    }
}
