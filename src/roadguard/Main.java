package roadguard;

import java.util.Scanner;
import roadguard.dao.UserDAO;
import roadguard.service.AuthService;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        UserDAO userDAO = new UserDAO();
        AuthService authService = new AuthService(userDAO);

        boolean running = true;
        while (running) {
            System.out.println("\n===== ROADGUARD SYSTEM =====");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    System.out.print("Password: ");
                    String pass = sc.nextLine();
                    System.out.print("Role (CITIZEN/AUTHORITY/ADMIN): ");
                    String role = sc.nextLine().toUpperCase();

                    boolean reg = authService.registerUser(name, email, pass, role);
                    System.out.println(reg ? "Registration successful!" : "Registration failed.");
                    break;

                case "2":
                    System.out.print("Email: ");
                    String loginEmail = sc.nextLine();
                    System.out.print("Password: ");
                    String loginPass = sc.nextLine();

                    user u = authService.login(loginEmail, loginPass);
                    if (u != null) {
                        System.out.println("Login successful! Welcome " + u.getName() + " (" + u.getRole() + ")");
                    } else {
                        System.out.println("Invalid email or password.");
                    }
                    break;

                case "3":
                    running = false;
                    System.out.println("Exiting RoadGuard...");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }
        sc.close();
    }
}
