package roadguard.menu;

import java.util.Scanner;

import roadguard.service.AuthService;

public class Registration {

    private AuthService authService;
    private Scanner scanner;

    public Registration(AuthService authService, Scanner scanner) {
        this.authService = authService;
        this.scanner = scanner;
    }

    public void start() {

        try {

            System.out.println("\n===== USER REGISTRATION =====");

            System.out.print("Enter name: ");
            String name = scanner.nextLine();

            System.out.print("Enter email: ");
            String email = scanner.nextLine();

            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            System.out.print(
                    "Enter role (CITIZEN/AUTHORITY/ADMIN): "
            );
            String role = scanner.nextLine().trim().toUpperCase();

            boolean registered = authService.registerUser(
                    name,
                    email,
                    password,
                    role
            );

            if (registered) {

                System.out.println(
                        "\nRegistration successful!"
                );

            } else {

                System.out.println(
                        "\nRegistration failed."
                );
            }

        } catch (IllegalStateException e) {

            System.out.println(
                    "Input system error: " + e.getMessage()
            );
        }
    }
}