package com.denfop.fabric;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public final class FabricItemGroups {
    public static final ItemGroup INDUSTRIAL_UPGRADE = FabricItemGroup.builder()
            .icon(() -> new ItemStack(FabricRegistries.ADVANCED_IRON_INGOT))
            .displayName(Text.translatable("itemGroup.industrialupgrade.main"))
            .entries((context, entries) -> {
                entries.add(new ItemStack(FabricRegistries.ADVANCED_IRON_INGOT));
                entries.add(new ItemStack(FabricRegistries.SILVER_INGOT));
                entries.add(new ItemStack(FabricRegistries.NICKEL_INGOT));
                entries.add(new ItemStack(FabricRegistries.ZINC_INGOT));
                entries.add(new ItemStack(FabricRegistries.COPPER_INGOT));
                entries.add(new ItemStack(FabricRegistries.CHROMIUM_INGOT));
                entries.add(new ItemStack(FabricRegistries.ALUMINUM_INGOT));
                entries.add(new ItemStack(FabricRegistries.MAGNESIUM_INGOT));
                entries.add(new ItemStack(FabricRegistries.TITANIUM_INGOT));
                entries.add(new ItemStack(FabricRegistries.MANGANESE_INGOT));
                entries.add(new ItemStack(FabricRegistries.ELECTRUM_INGOT));
                entries.add(new ItemStack(FabricRegistries.INVAR_INGOT));
                entries.add(new ItemStack(FabricRegistries.RED_BRASS_INGOT));
                entries.add(new ItemStack(FabricRegistries.NICHROME_INGOT));
                entries.add(new ItemStack(FabricRegistries.DURALUMIN_INGOT));
                entries.add(new ItemStack(FabricRegistries.ALUMEL_INGOT));
                entries.add(new ItemStack(FabricRegistries.FERROMANGANESE_INGOT));
                entries.add(new ItemStack(FabricRegistries.ALLOY_SMELTER.asItem()));
            })
            .build();

    private FabricItemGroups() {}

    public static void initialize() {
        net.minecraft.registry.Registry.register(
                net.minecraft.registry.Registries.ITEM_GROUP,
                new Identifier(IndustrialUpgradeFabric.MOD_ID, "industrial_upgrade"),
                INDUSTRIAL_UPGRADE
        );
    }
}
