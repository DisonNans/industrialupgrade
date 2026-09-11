package com.denfop.fabric.machine;

import com.denfop.fabric.energy.EnergyStorage;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;

/** Shared processing semantics based on the original IC2 electric-machine base classes. */
public abstract class FabricPoweredMachineBlockEntity extends BlockEntity implements Inventory {
    protected final DefaultedList<ItemStack> inventory;
    protected final EnergyStorage energy;
    protected int progress;
    protected boolean active;

    protected FabricPoweredMachineBlockEntity(BlockPos pos, BlockState state, int slots,
                                              double energyPerTick, int operationLength) {
        super(typeFor(pos, state));
        this.inventory = DefaultedList.ofSize(slots, ItemStack.EMPTY);
        this.energy = new EnergyStorage(energyPerTick * operationLength,
                energyPerTick * operationLength,
                energyPerTick * operationLength);
    }

    /** Concrete entities supply their registered type without relying on IC2 registries. */
    protected abstract net.minecraft.block.entity.BlockEntityType<? extends FabricPoweredMachineBlockEntity> registeredType();

    private static net.minecraft.block.entity.BlockEntityType<? extends FabricPoweredMachineBlockEntity> typeFor(BlockPos pos, BlockState state) {
        throw new IllegalStateException("Concrete Fabric machine must override registered type constructor path");
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putInt("progress", progress);
        nbt.putDouble("energy", energy.getEnergy());
        nbt.putBoolean("active", active);
    }
}
