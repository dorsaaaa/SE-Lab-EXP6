package com.example.energymanager.state;

import com.example.energymanager.EnergyManager;

public class EcoModeState implements SystemState {
    @Override
    public void activate(EnergyManager manager) {
        System.out.println("Returning to Active state...");
        manager.setCurrentState(new ActiveState());
    }

    @Override
    public void setToEcoMode(EnergyManager manager) {
        System.out.println("System is already in Eco Mode.");
    }

    @Override
    public void shutdown(EnergyManager manager) {
        System.out.println("Shutting down the system from Eco Mode...");
        manager.setCurrentState(new ShutdownState());
    }

    @Override
    public String getStatusDescription() {
        return "Eco Mode: Only essential systems are running.";
    }
}