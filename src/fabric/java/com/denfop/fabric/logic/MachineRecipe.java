package com.denfop.fabric.logic;

import java.util.List;

import net.minecraft.item.Item;

/**
 * Recipe primitive used by migrated Industrial Upgrade machines.
 * It intentionally keeps two independent input stacks because the original
 * Alloy Smelter used an IC2 double-input recipe rather than a normal shaped recipe.
 */
public record MachineRecipe(Item inputA, int inputACount, Item inputB, int inputBCount,
                            List<Output> outputs) {
    public record Output(Item item, int count) {}
}
