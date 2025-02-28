package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.DBConnection;
import model.Booking; // Assuming you have a Booking class to hold booking data

public class BookingDAO {

	public boolean bookRide(int userId, String pickup, String destination, double fare) {
	    // Updated query to insert into bookings with the user_id field
	    String query = "INSERT INTO bookings (user_id, pickup_location, destination, fare) VALUES (?, ?, ?, ?)";

	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(query)) {

	        // Set the user_id (which was previously customerId)
	        stmt.setInt(1, userId);
	        stmt.setString(2, pickup);
	        stmt.setString(3, destination);
	        stmt.setDouble(4, fare);

	        int rowsAffected = stmt.executeUpdate();
	        return rowsAffected > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();  // Print the stack trace to check for specific errors
	        return false;
	    }
	}

    

    // Method to create a booking
    public boolean createBooking(int customerId, String pickup, String destination, double fare) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO bookings (customer_name, pickup_location, destination, fare, status) "
                       + "VALUES ((SELECT customer_name FROM customers WHERE customer_id = ?), ?, ?, ?, 'Pending')";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, customerId);
            stmt.setString(2, pickup);
            stmt.setString(3, destination);
            stmt.setDouble(4, fare);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Return true if the booking was successfully created
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;  // Return false if the creation failed
    }

    // Method to cancel a booking
    public boolean cancelBooking(int bookingId) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "UPDATE bookings SET status = 'Cancelled' WHERE booking_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, bookingId);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;  // Return true if the update was successful
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;  // Return false if the cancellation failed
    }

    // Method to update booking status
    public boolean updateBookingStatus(int bookingId, String status) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "UPDATE bookings SET status = ? WHERE booking_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, status);
            stmt.setInt(2, bookingId);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;  // Return true if the update was successful
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;  // Return false if the update failed
    }

    public List<Booking> getAllBookings() {
        List<Booking> bookings = new ArrayList<>();
        String query = "SELECT b.booking_id, b.pickup_location, b.destination, b.fare, b.status, u.username " +
                       "FROM bookings b " +
                       "JOIN users u ON b.user_id = u.user_id";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Booking booking = new Booking();
                booking.setBookingId(rs.getInt("booking_id"));
                booking.setPickupLocation(rs.getString("pickup_location"));
                booking.setDestination(rs.getString("destination"));
                booking.setFare(rs.getDouble("fare"));
                booking.setStatus(rs.getString("status"));
                booking.setCustomerName(rs.getString("username"));  // Assuming you have customer name in users table
                bookings.add(booking);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookings;
    }
    }
