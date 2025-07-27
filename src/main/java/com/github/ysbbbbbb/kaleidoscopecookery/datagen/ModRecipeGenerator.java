package com.github.ysbbbbbb.kaleidoscopecookery.datagen;

import com.github.ysbbbbbb.kaleidoscopecookery.datagen.recipe.*;
import com.github.ysbbbbbb.kaleidoscopecookery.init.ModItems;
import com.google.common.collect.Lists;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeGenerator extends ModRecipeProvider {
    private final List<ModRecipeProvider> providers = Lists.newArrayList();

    public ModRecipeGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
        providers.add(new ChoppingBoardRecipeProvider(output, registries));
        providers.add(new DecorationRecipeProvider(output, registries));
        providers.add(new FoodBiteRecipeProvider(output, registries));
        providers.add(new PotRecipeProvider(output, registries));
        providers.add(new ShapedRecipeProvider(output, registries));
        providers.add(new ShapelessRecipeProvider(output, registries));
        providers.add(new SimpleCookingRecipeProvider(output, registries));
        providers.add(new SimplePotRecipeProvider(output, registries));
        providers.add(new StockpotRecipeProvider(output, registries));
    }

    @Override
    public void buildRecipes(RecipeOutput consumer) {
        netheriteSmithing(consumer, ModItems.DIAMOND_KITCHEN_KNIFE, RecipeCategory.TOOLS, ModItems.NETHERITE_KITCHEN_KNIFE);
        for (ModRecipeProvider provider : providers) {
            provider.buildRecipes(consumer);
        }
    }
}
