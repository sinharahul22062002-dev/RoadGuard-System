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
            System.out.println("2. Risk Calculator");
            System.out.println("3. Logout");

            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {

                case "1":

                    ComplaintPortal complaintPortal =
                            new ComplaintPortal(email, scanner);

                    complaintPortal.start();

                    break;

                case "2":

                    RiskCalculator riskCalculator =
                            new RiskCalculator(scanner);

                    riskCalculator.start();

                    break;

                case "3":

                    System.out.println("\nLogging out...");

                    running = false;

                    break;

                default:

                    System.out.println(
                            "\nInvalid choice. Please enter 1, 2 or 3."
                    );
            }
        }
    }
}