package roadguard.menu;

import java.util.Scanner;

public class RiskCalculator {

    private Scanner scanner;

    public RiskCalculator(Scanner scanner) {
        this.scanner = scanner;
    }

    public void start() {

        System.out.println("\n===== ROAD RISK CALCULATOR =====");

        System.out.println("\nTraffic Level:");
        System.out.println("1. Low");
        System.out.println("2. Medium");
        System.out.println("3. High");

        System.out.print("Enter choice: ");
        int traffic = Integer.parseInt(scanner.nextLine());

        System.out.println("\nRoad Condition:");
        System.out.println("1. Good");
        System.out.println("2. Moderate");
        System.out.println("3. Bad");

        System.out.print("Enter choice: ");
        int road = Integer.parseInt(scanner.nextLine());

        System.out.println("\nWeather:");
        System.out.println("1. Clear");
        System.out.println("2. Rainy");
        System.out.println("3. Foggy");

        System.out.print("Enter choice: ");
        int weather = Integer.parseInt(scanner.nextLine());

        System.out.print("\nEnter recent accident count: ");
        int accidents = Integer.parseInt(scanner.nextLine());

        int score = 0;

       
        if (traffic == 1) {
            score += 10;
        } else if (traffic == 2) {
            score += 20;
        } else if (traffic == 3) {
            score += 30;
        }

        if (road == 1) {
            score += 10;
        } else if (road == 2) {
            score += 20;
        } else if (road == 3) {
            score += 30;
        }

   
        if (weather == 1) {
            score += 5;
        } else if (weather == 2) {
            score += 15;
        } else if (weather == 3) {
            score += 25;
        }

     
       score += accidents * 5;

        score = Math.min(score, 100);

        String risk;

        if (score <= 30) {

            risk = "LOW";

        } else if (score <= 60) {

            risk = "MEDIUM";

        } else {

            risk = "HIGH";
        }

        System.out.println("\n===== RESULT =====");
        System.out.println("Risk Score: " + score);
        System.out.println("Risk Level: " + risk);
    }
}