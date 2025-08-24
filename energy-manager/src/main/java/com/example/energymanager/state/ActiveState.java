package com.example.energymanager.state;

import com.example.energymanager.EnergyManager;

public class ActiveState implements SystemState {
    @Override
    public void activate(EnergyManager manager) {
        System.out.println("سیستم در حال حاضر فعال است.");
    }

    @Override
    public void setToEcoMode(EnergyManager manager) {
        System.out.println("تغییر وضعیت به حالت اقتصادی (Eco)...");
        manager.setCurrentState(new EcoModeState());
    }

    @Override
    public void shutdown(EnergyManager manager) {
        System.out.println("سیستم در حال خاموش شدن...");
        manager.setCurrentState(new ShutdownState());
    }

    @Override
    public String getStatusDescription() {
        return "فعال (Active): همه سیستم‌ها روشن هستند.";
    }
}