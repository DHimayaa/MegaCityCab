package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Location;

import java.io.IOException;
import java.sql.*;

@WebServlet("/CalculateFareServlet")
public class CalculateFareServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pickup = request.getParameter("pickup");
        String destination = request.getParameter("destination");

        // Fetch coordinates for pickup and destination locations from the database
        Location pickupLocation = getLocationByName(pickup);
        Location destinationLocation = getLocationByName(destination);

        // Calculate the distance using Haversine formula
        double distance = calculateDistance(pickupLocation.getLatitude(), pickupLocation.getLongitude(),
                                           destinationLocation.getLatitude(), destinationLocation.getLongitude());

        // Calculate fare (Rs 120 per km)
        double fare = distance * 120;

        // Send the fare back as a JSON response
        response.setContentType("application/json");
        response.getWriter().write("{\"fare\": " + fare + "}");
    }

    // Method to calculate the distance using the Haversine formula
    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; // Radius of the earth in km
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c; // Distance in kilometers
    }

    // Method to get the location's latitude and longitude from the database
    private Location getLocationByName(String name) {
        Location location = null;
        String query = "SELECT latitude, longitude FROM locations WHERE name = ?";

        // Replace the below database connection parameters with your own
        String dbUrl = "jdbc:mysql://localhost:3306/MegaCityCab?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
        String dbUser = "root";
        String dbPassword = "12345678";

        try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
             PreparedStatement stmt = connection.prepareStatement(query)) {
            
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                double latitude = rs.getDouble("latitude");
                double longitude = rs.getDouble("longitude");
                location = new Location(name, latitude, longitude);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle database connection and query errors
        }
        return location;
    }
}
