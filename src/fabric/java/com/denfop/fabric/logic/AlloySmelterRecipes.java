package com.denfop.fabric.logic;

import com.denfop.fabric.FabricRegistries;
import com.denfop.fabric.MaterialTags;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;

/**
 * Behavior-preserving port of TileEntityAlloySmelter.init().
 * The original IC2 IRecipeInput/OreDictionary layer is replaced by
 * standalone Fabric Ingredients and Industrial Upgrade material tags.
 */
public final class AlloySmelterRecipes {
    public static final int ENERGY_PER_TICK = 1;
    public static final int OPERATION_LENGTH = 300;

    private static final List<MachineRecipe> RECIPES = new ArrayList<>();

    private AlloySmelterRecipes() {}

    public static void initialize() {
        if (!RECIPES.isEmpty()) return;

        // Original: iron x1 + coal x2 -> advanced iron ingot x1.
        RECIPES.add(recipe(
                item(Items.IRON_INGOT), 1,
                item(Items.COAL), 2,
                output(FabricRegistries.ADVANCED_IRON_INGOT, 1)
        ));

        // Original OreDictionary recipes, with the same input order and quantities.
        RECIPES.add(recipe(
                item(Items.GOLD_INGOT), 1,
                tag(MaterialTags.SILVER_INGOTS), 1,
                output(FabricRegistries.ELECTRUM_INGOT, 2)
        ));
        RECIPES.add(recipe(
                tag(MaterialTags.NICKEL_INGOTS), 1,
                item(Items.IRON_INGOT), 2,
                output(FabricRegistries.INVAR_INGOT, 3)
        ));
        RECIPES.add(recipe(
                tag(MaterialTags.COPPER_INGOTS), 1,
                tag(MaterialTags.ZINC_INGOTS), 1,
                output(FabricRegistries.RED_BRASS_INGOT, 1)
        ));
        RECIPES.add(recipe(
                tag(MaterialTags.NICKEL_INGOTS), 1,
                tag(MaterialTags.CHROMIUM_INGOTS), 1,
                output(FabricRegistries.NICHROME_INGOT, 1)
        ));
        RECIPES.add(recipe(
                tag(MaterialTags.ALUMINUM_INGOTS), 1,
                tag(MaterialTags.MAGNESIUM_INGOTS), 1,
                output(FabricRegistries.DURALUMIN_INGOT, 1)
        ));
        RECIPES.add(recipe(
                tag(MaterialTags.ALUMINUM_INGOTS), 1,
                tag(MaterialTags.TITANIUM_INGOTS), 1,
                output(FabricRegistries.ALUMEL_INGOT, 1)
        ));
        RECIPES.add(recipe(
                item(Items.IRON_INGOT), 1,
                tag(MaterialTags.MANGANESE_INGOTS), 1,
                output(FabricRegistries.FERROMANGANESE_INGOT, 1)
        ));
    }

    private static MachineRecipe recipe(Ingredient a, int ac, Ingredient b, int bc,
                                        MachineRecipe.Output output) {
        return new MachineRecipe(a, ac, b, bc, List.of(output));
    }

    private static MachineRecipe.Output output(Item item, int count) {
        return new MachineRecipe.Output(item, count);
    }

    private static Ingredient item(Item item) {
        return Ingredient.ofItems(item);
    }

    private static Ingredient tag(net.minecraft.registry.tag.TagKey<Item> tag) {
        return Ingredient.fromTag(tag);
    }

    public static List<MachineRecipe> all() {
        initialize();
        return Collections.unmodifiableList(RECIPES);
    }
}
