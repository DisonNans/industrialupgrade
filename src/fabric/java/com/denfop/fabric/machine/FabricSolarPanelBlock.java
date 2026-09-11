package com.denfop.fabric.machine;

import com.denfop.fabric.FabricSolarRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public final class FabricSolarPanelBlock extends Block implements BlockEntityProvider {
    private final int tier;
    private final double dayGeneration;
    private final double nightGeneration;
    private final double capacity;
    private final double output;

    public FabricSolarPanelBlock(Settings settings, int tier, double dayGeneration, double nightGeneration, double capacity, double output) {
        super(settings);
        this.tier = tier;
        this.dayGeneration = dayGeneration;
        this.nightGeneration = nightGeneration;
        this.capacity = capacity;
        this.output = output;
    }

    public int tier() { return tier; }
    public double dayGeneration() { return dayGeneration; }
    public double nightGeneration() { return nightGeneration; }
    public double capacity() { return capacity; }
    public double output() { return output; }

    @Override public BlockRenderType getRenderType(BlockState state) { return BlockRenderType.MODEL; }
    @Override public BlockEntity createBlockEntity(BlockPos pos, BlockState state) { return new FabricSolarPanelBlockEntity(pos, state); }

    @Override
    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        if (type == FabricSolarRegistry.BLOCK_ENTITY_TYPE) return (w, p, s, be) -> ((FabricSolarPanelBlockEntity) be).tick();
        return null;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient && world.getBlockEntity(pos) instanceof FabricSolarPanelBlockEntity panel) {
            player.sendMessage(Text.literal(panel.status()), true);
        }
        return ActionResult.SUCCESS;
    }
}
