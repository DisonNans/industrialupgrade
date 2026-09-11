package com.denfop.fabric;

import com.denfop.fabric.machine.AlloySmelterBlock;
import com.denfop.fabric.machine.AlloySmelterBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public final class FabricRegistries {
    public static final Item ADVANCED_IRON_INGOT = new Item(new Item.Settings());
    public static final Block ALLOY_SMELTER = new AlloySmelterBlock(Block.Settings.copy(Blocks.IRON_BLOCK));
    public static final net.minecraft.block.entity.BlockEntityType<AlloySmelterBlockEntity> ALLOY_SMELTER_BLOCK_ENTITY =
            FabricBlockEntityTypeBuilder.create(AlloySmelterBlockEntity::new, ALLOY_SMELTER).build();

    private static boolean initialized;

    private FabricRegistries() {}

    public static void initialize() {
        if (initialized) return;
        initialized = true;

        registerItem("advanced_iron_ingot", ADVANCED_IRON_INGOT);
        registerBlock("alloy_smelter", ALLOY_SMELTER, true);
        Registry.register(Registries.BLOCK_ENTITY_TYPE, IndustrialUpgradeFabric.id("alloy_smelter"), ALLOY_SMELTER_BLOCK_ENTITY);
    }

    private static void registerItem(String path, Item item) {
        Registry.register(Registries.ITEM, IndustrialUpgradeFabric.id(path), item);
    }

    private static void registerBlock(String path, Block block, boolean addBlockItem) {
        Identifier id = IndustrialUpgradeFabric.id(path);
        Registry.register(Registries.BLOCK, id, block);
        if (addBlockItem) {
            Registry.register(Registries.ITEM, id, new BlockItem(block, new Item.Settings()));
        }
    }
}
