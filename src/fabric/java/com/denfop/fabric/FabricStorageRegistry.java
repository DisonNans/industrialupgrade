package com.denfop.fabric;

import com.denfop.fabric.machine.FabricStorageBlock;
import com.denfop.fabric.machine.FabricStorageBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;

public final class FabricStorageRegistry {
    public static final Block BATBOX = new FabricStorageBlock(Block.Settings.copy(Blocks.IRON_BLOCK), 40000D, 32D);
    public static final Block CESU = new FabricStorageBlock(Block.Settings.copy(Blocks.IRON_BLOCK), 300000D, 128D);
    public static final Block MFE = new FabricStorageBlock(Block.Settings.copy(Blocks.IRON_BLOCK), 4000000D, 512D);
    public static final Block MFSU = new FabricStorageBlock(Block.Settings.copy(Blocks.IRON_BLOCK), 40000000D, 2048D);

    public static final net.minecraft.block.entity.BlockEntityType<FabricStorageBlockEntity> BLOCK_ENTITY_TYPE =
            FabricBlockEntityTypeBuilder.create(FabricStorageBlockEntity::new, BATBOX, CESU, MFE, MFSU).build();

    private static boolean initialized;
    private FabricStorageRegistry() {}

    public static void initialize() {
        if (initialized) return;
        initialized = true;
        registerBlock("batbox_iu", BATBOX);
        registerBlock("cesu_iu", CESU);
        registerBlock("mfe_iu", MFE);
        registerBlock("mfsu_iu", MFSU);
        Registry.register(Registries.BLOCK_ENTITY_TYPE, IndustrialUpgradeFabric.id("storage"), BLOCK_ENTITY_TYPE);
    }

    private static void registerBlock(String path, Block block) {
        var id = IndustrialUpgradeFabric.id(path);
        Registry.register(Registries.BLOCK, id, block);
        Registry.register(Registries.ITEM, id, new BlockItem(block, new Item.Settings()));
    }
}
