package com.github.ysbbbbbb.kaleidoscopecookery.datagen.recipe;

import com.github.ysbbbbbb.kaleidoscopecookery.datagen.builder.ChoppingBoardBuilder;
import com.github.ysbbbbbb.kaleidoscopecookery.init.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;

public class ChoppingBoardRecipeProvider extends ModRecipeProvider {
    public ChoppingBoardRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    public void buildRecipes(RecipeOutput consumer) {
        ChoppingBoardBuilder.builder()
                .setIngredient(Items.MUTTON)
                .setResult(ModItems.RAW_LAMB_CHOPS, 2)
                .setCutCount(4)
                .setModelId(modLoc("raw_lamb_chops"))
                .save(consumer);

        ChoppingBoardBuilder.builder()
                .setIngredient(Items.TROPICAL_FISH)
                .setResult(ModItems.SASHIMI, 2)
                .setCutCount(3)
                .setModelId(modLoc("sashimi"))
                .save(consumer);

        ChoppingBoardBuilder.builder()
                .setIngredient(Items.BEEF)
                .setResult(ModItems.RAW_COW_OFFAL, 2)
                .setCutCount(4)
                .setModelId(modLoc("raw_cow_offal"))
                .save(consumer);

        ChoppingBoardBuilder.builder()
                .setIngredient(Items.PORKCHOP)
                .setResult(ModItems.RAW_PORK_BELLY, 2)
                .setCutCount(4)
                .setModelId(modLoc("raw_pork_belly"))
                .save(consumer);
    }
}
