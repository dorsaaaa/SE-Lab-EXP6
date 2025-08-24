package com.example.energymanager;

import com.example.energymanager.state.*;
import com.example.energymanager.strategy.*;

/**
 * The Context class that uses State and Strategy patterns.
 */
public class EnergyManager {
    private SystemState currentState;
    private PricingStrategy currentPricingStrategy;
    private double totalConsumption;

    public EnergyManager() {
        // Initial state and strategy
        this.currentState = new ActiveState();
        this.currentPricingStrategy = new StandardPricingStrategy();
        this.totalConsumption = 0;
    }

    // --- State Management ---
    public void setCurrentState(SystemState state) {
        this.currentState = state;
    }
    
    public SystemState getCurrentState() { // Useful for testing
        return this.currentState;
    }

    public void activate() {
        currentState.activate(this);
    }

    public void setToEcoMode() {
        currentState.setToEcoMode(this);
    }

    public void shutdown() {
        currentState.shutdown(this);
    }

    public String getCurrentStatusDescription() {
        return currentState.getStatusDescription();
    }

    // --- Strategy Management ---
    public void setPricingStrategy(PricingStrategy strategy) {
        this.currentPricingStrategy = strategy;
        System.out.println("سیاست محاسبه هزینه تغییر کرد.");
    }

    // --- Business Logic ---
    public void addConsumption(double units) {
        if (units > 0) {
            this.totalConsumption += units;
        }
    }

    public double getTotalCost() {
        return currentPricingStrategy.calculateCost(totalConsumption);
    }

    public double simulateCost(double units) {
        return currentPricingStrategy.calculateCost(units);
    }
    
    public double getTotalConsumption() {
        return totalConsumption;
    }
}