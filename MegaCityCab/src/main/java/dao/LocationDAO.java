package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.DBConnection;

public class LocationDAO {

    public List<String> getLocationNames() {
        List<String> locations = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT name FROM locations";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                locations.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return locations;
    }

    public double[] getLocationCoordinates(String locationName) {
        double[] coordinates = new double[2];
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT longitude, latitude FROM locations WHERE name = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, locationName);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                coordinates[0] = rs.getDouble("longitude");
                coordinates[1] = rs.getDouble("latitude");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return coordinates;
    }
}
