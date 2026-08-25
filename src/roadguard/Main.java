package roadguard;

import java.util.Scanner;

import roadguard.dao.UserDAO;
import roadguard.menu.Login;
import roadguard.menu.Registration;
import roadguard.service.AuthService;
import roadguard.service.UserService;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // DAO
        UserDAO userDAO = new UserDAO();

        // Services
        AuthService authService = new AuthService(userDAO);
        UserService userService = new UserService(userDAO);

        boolean running = true;

        while (running) {

            System.out.println("\n===== ROADGUARD SYSTEM =====");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {

                case "1":

                    Registration registration =
                            new Registration(authService, scanner);

                    registration.start();

                    break;

                case "2":

                    Login login =
                            new Login(
                                    authService,
                                    userService,
                                    scanner
                            );

                    login.start();

                    break;

                case "3":

                    running = false;

                    System.out.println(
                            "\nThank you for using RoadGuard."
                    );

                    break;

                default:

                    System.out.println(
                            "\nInvalid choice. Please try again."
                    );
            }
        }

        scanner.close();
    }
}