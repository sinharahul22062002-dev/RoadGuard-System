package roadguard.menu;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import roadguard.dao.RepairDAO;

public class RepairPortal {

    private Scanner scanner;
    private RepairDAO repairDAO;

    public RepairPortal(Scanner scanner) {
        this.scanner = scanner;
        this.repairDAO = new RepairDAO();
    }

    public void start() {

        boolean running = true;

        while (running) {

            System.out.println("\n===== REPAIR MANAGEMENT =====");
            System.out.println("1. Add Repair");
            System.out.println("2. View Repairs");
            System.out.println("3. Update Repair Status");
            System.out.println("4. Back");

            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {

                case "1":
                    addRepair();
                    break;

                case "2":
                    repairDAO.viewRepairs();
                    break;

                case "3":
                    updateStatus();
                    break;

                case "4":
                    running = false;
                    break;

                default:
                    System.out.println(
                            "Invalid choice. Please enter 1, 2, 3 or 4."
                    );
            }
        }
    }


    private void addRepair() {

        System.out.println("\n===== ADD REPAIR =====");

        int complaintId;

        // Complaint ID
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


        int assignedTo;

        // Assigned Authority ID
        try {

            System.out.print(
                    "Enter assigned authority user ID: "
            );

            assignedTo = Integer.parseInt(
                    scanner.nextLine().trim()
            );

            if (assignedTo <= 0) {
                System.out.println(
                        "User ID must be a positive number."
                );
                return;
            }

        } catch (NumberFormatException e) {

            System.out.println(
                    "Invalid user ID. Please enter a number."
            );
            return;
        }


        // Repair Type
        System.out.println("\nSelect Repair Type:");
        System.out.println("1. POTHOLE_REPAIR");
        System.out.println("2. ROAD_REPAIR");
        System.out.println("3. STREETLIGHT_REPAIR");
        System.out.println("4. SIGNAGE_REPAIR");
        System.out.println("5. DRAINAGE_REPAIR");
        System.out.println("6. OTHER");

        System.out.print("Enter choice: ");

        String typeChoice =
                scanner.nextLine().trim();

        String repairType;

        switch (typeChoice) {

            case "1":
                repairType = "POTHOLE_REPAIR";
                break;

            case "2":
                repairType = "ROAD_REPAIR";
                break;

            case "3":
                repairType = "STREETLIGHT_REPAIR";
                break;

            case "4":
                repairType = "SIGNAGE_REPAIR";
                break;

            case "5":
                repairType = "DRAINAGE_REPAIR";
                break;

            case "6":
                repairType = "OTHER";
                break;

            default:
                System.out.println(
                        "Invalid repair type."
                );
                return;
        }


        // Status
        System.out.println("\nSelect Status:");
        System.out.println("1. ASSIGNED");
        System.out.println("2. IN_PROGRESS");
        System.out.println("3. COMPLETED");
        System.out.println("4. CANCELLED");

        System.out.print("Enter choice: ");

        String statusChoice =
                scanner.nextLine().trim();

        String status;

        switch (statusChoice) {

            case "1":
                status = "ASSIGNED";
                break;

            case "2":
                status = "IN_PROGRESS";
                break;

            case "3":
                status = "COMPLETED";
                break;

            case "4":
                status = "CANCELLED";
                break;

            default:
                System.out.println(
                        "Invalid status."
                );
                return;
        }


        // Assigned Date
        System.out.print(
                "Enter assigned date (YYYY-MM-DD): "
        );

        String assignedDate =
                scanner.nextLine().trim();

        if (assignedDate.isEmpty()) {

            System.out.println(
                    "Assigned date cannot be empty."
            );
            return;
        }

        // Validate date format
        try {

            LocalDate.parse(assignedDate);

        } catch (DateTimeParseException e) {

            System.out.println(
                    "Invalid date. Please use YYYY-MM-DD format."
            );
            return;
        }


        // Add repair
        boolean success =
                repairDAO.addRepair(
                        complaintId,
                        assignedTo,
                        repairType,
                        status,
                        assignedDate
                );


        if (success) {

            System.out.println(
                    "\nRepair added successfully!"
            );

        } else {

            System.out.println(
                    "\nFailed to add repair."
            );
        }
    }


    private void updateStatus() {

        System.out.println(
                "\n===== UPDATE REPAIR STATUS ====="
        );

        int repairId;

        try {

            System.out.print("Enter repair ID: ");

            repairId = Integer.parseInt(
                    scanner.nextLine().trim()
            );

            if (repairId <= 0) {

                System.out.println(
                        "Repair ID must be a positive number."
                );

                return;
            }

        } catch (NumberFormatException e) {

            System.out.println(
                    "Invalid repair ID. Please enter a number."
            );

            return;
        }


        System.out.println("\n1. ASSIGNED");
        System.out.println("2. IN_PROGRESS");
        System.out.println("3. COMPLETED");
        System.out.println("4. CANCELLED");

        System.out.print("Enter choice: ");

        String choice =
                scanner.nextLine().trim();

        String status;

        switch (choice) {

            case "1":
                status = "ASSIGNED";
                break;

            case "2":
                status = "IN_PROGRESS";
                break;

            case "3":
                status = "COMPLETED";
                break;

            case "4":
                status = "CANCELLED";
                break;

            default:
                System.out.println(
                        "Invalid status choice."
                );
                return;
        }


        boolean success =
                repairDAO.updateStatus(
                        repairId,
                        status
                );


        if (success) {

            System.out.println(
                    "Repair status updated successfully!"
            );

        } else {

            System.out.println(
                    "Repair status update failed."
            );
        }
    }
}