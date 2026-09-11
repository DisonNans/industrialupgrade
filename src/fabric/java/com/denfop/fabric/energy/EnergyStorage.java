package com.denfop.fabric.energy;

/**
 * Minimal standalone energy abstraction for Industrial Upgrade.
 * Values are intentionally kept as doubles because the 1.12.2 code used
 * double-precision capacities and transfer values in several subsystems.
 */
public final class EnergyStorage {
    private final double capacity;
    private final double maxReceive;
    private final double maxExtract;
    private double energy;

    public EnergyStorage(double capacity, double maxReceive, double maxExtract) {
        if (capacity < 0 || maxReceive < 0 || maxExtract < 0) {
            throw new IllegalArgumentException("Energy limits cannot be negative");
        }
        this.capacity = capacity;
        this.maxReceive = maxReceive;
        this.maxExtract = maxExtract;
    }

    public double getEnergy() {
        return energy;
    }

    public double getCapacity() {
        return capacity;
    }

    public double receive(double amount, boolean simulate) {
        if (amount <= 0) return 0;
        double accepted = Math.min(amount, Math.min(maxReceive, capacity - energy));
        if (!simulate) energy += accepted;
        return accepted;
    }

    public double extract(double amount, boolean simulate) {
        if (amount <= 0) return 0;
        double extracted = Math.min(amount, Math.min(maxExtract, energy));
        if (!simulate) energy -= extracted;
        return extracted;
    }

    public boolean use(double amount) {
        return extract(amount, false) >= amount;
    }

    public void setEnergy(double amount) {
        energy = Math.max(0, Math.min(capacity, amount));
    }
}
