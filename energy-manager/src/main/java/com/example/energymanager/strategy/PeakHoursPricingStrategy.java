package com.example.energymanager.strategy;

public class PeakHoursPricingStrategy implements PricingStrategy {
    private static final double RATE = 1000.0; // 1000 Toman per unit

    @Override
    public double calculateCost(double units) {
        return units * RATE;
    }
}