package com.github.ysbbbbbb.kaleidoscopecookery.crafting.recipe;

import com.github.ysbbbbbb.kaleidoscopecookery.init.ModRecipes;
import com.github.ysbbbbbb.kaleidoscopecookery.util.RecipeMatcher;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

import java.util.List;

public record PotRecipe(ResourceLocation id, int time, int stirFryCount, Ingredient carrier,
                        NonNullList<Ingredient> ingredients, ItemStack result) implements BaseRecipe<SimpleContainer> {
    public PotRecipe(ResourceLocation id, int time, int stirFryCount, Ingredient carrier,
                     List<Ingredient> ingredients, ItemStack result) {
        this(id, time, stirFryCount, carrier, NonNullList.of(Ingredient.EMPTY,
                BaseRecipe.fillInputs(ingredients)), result);
    }

    @Override
    public boolean matches(SimpleContainer container, Level level) {
        return RecipeMatcher.findMatches(container.items, ingredients) != null;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return ingredients;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess registryAccess) {
        return this.result;
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.POT_SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.POT_RECIPE;
    }
}
