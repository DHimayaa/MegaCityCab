package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import dao.BookingDAO;

/**
 * Servlet implementation class CancelBookingServlet
 */
@WebServlet("/CancelBookingServlet")
public class CancelBookingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookingIdStr = request.getParameter("bookingId");

        if (bookingIdStr == null) {
            response.sendRedirect("view_bookings.jsp?error=Invalid request");
            return;
        }

        int bookingId = Integer.parseInt(bookingIdStr);
        BookingDAO bookingDAO = new BookingDAO();
        boolean success = bookingDAO.cancelBooking(bookingId);

        if (success) {
            response.sendRedirect("view_bookings.jsp?msg=Booking cancelled");
        } else {
            response.sendRedirect("view_bookings.jsp?error=Failed to cancel booking");
        }
    }
}
