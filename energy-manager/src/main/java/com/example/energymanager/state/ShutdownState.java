package com.example.energymanager.state;

import com.example.energymanager.EnergyManager;

public class ShutdownState implements SystemState {
    @Override
    public void activate(EnergyManager manager) {
        System.out.println("Activating the system...");
        manager.setCurrentState(new ActiveState());
    }

    @Override
    public void setToEcoMode(EnergyManager manager) {
        System.out.println("Cannot switch to Eco Mode from Shutdown state. Please activate the system first.");
    }

    @Override
    public void shutdown(EnergyManager manager) {
        System.out.println("System is already shut down.");
    }

    @Override
    public String getStatusDescription() {
        return "Shutdown: All systems are off.";
    }
}