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
    public static final Item SILVER_INGOT = new Item(new Item.Settings());
    public static final Item NICKEL_INGOT = new Item(new Item.Settings());
    public static final Item ZINC_INGOT = new Item(new Item.Settings());
    public static final Item COPPER_INGOT = new Item(new Item.Settings());
    public static final Item CHROMIUM_INGOT = new Item(new Item.Settings());
    public static final Item ALUMINUM_INGOT = new Item(new Item.Settings());
    public static final Item MAGNESIUM_INGOT = new Item(new Item.Settings());
    public static final Item TITANIUM_INGOT = new Item(new Item.Settings());
    public static final Item MANGANESE_INGOT = new Item(new Item.Settings());

    public static final Item ELECTRUM_INGOT = new Item(new Item.Settings());
    public static final Item INVAR_INGOT = new Item(new Item.Settings());
    public static final Item RED_BRASS_INGOT = new Item(new Item.Settings());
    public static final Item NICHROME_INGOT = new Item(new Item.Settings());
    public static final Item DURALUMIN_INGOT = new Item(new Item.Settings());
    public static final Item ALUMEL_INGOT = new Item(new Item.Settings());
    public static final Item FERROMANGANESE_INGOT = new Item(new Item.Settings());

    public static final Block ALLOY_SMELTER = new AlloySmelterBlock(Block.Settings.copy(Blocks.IRON_BLOCK));
    public static final net.minecraft.block.entity.BlockEntityType<AlloySmelterBlockEntity> ALLOY_SMELTER_BLOCK_ENTITY =
            FabricBlockEntityTypeBuilder.create(AlloySmelterBlockEntity::new, ALLOY_SMELTER).build();

    private static boolean initialized;

    private FabricRegistries() {}

    public static void initialize() {
        if (initialized) return;
        initialized = true;

        registerItem("advanced_iron_ingot", ADVANCED_IRON_INGOT);
        registerItem("silver_ingot", SILVER_INGOT);
        registerItem("nickel_ingot", NICKEL_INGOT);
        registerItem("zinc_ingot", ZINC_INGOT);
        registerItem("copper_ingot", COPPER_INGOT);
        registerItem("chromium_ingot", CHROMIUM_INGOT);
        registerItem("aluminum_ingot", ALUMINUM_INGOT);
        registerItem("magnesium_ingot", MAGNESIUM_INGOT);
        registerItem("titanium_ingot", TITANIUM_INGOT);
        registerItem("manganese_ingot", MANGANESE_INGOT);

        registerItem("electrum_ingot", ELECTRUM_INGOT);
        registerItem("invar_ingot", INVAR_INGOT);
        registerItem("red_brass_ingot", RED_BRASS_INGOT);
        registerItem("nichrome_ingot", NICHROME_INGOT);
        registerItem("duralumin_ingot", DURALUMIN_INGOT);
        registerItem("alumel_ingot", ALUMEL_INGOT);
        registerItem("ferromanganese_ingot", FERROMANGANESE_INGOT);

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
