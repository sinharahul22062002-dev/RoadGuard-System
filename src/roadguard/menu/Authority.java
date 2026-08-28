package roadguard.menu;

import java.util.InputMismatchException;
import java.util.Scanner;

import roadguard.dao.ComplaintDAO;

public class Authority {

    private Scanner scanner;
    private ComplaintDAO complaintDAO;

    public Authority(Scanner scanner) {

        this.scanner = scanner;
        this.complaintDAO = new ComplaintDAO();
    }

    public void start() {

        boolean running = true;

        while (running) {

            System.out.println("\n===== AUTHORITY PORTAL =====");
            System.out.println("1. View Complaints");
            System.out.println("2. Update Complaint Status");
            System.out.println("3. Road Risk Management");
            System.out.println("4. Repair Management");
            System.out.println("5. Logout");

            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {

                case "1":

                    complaintDAO.viewAllComplaints();

                    break;

                case "2":

                    updateComplaintStatus();

                    break;

                case "3":

                    RoadRiskPortal riskPortal =
                            new RoadRiskPortal(scanner);

                    riskPortal.start();

                    break;

                case "4":

                    RepairPortal repairPortal =
                            new RepairPortal(scanner);

                    repairPortal.start();

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


    private void updateComplaintStatus() {

        System.out.println(
                "\n===== UPDATE COMPLAINT STATUS ====="
        );

        int complaintId;

        try {

            System.out.print("Enter complaint ID: ");

            complaintId = Integer.parseInt(
                    scanner.nextLine().trim()
            );

            if (complaintId <= 0) {

                System.out.println(
                        "Complaint ID must be a positive number."
                );

                return;
            }

        } catch (NumberFormatException e) {

            System.out.println(
                    "Invalid complaint ID. Please enter a number."
            );

            return;
        }


        System.out.println("\n1. PENDING");
        System.out.println("2. IN_PROGRESS");
        System.out.println("3. RESOLVED");

        System.out.print("Enter status: ");

        String choice = scanner.nextLine().trim();

        String status;

        switch (choice) {

            case "1":

                status = "PENDING";

                break;

            case "2":

                status = "IN_PROGRESS";

                break;

            case "3":

                status = "RESOLVED";

                break;

            default:

                System.out.println(
                        "Invalid status choice."
                );

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
                    "Status update failed. Complaint may not exist."
            );
        }
    }
}