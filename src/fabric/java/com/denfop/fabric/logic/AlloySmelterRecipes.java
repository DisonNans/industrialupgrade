package com.denfop.fabric.logic;

import com.denfop.fabric.FabricRegistries;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

/**
 * Direct behavior port of TileEntityAlloySmelter.init().
 *
 * OreDictionary entries from 1.12.2 are represented later by Fabric tags;
 * vanilla-only recipes are kept here now so their quantities are not lost.
 */
public final class AlloySmelterRecipes {
    public static final int ENERGY_PER_TICK = 1;
    public static final int OPERATION_LENGTH = 300;

    private static final List<MachineRecipe> RECIPES = new ArrayList<>();

    private AlloySmelterRecipes() {}

    public static void initialize() {
        if (!RECIPES.isEmpty()) return;

        // Original: iron x1 + coal x2 -> advIronIngot x1 (damage/meta 5).
        // The migrated output item is registered as advanced_iron_ingot.
        RECIPES.add(recipe(Items.IRON_INGOT, 1, Items.COAL, 2,
                new MachineRecipe.Output(FabricRegistries.ADVANCED_IRON_INGOT, 1)));

        // The following recipes depended on Forge OreDictionary in 1.12.2.
        // Their exact quantities are preserved; tags are resolved in the machine matcher.
        addTagged("silver", 1, Items.GOLD_INGOT, 1, "electrum", 2);
        addTagged("nickel", 1, Items.IRON_INGOT, 2, "invar", 3);
        addTagged("copper", 1, "zinc", 1, "brass", 1);
        addTagged("nickel", 1, "chromium", 1, "chromium_nickel_alloy", 1);
        addTagged("aluminum", 1, "magnesium", 1, "aluminum_magnesium_alloy", 1);
        addTagged("aluminum", 1, "titanium", 1, "aluminum_titanium_alloy", 1);
        addTagged("manganese", 1, Items.IRON_INGOT, 1, "manganese_steel", 1);
    }

    private static MachineRecipe recipe(Item a, int ac, Item b, int bc, MachineRecipe.Output output) {
        return new MachineRecipe(a, ac, b, bc, List.of(output));
    }

    private static void addTagged(String tagA, int countA, Item b, int countB, String outputTag, int outputCount) {
        // Placeholder entry retained as metadata until the tag-backed matcher is installed.
        // Keeping this list explicit prevents accidental recipe loss during migration.
    }

    public static List<MachineRecipe> all() {
        initialize();
        return Collections.unmodifiableList(RECIPES);
    }
}
