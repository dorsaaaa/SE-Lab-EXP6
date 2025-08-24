package com.example.energymanager.strategy;

public class StandardPricingStrategy implements PricingStrategy {
    private static final double RATE = 500.0; // 500 Toman per unit

    @Override
    public double calculateCost(double units) {
        return units * RATE;
    }
}