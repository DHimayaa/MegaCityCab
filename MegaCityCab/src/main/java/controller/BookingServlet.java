package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

import java.io.IOException;
import dao.BookingDAO;
import dao.LocationDAO;

@WebServlet("/BookingServlet")
public class BookingServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form parameters
        String pickup = request.getParameter("pickup");
        String destination = request.getParameter("destination");

        // Check if the user is logged in (check the session for user object)
        HttpSession session = request.getSession(false);  // Use false to avoid creating a new session
        if (session == null || session.getAttribute("user") == null) {
            // Redirect to login page if the user is not logged in
            response.sendRedirect("views/login.jsp?error=Please%20login%20first");
            return;
        }

        // Get the user object from session
        User user = (User) session.getAttribute("user");
        int customerId = user.getUserId(); // Assuming `User` has a method `getUserId()`

        // Get coordinates for pickup and destination
        LocationDAO locationDAO = new LocationDAO();
        double[] pickupCoordinates = locationDAO.getLocationCoordinates(pickup);
        double[] destinationCoordinates = locationDAO.getLocationCoordinates(destination);

        // Calculate distance using Haversine formula
        double distance = calculateDistance(pickupCoordinates[0], pickupCoordinates[1], 
                                            destinationCoordinates[0], destinationCoordinates[1]);

        // Calculate fare (120 Rs per km)
        double fare = distance * 120;

        // Call BookingDAO to book the ride
        BookingDAO bookingDAO = new BookingDAO();
        boolean isBooked = bookingDAO.bookRide(customerId, pickup, destination, fare);

        // Redirect based on success or failure
        if (isBooked) {
            response.sendRedirect("views/booking_confirmation.jsp?msg=Booking%20Successful");
        } else {
            response.sendRedirect("views/error.jsp?msg=Booking%20Failed");
        }
    }

    private double calculateDistance(double lon1, double lat1, double lon2, double lat2) {
        final int R = 6371; // Radius of the Earth in kilometers

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
                   Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                   Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c; // Distance in kilometers
    }
}
