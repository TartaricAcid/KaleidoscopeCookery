package com.github.ysbbbbbb.kaleidoscopecookery.crafting.serializer;

import com.github.ysbbbbbb.kaleidoscopecookery.crafting.recipe.PotRecipe;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class PotRecipeSerializer implements RecipeSerializer<PotRecipe> {
    public static final MapCodec<PotRecipe> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    Codec.INT.optionalFieldOf("time", 200).forGetter(PotRecipe::time),
                    Codec.INT.optionalFieldOf("stir_fry_count", 3).forGetter(PotRecipe::stirFryCount),
                    Ingredient.CODEC.optionalFieldOf("carrier", Ingredient.EMPTY).forGetter(PotRecipe::carrier),
                    Ingredient.CODEC.listOf().fieldOf("ingredients").xmap(
                            list -> list,
                            list -> list.stream().filter(i -> !i.isEmpty()).toList()
                    ).forGetter(recipe -> recipe.ingredients().stream().toList()),
                    ItemStack.CODEC.fieldOf("result").forGetter(PotRecipe::result)
            ).apply(instance, PotRecipe::new)
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, PotRecipe> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.INT, PotRecipe::time,
            ByteBufCodecs.INT, PotRecipe::stirFryCount,
            Ingredient.CONTENTS_STREAM_CODEC, PotRecipe::carrier,
            Ingredient.CONTENTS_STREAM_CODEC.apply(ByteBufCodecs.list()), PotRecipe::ingredients,
            ItemStack.STREAM_CODEC, PotRecipe::result,
            PotRecipe::new);

    @Override
    public MapCodec<PotRecipe> codec() {
        return CODEC;
    }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, PotRecipe> streamCodec() {
        return STREAM_CODEC;
    }
}
