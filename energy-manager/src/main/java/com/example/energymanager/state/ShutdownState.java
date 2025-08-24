package com.example.energymanager.state;

import com.example.energymanager.EnergyManager;

public class ShutdownState implements SystemState {
    @Override
    public void activate(EnergyManager manager) {
        System.out.println("سیستم در حال فعال شدن...");
        manager.setCurrentState(new ActiveState());
    }

    @Override
    public void setToEcoMode(EnergyManager manager) {
        System.out.println("امکان تغییر به حالت اقتصادی از حالت خاموش وجود ندارد. ابتدا سیستم را فعال کنید.");
    }

    @Override
    public void shutdown(EnergyManager manager) {
        System.out.println("سیستم در حال حاضر خاموش است.");
    }

    @Override
    public String getStatusDescription() {
        return "خاموش (Shutdown): همه سیستم‌ها خاموش هستند.";
    }
}