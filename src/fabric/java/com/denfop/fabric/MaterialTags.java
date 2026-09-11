package com.denfop.fabric;

import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public final class MaterialTags {
    public static final TagKey<Item> SILVER_INGOTS = itemTag("ingots/silver");
    public static final TagKey<Item> NICKEL_INGOTS = itemTag("ingots/nickel");
    public static final TagKey<Item> ZINC_INGOTS = itemTag("ingots/zinc");
    public static final TagKey<Item> COPPER_INGOTS = itemTag("ingots/copper");
    public static final TagKey<Item> CHROMIUM_INGOTS = itemTag("ingots/chromium");
    public static final TagKey<Item> ALUMINUM_INGOTS = itemTag("ingots/aluminum");
    public static final TagKey<Item> MAGNESIUM_INGOTS = itemTag("ingots/magnesium");
    public static final TagKey<Item> TITANIUM_INGOTS = itemTag("ingots/titanium");
    public static final TagKey<Item> MANGANESE_INGOTS = itemTag("ingots/manganese");

    private MaterialTags() {}

    private static TagKey<Item> itemTag(String path) {
        return TagKey.of(RegistryKeys.ITEM, IndustrialUpgradeFabric.id(path));
    }
}
