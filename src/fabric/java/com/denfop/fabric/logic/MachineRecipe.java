package com.denfop.fabric.logic;

import java.util.List;

import net.minecraft.item.Item;
import net.minecraft.recipe.Ingredient;

/**
 * Two-input machine recipe matching the original IC2 double-input machine model.
 * Ingredient replaces the old OreDictionary/IRecipeInput dependency.
 */
public record MachineRecipe(Ingredient inputA, int inputACount,
                            Ingredient inputB, int inputBCount,
                            List<Output> outputs) {
    public record Output(Item item, int count) {}

    public boolean matches(net.minecraft.item.ItemStack first, net.minecraft.item.ItemStack second) {
        return first.getCount() >= inputACount
                && second.getCount() >= inputBCount
                && inputA.test(first)
                && inputB.test(second);
    }
}
