package com.denfop.fabric;

import com.denfop.fabric.energy.CableType;
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
                entries.add(new ItemStack(FabricRegistries.LEAD_INGOT));
                entries.add(new ItemStack(FabricRegistries.TIN_INGOT));
                entries.add(new ItemStack(FabricRegistries.VANADY_INGOT));
                entries.add(new ItemStack(FabricRegistries.COBALT_INGOT));
                entries.add(new ItemStack(FabricRegistries.TUNGSTEN_INGOT));
                entries.add(new ItemStack(FabricRegistries.ELECTRUM_INGOT));
                entries.add(new ItemStack(FabricRegistries.INVAR_INGOT));
                entries.add(new ItemStack(FabricRegistries.RED_BRASS_INGOT));
                entries.add(new ItemStack(FabricRegistries.NICHROME_INGOT));
                entries.add(new ItemStack(FabricRegistries.DURALUMIN_INGOT));
                entries.add(new ItemStack(FabricRegistries.ALUMEL_INGOT));
                entries.add(new ItemStack(FabricRegistries.FERROMANGANESE_INGOT));
                entries.add(new ItemStack(FabricRegistries.ALUMINUM_BRONZE_INGOT));
                entries.add(new ItemStack(FabricRegistries.MUNTSA_INGOT));
                entries.add(new ItemStack(FabricRegistries.ALCLED_INGOT));
                entries.add(new ItemStack(FabricRegistries.VANADOALUMITE_INGOT));
                entries.add(new ItemStack(FabricRegistries.VITALIUM_INGOT));
                entries.add(new ItemStack(FabricRegistries.ALLOY_SMELTER.asItem()));
                entries.add(new ItemStack(FabricRegistries.ADVANCED_ALLOY_SMELTER.asItem()));
                entries.add(new ItemStack(FabricMultiMachineRegistry.IRON_INGOT));
                entries.add(new ItemStack(FabricMultiMachineRegistry.IRON_DUST));
                entries.add(new ItemStack(FabricMultiMachineRegistry.COPPER_DUST));
                entries.add(new ItemStack(FabricMultiMachineRegistry.TIN_DUST));
                entries.add(new ItemStack(FabricMultiMachineRegistry.LEAD_DUST));
                entries.add(new ItemStack(FabricMultiMachineRegistry.NICKEL_DUST));
                entries.add(new ItemStack(FabricMultiMachineRegistry.ZINC_DUST));
                entries.add(new ItemStack(FabricMultiMachineRegistry.SILVER_DUST));
                entries.add(new ItemStack(FabricMultiMachineRegistry.CHROMIUM_DUST));
                entries.add(new ItemStack(FabricMultiMachineRegistry.ALUMINUM_DUST));
                entries.add(new ItemStack(FabricMultiMachineRegistry.MAGNESIUM_DUST));
                entries.add(new ItemStack(FabricMultiMachineRegistry.TITANIUM_DUST));
                entries.add(new ItemStack(FabricMultiMachineRegistry.MANGANESE_DUST));
                entries.add(new ItemStack(FabricMultiMachineRegistry.VANADY_DUST));
                entries.add(new ItemStack(FabricMultiMachineRegistry.COBALT_DUST));
                entries.add(new ItemStack(FabricMultiMachineRegistry.TUNGSTEN_DUST));
                entries.add(new ItemStack(FabricMultiMachineRegistry.DOUBLE_MACERATOR.asItem()));
                entries.add(new ItemStack(FabricMultiMachineRegistry.TRIPLE_MACERATOR.asItem()));
                entries.add(new ItemStack(FabricMultiMachineRegistry.QUAD_MACERATOR.asItem()));
                entries.add(new ItemStack(FabricMultiMachineRegistry.DOUBLE_COMPRESSOR.asItem()));
                entries.add(new ItemStack(FabricMultiMachineRegistry.TRIPLE_COMPRESSOR.asItem()));
                entries.add(new ItemStack(FabricMultiMachineRegistry.QUAD_COMPRESSOR.asItem()));
                entries.add(new ItemStack(FabricMultiMachineRegistry.DOUBLE_ELECTRIC_FURNACE.asItem()));
                entries.add(new ItemStack(FabricMultiMachineRegistry.TRIPLE_ELECTRIC_FURNACE.asItem()));
                entries.add(new ItemStack(FabricMultiMachineRegistry.QUAD_ELECTRIC_FURNACE.asItem()));
                entries.add(new ItemStack(FabricMultiMachineRegistry.DOUBLE_EXTRACTOR.asItem()));
                entries.add(new ItemStack(FabricMultiMachineRegistry.TRIPLE_EXTRACTOR.asItem()));
                entries.add(new ItemStack(FabricMultiMachineRegistry.QUAD_EXTRACTOR.asItem()));
                entries.add(new ItemStack(FabricMultiMachineRegistry.DOUBLE_ROLLING.asItem()));
                entries.add(new ItemStack(FabricMultiMachineRegistry.TRIPLE_ROLLING.asItem()));
                entries.add(new ItemStack(FabricMultiMachineRegistry.QUAD_ROLLING.asItem()));
                entries.add(new ItemStack(FabricMultiMachineRegistry.DOUBLE_EXTRUDING.asItem()));
                entries.add(new ItemStack(FabricMultiMachineRegistry.TRIPLE_EXTRUDING.asItem()));
                entries.add(new ItemStack(FabricMultiMachineRegistry.QUAD_EXTRUDING.asItem()));
                entries.add(new ItemStack(FabricMultiMachineRegistry.DOUBLE_CUTTING.asItem()));
                entries.add(new ItemStack(FabricMultiMachineRegistry.TRIPLE_CUTTING.asItem()));
                entries.add(new ItemStack(FabricMultiMachineRegistry.QUAD_CUTTING.asItem()));
                entries.add(new ItemStack(FabricMultiMachineRegistry.DOUBLE_FERMER.asItem()));
                entries.add(new ItemStack(FabricMultiMachineRegistry.TRIPLE_FERMER.asItem()));
                entries.add(new ItemStack(FabricMultiMachineRegistry.QUAD_FERMER.asItem()));
                entries.add(new ItemStack(FabricMultiMachineRegistry.DOUBLE_RECYCLER.asItem()));
                entries.add(new ItemStack(FabricMultiMachineRegistry.TRIPLE_RECYCLER.asItem()));
                entries.add(new ItemStack(FabricMultiMachineRegistry.QUAD_RECYCLER.asItem()));
                entries.add(new ItemStack(FabricMultiMachineRegistry.DOUBLE_ASSAMPLER_SCRAP.asItem()));
                entries.add(new ItemStack(FabricMultiMachineRegistry.TRIPLE_ASSAMPLER_SCRAP.asItem()));
                entries.add(new ItemStack(FabricMultiMachineRegistry.QUAD_ASSAMPLER_SCRAP.asItem()));
                for (CableType type : CableType.values()) entries.add(new ItemStack(FabricCableRegistry.CABLES[type.ordinal()]));
                entries.add(new ItemStack(FabricGeneratorRegistry.ADVANCED_GENERATOR.asItem()));
                entries.add(new ItemStack(FabricGeneratorRegistry.IMPROVED_GENERATOR.asItem()));
                entries.add(new ItemStack(FabricGeneratorRegistry.PERFECT_GENERATOR.asItem()));
                entries.add(new ItemStack(FabricStorageRegistry.BATBOX.asItem()));
                entries.add(new ItemStack(FabricStorageRegistry.CESU.asItem()));
                entries.add(new ItemStack(FabricStorageRegistry.MFE.asItem()));
                entries.add(new ItemStack(FabricStorageRegistry.MFSU.asItem()));
                entries.add(new ItemStack(FabricStorageRegistry.IMP_MFSU.asItem()));
                entries.add(new ItemStack(FabricStorageRegistry.ULT_MFSU.asItem()));
                entries.add(new ItemStack(FabricStorageRegistry.PERFECT_MFSU.asItem()));
                entries.add(new ItemStack(FabricStorageRegistry.BARION_MFSU.asItem()));
                entries.add(new ItemStack(FabricStorageRegistry.HADRON_MFSU.asItem()));
                entries.add(new ItemStack(FabricStorageRegistry.GRAVITON_MFSU.asItem()));
                entries.add(new ItemStack(FabricStorageRegistry.QUARK_MFSU.asItem()));
                for (var panel : FabricSolarRegistry.PANELS) entries.add(new ItemStack(panel.asItem()));
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
