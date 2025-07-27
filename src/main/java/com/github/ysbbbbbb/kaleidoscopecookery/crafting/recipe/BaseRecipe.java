package com.github.ysbbbbbb.kaleidoscopecookery.crafting.recipe;

import net.minecraft.core.RegistryAccess;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;

import java.util.List;

public interface BaseRecipe<C extends Container> extends Recipe<C> {
    int RECIPES_SIZE = 9;

    static Ingredient[] fillInputs(List<Ingredient> inputs) {
        Ingredient[] newInputs = new Ingredient[RECIPES_SIZE];
        for (int i = 0; i < RECIPES_SIZE; i++) {
            if (i < inputs.size()) {
                newInputs[i] = inputs.get(i);
            } else {
                newInputs[i] = Ingredient.EMPTY;
            }
        }
        return newInputs;
    }

    @Override
    default ItemStack assemble(C container, RegistryAccess registryAccess) {
        return getResultItem(registryAccess).copy();
    }

    @Override
    default boolean isSpecial() {
        return true;
    }

    @Override
    default boolean canCraftInDimensions(int width, int height) {
        return false;
    }
}
