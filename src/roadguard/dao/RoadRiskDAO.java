package roadguard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import roadguard.connection.DBConnection;

public class RoadRiskDAO {

    public boolean addRisk(String location,
                           String roadCondition,
                           String trafficLevel,
                           int accidentCount,
                           String riskLevel) {

        try {

            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO road_risks " +
                         "(location, road_condition, traffic_level, " +
                         "accident_count, risk_level) " +
                         "VALUES (?, ?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, location);
            ps.setString(2, roadCondition);
            ps.setString(3, trafficLevel);
            ps.setInt(4, accidentCount);
            ps.setString(5, riskLevel);

            ps.executeUpdate();

            con.close();

            return true;

        } catch (Exception e) {

            System.out.println("Could not add road risk!");
            return false;
        }
    }


    public void viewRisks() {

        try {

            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM road_risks";

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                System.out.println("\n-------------------------");

                System.out.println("Risk ID: "
                        + rs.getInt("risk_id"));

                System.out.println("Location: "
                        + rs.getString("location"));

                System.out.println("Road Condition: "
                        + rs.getString("road_condition"));

                System.out.println("Traffic Level: "
                        + rs.getString("traffic_level"));

                System.out.println("Accidents: "
                        + rs.getInt("accident_count"));

                System.out.println("Risk Level: "
                        + rs.getString("risk_level"));
            }

            con.close();

        } catch (Exception e) {

            System.out.println("Could not view road risks!");
        }
    }
}