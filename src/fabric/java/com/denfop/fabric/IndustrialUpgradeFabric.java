package com.denfop.fabric;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class IndustrialUpgradeFabric implements ModInitializer {
    public static final String MOD_ID = "industrialupgrade";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static Identifier id(String path) { return new Identifier(MOD_ID, path); }

    @Override
    public void onInitialize() {
        FabricItemGroups.initialize();
        FabricRegistries.initialize();
        FabricMultiMachineRegistry.initialize();
        FabricCableRegistry.initialize();
        FabricGeneratorRegistry.initialize();
        FabricStorageRegistry.initialize();
        FabricSolarRegistry.initialize();
        FabricBlockEntities.initialize();
        FabricRecipes.initialize();
        FabricNetworking.initialize();
        LOGGER.info("Industrial Upgrade Fabric port initialized; original 1.12.2 gameplay migration is active.");
    }
}
