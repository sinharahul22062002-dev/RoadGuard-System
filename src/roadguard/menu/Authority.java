package roadguard.menu;

import java.util.Scanner;

import roadguard.dao.ComplaintDAO;

public class Authority {

    private Scanner scanner;
    private ComplaintDAO complaintDAO;

    public Authority() {

        scanner = new Scanner(System.in);
        complaintDAO = new ComplaintDAO();
    }

    public void start() {

        boolean running = true;

        while (running) {

            System.out.println("\n===== AUTHORITY PORTAL =====");
            System.out.println("1. View Complaints");
            System.out.println("2. Update Complaint Status");
            System.out.println("3. View Road Risks");
            System.out.println("4. Manage Repairs");
            System.out.println("5. Logout");

            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {

                case "1":

                    viewComplaints();

                    break;

                case "2":

                    updateComplaintStatus();

                    break;

                case "3":

                    System.out.println(
                            "\nRoad risk management will be added later."
                    );

                    break;

                case "4":

                    System.out.println(
                            "\nRepair management will be added later."
                    );

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


    private void viewComplaints() {

        System.out.println("\n===== ALL COMPLAINTS =====");

        complaintDAO.viewAllComplaints();
    }


    private void updateComplaintStatus() {

        System.out.println(
                "\n===== UPDATE COMPLAINT STATUS ====="
        );

        System.out.print("Enter complaint ID: ");

        int complaintId =
                Integer.parseInt(scanner.nextLine());

        System.out.println("\n1. PENDING");
        System.out.println("2. IN_PROGRESS");
        System.out.println("3. RESOLVED");

        System.out.print("Enter status: ");

        String choice = scanner.nextLine();

        String status;

        if (choice.equals("1")) {

            status = "PENDING";

        } else if (choice.equals("2")) {

            status = "IN_PROGRESS";

        } else if (choice.equals("3")) {

            status = "RESOLVED";

        } else {

            System.out.println("Invalid status.");

            return;
        }

        boolean success =
                complaintDAO.updateStatus(
                        complaintId,
                        status
                );

        if (success) {

            System.out.println(
                    "Status updated successfully!"
            );

        } else {

            System.out.println(
                    "Status update failed!"
            );
        }
    }
}