package com.denfop.fabric;

public final class FabricRecipes {
    private static boolean initialized;

    private FabricRecipes() {}

    public static void initialize() {
        if (initialized) return;
        initialized = true;
        // The original 1.12.2 recipe managers will be migrated into native
        // Minecraft recipe types plus Industrial Upgrade machine recipe managers.
    }
}
