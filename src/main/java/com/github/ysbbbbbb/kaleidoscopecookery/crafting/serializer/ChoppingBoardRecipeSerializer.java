package com.github.ysbbbbbb.kaleidoscopecookery.crafting.serializer;

import com.github.ysbbbbbb.kaleidoscopecookery.KaleidoscopeCookery;
import com.github.ysbbbbbb.kaleidoscopecookery.crafting.recipe.ChoppingBoardRecipe;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class ChoppingBoardRecipeSerializer implements RecipeSerializer<ChoppingBoardRecipe> {
    public static final ResourceLocation EMPTY = ResourceLocation.fromNamespaceAndPath(KaleidoscopeCookery.MOD_ID, "empty");
    public static final MapCodec<ChoppingBoardRecipe> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    Ingredient.CODEC.fieldOf("ingredient").forGetter(ChoppingBoardRecipe::getIngredient),
                    ItemStack.CODEC.fieldOf("result").forGetter(ChoppingBoardRecipe::getResult),
                    Codec.INT.optionalFieldOf("cut_count", 3).forGetter(ChoppingBoardRecipe::getCutCount),
                    ResourceLocation.CODEC.optionalFieldOf("model_id", EMPTY).forGetter(ChoppingBoardRecipe::getModelId)
            ).apply(instance, ChoppingBoardRecipe::new)
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, ChoppingBoardRecipe> STREAM_CODEC = StreamCodec.composite(
            Ingredient.CONTENTS_STREAM_CODEC, ChoppingBoardRecipe::getIngredient,
            ItemStack.STREAM_CODEC, ChoppingBoardRecipe::getResult,
            ByteBufCodecs.INT, ChoppingBoardRecipe::getCutCount,
            ResourceLocation.STREAM_CODEC, ChoppingBoardRecipe::getModelId,
            ChoppingBoardRecipe::new);

    @Override
    public MapCodec<ChoppingBoardRecipe> codec() {
        return CODEC;
    }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, ChoppingBoardRecipe> streamCodec() {
        return STREAM_CODEC;
    }
}
