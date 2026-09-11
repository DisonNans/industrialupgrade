package com.denfop.fabric;

import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;

public final class FabricRegistries {
    private static boolean initialized;

    private FabricRegistries() {}

    public static void initialize() {
        if (initialized) return;
        initialized = true;
        // Machine, material and item registration is deliberately centralized here.
        // Each migrated registry entry will retain the original Industrial Upgrade id.
    }

    public static <T> T register(String path, T value) {
        return Registry.register(Registries.ITEM, IndustrialUpgradeFabric.id(path), (net.minecraft.item.Item) value);
    }
}
