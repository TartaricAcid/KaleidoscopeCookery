package com.github.ysbbbbbb.kaleidoscopecookery.crafting.serializer;

import com.github.ysbbbbbb.kaleidoscopecookery.crafting.recipe.ChoppingBoardRecipe;
import com.google.gson.JsonObject;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;

public class ChoppingBoardRecipeSerializer implements RecipeSerializer<ChoppingBoardRecipe> {
    @Override
    public ChoppingBoardRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
        Ingredient ingredient;
        if (GsonHelper.isArrayNode(json, "ingredient")) {
            ingredient = Ingredient.fromJson(GsonHelper.getAsJsonArray(json, "ingredient"), false);
        } else {
            ingredient = Ingredient.fromJson(GsonHelper.getAsJsonObject(json, "ingredient"), false);
        }
        ItemStack result = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "result"));
        int cutCount = GsonHelper.getAsInt(json, "cut_count", 3);
        ResourceLocation modelId = new ResourceLocation(GsonHelper.getAsString(json, "model_id", ""));
        return new ChoppingBoardRecipe(recipeId, ingredient, result, cutCount, modelId);
    }

    @Override
    public ChoppingBoardRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
        Ingredient ingredient = Ingredient.fromNetwork(buffer);
        ItemStack result = buffer.readItem();
        int cutCount = buffer.readVarInt();
        ResourceLocation modelId = buffer.readResourceLocation();
        return new ChoppingBoardRecipe(recipeId, ingredient, result, cutCount, modelId);
    }

    @Override
    public void toNetwork(FriendlyByteBuf buffer, ChoppingBoardRecipe recipe) {
        recipe.getIngredient().toNetwork(buffer);
        buffer.writeItem(recipe.getResult());
        buffer.writeVarInt(recipe.getCutCount());
        buffer.writeResourceLocation(recipe.getModelId());
    }
}
