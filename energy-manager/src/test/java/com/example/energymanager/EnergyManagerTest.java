package com.example.energymanager;

import com.example.energymanager.strategy.PeakHoursPricingStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EnergyManagerTest {

    private EnergyManager manager;

    @BeforeEach
    void setUp() {
        manager = new EnergyManager(); 
    }

    @Test
    void testInitialTariffIsStandard() {
        assertEquals(5000.0, manager.simulateCost(10), 0.01);
    }

    @Test
    void testChangeTariffToPeak() {
        manager.setPricingStrategy(new PeakHoursPricingStrategy());
        assertEquals(10000.0, manager.simulateCost(10), 0.01);
    }
    
    @Test
    void testTotalCostCalculationAfterTariffChange() {
        manager.addConsumption(20); // 20 units
        manager.setPricingStrategy(new PeakHoursPricingStrategy());
        // Total consumption is 20. Current tariff is peak. 20 * 1000 = 20000
        assertEquals(20000.0, manager.getTotalCost(), 0.01);
    }
}