package com.denfop.fabric.machine;

import com.denfop.fabric.energy.CableType;
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

public final class FabricCableBlock extends Block implements BlockEntityProvider {
    private final CableType cableType;

    public FabricCableBlock(Settings settings, CableType cableType) {
        super(settings.nonOpaque());
        this.cableType = cableType;
    }

    public CableType cableType() { return cableType; }

    @Override public BlockRenderType getRenderType(BlockState state) { return BlockRenderType.MODEL; }
    @Override public BlockEntity createBlockEntity(BlockPos pos, BlockState state) { return new FabricCableBlockEntity(pos, state); }

    @Override
    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        if (type == FabricCableRegistry.BLOCK_ENTITY_TYPE) {
            return (w, p, s, be) -> ((FabricCableBlockEntity) be).tick();
        }
        return null;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient && world.getBlockEntity(pos) instanceof FabricCableBlockEntity cable) {
            player.sendMessage(Text.literal(cable.status()), true);
        }
        return ActionResult.SUCCESS;
    }
}
