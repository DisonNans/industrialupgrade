package com.denfop.fabric;

import com.denfop.fabric.machine.FabricSolarPanelBlock;
import com.denfop.fabric.machine.FabricSolarPanelBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;

public final class FabricSolarRegistry {
    public static final String[] NAMES = {
            "advanced_solar_panel", "hybrid_solar_panel", "perfect_solar_panel", "quantum_solar_panel",
            "spectral_solar_panel", "proton_solar_panel", "singular_solar_panel", "diffraction_solar_panel",
            "photonic_solar_panel", "neutronium_solar_panel", "barion_solar_panel", "hadron_solar_panel",
            "graviton_solar_panel", "quark_solar_panel"
    };
    private static final double[] GENERATION = {
            1D, 8D, 64D, 512D, 4096D, 32768D, 262144D,
            1048576D, 4194304D, 16777216D, 67108864D, 268435456D,
            1073741824D, 4294967296D
    };
    private static final double[] STORAGE = {
            8000D, 64000D, 512000D, 4096000D, 32768000D, 262144000D, 2097152000D,
            8388608000D, 33554432000D, 134217728000D, 536870912000D, 2147483648000D,
            8589934592000D, 34359738368000D
    };
    public static final Block[] PANELS = new Block[NAMES.length];
    public static final net.minecraft.block.entity.BlockEntityType<FabricSolarPanelBlockEntity> BLOCK_ENTITY_TYPE;

    static {
        for (int i = 0; i < PANELS.length; i++) {
            PANELS[i] = new FabricSolarPanelBlock(Block.Settings.copy(Blocks.IRON_BLOCK), i, GENERATION[i], STORAGE[i]);
        }
        BLOCK_ENTITY_TYPE = FabricBlockEntityTypeBuilder.create(FabricSolarPanelBlockEntity::new, PANELS).build();
    }

    private static boolean initialized;
    private FabricSolarRegistry() {}

    public static void initialize() {
        if (initialized) return;
        initialized = true;
        for (int i = 0; i < PANELS.length; i++) {
            var id = IndustrialUpgradeFabric.id(NAMES[i]);
            Registry.register(Registries.BLOCK, id, PANELS[i]);
            Registry.register(Registries.ITEM, id, new BlockItem(PANELS[i], new Item.Settings()));
        }
        Registry.register(Registries.BLOCK_ENTITY_TYPE, IndustrialUpgradeFabric.id("solar_panel"), BLOCK_ENTITY_TYPE);
    }
}
