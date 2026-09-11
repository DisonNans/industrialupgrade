package com.denfop.fabric.machine;

import com.denfop.fabric.FabricGeneratorRegistry;
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

public final class FabricGeneratorBlock extends Block implements BlockEntityProvider {
    private final double coefficient;
    private final double capacity;

    public FabricGeneratorBlock(Settings settings, double coefficient, double capacity) {
        super(settings);
        this.coefficient = coefficient;
        this.capacity = capacity;
    }

    public double coefficient() { return coefficient; }
    public double capacity() { return capacity; }

    @Override public BlockRenderType getRenderType(BlockState state) { return BlockRenderType.MODEL; }
    @Override public BlockEntity createBlockEntity(BlockPos pos, BlockState state) { return new FabricGeneratorBlockEntity(pos, state); }

    @Override
    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        if (type == FabricGeneratorRegistry.BLOCK_ENTITY_TYPE) return (w, p, s, be) -> ((FabricGeneratorBlockEntity) be).tick();
        return null;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient && world.getBlockEntity(pos) instanceof FabricGeneratorBlockEntity generator) {
            player.sendMessage(Text.literal(generator.status()), true);
        }
        return ActionResult.SUCCESS;
    }
}
