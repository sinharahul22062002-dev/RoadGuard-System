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
            System.out.println("1. Add Risk Assessment");
            System.out.println("2. View Risk Assessments");
            System.out.println("3. Back");

            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine().trim();

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
                    System.out.println(
                            "Invalid choice. Please enter 1, 2 or 3."
                    );
            }
        }
    }


    private void addRisk() {

        System.out.println("\n===== ADD RISK ASSESSMENT =====");

        int locationId;

        // Location ID
        try {

            System.out.print("Enter location ID: ");

            locationId = Integer.parseInt(
                    scanner.nextLine().trim()
            );

            if (locationId <= 0) {

                System.out.println(
                        "Location ID must be a positive number."
                );

                return;
            }

        } catch (NumberFormatException e) {

            System.out.println(
                    "Invalid location ID. Please enter a number."
            );

            return;
        }


        // Accident Score
        Double accidentScore = readScore(
                "Enter accident score (0-100): "
        );

        if (accidentScore == null) {
            return;
        }


        // Road Score
        Double roadScore = readScore(
                "Enter road condition score (0-100): "
        );

        if (roadScore == null) {
            return;
        }


        // Traffic Score
        Double trafficScore = readScore(
                "Enter traffic score (0-100): "
        );

        if (trafficScore == null) {
            return;
        }


        // Complaint Score
        Double complaintScore = readScore(
                "Enter complaint score (0-100): "
        );

        if (complaintScore == null) {
            return;
        }


        // Calculate total score
        double totalScore =
                (accidentScore
                + roadScore
                + trafficScore
                + complaintScore) / 4.0;


        // Calculate risk level
        String riskLevel;

        if (totalScore >= 90) {

            riskLevel = "CRITICAL";

        } else if (totalScore >= 75) {

            riskLevel = "HIGH";

        } else if (totalScore >= 50) {

            riskLevel = "MEDIUM";

        } else {

            riskLevel = "LOW";
        }


        System.out.println(
                "\nCalculated Total Score: "
                + totalScore
        );

        System.out.println(
                "Calculated Risk Level: "
                + riskLevel
        );


        // Save assessment
        boolean success =
                roadRiskDAO.addRisk(
                        locationId,
                        accidentScore,
                        roadScore,
                        trafficScore,
                        complaintScore,
                        totalScore,
                        riskLevel
                );


        if (success) {

            System.out.println(
                    "\nRisk assessment added successfully!"
            );

        } else {

            System.out.println(
                    "\nFailed to add risk assessment."
            );
        }
    }


    private Double readScore(String message) {

        try {

            System.out.print(message);

            double score = Double.parseDouble(
                    scanner.nextLine().trim()
            );

            if (score < 0 || score > 100) {

                System.out.println(
                        "Score must be between 0 and 100."
                );

                return null;
            }

            return score;

        } catch (NumberFormatException e) {

            System.out.println(
                    "Invalid score. Please enter a number."
            );

            return null;
        }
    }
}