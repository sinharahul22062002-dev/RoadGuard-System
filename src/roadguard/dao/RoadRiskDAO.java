package roadguard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import roadguard.connection.DBConnection;

public class RoadRiskDAO {

    // Add new risk assessment
    public boolean addRisk(int locationId,
                           double accidentScore,
                           double roadScore,
                           double trafficScore,
                           double complaintScore,
                           double totalScore,
                           String riskLevel) {

        String sql = "INSERT INTO risk_assessments "
                   + "(location_id, accident_score, road_score, "
                   + "traffic_score, complaint_score, total_score, risk_level) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        Connection con = null;
        PreparedStatement ps = null;

        try {

            con = DBConnection.getConnection();

            if (con == null) {
                System.out.println("Database connection unavailable.");
                return false;
            }

            ps = con.prepareStatement(sql);

            ps.setInt(1, locationId);
            ps.setDouble(2, accidentScore);
            ps.setDouble(3, roadScore);
            ps.setDouble(4, trafficScore);
            ps.setDouble(5, complaintScore);
            ps.setDouble(6, totalScore);
            ps.setString(7, riskLevel);

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {

            System.out.println("Could not add risk assessment.");
            System.out.println("Database operation failed.");

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

                System.out.println("Could not close database resources.");
            }
        }
    }


    // View all risk assessments
    public void viewRisks() {

        String sql =
                "SELECT r.risk_id, "
              + "l.area_name, l.city, "
              + "r.accident_score, "
              + "r.road_score, "
              + "r.traffic_score, "
              + "r.complaint_score, "
              + "r.total_score, "
              + "r.risk_level, "
              + "r.assessment_date "
              + "FROM risk_assessments r "
              + "JOIN locations l "
              + "ON r.location_id = l.location_id "
              + "ORDER BY r.total_score DESC";

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            con = DBConnection.getConnection();

            if (con == null) {
                System.out.println("Database connection unavailable.");
                return;
            }

            ps = con.prepareStatement(sql);

            rs = ps.executeQuery();

            boolean found = false;

            while (rs.next()) {

                found = true;

                System.out.println("\n-------------------------");

                System.out.println(
                        "Risk ID: "
                        + rs.getInt("risk_id")
                );

                System.out.println(
                        "Location: "
                        + rs.getString("area_name")
                        + ", "
                        + rs.getString("city")
                );

                System.out.println(
                        "Accident Score: "
                        + rs.getDouble("accident_score")
                );

                System.out.println(
                        "Road Score: "
                        + rs.getDouble("road_score")
                );

                System.out.println(
                        "Traffic Score: "
                        + rs.getDouble("traffic_score")
                );

                System.out.println(
                        "Complaint Score: "
                        + rs.getDouble("complaint_score")
                );

                System.out.println(
                        "Total Score: "
                        + rs.getDouble("total_score")
                );

                System.out.println(
                        "Risk Level: "
                        + rs.getString("risk_level")
                );

                System.out.println(
                        "Assessment Date: "
                        + rs.getString("assessment_date")
                );
            }

            if (!found) {
                System.out.println(
                        "No risk assessments found."
                );
            }

        } catch (SQLException e) {

            System.out.println(
                    "Could not view risk assessments."
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
}