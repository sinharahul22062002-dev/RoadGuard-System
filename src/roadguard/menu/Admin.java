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

            String choice = scanner.nextLine().trim();

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

                    System.out.println(
                            "\nLogging out..."
                    );

                    running = false;
                    break;

                default:

                    System.out.println(
                            "\nInvalid choice. Please enter 1-5."
                    );
            }
        }
    }


    // Find User
    private void findUser() {

        System.out.println("\n===== FIND USER =====");

        int userId;

        try {

            System.out.print("Enter user ID: ");

            userId = Integer.parseInt(
                    scanner.nextLine().trim()
            );

        } catch (NumberFormatException e) {

            System.out.println(
                    "Invalid user ID. Please enter a number."
            );

            return;
        }


        if (userId <= 0) {

            System.out.println(
                    "User ID must be a positive number."
            );

            return;
        }


        user foundUser =
                userService.findUserById(userId);

        if (foundUser != null) {

            System.out.println("\nUser found:");
            System.out.println("-------------------------");

            System.out.println(
                    "User ID: " + foundUser.getUserId()
            );

            System.out.println(
                    "Name: " + foundUser.getName()
            );

            System.out.println(
                    "Email: " + foundUser.getEmail()
            );

            System.out.println(
                    "Role: " + foundUser.getRole()
            );

            System.out.println(
                    "Created At: " + foundUser.getCreatedAt()
            );

        } else {

            System.out.println(
                    "\nUser not found."
            );
        }
    }


    // Update User Name
    private void updateUserName() {

        System.out.println("\n===== UPDATE USER NAME =====");

        int userId;

        try {

            System.out.print("Enter user ID: ");

            userId = Integer.parseInt(
                    scanner.nextLine().trim()
            );

        } catch (NumberFormatException e) {

            System.out.println(
                    "Invalid user ID. Please enter a number."
            );

            return;
        }


        if (userId <= 0) {

            System.out.println(
                    "User ID must be a positive number."
            );

            return;
        }


        System.out.print("Enter new name: ");

        String newName =
                scanner.nextLine().trim();


        if (newName.isEmpty()) {

            System.out.println(
                    "Name cannot be empty."
            );

            return;
        }


        boolean updated =
                userService.updateUserName(
                        userId,
                        newName
                );

        if (updated) {

            System.out.println(
                    "\nName updated successfully."
            );

        } else {

            System.out.println(
                    "\nUser not found or name update failed."
            );
        }
    }


    // Update User Email
    private void updateUserEmail() {

        System.out.println("\n===== UPDATE USER EMAIL =====");

        int userId;

        try {

            System.out.print("Enter user ID: ");

            userId = Integer.parseInt(
                    scanner.nextLine().trim()
            );

        } catch (NumberFormatException e) {

            System.out.println(
                    "Invalid user ID. Please enter a number."
            );

            return;
        }


        if (userId <= 0) {

            System.out.println(
                    "User ID must be a positive number."
            );

            return;
        }


        System.out.print("Enter new email: ");

        String newEmail =
                scanner.nextLine().trim();


        if (newEmail.isEmpty()) {

            System.out.println(
                    "Email cannot be empty."
            );

            return;
        }


        if (!newEmail.contains("@")) {

            System.out.println(
                    "Invalid email address."
            );

            return;
        }


        boolean updated =
                userService.updateUserEmail(
                        userId,
                        newEmail
                );

        if (updated) {

            System.out.println(
                    "\nEmail updated successfully."
            );

        } else {

            System.out.println(
                    "\nUser not found, email already exists, "
                    + "or email update failed."
            );
        }
    }


    // Delete User
    private void deleteUser() {

        System.out.println("\n===== DELETE USER =====");

        int userId;

        try {

            System.out.print("Enter user ID: ");

            userId = Integer.parseInt(
                    scanner.nextLine().trim()
            );

        } catch (NumberFormatException e) {

            System.out.println(
                    "Invalid user ID. Please enter a number."
            );

            return;
        }


        if (userId <= 0) {

            System.out.println(
                    "User ID must be a positive number."
            );

            return;
        }


        System.out.print(
                "Are you sure you want to delete this user? (YES/NO): "
        );

        String confirmation =
                scanner.nextLine().trim().toUpperCase();


        if (!confirmation.equals("YES")) {

            System.out.println(
                    "User deletion cancelled."
            );

            return;
        }


        boolean deleted =
                userService.deleteUser(userId);

        if (deleted) {

            System.out.println(
                    "\nUser deleted successfully."
            );

        } else {

            System.out.println(
                    "\nUser not found or could not be deleted."
            );
        }
    }
}