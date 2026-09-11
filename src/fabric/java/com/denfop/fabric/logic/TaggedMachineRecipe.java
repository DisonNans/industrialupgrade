package com.denfop.fabric.logic;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;

import java.util.List;

/**
 * Recipe representation for the Fabric port.
 * Ingredients allow direct replacement of the 1.12.2 OreDictionary semantics
 * without taking a dependency on IndustrialCraft 2 or Forge.
 */
public record TaggedMachineRecipe(
        Ingredient inputA,
        int inputACount,
        Ingredient inputB,
        int inputBCount,
        List<ItemStack> outputs
) {
    public TaggedMachineRecipe {
        outputs = outputs.stream().map(ItemStack::copy).toList();
    }

    public boolean matches(ItemStack first, ItemStack second) {
        return first.getCount() >= inputACount
                && second.getCount() >= inputBCount
                && inputA.test(first)
                && inputB.test(second);
    }
}
