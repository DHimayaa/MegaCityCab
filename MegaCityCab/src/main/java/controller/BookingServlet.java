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
import generator.BillGenerator;  // Import BillGenerator class
import model.Bill;

@WebServlet("/BookingServlet")
public class BookingServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // POST method to handle the booking and bill generation
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form parameters (pickup and destination locations)
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
        int userId = user.getUserId(); // Assuming `User` has a method `getUserId()`

        // Get coordinates for pickup and destination
        LocationDAO locationDAO = new LocationDAO();
        double[] pickupCoordinates = locationDAO.getLocationCoordinates(pickup);
        double[] destinationCoordinates = locationDAO.getLocationCoordinates(destination);

        // Calculate distance using Haversine formula
        double distance = calculateDistance(pickupCoordinates[0], pickupCoordinates[1], 
                                            destinationCoordinates[0], destinationCoordinates[1]);

        // Calculate fare (assuming 120 Rs per km)
        double fare = distance * 120;

        // Call BookingDAO to book the ride and get the booking_id
        BookingDAO bookingDAO = new BookingDAO();
        int bookingId = bookingDAO.bookRide(userId, pickup, destination, fare);  // Now returns booking_id, not boolean

        // If the booking is successful, generate the bill
        if (bookingId != -1) {
            // Get the real path for saving the PDF files (this could be a static folder path or passed from configuration)
            String pdfDir = getServletContext().getRealPath("/") + "generated_pdfs";  // Absolute path to 'generated_pdfs' folder

            // Instantiate BillGenerator with the path for saving PDFs
            BillGenerator billGenerator = new BillGenerator(pdfDir);

            // Generate the bill with bookingId and fare
            Bill generatedBill = billGenerator.generateBill(bookingId, fare); // Pass bookingId to BillGenerator

            if (generatedBill != null) {
                // Set the generated bill as a request attribute and forward to booking confirmation page
                request.setAttribute("bill", generatedBill);
                request.getRequestDispatcher("/views/booking_confirmation.jsp").forward(request, response);
            } else {
                // If bill generation failed, redirect to an error page
                response.sendRedirect("views/error.jsp?msg=Failed%20to%20generate%20bill");
            }
        } else {
            // Redirect to error page if booking failed
            response.sendRedirect("views/error.jsp?msg=Booking%20Failed");
        }
    }

    // Method to calculate the distance between pickup and destination using Haversine formula
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
