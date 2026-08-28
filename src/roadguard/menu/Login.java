package roadguard.menu;

import java.util.Scanner;
import roadguard.service.AuthService;
import roadguard.service.UserService;
import roadguard.user;

public class Login {

    private AuthService authService;
    private UserService userService;
    private Scanner scanner;

    public Login(AuthService authService,
                 UserService userService,
                 Scanner scanner) {

        this.authService = authService;
        this.userService = userService;
        this.scanner = scanner;
    }

    public void start() {

        System.out.println("\n===== USER LOGIN =====");

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        user loggedInUser = authService.login(email, password);

        if (loggedInUser == null) {

            System.out.println("\nInvalid email or password.");
            return;
        }

        System.out.println("\nLogin successful!");
        System.out.println("Welcome, " + loggedInUser.getName());
        System.out.println("Role: " + loggedInUser.getRole());

        openRoleMenu(loggedInUser);
    }

    private void openRoleMenu(user loggedInUser) {

        String role = loggedInUser.getRole();

        if (role.equals("CITIZEN")) {

            Citizen citizen =
                    new Citizen(loggedInUser.getEmail(), scanner);

            citizen.start();

        } else if (role.equals("AUTHORITY")) {

            Authority authority = new Authority(scanner);

            authority.start();

        } else if (role.equals("ADMIN")) {

            Admin admin = new Admin(userService, scanner);

            admin.start();

        } else {

            System.out.println("Invalid user role.");
        }
    }
}