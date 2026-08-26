package roadguard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import roadguard.connection.DBConnection;

public class ComplaintDAO {

    public boolean addComplaint(String email, String description, String location) {

        try {
            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO complaints " +
                         "(citizen_email, description, location, status) " +
                         "VALUES (?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, email);
            ps.setString(2, description);
            ps.setString(3, location);
            ps.setString(4, "PENDING");

            ps.executeUpdate();

            con.close();

            return true;

        } catch (Exception e) {

            System.out.println("Complaint failed!");
            return false;
        }
    }


    public void viewComplaints(String email) {

        try {

            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM complaints WHERE citizen_email = ?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                System.out.println("\nComplaint ID: "
                        + rs.getInt("complaint_id"));

                System.out.println("Description: "
                        + rs.getString("description"));

                System.out.println("Location: "
                        + rs.getString("location"));

                System.out.println("Status: "
                        + rs.getString("status"));
            }

            con.close();

        } catch (Exception e) {

            System.out.println("Could not view complaints!");
        }
    }
}