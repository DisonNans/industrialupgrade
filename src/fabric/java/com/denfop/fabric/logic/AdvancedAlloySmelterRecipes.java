package com.denfop.fabric.logic;

import com.denfop.fabric.FabricRegistries;
import net.minecraft.item.Item;
import net.minecraft.recipe.Ingredient;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Exact three-input recipe set from TileEntityAdvAlloySmelter.init(). */
public final class AdvancedAlloySmelterRecipes {
    public static final int ENERGY_PER_TICK = 1;
    public static final int OPERATION_LENGTH = 300;
    private static final List<TripleMachineRecipe> RECIPES = new ArrayList<>();

    private AdvancedAlloySmelterRecipes() {}

    public static void initialize() {
        if (!RECIPES.isEmpty()) return;
        add(
                FabricRegistries.COPPER_INGOT, FabricRegistries.ZINC_INGOT, FabricRegistries.LEAD_INGOT,
                FabricRegistries.MUNTSA_INGOT
        );
        add(
                FabricRegistries.ALUMINUM_INGOT, FabricRegistries.MAGNESIUM_INGOT, FabricRegistries.MANGANESE_INGOT,
                FabricRegistries.ALCLED_INGOT
        );
        add(
                FabricRegistries.ALUMINUM_INGOT, FabricRegistries.COPPER_INGOT, FabricRegistries.TIN_INGOT,
                FabricRegistries.ALUMINUM_BRONZE_INGOT
        );
        add(
                FabricRegistries.ALUMINUM_INGOT, FabricRegistries.VANADY_INGOT, FabricRegistries.COBALT_INGOT,
                FabricRegistries.VANADOALUMITE_INGOT
        );
        add(
                FabricRegistries.CHROMIUM_INGOT, FabricRegistries.TUNGSTEN_INGOT, FabricRegistries.NICKEL_INGOT,
                FabricRegistries.VITALIUM_INGOT
        );
    }

    private static void add(Item a, Item b, Item c, Item output) {
        RECIPES.add(new TripleMachineRecipe(
                Ingredient.ofItems(a), 1,
                Ingredient.ofItems(b), 1,
                Ingredient.ofItems(c), 1,
                List.of(new TripleMachineRecipe.Output(output, 1))
        ));
    }

    public static List<TripleMachineRecipe> all() {
        initialize();
        return Collections.unmodifiableList(RECIPES);
    }
}
