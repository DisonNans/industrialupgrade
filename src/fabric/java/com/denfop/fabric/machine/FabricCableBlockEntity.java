package com.denfop.fabric.machine;

import com.denfop.fabric.energy.CableType;
import com.denfop.fabric.energy.EnergyNode;
import com.denfop.fabric.energy.EnergyStorage;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public final class FabricCableBlockEntity extends BlockEntity implements EnergyNode {
    private final CableType cableType;
    private final EnergyStorage energy;

    public FabricCableBlockEntity(BlockPos pos, BlockState state) {
        super(FabricCableRegistry.BLOCK_ENTITY_TYPE, pos, state);
        this.cableType = ((FabricCableBlock) state.getBlock()).cableType();
        this.energy = new EnergyStorage(cableType.capacity(), cableType.capacity(), cableType.capacity());
    }

    public void tick() {
        if (world == null || world.isClient || energy.getEnergy() <= 0) return;
        double available = Math.min(energy.getEnergy(), maxTransferPerTick());
        for (Direction direction : Direction.values()) {
            if (available <= 0) break;
            BlockEntity neighbor = world.getBlockEntity(pos.offset(direction));
            if (!(neighbor instanceof EnergyNode target) || !target.acceptsEnergy()) continue;
            if (neighbor == this) continue;
            double toSend = Math.min(available, target.maxTransferPerTick());
            if (toSend <= 0) continue;
            double extracted = energy.extract(toSend, true);
            double accepted = target.energyStorage().receive(extracted * (1D - CableType.LOSS), false);
            if (accepted <= 0) continue;
            double consumed = accepted / (1D - CableType.LOSS);
            energy.extract(consumed, false);
            available -= consumed;
        }
        markDirty();
    }

    @Override public EnergyStorage energyStorage() { return energy; }
    @Override public double maxTransferPerTick() { return Math.min(cableType.capacity(), 8192D); }
    public void addEnergy(double amount) { energy.receive(Math.max(0, amount), false); markDirty(); }
    public String status() { return "Cable " + cableType.name() + " | EU " + (long) energy.getEnergy() + "/" + (long) energy.getCapacity(); }

    @Override protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putDouble("energy", energy.getEnergy());
    }

    @Override public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        energy.setEnergy(nbt.getDouble("energy"));
    }
}
