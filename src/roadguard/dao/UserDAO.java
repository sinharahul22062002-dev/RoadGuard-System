package roadguard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import roadguard.connection.DBConnection;
import roadguard.user;

public class UserDAO {

    public boolean addUser(user newUser) {

        String sql = "INSERT INTO users (name, email, password_hash, role) "
                   + "VALUES (?, ?, ?, ?)";

        try (
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement =
                    connection.prepareStatement(sql)
        ) {

            statement.setString(1, newUser.getName());
            statement.setString(2, newUser.getEmail());
            statement.setString(3, newUser.getPasswordHash());
            statement.setString(4, newUser.getRole());

            int rows = statement.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {

            System.out.println("Error adding user.");
            e.printStackTrace();

            return false;
        }
    }


    public user findByEmail(String email) {

        String sql = "SELECT * FROM users WHERE email = ?";

        try (
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement =
                    connection.prepareStatement(sql)
        ) {

            statement.setString(1, email);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return createUserFromResultSet(resultSet);
            }

        } catch (SQLException e) {

            System.out.println("Error finding user.");
            e.printStackTrace();
        }

        return null;
    }


    public user findById(int userId) {

        String sql = "SELECT * FROM users WHERE user_id = ?";

        try (
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement =
                    connection.prepareStatement(sql)
        ) {

            statement.setInt(1, userId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return createUserFromResultSet(resultSet);
            }

        } catch (SQLException e) {

            System.out.println("Error finding user.");
            e.printStackTrace();
        }

        return null;
    }


    public boolean updateName(int userId, String newName) {

        String sql = "UPDATE users SET name = ? WHERE user_id = ?";

        try (
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement =
                    connection.prepareStatement(sql)
        ) {

            statement.setString(1, newName);
            statement.setInt(2, userId);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println("Error updating name.");
            e.printStackTrace();

            return false;
        }
    }


    public boolean updateEmail(int userId, String newEmail) {

        String sql = "UPDATE users SET email = ? WHERE user_id = ?";

        try (
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement =
                    connection.prepareStatement(sql)
        ) {

            statement.setString(1, newEmail);
            statement.setInt(2, userId);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println("Error updating email.");
            e.printStackTrace();

            return false;
        }
    }


    public boolean deleteUser(int userId) {

        String sql = "DELETE FROM users WHERE user_id = ?";

        try (
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement =
                    connection.prepareStatement(sql)
        ) {

            statement.setInt(1, userId);

            int rows = statement.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {

            System.out.println("Error deleting user.");
            e.printStackTrace();

            return false;
        }
    }


    private user createUserFromResultSet(ResultSet resultSet)
            throws SQLException {

        int userId = resultSet.getInt("user_id");

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
            createdAt = timestamp.toLocalDateTime();
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