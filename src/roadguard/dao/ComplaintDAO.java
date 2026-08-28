package roadguard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import roadguard.connection.DBConnection;

public class ComplaintDAO {

    // ADD COMPLAINT

    public boolean addComplaint(String email,
                                String type,
                                String description,
                                String severity,
                                String areaName,
                                String city) {

        Connection con = null;

        try {

            // Basic validation

            if (email == null || email.trim().isEmpty()) {
                throw new IllegalArgumentException(
                        "Email cannot be empty."
                );
            }

            if (type == null || type.trim().isEmpty()) {
                throw new IllegalArgumentException(
                        "Complaint type cannot be empty."
                );
            }

            if (description == null || description.trim().isEmpty()) {
                throw new IllegalArgumentException(
                        "Description cannot be empty."
                );
            }

            if (severity == null || severity.trim().isEmpty()) {
                throw new IllegalArgumentException(
                        "Severity cannot be empty."
                );
            }

            if (areaName == null || areaName.trim().isEmpty()) {
                throw new IllegalArgumentException(
                        "Area name cannot be empty."
                );
            }

            if (city == null || city.trim().isEmpty()) {
                throw new IllegalArgumentException(
                        "City cannot be empty."
                );
            }


            // Database connection

            con = DBConnection.getConnection();

            if (con == null) {
                System.out.println(
                        "Unable to connect to database."
                );
                return false;
            }


            // STEP 1: Find User ID using Email

            String userSql =
                    "SELECT user_id FROM users WHERE email = ?";

            PreparedStatement userPs =
                    con.prepareStatement(userSql);

            userPs.setString(1, email.trim());

            ResultSet userRs =
                    userPs.executeQuery();

            if (!userRs.next()) {

                System.out.println("User not found.");

                userRs.close();
                userPs.close();
                con.close();

                return false;
            }

            int userId =
                    userRs.getInt("user_id");

            userRs.close();
            userPs.close();


            // STEP 2: Find Location

            String locationSql =
                    "SELECT location_id FROM locations " +
                    "WHERE area_name = ? AND city = ?";

            PreparedStatement locationPs =
                    con.prepareStatement(locationSql);

            locationPs.setString(1, areaName.trim());
            locationPs.setString(2, city.trim());

            ResultSet locationRs =
                    locationPs.executeQuery();

            int locationId;

            // Location already exists

            if (locationRs.next()) {

                locationId =
                        locationRs.getInt("location_id");

                System.out.println(
                        "Existing location found."
                );

            }

            // Location does not exist

            else {

                locationRs.close();
                locationPs.close();

                String insertLocationSql =
                        "INSERT INTO locations " +
                        "(area_name, city) VALUES (?, ?)";

                PreparedStatement insertLocationPs =
                        con.prepareStatement(
                                insertLocationSql,
                                java.sql.Statement.RETURN_GENERATED_KEYS
                        );

                insertLocationPs.setString(
                        1,
                        areaName.trim()
                );

                insertLocationPs.setString(
                        2,
                        city.trim()
                );

                int locationRows =
                        insertLocationPs.executeUpdate();

                if (locationRows == 0) {

                    insertLocationPs.close();
                    con.close();

                    System.out.println(
                            "Could not create location."
                    );

                    return false;
                }

                ResultSet generatedKeys =
                        insertLocationPs.getGeneratedKeys();

                if (!generatedKeys.next()) {

                    generatedKeys.close();
                    insertLocationPs.close();
                    con.close();

                    System.out.println(
                            "Could not get location ID."
                    );

                    return false;
                }

                locationId =
                        generatedKeys.getInt(1);

                generatedKeys.close();
                insertLocationPs.close();

                System.out.println(
                        "New location added successfully."
                );
            }


            // STEP 3: Insert Complaint

            String complaintSql =
                    "INSERT INTO complaints " +
                    "(user_id, location_id, type, description, " +
                    "severity, status) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement complaintPs =
                    con.prepareStatement(complaintSql);

            complaintPs.setInt(1, userId);
            complaintPs.setInt(2, locationId);
            complaintPs.setString(3, type);
            complaintPs.setString(
                    4,
                    description.trim()
            );
            complaintPs.setString(5, severity);
            complaintPs.setString(6, "PENDING");

            int rows =
                    complaintPs.executeUpdate();

            complaintPs.close();
            con.close();

            return rows > 0;


        } catch (IllegalArgumentException e) {

            System.out.println(
                    "Invalid complaint data: "
                    + e.getMessage()
            );

            closeConnection(con);

            return false;

        } catch (NullPointerException e) {

            System.out.println(
                    "Required complaint information is missing."
            );

            closeConnection(con);

            return false;

        } catch (Exception e) {

            System.out.println(
                    "Complaint submission failed."
            );

            System.out.println(
                    "Error: " + e.getMessage()
            );

            closeConnection(con);

            return false;
        }
    }


