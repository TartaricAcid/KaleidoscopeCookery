package com.github.ysbbbbbb.kaleidoscopecookery.datagen.recipe;

import com.github.ysbbbbbb.kaleidoscopecookery.datagen.builder.PotRecipeBuilder;
import com.github.ysbbbbbb.kaleidoscopecookery.init.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.crafting.CampfireCookingRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.item.crafting.SmokingRecipe;
import net.minecraft.world.level.ItemLike;

import java.util.concurrent.CompletableFuture;

public class SimpleCookingRecipeProvider extends ModRecipeProvider {
    public SimpleCookingRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    public void buildRecipes(RecipeOutput consumer) {
        simpleCookingRecipe(ModItems.RAW_LAMB_CHOPS, ModItems.COOKED_LAMB_CHOPS, 0.35F, consumer);
        simpleCookingRecipe(ModItems.RAW_COW_OFFAL, ModItems.COOKED_COW_OFFAL, 0.35F, consumer);
        simpleCookingRecipe(ModItems.RAW_PORK_BELLY, ModItems.COOKED_PORK_BELLY, 0.35F, consumer);
    }

    public void simpleCookingRecipe(ItemLike input, ItemLike output, float experience, RecipeOutput consumer) {
        simpleCookingRecipe(consumer, "smoking", RecipeSerializer.SMOKING_RECIPE, SmokingRecipe::new, 100, input, output, experience);
        simpleCookingRecipe(consumer, "campfire_cooking", RecipeSerializer.CAMPFIRE_COOKING_RECIPE, CampfireCookingRecipe::new, 600, input, output, experience);
        simpleCookingRecipe(consumer, "smelting", RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, 200, input, output, experience);
        PotRecipeBuilder.builder().addInput(input).setResult(output.asItem()).save(consumer);
    }
}
