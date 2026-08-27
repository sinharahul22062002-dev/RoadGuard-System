package roadguard.menu;

import java.util.Scanner;

public class Citizen {

    private Scanner scanner;
    private String email;

    public Citizen(String email, Scanner scanner) {

        this.email = email;
        this.scanner = scanner;
    }

    public void start() {

        boolean running = true;

        while (running) {

            System.out.println("\n===== CITIZEN PORTAL =====");
            System.out.println("1. Complaint Portal");
            System.out.println("2. Logout");

            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {

                case "1":

                    ComplaintPortal portal =
                            new ComplaintPortal(email, scanner);

                    portal.start();

                    break;

                case "2":

                    System.out.println("\nLogging out...");

                    running = false;

                    break;

                default:

                    System.out.println("\nInvalid choice.");
            }
        }
    }
}