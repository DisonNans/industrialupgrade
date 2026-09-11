package com.denfop.fabric.logic;

import java.util.List;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;

/** Three-input equivalent of the original IC2 InvSlotTripleMachineRecipe. */
public record TripleMachineRecipe(
        Ingredient inputA, int inputACount,
        Ingredient inputB, int inputBCount,
        Ingredient inputC, int inputCCount,
        List<Output> outputs) {

    public record Output(Item item, int count) {}

    public boolean matches(ItemStack a, ItemStack b, ItemStack c) {
        return a.getCount() >= inputACount
                && b.getCount() >= inputBCount
                && c.getCount() >= inputCCount
                && inputA.test(a)
                && inputB.test(b)
                && inputC.test(c);
    }
}
