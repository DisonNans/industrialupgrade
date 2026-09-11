package com.denfop.fabric.machine;

import com.denfop.fabric.FabricRegistries;
import com.denfop.fabric.energy.EnergyStorage;
import com.denfop.fabric.logic.AdvancedAlloySmelterRecipes;
import com.denfop.fabric.logic.TripleMachineRecipe;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;

/**
 * Standalone Fabric port of TileEntityTripleElectricMachine/TileEntityAdvAlloySmelter.
 * Original processing constants: 1 energy/tick, 300 ticks, capacity 300.
 */
public final class AdvancedAlloySmelterBlockEntity extends BlockEntity implements Inventory {
    private static final int INPUT_A = 0;
    private static final int INPUT_B = 1;
    private static final int INPUT_C = 2;
    private static final int OUTPUT = 3;

    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(4, ItemStack.EMPTY);
    private final EnergyStorage energy = new EnergyStorage(300.0D, 300.0D, 300.0D);
    private int progress;
    private boolean active;

    public AdvancedAlloySmelterBlockEntity(BlockPos pos, BlockState state) {
        super(FabricRegistries.ADVANCED_ALLOY_SMELTER_BLOCK_ENTITY, pos, state);
    }

    public void tick() {
        if (world == null || world.isClient) return;

        TripleMachineRecipe recipe = findRecipe();
        if (recipe == null || !canOutput(recipe) || energy.getEnergy() < AdvancedAlloySmelterRecipes.ENERGY_PER_TICK) {
            progress = 0;
            active = false;
            markDirty();
            return;
        }

        active = true;
        energy.use(AdvancedAlloySmelterRecipes.ENERGY_PER_TICK);
        progress++;

        if (progress >= AdvancedAlloySmelterRecipes.OPERATION_LENGTH) {
            consumeInputs(recipe);
            insertOutputs(recipe);
            progress = 0;
        }
        markDirty();
    }

    private TripleMachineRecipe findRecipe() {
        ItemStack a = inventory.get(INPUT_A);
        ItemStack b = inventory.get(INPUT_B);
        ItemStack c = inventory.get(INPUT_C);
        for (TripleMachineRecipe recipe : AdvancedAlloySmelterRecipes.all()) {
            if (recipe.matches(a, b, c)) return recipe;
        }
        return null;
    }

    private boolean canOutput(TripleMachineRecipe recipe) {
        ItemStack current = inventory.get(OUTPUT);
        for (TripleMachineRecipe.Output output : recipe.outputs()) {
            if (!current.isEmpty() && current.getItem() != output.item()) return false;
            if (!current.isEmpty() && current.getCount() + output.count() > current.getMaxCount()) return false;
            if (current.isEmpty() && output.count() > 64) return false;
        }
        return true;
    }

    private void consumeInputs(TripleMachineRecipe recipe) {
        inventory.get(INPUT_A).decrement(recipe.inputACount());
        inventory.get(INPUT_B).decrement(recipe.inputBCount());
        inventory.get(INPUT_C).decrement(recipe.inputCCount());
    }

    private void insertOutputs(TripleMachineRecipe recipe) {
        for (TripleMachineRecipe.Output output : recipe.outputs()) {
            ItemStack current = inventory.get(OUTPUT);
            if (current.isEmpty()) inventory.set(OUTPUT, new ItemStack(output.item(), output.count()));
            else current.increment(output.count());
        }
    }

    public void addEnergy(double amount) {
        energy.receive(Math.max(0.0D, amount), false);
        markDirty();
    }

    public double getEnergy() { return energy.getEnergy(); }
    public double getMaxEnergy() { return energy.getCapacity(); }
    public int getProgress() { return progress; }
    public int getOperationLength() { return AdvancedAlloySmelterRecipes.OPERATION_LENGTH; }
    public boolean isActive() { return active; }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putInt("progress", progress);
        nbt.putDouble("energy", energy.getEnergy());
        nbt.putBoolean("active", active);
        for (int i = 0; i < inventory.size(); i++) {
            if (!inventory.get(i).isEmpty()) nbt.put("slot_" + i, inventory.get(i).writeNbt(new NbtCompound()));
        }
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        progress = nbt.getInt("progress");
        energy.setEnergy(nbt.getDouble("energy"));
        active = nbt.getBoolean("active");
        for (int i = 0; i < inventory.size(); i++) {
            inventory.set(i, ItemStack.EMPTY);
            if (nbt.contains("slot_" + i)) inventory.set(i, ItemStack.fromNbt(nbt.getCompound("slot_" + i)));
        }
    }

    @Override public int size() { return inventory.size(); }
    @Override public boolean isEmpty() { return inventory.stream().allMatch(ItemStack::isEmpty); }
    @Override public ItemStack getStack(int slot) { return inventory.get(slot); }
    @Override public ItemStack removeStack(int slot, int amount) { return net.minecraft.inventory.Inventories.splitStack(inventory, slot, amount); }
    @Override public ItemStack removeStack(int slot) { return net.minecraft.inventory.Inventories.removeStack(inventory, slot); }
    @Override public void setStack(int slot, ItemStack stack) { inventory.set(slot, stack); markDirty(); }
    @Override public void markDirty() { super.markDirty(); }
    @Override public boolean canPlayerUse(net.minecraft.entity.player.PlayerEntity player) {
        return world != null && player.squaredDistanceTo(pos.getX() + .5, pos.getY() + .5, pos.getZ() + .5) <= 64.0;
    }
    @Override public void clear() { inventory.replaceAll(stack -> ItemStack.EMPTY); markDirty(); }
}
