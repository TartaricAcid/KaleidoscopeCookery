package com.github.ysbbbbbb.kaleidoscopecookery.compat.jei;

import com.github.ysbbbbbb.kaleidoscopecookery.KaleidoscopeCookery;
import com.github.ysbbbbbb.kaleidoscopecookery.compat.jei.category.ChoppingBoardRecipeCategory;
import com.github.ysbbbbbb.kaleidoscopecookery.compat.jei.category.PotRecipeCategory;
import com.github.ysbbbbbb.kaleidoscopecookery.compat.jei.category.StockpotRecipeCategory;
import com.github.ysbbbbbb.kaleidoscopecookery.init.ModItems;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.resources.ResourceLocation;

@JeiPlugin
public class ModJeiPlugin implements IModPlugin {
    private static final ResourceLocation UID = new ResourceLocation(KaleidoscopeCookery.MOD_ID, "jei");

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new PotRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new ChoppingBoardRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new StockpotRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        registration.addRecipes(PotRecipeCategory.TYPE, PotRecipeCategory.getRecipes());
        registration.addRecipes(ChoppingBoardRecipeCategory.TYPE, ChoppingBoardRecipeCategory.getRecipes());
        registration.addRecipes(StockpotRecipeCategory.TYPE, StockpotRecipeCategory.getRecipes());
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(ModItems.POT.getDefaultInstance(), PotRecipeCategory.TYPE);
        registration.addRecipeCatalyst(ModItems.CHOPPING_BOARD.getDefaultInstance(), ChoppingBoardRecipeCategory.TYPE);
        registration.addRecipeCatalyst(ModItems.STOCKPOT.getDefaultInstance(), StockpotRecipeCategory.TYPE);
    }

    @Override
    public ResourceLocation getPluginUid() {
        return UID;
    }
}
