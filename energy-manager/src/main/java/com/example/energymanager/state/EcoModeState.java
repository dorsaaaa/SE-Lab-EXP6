package com.example.energymanager.state;

import com.example.energymanager.EnergyManager;

public class EcoModeState implements SystemState {
    @Override
    public void activate(EnergyManager manager) {
        System.out.println("بازگشت به حالت فعال (Active)...");
        manager.setCurrentState(new ActiveState());
    }

    @Override
    public void setToEcoMode(EnergyManager manager) {
        System.out.println("سیستم در حال حاضر در حالت اقتصادی است.");
    }

    @Override
    public void shutdown(EnergyManager manager) {
        System.out.println("سیستم از حالت اقتصادی خاموش می‌شود...");
        manager.setCurrentState(new ShutdownState());
    }

    @Override
    public String getStatusDescription() {
        return "اقتصادی (Eco): تنها سیستم‌های حیاتی روشن هستند.";
    }
}