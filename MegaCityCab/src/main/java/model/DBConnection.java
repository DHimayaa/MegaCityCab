package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    
    // Database credentials
    private static final String URL = "jdbc:mysql://localhost:3306/MegaCityCab?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "root";  // MySQL Username
    private static final String PASSWORD = "kavindu"; // MySQL Password

    // Singleton connection instance
    private static Connection conn = null;

    // Private constructor to prevent instantiation
    private DBConnection() {}

    /**
     * Method to get the database connection.
     * @return Connection object to the database.
     * @throws SQLException If there is an error connecting to the database.
     */
    public static Connection getConnection() throws SQLException {
        try {
            if (conn == null || conn.isClosed()) {
                // Explicitly load the MySQL JDBC driver
                Class.forName("com.mysql.cj.jdbc.Driver");
                
                // Establish the connection using the URL
                conn = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Database connected successfully!");
            }
        } catch (ClassNotFoundException e) {
            System.err.println("ERROR: MySQL JDBC Driver not found.");
            e.printStackTrace();
            throw new SQLException("Database driver not found.");
        } catch (SQLException e) {
            System.err.println("ERROR: Failed to connect to database - " + e.getMessage());
            throw new SQLException("Error connecting to the database.");
        }
        return conn;
    }

    /**
     * Method to close the database connection (if needed).
     * It is important to close the connection to free up resources.
     */
    public static void closeConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Database connection closed successfully.");
            }
        } catch (SQLException e) {
            System.err.println("Error closing database connection: " + e.getMessage());
        }
    }
}
