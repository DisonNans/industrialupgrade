package com.denfop.fabric;

import com.denfop.fabric.machine.FabricGeneratorBlock;
import com.denfop.fabric.machine.FabricGeneratorBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;

public final class FabricGeneratorRegistry {
    public static final Block ADVANCED_GENERATOR = new FabricGeneratorBlock(Block.Settings.copy(Blocks.IRON_BLOCK), 1.2D, 8000D);
    public static final Block IMPROVED_GENERATOR = new FabricGeneratorBlock(Block.Settings.copy(Blocks.IRON_BLOCK), 1.4D, 16000D);
    public static final Block PERFECT_GENERATOR = new FabricGeneratorBlock(Block.Settings.copy(Blocks.IRON_BLOCK), 1.8D, 32000D);

    public static final net.minecraft.block.entity.BlockEntityType<FabricGeneratorBlockEntity> BLOCK_ENTITY_TYPE =
            FabricBlockEntityTypeBuilder.create(FabricGeneratorBlockEntity::new, ADVANCED_GENERATOR, IMPROVED_GENERATOR, PERFECT_GENERATOR).build();

    private static boolean initialized;
    private FabricGeneratorRegistry() {}

    public static void initialize() {
        if (initialized) return;
        initialized = true;
        registerBlock("advanced_generator", ADVANCED_GENERATOR);
        registerBlock("improved_generator", IMPROVED_GENERATOR);
        registerBlock("perfect_generator", PERFECT_GENERATOR);
        Registry.register(Registries.BLOCK_ENTITY_TYPE, IndustrialUpgradeFabric.id("generator"), BLOCK_ENTITY_TYPE);
    }

    private static void registerBlock(String path, Block block) {
        var id = IndustrialUpgradeFabric.id(path);
        Registry.register(Registries.BLOCK, id, block);
        Registry.register(Registries.ITEM, id, new BlockItem(block, new Item.Settings()));
    }
}
