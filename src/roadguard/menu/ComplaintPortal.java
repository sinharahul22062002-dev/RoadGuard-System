package roadguard.menu;

import java.util.Scanner;
import roadguard.dao.ComplaintDAO;

public class ComplaintPortal {

    private Scanner scanner;
    private ComplaintDAO complaintDAO;
    private String email;

    public ComplaintPortal(String email, Scanner scanner) {

        this.email = email;
        this.scanner = scanner;
        this.complaintDAO = new ComplaintDAO();
    }


    public void start() {

        boolean running = true;

        while (running) {

            System.out.println("\n===== COMPLAINT PORTAL =====");
            System.out.println("1. Submit Complaint");
            System.out.println("2. View My Complaints");
            System.out.println("3. Back");

            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {

                case "1":
                    submitComplaint();
                    break;

                case "2":
                    viewComplaints();
                    break;

                case "3":
                    running = false;
                    break;

                default:
                    System.out.println(
                            "Invalid choice. Please enter 1, 2 or 3."
                    );
            }
        }
    }


    // Submit Complaint
    private void submitComplaint() {

        System.out.println("\n===== SUBMIT COMPLAINT =====");


        // Complaint Type

        System.out.println("\nSelect Complaint Type:");
        System.out.println("1. POTHOLE");
        System.out.println("2. DAMAGED_ROAD");
        System.out.println("3. BROKEN_STREETLIGHT");
        System.out.println("4. WATERLOGGING");
        System.out.println("5. MISSING_SIGN");
        System.out.println("6. OTHER");

        System.out.print("Enter choice: ");

        String typeChoice =
                scanner.nextLine().trim();

        String type;

        switch (typeChoice) {

            case "1":
                type = "POTHOLE";
                break;

            case "2":
                type = "DAMAGED_ROAD";
                break;

            case "3":
                type = "BROKEN_STREETLIGHT";
                break;

            case "4":
                type = "WATERLOGGING";
                break;

            case "5":
                type = "MISSING_SIGN";
                break;

            case "6":
                type = "OTHER";
                break;

            default:
                System.out.println(
                        "Invalid complaint type."
                );
                return;
        }


        // Description

        System.out.print(
                "\nEnter complaint description: "
        );

        String description =
                scanner.nextLine().trim();


        // Location

        System.out.print("Enter area name: ");

        String areaName =
                scanner.nextLine().trim();


        System.out.print("Enter city: ");

        String city =
                scanner.nextLine().trim();


        // Severity

        System.out.println("\nSelect Severity:");
        System.out.println("1. LOW");
        System.out.println("2. MEDIUM");
        System.out.println("3. HIGH");
        System.out.println("4. CRITICAL");

        System.out.print("Enter choice: ");

        String severityChoice =
                scanner.nextLine().trim();

        String severity;

        switch (severityChoice) {

            case "1":
                severity = "LOW";
                break;

            case "2":
                severity = "MEDIUM";
                break;

            case "3":
                severity = "HIGH";
                break;

            case "4":
                severity = "CRITICAL";
                break;

            default:
                System.out.println(
                        "Invalid severity."
                );
                return;
        }


        // Validation

        if (description.isEmpty()) {

            System.out.println(
                    "Complaint description cannot be empty."
            );

            return;
        }

        if (areaName.isEmpty()) {

            System.out.println(
                    "Area name cannot be empty."
            );

            return;
        }

        if (city.isEmpty()) {

            System.out.println(
                    "City cannot be empty."
            );

            return;
        }


        // Add Complaint

        boolean success =
                complaintDAO.addComplaint(
                        email,
                        type,
                        description,
                        severity,
                        areaName,
                        city
                );


        if (success) {

            System.out.println(
                    "\nComplaint submitted successfully!"
            );

        } else {

            System.out.println(
                    "\nComplaint submission failed."
            );
        }
    }


    // View My Complaints

    private void viewComplaints() {

        System.out.println("\n===== MY COMPLAINTS =====");

        complaintDAO.viewComplaints(email);
    }
}
