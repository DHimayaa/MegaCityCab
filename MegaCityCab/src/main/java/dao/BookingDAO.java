package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.DBConnection;
import model.Bill;
import model.Booking;

public class BookingDAO {

	// In BookingDAO.java
	public int bookRide(int userId, String pickup, String destination, double fare) {
	    String sql = "INSERT INTO bookings (user_id, pickup_location, destination, fare) VALUES (?, ?, ?, ?)";
	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

	        stmt.setInt(1, userId);  // Use user_id instead of customer_id
	        stmt.setString(2, pickup);
	        stmt.setString(3, destination);
	        stmt.setDouble(4, fare);

	        int rowsAffected = stmt.executeUpdate();

	        if (rowsAffected > 0) {
	            // Get the generated booking_id
	            ResultSet generatedKeys = stmt.getGeneratedKeys();
	            if (generatedKeys.next()) {
	                return generatedKeys.getInt(1);  // Return the generated booking_id
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return -1;  // Return -1 if the booking failed
	}

    // Method to create a booking
    public boolean createBooking(int customerId, String pickup, String destination, double fare) {
        String sql = "INSERT INTO bookings (customer_name, pickup_location, destination, fare, status) "
                   + "VALUES ((SELECT customer_name FROM customers WHERE customer_id = ?), ?, ?, ?, 'Pending')";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, customerId);
            stmt.setString(2, pickup);
            stmt.setString(3, destination);
            stmt.setDouble(4, fare);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to cancel a booking
    public boolean cancelBooking(int bookingId) {
        String sql = "UPDATE bookings SET status = 'Cancelled' WHERE booking_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, bookingId);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to update booking status
    public boolean updateBookingStatus(int bookingId, String status) {
        String sql = "UPDATE bookings SET status = ? WHERE booking_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, status);
            stmt.setInt(2, bookingId);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Booking> getAllBookings() {
        List<Booking> bookings = new ArrayList<>();
        String query = "SELECT b.booking_id, b.pickup_location, b.destination, b.fare, b.status, u.username "
                      + "FROM bookings b "
                      + "JOIN users u ON b.user_id = u.user_id";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Booking booking = new Booking(
                    rs.getInt("booking_id"),
                    rs.getString("username"),
                    rs.getString("pickup_location"),
                    rs.getString("destination"),
                    rs.getDouble("fare"),
                    rs.getString("status"),
                    null 
                );
                bookings.add(booking);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookings;
    }
    
 // Method to get bookings by user ID
    public List<Booking> getBookingsByUserId(int userId) {
        List<Booking> bookings = new ArrayList<>();
        String query = "SELECT b.booking_id, b.pickup_location, b.destination, b.fare, b.status, u.username "
                     + "FROM bookings b "
                     + "JOIN users u ON b.user_id = u.user_id "
                     + "WHERE b.user_id = ?"; // Fetch bookings for the given user ID

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userId);  // Set the user ID in the query
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // Create a Booking object and populate it with data from the ResultSet
                Booking booking = new Booking(
                    rs.getInt("booking_id"),
                    rs.getString("username"),  // Get the customer's name (username)
                    rs.getString("pickup_location"),
                    rs.getString("destination"),
                    rs.getDouble("fare"),
                    rs.getString("status"),
                    null// Assuming booking_time is a valid column in the database
                );
                bookings.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookings;
    }
    
    
    public Booking getBookingById(int bookingId) {
        String query = "SELECT * FROM bookings WHERE booking_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, bookingId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Create a booking object and return it
                return new Booking(
                    rs.getInt("booking_id"),
                    rs.getString("customer_name"),
                    rs.getString("pickup_location"),
                    rs.getString("destination"),
                    rs.getDouble("fare"),
                    rs.getString("status"),
                    null
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Return null if no booking is found
    }



}