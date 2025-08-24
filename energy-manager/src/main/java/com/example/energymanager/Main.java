package com.example.energymanager;

import com.example.energymanager.strategy.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EnergyManager manager = new EnergyManager();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        // Simulate some initial consumption
        manager.addConsumption(150);

        while (running) {
            printMenu();
            try {
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        changeSystemState(manager, scanner);
                        break;
                    case 2:
                        changePricingPolicy(manager, scanner);
                        break;
                    case 3:
                        System.out.println("\nCurrent Status: " + manager.getCurrentStatusDescription());
                        break;
                    case 4:
                        System.out.printf("\nTotal consumption so far: %.2f units\n", manager.getTotalConsumption());
                        System.out.printf("Total cost based on current policy: $%.2f\n", manager.getTotalCost());
                        break;
                    case 5:
                        System.out.print("Enter consumption amount to simulate (units): ");
                        double units = scanner.nextDouble();
                        System.out.printf("Cost for %.2f units: $%.2f\n", units, manager.simulateCost(units));
                        break;
                    case 0:
                        running = false;
                        System.out.println("Exiting the program...");
                        break;
                    default:
                        System.out.println("Invalid option!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Please enter a number.");
                scanner.next(); // Clear the invalid input
            }
            System.out.println("------------------------------------");
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n*** Smart Energy Management System ***");
        System.out.println("1. Change System State (Admin)");
        System.out.println("2. Change Pricing Policy (Admin)");
        System.out.println("3. View Current System Status");
        System.out.println("4. View Total Energy Cost");
        System.out.println("5. Simulate Consumption Cost");
        System.out.println("0. Exit");
        System.out.print("Please select an option: ");
    }

    private static void changeSystemState(EnergyManager manager, Scanner scanner) {
        System.out.println("\nWhich state do you want to select?");
        System.out.println("1. Active");
        System.out.println("2. Eco Mode");
        System.out.println("3. Shutdown");
        int stateChoice = scanner.nextInt();
        switch (stateChoice) {
            case 1: manager.activate(); break;
            case 2: manager.setToEcoMode(); break;
            case 3: manager.shutdown(); break;
            default: System.out.println("Invalid selection.");
        }
    }

    private static void changePricingPolicy(EnergyManager manager, Scanner scanner) {
        System.out.println("\nWhich pricing policy do you want to select?");
        System.out.println("1. Standard Tariff");
        System.out.println("2. Peak Hours Tariff");
        System.out.println("3. Green Mode Tariff");
        int policyChoice = scanner.nextInt();
        switch (policyChoice) {
            case 1: manager.setPricingStrategy(new StandardPricingStrategy()); break;
            case 2: manager.setPricingStrategy(new PeakHoursPricingStrategy()); break;
            case 3: manager.setPricingStrategy(new GreenModePricingStrategy()); break;
            default: System.out.println("Invalid selection.");
        }
    }
}