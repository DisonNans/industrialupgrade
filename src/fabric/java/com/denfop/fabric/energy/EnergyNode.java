package com.denfop.fabric.energy;

public interface EnergyNode {
    EnergyStorage energyStorage();
    double maxTransferPerTick();
    default boolean acceptsEnergy() { return true; }
}
