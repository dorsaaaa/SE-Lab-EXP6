package com.example.energymanager.state;

import com.example.energymanager.EnergyManager;

public class ActiveState implements SystemState {
    @Override
    public void activate(EnergyManager manager) {
        System.out.println("System is already in Active state.");
    }

    @Override
    public void setToEcoMode(EnergyManager manager) {
        System.out.println("Switching to Eco Mode...");
        manager.setCurrentState(new EcoModeState());
    }

    @Override
    public void shutdown(EnergyManager manager) {
        System.out.println("Shutting down the system...");
        manager.setCurrentState(new ShutdownState());
    }

    @Override
    public String getStatusDescription() {
        return "Active: All systems are running.";
    }
}