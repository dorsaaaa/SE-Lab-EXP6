package com.example.energymanager.strategy;

public class GreenModePricingStrategy implements PricingStrategy {
    private static final double RATE = 300.0; // 300 Toman per unit

    @Override
    public double calculateCost(double units) {
        return units * RATE;
    }
}