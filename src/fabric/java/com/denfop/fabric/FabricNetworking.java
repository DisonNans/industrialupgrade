package com.denfop.fabric;

public final class FabricNetworking {
    private static boolean initialized;

    private FabricNetworking() {}

    public static void initialize() {
        if (initialized) return;
        initialized = true;
        // Networking will use Fabric's play networking API. Packet payloads
        // are kept separate from machine logic so server/client behavior stays deterministic.
    }
}
