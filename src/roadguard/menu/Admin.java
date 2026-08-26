package roadguard.menu;

import java.util.Scanner;

import roadguard.service.UserService;
import roadguard.user;

public class Admin {

    private Scanner scanner;
    private UserService userService;

    public Admin(UserService userService, Scanner scanner) {
        this.userService = userService;
        this.scanner = scanner;
    }

    public void start() {

        boolean running = true;

        while (running) {

            System.out.println("\n===== ADMIN PORTAL =====");
            System.out.println("1. Find User");
            System.out.println("2. Update User Name");
            System.out.println("3. Update User Email");
            System.out.println("4. Delete User");
            System.out.println("5. Logout");

            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {

                case "1":
                    findUser();
                    break;

                case "2":
                    updateUserName();
                    break;

                case "3":
                    updateUserEmail();
                    break;

                case "4":
                    deleteUser();
                    break;

                case "5":
                    System.out.println("\nLogging out...");
                    running = false;
                    break;

                default:
                    System.out.println(
                            "\nInvalid choice. Please try again."
                    );
            }
        }
    }

    private void findUser() {

        System.out.print("Enter user ID: ");
        int userId = Integer.parseInt(scanner.nextLine());

        user foundUser = userService.findUserById(userId);

        if (foundUser != null) {
            System.out.println("\nUser found:");
            System.out.println(foundUser);
        } else {
            System.out.println("\nUser not found.");
        }
    }

    private void updateUserName() {

        System.out.print("Enter user ID: ");
        int userId = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter new name: ");
        String newName = scanner.nextLine();

        boolean updated =
                userService.updateUserName(userId, newName);

        if (updated) {
            System.out.println("\nName updated successfully.");
        } else {
            System.out.println("\nFailed to update name.");
        }
    }

    private void updateUserEmail() {

        System.out.print("Enter user ID: ");
        int userId = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter new email: ");
        String newEmail = scanner.nextLine();

        boolean updated =
                userService.updateUserEmail(userId, newEmail);

        if (updated) {
            System.out.println("\nEmail updated successfully.");
        } else {
            System.out.println(
                    "\nFailed to update email."
            );
        }
    }

    private void deleteUser() {

        System.out.print("Enter user ID: ");
        int userId = Integer.parseInt(scanner.nextLine());

        boolean deleted =
                userService.deleteUser(userId);

        if (deleted) {
            System.out.println("\nUser deleted successfully.");
        } else {
            System.out.println("\nUser not found.");
        }
    }
}