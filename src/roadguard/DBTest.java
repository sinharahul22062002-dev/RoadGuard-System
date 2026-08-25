package roadguard;

import java.sql.Connection;
import roadguard.connection.DBConnection;

public class DBTest {

    public static void main(String[] args) {

        Connection connection = DBConnection.getConnection();

        if (connection != null) {
            System.out.println("Database connected successfully!");

            try {
                connection.close();
                System.out.println("Connection closed.");
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("Database connection failed!");
        }
    }
}