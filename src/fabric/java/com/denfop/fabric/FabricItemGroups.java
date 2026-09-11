package com.denfop.fabric;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public final class FabricItemGroups {
    public static final ItemGroup INDUSTRIAL_UPGRADE = FabricItemGroup.builder()
            .icon(() -> new ItemStack(net.minecraft.item.Items.IRON_INGOT))
            .displayName(Text.translatable("itemGroup.industrialupgrade.main"))
            .entries((context, entries) -> { })
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
