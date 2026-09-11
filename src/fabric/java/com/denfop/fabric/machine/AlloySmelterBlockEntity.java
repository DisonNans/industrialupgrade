package com.denfop.fabric.machine;

import com.denfop.fabric.FabricRegistries;
import com.denfop.fabric.energy.EnergyStorage;
import com.denfop.fabric.logic.AlloySmelterRecipes;
import com.denfop.fabric.logic.MachineRecipe;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;

/**
 * Behavior-preserving port of the original TileEntityDoubleElectricMachine semantics.
 *
 * Original values verified against 1.12.2:
 * - operation length = 300 ticks
 * - energy demand = 1 per tick
 * - internal capacity = 300
 * - two input slots and one output slot
 * - output availability is checked before energy is consumed
 * - progress resets when processing cannot continue
 */
public final class AlloySmelterBlockEntity extends BlockEntity implements Inventory {
    private static final int INPUT_A = 0;
    private static final int INPUT_B = 1;
    private static final int OUTPUT = 2;

    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(3, ItemStack.EMPTY);
    private final EnergyStorage energy = new EnergyStorage(300.0D, 300.0D, 300.0D);
    private int progress;
    private boolean active;

    public AlloySmelterBlockEntity(BlockPos pos, BlockState state) {
        super(FabricRegistries.ALLOY_SMELTER_BLOCK_ENTITY, pos, state);
    }

    public void tick() {
        if (world == null || world.isClient) return;

        MachineRecipe recipe = findRecipe();
        if (recipe == null || !canOutput(recipe) || energy.getEnergy() < AlloySmelterRecipes.ENERGY_PER_TICK) {
            progress = 0;
            active = false;
            markDirty();
            return;
        }

        active = true;
        energy.use(AlloySmelterRecipes.ENERGY_PER_TICK);
        progress++;

        if (progress >= AlloySmelterRecipes.OPERATION_LENGTH) {
            consumeInputs(recipe);
            insertOutputs(recipe);
            progress = 0;
        }

        markDirty();
    }

    private MachineRecipe findRecipe() {
        ItemStack first = inventory.get(INPUT_A);
        ItemStack second = inventory.get(INPUT_B);
        for (MachineRecipe recipe : AlloySmelterRecipes.all()) {
            if (recipe.matches(first, second)) return recipe;
        }
        return null;
    }

    private boolean canOutput(MachineRecipe recipe) {
        ItemStack current = inventory.get(OUTPUT);
        for (MachineRecipe.Output output : recipe.outputs()) {
            if (!current.isEmpty() && current.getItem() != output.item()) return false;
            if (!current.isEmpty() && current.getCount() + output.count() > current.getMaxCount()) return false;
            if (current.isEmpty() && output.count() > 64) return false;
        }
        return true;
    }

    private void consumeInputs(MachineRecipe recipe) {
        inventory.get(INPUT_A).decrement(recipe.inputACount());
        inventory.get(INPUT_B).decrement(recipe.inputBCount());
    }

    private void insertOutputs(MachineRecipe recipe) {
        for (MachineRecipe.Output output : recipe.outputs()) {
            ItemStack current = inventory.get(OUTPUT);
            if (current.isEmpty()) {
                inventory.set(OUTPUT, new ItemStack(output.item(), output.count()));
            } else {
                current.increment(output.count());
            }
        }
    }

    public void addEnergy(double amount) {
        energy.receive(Math.max(0.0D, amount), false);
        markDirty();
    }

    public double getEnergy() { return energy.getEnergy(); }
    public double getMaxEnergy() { return energy.getCapacity(); }
    public int getProgress() { return progress; }
    public int getOperationLength() { return AlloySmelterRecipes.OPERATION_LENGTH; }
    public boolean isActive() { return active; }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putInt("progress", progress);
        nbt.putDouble("energy", energy.getEnergy());
        nbt.putBoolean("active", active);
        for (int i = 0; i < inventory.size(); i++) {
            if (!inventory.get(i).isEmpty()) {
                nbt.put("slot_" + i, inventory.get(i).writeNbt(new NbtCompound()));
            }
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
            if (nbt.contains("slot_" + i)) {
                inventory.set(i, ItemStack.fromNbt(nbt.getCompound("slot_" + i)));
            }
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
        return world != null && player.squaredDistanceTo(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5) <= 64.0;
    }
    @Override public void clear() { inventory.replaceAll(stack -> ItemStack.EMPTY); markDirty(); }
}
