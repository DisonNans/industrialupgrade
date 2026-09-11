package com.denfop.fabric.machine;

import com.denfop.fabric.FabricSolarRegistry;
import com.denfop.fabric.energy.EnergyNode;
import com.denfop.fabric.energy.EnergyStorage;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;

public final class FabricSolarPanelBlockEntity extends BlockEntity implements EnergyNode {
    private final int tier;
    private final double dayGeneration;
    private final double nightGeneration;
    private final double output;
    private final EnergyStorage energy;

    public FabricSolarPanelBlockEntity(BlockPos pos, BlockState state) {
        super(FabricSolarRegistry.BLOCK_ENTITY_TYPE, pos, state);
        FabricSolarPanelBlock block = (FabricSolarPanelBlock) state.getBlock();
        this.tier = block.tier();
        this.dayGeneration = block.dayGeneration();
        this.nightGeneration = block.nightGeneration();
        this.output = block.output();
        this.energy = new EnergyStorage(block.capacity(), output, output);
    }

    public void tick() {
        if (world == null || world.isClient) return;
        long time = world.getTimeOfDay() % 24000L;
        boolean daylight = time >= 1000L && time < 12000L;
        boolean exposed = world.isSkyVisible(pos.up());
        double generation = daylight ? dayGeneration : nightGeneration;
        if (exposed) energy.receive(generation, false);

        double available = Math.min(energy.getEnergy(), output);
        for (var direction : net.minecraft.util.math.Direction.values()) {
            if (available <= 0) break;
            var neighbor = world.getBlockEntity(pos.offset(direction));
            if (!(neighbor instanceof EnergyNode target) || !target.acceptsEnergy()) continue;
            double toSend = Math.min(available, target.maxTransferPerTick());
            double extracted = energy.extract(toSend, true);
            if (extracted <= 0) continue;
            double accepted = target.energyStorage().receive(extracted, false);
            if (accepted > 0) {
                energy.extract(accepted, false);
                available -= accepted;
            }
        }
        markDirty();
    }

    @Override public EnergyStorage energyStorage() { return energy; }
    @Override public double maxTransferPerTick() { return output; }

    public String status() {
        return "Solar " + (tier + 1) + " | Day " + (long) dayGeneration + " | Night " + (long) nightGeneration
                + " | EU " + (long) energy.getEnergy() + "/" + (long) energy.getCapacity() + " | Out " + (long) output;
    }

    @Override protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putDouble("energy", energy.getEnergy());
    }

    @Override public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        energy.setEnergy(nbt.getDouble("energy"));
    }
}
