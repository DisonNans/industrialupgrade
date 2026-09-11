package com.denfop.fabric.machine;

import com.denfop.fabric.FabricMultiMachineRegistry;
import com.denfop.fabric.FabricRegistries;
import com.denfop.fabric.energy.EnergyStorage;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;

/** Shared backend for the classic multi-machine progression. */
public final class IndustrialProcessorBlockEntity extends BlockEntity implements Inventory {
    private final int machineType;
    private final int slotCount;
    private final DefaultedList<ItemStack> inventory;
    private final EnergyStorage energy;
    private final int operationLength;
    private final int energyPerTick;
    private final int[] progress;
    private boolean active;

    public IndustrialProcessorBlockEntity(BlockPos pos, BlockState state) {
        super(FabricMultiMachineRegistry.BLOCK_ENTITY_TYPE, pos, state);
        IndustrialProcessorBlock block = (IndustrialProcessorBlock) state.getBlock();
        this.machineType = block.machineType();
        this.slotCount = block.slotCount();
        this.inventory = DefaultedList.ofSize(slotCount + 1, ItemStack.EMPTY);
        this.operationLength = switch (machineType) {
            case 3 -> 100;
            case 5, 6, 7 -> 200;
            case 8 -> 500;
            case 9 -> 45;
            case 10 -> 25;
            default -> 300;
        };
        this.energyPerTick = switch (machineType) {
            case 3 -> 3;
            case 5, 6, 7 -> 10;
            case 8 -> 4;
            case 9, 10 -> 1;
            default -> 2;
        };
        this.energy = new EnergyStorage((double) energyPerTick * operationLength * slotCount,
                (double) energyPerTick * operationLength * slotCount,
                (double) energyPerTick * operationLength * slotCount);
        this.progress = new int[slotCount];
    }

    public void tick() {
        if (world == null || world.isClient) return;
        active = false;
        for (int i = 0; i < slotCount; i++) {
            ItemStack input = inventory.get(i);
            Item outputItem = outputFor(input, machineType);
            if (outputItem == null) { progress[i] = 0; continue; }
            int amount = outputCount(machineType);
            if (!canOutput(outputItem, amount) || input.getCount() < inputCount(machineType)) {
                progress[i] = 0;
                continue;
            }
            if (energy.getEnergy() < energyPerTick) { progress[i] = 0; continue; }
            energy.use(energyPerTick);
            progress[i]++;
            active = true;
            if (progress[i] >= operationLength) {
                input.decrement(inputCount(machineType));
                ItemStack out = inventory.get(slotCount);
                if (out.isEmpty()) inventory.set(slotCount, new ItemStack(outputItem, amount));
                else out.increment(amount);
                progress[i] = 0;
            }
        }
        markDirty();
    }

    private Item outputFor(ItemStack input, int type) {
        if (input.isEmpty()) return null;
        if (type == 1) return dustFor(input.getItem());
        if (type == 2 || type == 3 || type == 4) return ingotFor(input.getItem());
        return input.getItem();
    }

    private int inputCount(int type) { return type == 2 ? 9 : 1; }
    private int outputCount(int type) { return type == 1 ? 2 : 1; }
    private boolean canOutput(Item item, int count) {
        ItemStack current = inventory.get(slotCount);
        return current.isEmpty() || (current.getItem() == item && current.getCount() + count <= current.getMaxCount());
    }

