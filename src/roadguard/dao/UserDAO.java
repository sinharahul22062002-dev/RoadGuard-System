package roadguard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import roadguard.connection.DBConnection;
import roadguard.user;

public class UserDAO {

    // Add new user
    public boolean addUser(user newUser) {

        String sql =
                "INSERT INTO users " +
                "(name, email, password_hash, role) " +
                "VALUES (?, ?, ?, ?)";

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            if (connection == null) {
                System.out.println(
                        "Cannot add user. Database connection failed."
                );
                return false;
            }

            statement.setString(1, newUser.getName());
            statement.setString(2, newUser.getEmail());
            statement.setString(3, newUser.getPasswordHash());
            statement.setString(4, newUser.getRole());

            int rows = statement.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Could not add user."
            );

            return false;
        }
    }


    // Find user by email
    public user findByEmail(String email) {

        String sql =
                "SELECT * FROM users WHERE email = ?";

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            if (connection == null) {
                System.out.println(
                        "Cannot search user. Database connection failed."
                );
                return null;
            }

            statement.setString(1, email);

            ResultSet resultSet =
                    statement.executeQuery();

            if (resultSet.next()) {

                return createUserFromResultSet(
                        resultSet
                );
            }

        } catch (SQLException e) {

            System.out.println(
                    "Could not find user."
            );
        }

        return null;
    }


    // Find user by ID
    public user findById(int userId) {

        String sql =
                "SELECT * FROM users WHERE user_id = ?";

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            if (connection == null) {
                System.out.println(
                        "Cannot search user. Database connection failed."
                );
                return null;
            }

            statement.setInt(1, userId);

            ResultSet resultSet =
                    statement.executeQuery();

            if (resultSet.next()) {

                return createUserFromResultSet(
                        resultSet
                );
            }

        } catch (SQLException e) {

            System.out.println(
                    "Could not find user."
            );
        }

        return null;
    }


    // Update user name
    public boolean updateName(
            int userId,
            String newName) {

        String sql =
                "UPDATE users SET name = ? " +
                "WHERE user_id = ?";

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            if (connection == null) {
                System.out.println(
                        "Cannot update name. Database connection failed."
                );
                return false;
            }

            statement.setString(1, newName);
            statement.setInt(2, userId);

            int rows =
                    statement.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Could not update user name."
            );

            return false;
        }
    }


    // Update user email
    public boolean updateEmail(
            int userId,
            String newEmail) {

        String sql =
                "UPDATE users SET email = ? " +
                "WHERE user_id = ?";

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            if (connection == null) {
                System.out.println(
                        "Cannot update email. Database connection failed."
                );
                return false;
            }

            statement.setString(1, newEmail);
            statement.setInt(2, userId);

            int rows =
                    statement.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Could not update user email."
            );

            return false;
        }
    }


    // Delete user
    public boolean deleteUser(int userId) {

        String sql =
                "DELETE FROM users WHERE user_id = ?";

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            if (connection == null) {
                System.out.println(
                        "Cannot delete user. Database connection failed."
                );
                return false;
            }

            statement.setInt(1, userId);

            int rows =
                    statement.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Could not delete user."
            );

            return false;
        }
    }


    // Convert ResultSet into user object
    private user createUserFromResultSet(
            ResultSet resultSet)
            throws SQLException {

        int userId =
                resultSet.getInt("user_id");

        String name =
                resultSet.getString("name");

        String email =
                resultSet.getString("email");

        String passwordHash =
                resultSet.getString("password_hash");

        String role =
                resultSet.getString("role");

        java.sql.Timestamp timestamp =
                resultSet.getTimestamp("created_at");

        java.time.LocalDateTime createdAt = null;

        if (timestamp != null) {

            createdAt =
                    timestamp.toLocalDateTime();
        }

        return new user(
                userId,
                name,
                email,
                passwordHash,
                role,
                createdAt
        );
    }
}