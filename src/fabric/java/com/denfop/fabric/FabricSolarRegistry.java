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
    private static final double[] DAY_GENERATION = {
            5D, 20D, 80D, 320D, 1280D, 5120D, 20480D, 81920D,
            327680D, 1325720D, 5302880D, 21211520D, 84846080D, 339384320D
    };
    private static final double[] NIGHT_GENERATION = {
            5D, 10D, 40D, 160D, 640D, 2560D, 10240D, 40960D,
            327680D, 1325720D, 5302880D, 21211520D, 84846080D, 339384320D
    };
    private static final double[] STORAGE = {
            3200D, 20000D, 200000D, 1000000D, 5000000D, 5000000D, 100000000D,
            1500000000D, 5000000000D, 6500000000D, 10000000000D, 2500000000D,
            25000000000D, 2500000000000D
    };
    private static final double[] OUTPUT = {
            10D, 40D, 160D, 640D, 2560D, 10240D, 40960D, 327680D,
            655360D, 2651440D, 10605760D, 42423040D, 169692160D, 678768640D
    };
    public static final Block[] PANELS = new Block[NAMES.length];
    public static final net.minecraft.block.entity.BlockEntityType<FabricSolarPanelBlockEntity> BLOCK_ENTITY_TYPE;

    static {
        for (int i = 0; i < PANELS.length; i++) {
            PANELS[i] = new FabricSolarPanelBlock(
                    Block.Settings.copy(Blocks.IRON_BLOCK), i,
                    DAY_GENERATION[i], NIGHT_GENERATION[i], STORAGE[i], OUTPUT[i]
            );
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
