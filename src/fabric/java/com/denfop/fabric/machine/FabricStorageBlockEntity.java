package com.denfop.fabric.machine;

import com.denfop.fabric.FabricStorageRegistry;
import com.denfop.fabric.energy.EnergyNode;
import com.denfop.fabric.energy.EnergyStorage;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public final class FabricStorageBlockEntity extends BlockEntity implements EnergyNode {
    private final EnergyStorage energy;
    private final double transfer;

    public FabricStorageBlockEntity(BlockPos pos, BlockState state) {
        super(FabricStorageRegistry.BLOCK_ENTITY_TYPE, pos, state);
        FabricStorageBlock block = (FabricStorageBlock) state.getBlock();
        this.energy = new EnergyStorage(block.capacity(), block.transfer(), block.transfer());
        this.transfer = block.transfer();
    }

    public void tick() {
        if (world == null || world.isClient || energy.getEnergy() <= 0) return;
        double available = Math.min(energy.getEnergy(), transfer);
        for (Direction direction : Direction.values()) {
            if (available <= 0) break;
            BlockEntity neighbor = world.getBlockEntity(pos.offset(direction));
            if (!(neighbor instanceof EnergyNode target) || !target.acceptsEnergy()) continue;
            if (neighbor == this) continue;
            double amount = Math.min(available, target.maxTransferPerTick());
            double accepted = target.energyStorage().receive(amount, false);
            if (accepted > 0) {
                energy.extract(accepted, false);
                available -= accepted;
            }
        }
        markDirty();
    }

    @Override public EnergyStorage energyStorage() { return energy; }
    @Override public double maxTransferPerTick() { return transfer; }
    public void addEnergy(double amount) { energy.receive(Math.max(0, amount), false); markDirty(); }
    public String status() { return "Storage | EU " + (long) energy.getEnergy() + "/" + (long) energy.getCapacity() + " | Transfer " + (long) transfer; }

    @Override protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putDouble("energy", energy.getEnergy());
    }

    @Override public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        energy.setEnergy(nbt.getDouble("energy"));
    }
}
