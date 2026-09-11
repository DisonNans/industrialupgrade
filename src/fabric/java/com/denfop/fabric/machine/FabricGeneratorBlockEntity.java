package com.denfop.fabric.machine;

import com.denfop.fabric.FabricGeneratorRegistry;
import com.denfop.fabric.energy.EnergyNode;
import com.denfop.fabric.energy.EnergyStorage;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public final class FabricGeneratorBlockEntity extends BlockEntity implements Inventory, EnergyNode {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(1, ItemStack.EMPTY);
    private final EnergyStorage energy;
    private final double production;
    private int burnRemaining;
    private int burnTotal;

    public FabricGeneratorBlockEntity(BlockPos pos, BlockState state) {
        super(FabricGeneratorRegistry.BLOCK_ENTITY_TYPE, pos, state);
        FabricGeneratorBlock block = (FabricGeneratorBlock) state.getBlock();
        this.production = 10D * block.coefficient();
        this.energy = new EnergyStorage(block.capacity(), production, block.capacity());
    }

    public void tick() {
        if (world == null || world.isClient) return;
        if (burnRemaining <= 0) consumeFuel();
        if (burnRemaining > 0 && energy.getEnergy() < energy.getCapacity()) {
            double produced = Math.min(production, energy.getCapacity() - energy.getEnergy());
            energy.receive(produced, false);
            burnRemaining--;
        }
        if (energy.getEnergy() > 0) {
            for (Direction direction : Direction.values()) {
                BlockEntity neighbor = world.getBlockEntity(pos.offset(direction));
                if (neighbor instanceof EnergyNode target && target.acceptsEnergy()) {
                    double amount = Math.min(energy.getEnergy(), Math.min(production, target.maxTransferPerTick()));
                    double accepted = target.energyStorage().receive(amount * 0.8D, false);
                    if (accepted > 0) energy.extract(accepted / 0.8D, false);
                }
            }
        }
        markDirty();
    }

    private void consumeFuel() {
        ItemStack stack = inventory.get(0);
        if (stack.isEmpty()) return;
        Integer fuel = FuelRegistry.INSTANCE.get(stack.getItem());
        if (fuel == null || fuel <= 0) return;
        stack.decrement(1);
        burnTotal = Math.max(1, fuel);
        burnRemaining = burnTotal;
    }

    @Override public EnergyStorage energyStorage() { return energy; }
    @Override public double maxTransferPerTick() { return production; }
    @Override public boolean acceptsEnergy() { return false; }
    public String status() { return "Generator " + production + " EU/t | EU " + (long) energy.getEnergy() + "/" + (long) energy.getCapacity() + " | Burn " + burnRemaining; }

    @Override protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putDouble("energy", energy.getEnergy());
        nbt.putInt("burn", burnRemaining);
        nbt.putInt("burn_total", burnTotal);
        if (!inventory.get(0).isEmpty()) nbt.put("fuel", inventory.get(0).writeNbt(new NbtCompound()));
    }

    @Override public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        energy.setEnergy(nbt.getDouble("energy"));
        burnRemaining = nbt.getInt("burn");
        burnTotal = nbt.getInt("burn_total");
        inventory.set(0, nbt.contains("fuel") ? ItemStack.fromNbt(nbt.getCompound("fuel")) : ItemStack.EMPTY);
    }

    @Override public int size() { return 1; }
    @Override public boolean isEmpty() { return inventory.get(0).isEmpty(); }
    @Override public ItemStack getStack(int slot) { return inventory.get(0); }
    @Override public ItemStack removeStack(int slot, int amount) { return net.minecraft.inventory.Inventories.splitStack(inventory, 0, amount); }
    @Override public ItemStack removeStack(int slot) { return net.minecraft.inventory.Inventories.removeStack(inventory, 0); }
    @Override public void setStack(int slot, ItemStack stack) { inventory.set(0, stack); markDirty(); }
    @Override public void clear() { inventory.set(0, ItemStack.EMPTY); markDirty(); }
    @Override public boolean canPlayerUse(net.minecraft.entity.player.PlayerEntity player) { return world != null && player.squaredDistanceTo(pos.getX() + .5, pos.getY() + .5, pos.getZ() + .5) <= 64; }
}
