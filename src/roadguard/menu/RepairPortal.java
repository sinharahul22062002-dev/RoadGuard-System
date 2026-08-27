package roadguard.menu;

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
            String choice = scanner.nextLine();

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
                    System.out.println("Invalid choice.");
            }
        }
    }


    private void addRepair() {

        System.out.println("\n===== ADD REPAIR =====");

        System.out.print("Enter location: ");
        String location = scanner.nextLine();

        System.out.print("Enter repair description: ");
        String description = scanner.nextLine();

        boolean success =
                repairDAO.addRepair(location, description);

        if (success) {
            System.out.println("Repair added successfully!");
        } else {
            System.out.println("Failed to add repair.");
        }
    }


    private void updateStatus() {

        System.out.println("\n===== UPDATE REPAIR STATUS =====");

        System.out.print("Enter repair ID: ");
        int repairId =
                Integer.parseInt(scanner.nextLine());

        System.out.println("1. PENDING");
        System.out.println("2. IN_PROGRESS");
        System.out.println("3. COMPLETED");

        System.out.print("Enter choice: ");
        String choice = scanner.nextLine();

        String status;

        if (choice.equals("1")) {

            status = "PENDING";

        } else if (choice.equals("2")) {

            status = "IN_PROGRESS";

        } else if (choice.equals("3")) {

            status = "COMPLETED";

        } else {

            System.out.println("Invalid choice.");
            return;
        }

        boolean success =
                repairDAO.updateStatus(repairId, status);

        if (success) {
            System.out.println("Repair status updated!");
        } else {
            System.out.println("Update failed.");
        }
    }
}