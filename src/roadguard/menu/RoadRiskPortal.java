package roadguard.menu;

import java.util.Scanner;

import roadguard.dao.RoadRiskDAO;

public class RoadRiskPortal {

    private Scanner scanner;
    private RoadRiskDAO roadRiskDAO;

    public RoadRiskPortal(Scanner scanner) {

        this.scanner = scanner;
        this.roadRiskDAO = new RoadRiskDAO();
    }

    public void start() {

        boolean running = true;

        while (running) {

            System.out.println("\n===== ROAD RISK MANAGEMENT =====");
            System.out.println("1. Add Road Risk");
            System.out.println("2. View Road Risks");
            System.out.println("3. Back");

            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {

                case "1":
                    addRisk();
                    break;

                case "2":
                    roadRiskDAO.viewRisks();
                    break;

                case "3":
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }


    private void addRisk() {

        System.out.println("\n===== ADD ROAD RISK =====");

        System.out.print("Enter location: ");
        String location = scanner.nextLine();

        System.out.print("Enter road condition (GOOD/MODERATE/BAD): ");
        String roadCondition = scanner.nextLine().toUpperCase();

        System.out.print("Enter traffic level (LOW/MEDIUM/HIGH): ");
        String trafficLevel = scanner.nextLine().toUpperCase();

        System.out.print("Enter accident count: ");
        int accidentCount = Integer.parseInt(scanner.nextLine());

        String riskLevel;

        if (roadCondition.equals("BAD")
                && trafficLevel.equals("HIGH")) {

            riskLevel = "HIGH";

        } else if (roadCondition.equals("BAD")
                || trafficLevel.equals("HIGH")
                || accidentCount >= 5) {

            riskLevel = "MEDIUM";

        } else {

            riskLevel = "LOW";
        }

        System.out.println("Calculated Risk: " + riskLevel);

        boolean success = roadRiskDAO.addRisk(
                location,
                roadCondition,
                trafficLevel,
                accidentCount,
                riskLevel
        );

        if (success) {
            System.out.println("Road risk added successfully!");
        } else {
            System.out.println("Failed to add road risk.");
        }
    }
}