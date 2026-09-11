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
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public final class IndustrialProcessorBlock extends Block implements BlockEntityProvider {
    private final int machineType;
    private final int slotCount;

    public IndustrialProcessorBlock(Settings settings, int machineType, int slotCount) {
        super(settings);
        this.machineType = machineType;
        this.slotCount = slotCount;
    }

    public int machineType() { return machineType; }
    public int slotCount() { return slotCount; }

    @Override
    public BlockRenderType getRenderType(BlockState state) { return BlockRenderType.MODEL; }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new IndustrialProcessorBlockEntity(pos, state, machineType, slotCount);
    }

    @Override
    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        if (type == FabricRegistries.INDUSTRIAL_PROCESSOR_BLOCK_ENTITY) {
            return (w, p, s, be) -> ((IndustrialProcessorBlockEntity) be).tick();
        }
        return null;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient && world.getBlockEntity(pos) instanceof IndustrialProcessorBlockEntity be) {
            player.sendMessage(Text.literal(be.status()), true);
        }
        return ActionResult.SUCCESS;
    }
}
