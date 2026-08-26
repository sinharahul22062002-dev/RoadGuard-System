package roadguard.menu;

import java.util.Scanner;

public class Citizen {

    private Scanner scanner;

    public Citizen() {
        scanner = new Scanner(System.in);
    }

    public void start() {

        boolean running = true;

        while (running) {

            System.out.println("\n===== CITIZEN PORTAL =====");
            System.out.println("1. Submit Complaint");
            System.out.println("2. View My Complaints");
            System.out.println("3. Logout");

            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {

                case "1":
                    System.out.println(
                            "\nComplaint feature will be added later."
                    );
                    break;

                case "2":
                    System.out.println(
                            "\nView complaints feature will be added later."
                    );
                    break;

                case "3":
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
}