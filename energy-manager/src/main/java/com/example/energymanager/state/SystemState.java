package com.example.energymanager.state;

import com.example.energymanager.EnergyManager;

/**
 * The State interface for different system states.
 */
public interface SystemState {
    void activate(EnergyManager manager);
    void setToEcoMode(EnergyManager manager);
    void shutdown(EnergyManager manager);
    String getStatusDescription();
}