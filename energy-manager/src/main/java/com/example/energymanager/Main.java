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
                        System.out.println("\nوضعیت فعلی: " + manager.getCurrentStatusDescription());
                        break;
                    case 4:
                        System.out.printf("\nکل مصرف تا این لحظه: %.2f واحد\n", manager.getTotalConsumption());
                        System.out.printf("هزینه کل بر اساس تعرفه فعلی: %.2f تومان\n", manager.getTotalCost());
                        break;
                    case 5:
                        System.out.print("مقدار مصرف برای شبیه‌سازی (واحد): ");
                        double units = scanner.nextDouble();
                        System.out.printf("هزینه %.2f واحد مصرف: %.2f تومان\n", units, manager.simulateCost(units));
                        break;
                    case 0:
                        running = false;
                        System.out.println("خروج از برنامه...");
                        break;
                    default:
                        System.out.println("گزینه نامعتبر است!");
                }
            } catch (InputMismatchException e) {
                System.out.println("خطا: لطفاً فقط عدد وارد کنید.");
                scanner.next(); // Clear the invalid input
            }
            System.out.println("------------------------------------");
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n*** سیستم مدیریت انرژی هوشمند ***");
        System.out.println("1. تغییر وضعیت سیستم (مخصوص مدیر)");
        System.out.println("2. تغییر سیاست محاسبه هزینه (مخصوص مدیر)");
        System.out.println("3. مشاهده وضعیت فعلی سیستم");
        System.out.println("4. مشاهده هزینه کل مصرف انرژی");
        System.out.println("5. شبیه‌سازی هزینه مصرف");
        System.out.println("0. خروج");
        System.out.print("لطفا گزینه مورد نظر را انتخاب کنید: ");
    }

    private static void changeSystemState(EnergyManager manager, Scanner scanner) {
        System.out.println("\nکدام وضعیت را انتخاب می‌کنید؟");
        System.out.println("1. فعال (Active)");
        System.out.println("2. اقتصادی (Eco)");
        System.out.println("3. خاموش (Shutdown)");
        int stateChoice = scanner.nextInt();
        switch (stateChoice) {
            case 1: manager.activate(); break;
            case 2: manager.setToEcoMode(); break;
            case 3: manager.shutdown(); break;
            default: System.out.println("انتخاب نامعتبر.");
        }
    }

    private static void changePricingPolicy(EnergyManager manager, Scanner scanner) {
        System.out.println("\nکدام سیاست تعرفه را انتخاب می‌کنید؟");
        System.out.println("1. تعرفه معمولی (Standard)");
        System.out.println("2. تعرفه زمان اوج مصرف (Peak Hours)");
        System.out.println("3. تعرفه سبز (Green)");
        int policyChoice = scanner.nextInt();
        switch (policyChoice) {
            case 1: manager.setPricingStrategy(new StandardPricingStrategy()); break;
            case 2: manager.setPricingStrategy(new PeakHoursPricingStrategy()); break;
            case 3: manager.setPricingStrategy(new GreenModePricingStrategy()); break;
            default: System.out.println("انتخاب نامعتبر.");
        }
    }
}