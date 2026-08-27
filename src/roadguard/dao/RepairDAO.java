package roadguard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import roadguard.connection.DBConnection;

public class RepairDAO {

    public boolean addRepair(String location,
                             String description) {

        try {

            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO repairs " +
                         "(location, description, status) " +
                         "VALUES (?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, location);
            ps.setString(2, description);
            ps.setString(3, "PENDING");

            ps.executeUpdate();

            con.close();

            return true;

        } catch (Exception e) {

            System.out.println("Could not add repair!");
            return false;
        }
    }


    public void viewRepairs() {

        try {

            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM repairs";

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                System.out.println("\n-------------------------");

                System.out.println("Repair ID: "
                        + rs.getInt("repair_id"));

                System.out.println("Location: "
                        + rs.getString("location"));

                System.out.println("Description: "
                        + rs.getString("description"));

                System.out.println("Status: "
                        + rs.getString("status"));
            }

            con.close();

        } catch (Exception e) {

            System.out.println("Could not view repairs!");
        }
    }


    public boolean updateStatus(int repairId,
                                String status) {

        try {

            Connection con = DBConnection.getConnection();

            String sql = "UPDATE repairs SET status = ? " +
                         "WHERE repair_id = ?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, status);
            ps.setInt(2, repairId);

            ps.executeUpdate();

            con.close();

            return true;

        } catch (Exception e) {

            System.out.println("Repair status update failed!");
            return false;
        }
    }
}