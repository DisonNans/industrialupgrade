package com.denfop.fabric;

public final class FabricBlockEntities {
    private static boolean initialized;

    private FabricBlockEntities() {}

    public static void initialize() {
        if (initialized) return;
        initialized = true;
        // Migrated machine BlockEntity types will be registered here.
    }
}
