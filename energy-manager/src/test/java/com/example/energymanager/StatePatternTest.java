package com.example.energymanager;

import com.example.energymanager.state.ActiveState;
import com.example.energymanager.state.EcoModeState;
import com.example.energymanager.state.ShutdownState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StatePatternTest {

    private EnergyManager manager;

    @BeforeEach
    void setUp() {
        manager = new EnergyManager();
    }

    @Test
    void testInitialStateIsActive() {
        assertTrue(manager.getCurrentState() instanceof ActiveState);
    }

    @Test
    void testTransitionFromActiveToEco() {
        manager.setToEcoMode();
        assertTrue(manager.getCurrentState() instanceof EcoModeState);
    }

    @Test
    void testTransitionFromActiveToShutdown() {
        manager.shutdown();
        assertTrue(manager.getCurrentState() instanceof ShutdownState);
    }

    @Test
    void testTransitionFromEcoToActive() {
        manager.setToEcoMode(); 
        manager.activate();     
        assertTrue(manager.getCurrentState() instanceof ActiveState);
    }
}