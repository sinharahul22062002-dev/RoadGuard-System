package roadguard.menu;

import java.util.Scanner;

import roadguard.dao.ComplaintDAO;

public class ComplaintPortal {

    private Scanner scanner;
    private ComplaintDAO complaintDAO;
    private String email;

    public ComplaintPortal(String email) {
        this.email = email;
        scanner = new Scanner(System.in);
        complaintDAO = new ComplaintDAO();
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
                    submitComplaint();
                    break;

                case "2":
                    viewComplaints();
                    break;

                case "3":
                    System.out.println("Logging out...");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private void submitComplaint() {

        System.out.println("\n===== SUBMIT COMPLAINT =====");

        System.out.print("Enter complaint description: ");
        String description = scanner.nextLine();

        System.out.print("Enter location: ");
        String location = scanner.nextLine();

        boolean success =
                complaintDAO.addComplaint(email, description, location);

        if (success) {
            System.out.println("\nComplaint submitted successfully!");
        } else {
            System.out.println("\nComplaint submission failed.");
        }
    }

    private void viewComplaints() {

        System.out.println("\n===== MY COMPLAINTS =====");

        System.out.println(
                "Complaint viewing feature is not available yet."
        );
    }
}