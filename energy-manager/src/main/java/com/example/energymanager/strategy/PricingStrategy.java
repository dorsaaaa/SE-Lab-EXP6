package com.example.energymanager.strategy;

/**
 * The Strategy interface for different pricing algorithms.
 */
public interface PricingStrategy {
    double calculateCost(double units);
}