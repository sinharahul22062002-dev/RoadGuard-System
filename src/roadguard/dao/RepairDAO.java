package roadguard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import roadguard.connection.DBConnection;

public class RepairDAO {

    // Add new repair
    public boolean addRepair(int complaintId,
                             int assignedTo,
                             String repairType,
                             String status,
                             String assignedDate) {

        String sql = "INSERT INTO repairs "
                   + "(complaint_id, assigned_to, repair_type, "
                   + "status, assigned_date) "
                   + "VALUES (?, ?, ?, ?, ?)";

        Connection con = null;
        PreparedStatement ps = null;

        try {

            con = DBConnection.getConnection();

            if (con == null) {
                System.out.println(
                        "Database connection unavailable."
                );
                return false;
            }

            ps = con.prepareStatement(sql);

            ps.setInt(1, complaintId);
            ps.setInt(2, assignedTo);
            ps.setString(3, repairType);
            ps.setString(4, status);
            ps.setString(5, assignedDate);

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Could not add repair."
            );

            return false;

        } finally {

            try {

                if (ps != null) {
                    ps.close();
                }

                if (con != null) {
                    con.close();
                }

            } catch (SQLException e) {

                System.out.println(
                        "Could not close database resources."
                );
            }
        }
    }


    // View all repairs
    public void viewRepairs() {

        String sql =
                "SELECT r.repair_id, "
              + "r.complaint_id, "
              + "u.name AS assigned_name, "
              + "u.email AS assigned_email, "
              + "r.repair_type, "
              + "r.status, "
              + "r.assigned_date, "
              + "r.completed_date "
              + "FROM repairs r "
              + "JOIN users u "
              + "ON r.assigned_to = u.user_id "
              + "ORDER BY r.repair_id";

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            con = DBConnection.getConnection();

            if (con == null) {
                System.out.println(
                        "Database connection unavailable."
                );
                return;
            }

            ps = con.prepareStatement(sql);

            rs = ps.executeQuery();

            boolean found = false;

            while (rs.next()) {

                found = true;

                System.out.println(
                        "\n-------------------------"
                );

                System.out.println(
                        "Repair ID: "
                        + rs.getInt("repair_id")
                );

                System.out.println(
                        "Complaint ID: "
                        + rs.getInt("complaint_id")
                );

                System.out.println(
                        "Assigned To: "
                        + rs.getString("assigned_name")
                        + " ("
                        + rs.getString("assigned_email")
                        + ")"
                );

                System.out.println(
                        "Repair Type: "
                        + rs.getString("repair_type")
                );

                System.out.println(
                        "Status: "
                        + rs.getString("status")
                );

                System.out.println(
                        "Assigned Date: "
                        + rs.getDate("assigned_date")
                );

                System.out.println(
                        "Completed Date: "
                        + rs.getDate("completed_date")
                );
            }

            if (!found) {
                System.out.println(
                        "No repairs found."
                );
            }

        } catch (SQLException e) {

            System.out.println(
                    "Could not view repairs."
            );

        } finally {

            try {

                if (rs != null) {
                    rs.close();
                }

                if (ps != null) {
                    ps.close();
                }

                if (con != null) {
                    con.close();
                }

            } catch (SQLException e) {

                System.out.println(
                        "Could not close database resources."
                );
            }
        }
    }


    // Update repair status
    public boolean updateStatus(int repairId,
                                String status) {

        String sql =
                "UPDATE repairs SET status = ? "
              + "WHERE repair_id = ?";

        Connection con = null;
        PreparedStatement ps = null;

        try {

            con = DBConnection.getConnection();

            if (con == null) {
                System.out.println(
                        "Database connection unavailable."
                );
                return false;
            }

            ps = con.prepareStatement(sql);

            ps.setString(1, status);
            ps.setInt(2, repairId);

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Repair status update failed."
            );

            return false;

        } finally {

            try {

                if (ps != null) {
                    ps.close();
                }

                if (con != null) {
                    con.close();
                }

            } catch (SQLException e) {

                System.out.println(
                        "Could not close database resources."
                );
            }
        }
    }
}