package com.denfop.fabric;

import com.denfop.fabric.energy.CableType;
import com.denfop.fabric.machine.FabricCableBlock;
import com.denfop.fabric.machine.FabricCableBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;

public final class FabricCableRegistry {
    public static final Block[] CABLES = new Block[CableType.values().length];
    public static final net.minecraft.block.entity.BlockEntityType<FabricCableBlockEntity> BLOCK_ENTITY_TYPE;
    private static boolean initialized;

    static {
        for (CableType type : CableType.values()) {
            CABLES[type.ordinal()] = new FabricCableBlock(Block.Settings.copy(Blocks.GLASS), type);
        }
        BLOCK_ENTITY_TYPE = FabricBlockEntityTypeBuilder.create(FabricCableBlockEntity::new, CABLES).build();
    }

    private FabricCableRegistry() {}

    public static void initialize() {
        if (initialized) return;
        initialized = true;
        for (CableType type : CableType.values()) {
            String path = type.name().toLowerCase() + "_cable";
            Block block = CABLES[type.ordinal()];
            net.minecraft.util.Identifier id = IndustrialUpgradeFabric.id(path);
            Registry.register(Registries.BLOCK, id, block);
            Registry.register(Registries.ITEM, id, new BlockItem(block, new Item.Settings()));
        }
        Registry.register(Registries.BLOCK_ENTITY_TYPE, IndustrialUpgradeFabric.id("cable"), BLOCK_ENTITY_TYPE);
    }
}