    // CITIZEN: VIEW OWN COMPLAINTS

    public void viewComplaints(String email) {

        Connection con = null;

        try {

            if (email == null || email.trim().isEmpty()) {

                throw new IllegalArgumentException(
                        "Email cannot be empty."
                );
            }


            con = DBConnection.getConnection();

            if (con == null) {

                System.out.println(
                        "Unable to connect to database."
                );

                return;
            }


            String sql =
                    "SELECT c.complaint_id, " +
                    "l.area_name, l.city, " +
                    "c.type, c.description, " +
                    "c.severity, c.status, c.reported_at " +
                    "FROM complaints c " +
                    "JOIN users u " +
                    "ON c.user_id = u.user_id " +
                    "JOIN locations l " +
                    "ON c.location_id = l.location_id " +
                    "WHERE u.email = ? " +
                    "ORDER BY c.complaint_id DESC";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, email.trim());

            ResultSet rs =
                    ps.executeQuery();

            boolean found = false;


            while (rs.next()) {

                found = true;

                System.out.println(
                        "\n-------------------------"
                );

                System.out.println(
                        "Complaint ID: "
                        + rs.getInt("complaint_id")
                );

                System.out.println(
                        "Location: "
                        + rs.getString("area_name")
                        + ", "
                        + rs.getString("city")
                );

                System.out.println(
                        "Type: "
                        + rs.getString("type")
                );

                System.out.println(
                        "Description: "
                        + rs.getString("description")
                );

                System.out.println(
                        "Severity: "
                        + rs.getString("severity")
                );

                System.out.println(
                        "Status: "
                        + rs.getString("status")
                );

                System.out.println(
                        "Reported At: "
                        + rs.getString("reported_at")
                );
            }


            if (!found) {

                System.out.println(
                        "No complaints found."
                );
            }


            rs.close();
            ps.close();
            con.close();


        } catch (IllegalArgumentException e) {

            System.out.println(
                    "Invalid email: "
                    + e.getMessage()
            );

            closeConnection(con);

        } catch (NullPointerException e) {

            System.out.println(
                    "Required information is missing."
            );

            closeConnection(con);

        } catch (Exception e) {

            System.out.println(
                    "Could not view complaints."
            );

            System.out.println(
                    "Error: " + e.getMessage()
            );

            closeConnection(con);
        }
    }


    // AUTHORITY: VIEW ALL COMPLAINTS

    public void viewAllComplaints() {

        Connection con = null;

        try {

            con = DBConnection.getConnection();

            if (con == null) {

                System.out.println(
                        "Unable to connect to database."
                );

                return;
            }


            String sql =
                    "SELECT c.complaint_id, " +
                    "u.email, " +
                    "l.area_name, l.city, " +
                    "c.type, c.description, " +
                    "c.severity, c.status, c.reported_at " +
                    "FROM complaints c " +
                    "JOIN users u " +
                    "ON c.user_id = u.user_id " +
                    "JOIN locations l " +
                    "ON c.location_id = l.location_id " +
                    "ORDER BY c.complaint_id DESC";


            PreparedStatement ps =
                    con.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery();

            boolean found = false;


            while (rs.next()) {

                found = true;

                System.out.println(
                        "\n-------------------------"
                );

                System.out.println(
                        "Complaint ID: "
                        + rs.getInt("complaint_id")
                );

                System.out.println(
                        "Citizen Email: "
                        + rs.getString("email")
                );

                System.out.println(
                        "Location: "
                        + rs.getString("area_name")
                        + ", "
                        + rs.getString("city")
                );

                System.out.println(
                        "Type: "
                        + rs.getString("type")
                );

                System.out.println(
                        "Description: "
                        + rs.getString("description")
                );

                System.out.println(
                        "Severity: "
                        + rs.getString("severity")
                );

                System.out.println(
                        "Status: "
                        + rs.getString("status")
                );

                System.out.println(
                        "Reported At: "
                        + rs.getString("reported_at")
                );
            }


            if (!found) {

                System.out.println(
                        "No complaints found."
                );
            }


            rs.close();
            ps.close();
            con.close();


        } catch (NullPointerException e) {

            System.out.println(
                    "Required information is missing."
            );

            closeConnection(con);

        } catch (Exception e) {

            System.out.println(
                    "Could not view all complaints."
            );

            System.out.println(
                    "Error: " + e.getMessage()
            );

            closeConnection(con);
        }
    }


    // AUTHORITY: UPDATE COMPLAINT STATUS

    public boolean updateStatus(int complaintId,
                                String status) {

        Connection con = null;

        try {

            if (complaintId <= 0) {

                throw new IllegalArgumentException(
                        "Invalid complaint ID."
                );
            }

            if (status == null || status.trim().isEmpty()) {

                throw new IllegalArgumentException(
                        "Status cannot be empty."
                );
            }


            // Check valid status values from SQL ENUM

            if (!status.equals("PENDING")
                    && !status.equals("VERIFIED")
                    && !status.equals("IN_PROGRESS")
                    && !status.equals("RESOLVED")
                    && !status.equals("REJECTED")) {

                throw new IllegalArgumentException(
                        "Invalid complaint status."
                );
            }


            con = DBConnection.getConnection();

            if (con == null) {

                System.out.println(
                        "Unable to connect to database."
                );

                return false;
            }


            String sql =
                    "UPDATE complaints " +
                    "SET status = ? " +
                    "WHERE complaint_id = ?";


            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, status);
            ps.setInt(2, complaintId);

            int rows =
                    ps.executeUpdate();

            ps.close();
            con.close();

            return rows > 0;


        } catch (IllegalArgumentException e) {

            System.out.println(
                    "Invalid status update: "
                    + e.getMessage()
            );

            closeConnection(con);

            return false;

        } catch (NullPointerException e) {

            System.out.println(
                    "Required information is missing."
            );

            closeConnection(con);

            return false;

        } catch (Exception e) {

            System.out.println(
                    "Could not update complaint."
            );

            System.out.println(
                    "Error: " + e.getMessage()
            );

            closeConnection(con);

            return false;
        }
    }

    // SHOW LOCATIONS

    public void showLocations() {

        Connection con = null;

        try {

            con = DBConnection.getConnection();

            if (con == null) {

                System.out.println(
                        "Unable to connect to database."
                );

                return;
            }


            String sql =
                    "SELECT location_id, area_name, city " +
                    "FROM locations " +
                    "ORDER BY city, area_name";


            PreparedStatement ps =
                    con.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery();


            boolean found = false;

            System.out.println(
                    "\n===== AVAILABLE LOCATIONS ====="
            );


            while (rs.next()) {

                found = true;

                System.out.println(
                        rs.getInt("location_id")
                        + ". "
                        + rs.getString("area_name")
                        + ", "
                        + rs.getString("city")
                );
            }


            if (!found) {

                System.out.println(
                        "No locations found."
                );
            }


            rs.close();
            ps.close();
            con.close();


        } catch (NullPointerException e) {

            System.out.println(
                    "Required information is missing."
            );

            closeConnection(con);

        } catch (Exception e) {

            System.out.println(
                    "Could not display locations."
            );

            System.out.println(
                    "Error: " + e.getMessage()
            );

            closeConnection(con);
        }
    }

    // CLOSE CONNECTION

    private void closeConnection(Connection con) {

        if (con != null) {

            try {

                con.close();

            } catch (Exception e) {

                System.out.println(
                        "Could not close database connection."
                );
            }
        }
    }
}