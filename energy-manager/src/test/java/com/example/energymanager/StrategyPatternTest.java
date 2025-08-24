package com.example.energymanager;

import com.example.energymanager.strategy.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StrategyPatternTest {

    @Test
    void testStandardPricing() {
        PricingStrategy strategy = new StandardPricingStrategy();
        assertEquals(5000.0, strategy.calculateCost(10), 0.01);
    }

    @Test
    void testPeakHoursPricing() {
        PricingStrategy strategy = new PeakHoursPricingStrategy();
        assertEquals(10000.0, strategy.calculateCost(10), 0.01);
    }

    @Test
    void testGreenModePricing() {
        PricingStrategy strategy = new GreenModePricingStrategy();
        assertEquals(3000.0, strategy.calculateCost(10), 0.01);
    }
}