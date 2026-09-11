package com.denfop.fabric;

import com.denfop.fabric.machine.IndustrialProcessorBlock;
import com.denfop.fabric.machine.IndustrialProcessorBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;

/** Standalone registry for the migrated IC2-style multi-machine line. */
public final class FabricMultiMachineRegistry {
    public static final Item IRON_INGOT = new Item(new Item.Settings());
    public static final Item IRON_DUST = new Item(new Item.Settings());
    public static final Item COPPER_DUST = new Item(new Item.Settings());
    public static final Item TIN_DUST = new Item(new Item.Settings());
    public static final Item LEAD_DUST = new Item(new Item.Settings());
    public static final Item NICKEL_DUST = new Item(new Item.Settings());
    public static final Item ZINC_DUST = new Item(new Item.Settings());
    public static final Item SILVER_DUST = new Item(new Item.Settings());
    public static final Item CHROMIUM_DUST = new Item(new Item.Settings());
    public static final Item ALUMINUM_DUST = new Item(new Item.Settings());
    public static final Item MAGNESIUM_DUST = new Item(new Item.Settings());
    public static final Item TITANIUM_DUST = new Item(new Item.Settings());
    public static final Item MANGANESE_DUST = new Item(new Item.Settings());
    public static final Item VANADY_DUST = new Item(new Item.Settings());
    public static final Item COBALT_DUST = new Item(new Item.Settings());
    public static final Item TUNGSTEN_DUST = new Item(new Item.Settings());

    public static final Block DOUBLE_MACERATOR = machine("double_macerator", 1, 2);
    public static final Block TRIPLE_MACERATOR = machine("triple_macerator", 1, 3);
    public static final Block QUAD_MACERATOR = machine("quad_macerator", 1, 4);
    public static final Block DOUBLE_COMPRESSOR = machine("double_compressor", 2, 2);
    public static final Block TRIPLE_COMPRESSOR = machine("triple_compressor", 2, 3);
    public static final Block QUAD_COMPRESSOR = machine("quad_compressor", 2, 4);
    public static final Block DOUBLE_ELECTRIC_FURNACE = machine("double_electric_furnace", 3, 2);
    public static final Block TRIPLE_ELECTRIC_FURNACE = machine("triple_electric_furnace", 3, 3);
    public static final Block QUAD_ELECTRIC_FURNACE = machine("quad_electric_furnace", 3, 4);
    public static final Block DOUBLE_EXTRACTOR = machine("double_extractor", 4, 2);
    public static final Block TRIPLE_EXTRACTOR = machine("triple_extractor", 4, 3);
    public static final Block QUAD_EXTRACTOR = machine("quad_extractor", 4, 4);

    public static final net.minecraft.block.entity.BlockEntityType<IndustrialProcessorBlockEntity> BLOCK_ENTITY_TYPE =
            FabricBlockEntityTypeBuilder.create(IndustrialProcessorBlockEntity::new,
                    DOUBLE_MACERATOR, TRIPLE_MACERATOR, QUAD_MACERATOR,
                    DOUBLE_COMPRESSOR, TRIPLE_COMPRESSOR, QUAD_COMPRESSOR,
                    DOUBLE_ELECTRIC_FURNACE, TRIPLE_ELECTRIC_FURNACE, QUAD_ELECTRIC_FURNACE,
                    DOUBLE_EXTRACTOR, TRIPLE_EXTRACTOR, QUAD_EXTRACTOR).build();

    private static boolean initialized;

    private FabricMultiMachineRegistry() {}

    private static Block machine(String id, int type, int slots) {
        return new IndustrialProcessorBlock(Block.Settings.copy(Blocks.IRON_BLOCK), type, slots);
    }

    public static void initialize() {
        if (initialized) return;
        initialized = true;
        registerItem("iron_ingot", IRON_INGOT);
        registerItem("iron_dust", IRON_DUST);
        registerItem("copper_dust", COPPER_DUST);
        registerItem("tin_dust", TIN_DUST);
        registerItem("lead_dust", LEAD_DUST);
        registerItem("nickel_dust", NICKEL_DUST);
        registerItem("zinc_dust", ZINC_DUST);
        registerItem("silver_dust", SILVER_DUST);
        registerItem("chromium_dust", CHROMIUM_DUST);
        registerItem("aluminum_dust", ALUMINUM_DUST);
        registerItem("magnesium_dust", MAGNESIUM_DUST);
        registerItem("titanium_dust", TITANIUM_DUST);
        registerItem("manganese_dust", MANGANESE_DUST);
        registerItem("vanady_dust", VANADY_DUST);
        registerItem("cobalt_dust", COBALT_DUST);
        registerItem("tungsten_dust", TUNGSTEN_DUST);

        registerBlock("double_macerator", DOUBLE_MACERATOR);
        registerBlock("triple_macerator", TRIPLE_MACERATOR);
        registerBlock("quad_macerator", QUAD_MACERATOR);
        registerBlock("double_compressor", DOUBLE_COMPRESSOR);
        registerBlock("triple_compressor", TRIPLE_COMPRESSOR);
        registerBlock("quad_compressor", QUAD_COMPRESSOR);
        registerBlock("double_electric_furnace", DOUBLE_ELECTRIC_FURNACE);
        registerBlock("triple_electric_furnace", TRIPLE_ELECTRIC_FURNACE);
        registerBlock("quad_electric_furnace", QUAD_ELECTRIC_FURNACE);
        registerBlock("double_extractor", DOUBLE_EXTRACTOR);
        registerBlock("triple_extractor", TRIPLE_EXTRACTOR);
        registerBlock("quad_extractor", QUAD_EXTRACTOR);
        Registry.register(Registries.BLOCK_ENTITY_TYPE, IndustrialUpgradeFabric.id("industrial_processor"), BLOCK_ENTITY_TYPE);
    }

    private static void registerItem(String path, Item item) {
        Registry.register(Registries.ITEM, IndustrialUpgradeFabric.id(path), item);
    }

    private static void registerBlock(String path, Block block) {
        var id = IndustrialUpgradeFabric.id(path);
        Registry.register(Registries.BLOCK, id, block);
        Registry.register(Registries.ITEM, id, new BlockItem(block, new Item.Settings()));
    }
}