    private Item dustFor(Item item) {
        if (item == FabricRegistries.IRON_INGOT || item == net.minecraft.item.Items.IRON_INGOT) return FabricMultiMachineRegistry.IRON_DUST;
        if (item == net.minecraft.item.Items.COPPER_INGOT) return FabricMultiMachineRegistry.COPPER_DUST;
        if (item == FabricRegistries.TIN_INGOT) return FabricMultiMachineRegistry.TIN_DUST;
        if (item == FabricRegistries.LEAD_INGOT) return FabricMultiMachineRegistry.LEAD_DUST;
        if (item == FabricRegistries.NICKEL_INGOT) return FabricMultiMachineRegistry.NICKEL_DUST;
        if (item == FabricRegistries.ZINC_INGOT) return FabricMultiMachineRegistry.ZINC_DUST;
        if (item == FabricRegistries.SILVER_INGOT) return FabricMultiMachineRegistry.SILVER_DUST;
        if (item == FabricRegistries.CHROMIUM_INGOT) return FabricMultiMachineRegistry.CHROMIUM_DUST;
        if (item == FabricRegistries.ALUMINUM_INGOT) return FabricMultiMachineRegistry.ALUMINUM_DUST;
        if (item == FabricRegistries.MAGNESIUM_INGOT) return FabricMultiMachineRegistry.MAGNESIUM_DUST;
        if (item == FabricRegistries.TITANIUM_INGOT) return FabricMultiMachineRegistry.TITANIUM_DUST;
        if (item == FabricRegistries.MANGANESE_INGOT) return FabricMultiMachineRegistry.MANGANESE_DUST;
        if (item == FabricRegistries.VANADY_INGOT) return FabricMultiMachineRegistry.VANADY_DUST;
        if (item == FabricRegistries.COBALT_INGOT) return FabricMultiMachineRegistry.COBALT_DUST;
        if (item == FabricRegistries.TUNGSTEN_INGOT) return FabricMultiMachineRegistry.TUNGSTEN_DUST;
        return null;
    }

    private Item ingotFor(Item item) {
        if (item == FabricMultiMachineRegistry.IRON_DUST) return FabricRegistries.IRON_INGOT;
        if (item == FabricMultiMachineRegistry.COPPER_DUST) return net.minecraft.item.Items.COPPER_INGOT;
        if (item == FabricMultiMachineRegistry.TIN_DUST) return FabricRegistries.TIN_INGOT;
        if (item == FabricMultiMachineRegistry.LEAD_DUST) return FabricRegistries.LEAD_INGOT;
        if (item == FabricMultiMachineRegistry.NICKEL_DUST) return FabricRegistries.NICKEL_INGOT;
        if (item == FabricMultiMachineRegistry.ZINC_DUST) return FabricRegistries.ZINC_INGOT;
        if (item == FabricMultiMachineRegistry.SILVER_DUST) return FabricRegistries.SILVER_INGOT;
        if (item == FabricMultiMachineRegistry.CHROMIUM_DUST) return FabricRegistries.CHROMIUM_INGOT;
        if (item == FabricMultiMachineRegistry.ALUMINUM_DUST) return FabricRegistries.ALUMINUM_INGOT;
        if (item == FabricMultiMachineRegistry.MAGNESIUM_DUST) return FabricRegistries.MAGNESIUM_INGOT;
        if (item == FabricMultiMachineRegistry.TITANIUM_DUST) return FabricRegistries.TITANIUM_INGOT;
        if (item == FabricMultiMachineRegistry.MANGANESE_DUST) return FabricRegistries.MANGANESE_INGOT;
        if (item == FabricMultiMachineRegistry.VANADY_DUST) return FabricRegistries.VANADY_INGOT;
        if (item == FabricMultiMachineRegistry.COBALT_DUST) return FabricRegistries.COBALT_INGOT;
        if (item == FabricMultiMachineRegistry.TUNGSTEN_DUST) return FabricRegistries.TUNGSTEN_INGOT;
        return null;
    }

    public void addEnergy(double amount) { energy.receive(Math.max(0.0D, amount), false); markDirty(); }
    public String status() { return "Machine: " + machineType + " | EU: " + (int) energy.getEnergy() + "/" + (int) energy.getCapacity() + " | Active: " + active; }

    @Override protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putDouble("energy", energy.getEnergy());
        nbt.putBoolean("active", active);
        for (int i = 0; i < progress.length; i++) nbt.putInt("progress_" + i, progress[i]);
        for (int i = 0; i < inventory.size(); i++) if (!inventory.get(i).isEmpty()) nbt.put("slot_" + i, inventory.get(i).writeNbt(new NbtCompound()));
    }

    @Override public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        energy.setEnergy(nbt.getDouble("energy"));
        active = nbt.getBoolean("active");
        for (int i = 0; i < progress.length; i++) progress[i] = nbt.getInt("progress_" + i);
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
    @Override public void clear() { inventory.replaceAll(s -> ItemStack.EMPTY); markDirty(); }
    @Override public boolean canPlayerUse(net.minecraft.entity.player.PlayerEntity player) { return world != null && player.squaredDistanceTo(pos.getX() + .5, pos.getY() + .5, pos.getZ() + .5) <= 64; }
}
