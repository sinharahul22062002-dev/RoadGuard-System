package roadguard.menu;

import java.util.Scanner;

public class Authority {

    private Scanner scanner;

    public Authority() {
        scanner = new Scanner(System.in);
    }

    public void start() {

        boolean running = true;

        while (running) {

            System.out.println("\n===== AUTHORITY PORTAL =====");
            System.out.println("1. View Complaints");
            System.out.println("2. View Road Risks");
            System.out.println("3. Manage Repairs");
            System.out.println("4. Logout");

            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {

                case "1":
                    System.out.println(
                            "\nComplaint management will be added later."
                    );
                    break;

                case "2":
                    System.out.println(
                            "\nRoad risk management will be added later."
                    );
                    break;

                case "3":
                    System.out.println(
                            "\nRepair management will be added later."
                    );
                    break;

                case "4":
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