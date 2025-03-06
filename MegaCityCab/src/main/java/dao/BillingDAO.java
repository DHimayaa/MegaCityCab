package dao;

import model.Bill;
import model.DBConnection;

import java.sql.*;

public class BillingDAO {

    // Method to create a bill for a booking
    public boolean createBill(int bookingId, double totalAmount) {
        String sql = "INSERT INTO bills (booking_id, total_amount) VALUES (?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, bookingId);
            stmt.setDouble(2, totalAmount);

            // Execute the insert operation
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to retrieve bill by booking ID
    public Bill getBillByBookingId(int bookingId) {
        String sql = "SELECT bill_id, booking_id, total_amount, bill_time FROM bills WHERE booking_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, bookingId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Bill(
                        rs.getInt("bill_id"),
                        rs.getInt("booking_id"),
                        rs.getDouble("total_amount"),
                        rs.getTimestamp("bill_time")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
