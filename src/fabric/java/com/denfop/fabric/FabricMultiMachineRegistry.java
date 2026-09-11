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

    public static final Block DOUBLE_ROLLING = machine("double_rolling", 5, 2);
    public static final Block TRIPLE_ROLLING = machine("triple_rolling", 5, 3);
    public static final Block QUAD_ROLLING = machine("quad_rolling", 5, 4);
    public static final Block DOUBLE_EXTRUDING = machine("double_extruding", 6, 2);
    public static final Block TRIPLE_EXTRUDING = machine("triple_extruding", 6, 3);
    public static final Block QUAD_EXTRUDING = machine("quad_extruding", 6, 4);
    public static final Block DOUBLE_CUTTING = machine("double_cutting", 7, 2);
    public static final Block TRIPLE_CUTTING = machine("triple_cutting", 7, 3);
    public static final Block QUAD_CUTTING = machine("quad_cutting", 7, 4);
    public static final Block DOUBLE_FERMER = machine("double_fermer", 8, 2);
    public static final Block TRIPLE_FERMER = machine("triple_fermer", 8, 3);
    public static final Block QUAD_FERMER = machine("quad_fermer", 8, 4);
    public static final Block DOUBLE_RECYCLER = machine("double_recycler", 9, 2);
    public static final Block TRIPLE_RECYCLER = machine("triple_recycler", 9, 3);
    public static final Block QUAD_RECYCLER = machine("quad_recycler", 9, 4);
    public static final Block DOUBLE_ASSAMPLER_SCRAP = machine("double_assampler_scrap", 10, 2);
    public static final Block TRIPLE_ASSAMPLER_SCRAP = machine("triple_assampler_scrap", 10, 3);
    public static final Block QUAD_ASSAMPLER_SCRAP = machine("quad_assampler_scrap", 10, 4);

    public static final net.minecraft.block.entity.BlockEntityType<IndustrialProcessorBlockEntity> BLOCK_ENTITY_TYPE =
            FabricBlockEntityTypeBuilder.create(IndustrialProcessorBlockEntity::new,
                    DOUBLE_MACERATOR, TRIPLE_MACERATOR, QUAD_MACERATOR,
                    DOUBLE_COMPRESSOR, TRIPLE_COMPRESSOR, QUAD_COMPRESSOR,
                    DOUBLE_ELECTRIC_FURNACE, TRIPLE_ELECTRIC_FURNACE, QUAD_ELECTRIC_FURNACE,
                    DOUBLE_EXTRACTOR, TRIPLE_EXTRACTOR, QUAD_EXTRACTOR,
                    DOUBLE_ROLLING, TRIPLE_ROLLING, QUAD_ROLLING,
                    DOUBLE_EXTRUDING, TRIPLE_EXTRUDING, QUAD_EXTRUDING,
                    DOUBLE_CUTTING, TRIPLE_CUTTING, QUAD_CUTTING,
                    DOUBLE_FERMER, TRIPLE_FERMER, QUAD_FERMER,
                    DOUBLE_RECYCLER, TRIPLE_RECYCLER, QUAD_RECYCLER,
                    DOUBLE_ASSAMPLER_SCRAP, TRIPLE_ASSAMPLER_SCRAP, QUAD_ASSAMPLER_SCRAP).build();

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
        registerBlock("double_rolling", DOUBLE_ROLLING);
        registerBlock("triple_rolling", TRIPLE_ROLLING);
        registerBlock("quad_rolling", QUAD_ROLLING);
        registerBlock("double_extruding", DOUBLE_EXTRUDING);
        registerBlock("triple_extruding", TRIPLE_EXTRUDING);
        registerBlock("quad_extruding", QUAD_EXTRUDING);
        registerBlock("double_cutting", DOUBLE_CUTTING);
        registerBlock("triple_cutting", TRIPLE_CUTTING);
        registerBlock("quad_cutting", QUAD_CUTTING);
        registerBlock("double_fermer", DOUBLE_FERMER);
        registerBlock("triple_fermer", TRIPLE_FERMER);
        registerBlock("quad_fermer", QUAD_FERMER);
        registerBlock("double_recycler", DOUBLE_RECYCLER);
        registerBlock("triple_recycler", TRIPLE_RECYCLER);
        registerBlock("quad_recycler", QUAD_RECYCLER);
        registerBlock("double_assampler_scrap", DOUBLE_ASSAMPLER_SCRAP);
        registerBlock("triple_assampler_scrap", TRIPLE_ASSAMPLER_SCRAP);
        registerBlock("quad_assampler_scrap", QUAD_ASSAMPLER_SCRAP);
        Registry.register(Registries.BLOCK_ENTITY_TYPE, IndustrialUpgradeFabric.id("industrial_processor"), BLOCK_ENTITY_TYPE);
    }

    private static void registerItem(String path, Item item) { Registry.register(Registries.ITEM, IndustrialUpgradeFabric.id(path), item); }
    private static void registerBlock(String path, Block block) {
        var id = IndustrialUpgradeFabric.id(path);
        Registry.register(Registries.BLOCK, id, block);
        Registry.register(Registries.ITEM, id, new BlockItem(block, new Item.Settings()));
    }
}
