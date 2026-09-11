package com.denfop.fabric.machine;

import com.denfop.fabric.FabricRegistries;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public final class AdvancedAlloySmelterBlock extends Block implements BlockEntityProvider {
    public AdvancedAlloySmelterBlock(Settings settings) { super(settings); }

    @Override
    public BlockRenderType getRenderType(BlockState state) { return BlockRenderType.MODEL; }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new AdvancedAlloySmelterBlockEntity(pos, state);
    }

    @Override
    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        if (type == FabricRegistries.ADVANCED_ALLOY_SMELTER_BLOCK_ENTITY) {
            return (w, p, s, be) -> ((AdvancedAlloySmelterBlockEntity) be).tick();
        }
        return null;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            player.sendMessage(net.minecraft.text.Text.translatable("block.industrialupgrade.advanced_alloy_smelter.status"), true);
        }
        return ActionResult.SUCCESS;
    }
}
