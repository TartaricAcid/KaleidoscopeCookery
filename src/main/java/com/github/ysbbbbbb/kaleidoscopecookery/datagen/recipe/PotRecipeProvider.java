package com.github.ysbbbbbb.kaleidoscopecookery.datagen.recipe;

import com.github.ysbbbbbb.kaleidoscopecookery.datagen.builder.PotRecipeBuilder;
import com.github.ysbbbbbb.kaleidoscopecookery.init.ModItems;
import com.github.ysbbbbbb.kaleidoscopecookery.init.tag.TagCommon;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;

public class PotRecipeProvider extends ModRecipeProvider {
    public PotRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    public void buildRecipes(RecipeOutput consumer) {
        PotRecipeBuilder.builder()
                .addInput(TagCommon.COOKED_EGGS, TagCommon.COOKED_EGGS,
                        TagCommon.CROPS_TOMATO, TagCommon.CROPS_TOMATO)
                .setBowlCarrier()
                .setResult(ModItems.SCRAMBLE_EGG_WITH_TOMATOES)
                .save(consumer);

        PotRecipeBuilder.builder()
                .addInput(TagCommon.COOKED_EGGS, TagCommon.CROPS_TOMATO, TagCommon.CROPS_TOMATO)
                .setCarrier(ModItems.COOKED_RICE)
                .setResult(ModItems.SCRAMBLE_EGG_WITH_TOMATOES_RICE_BOWL)
                .save(consumer);

        PotRecipeBuilder.builder()
                .addInput(TagCommon.COOKED_EGGS, TagCommon.COOKED_EGGS,
                        TagCommon.COOKED_EGGS, TagCommon.COOKED_EGGS,
                        TagCommon.CROPS_TOMATO, TagCommon.CROPS_TOMATO,
                        TagCommon.CROPS_TOMATO, TagCommon.CROPS_TOMATO)
                .setBowlCarrier()
                .setResult(ModItems.SCRAMBLE_EGG_WITH_TOMATOES, 2)
                .save(consumer, "scramble_egg_with_tomatoes_2");

        PotRecipeBuilder.builder()
                .addInput(TagCommon.COOKED_EGGS, TagCommon.COOKED_EGGS, TagCommon.COOKED_EGGS,
                        TagCommon.CROPS_TOMATO, TagCommon.CROPS_TOMATO, TagCommon.CROPS_TOMATO,
                        TagCommon.CROPS_TOMATO)
                .setCarrier(ModItems.COOKED_RICE)
                .setResult(ModItems.SCRAMBLE_EGG_WITH_TOMATOES_RICE_BOWL, 2)
                .save(consumer, "scramble_egg_with_tomatoes_rice_bowl_2");

        PotRecipeBuilder.builder()
                .addInput(ModItems.RAW_COW_OFFAL, ModItems.RAW_COW_OFFAL, ModItems.RAW_COW_OFFAL,
                        TagCommon.CROPS_CHILI_PEPPER, TagCommon.CROPS_LETTUCE, TagCommon.CROPS_LETTUCE)
                .setBowlCarrier()
                .setResult(ModItems.STIR_FRIED_BEEF_OFFAL)
                .save(consumer);

        PotRecipeBuilder.builder()
                .addInput(ModItems.RAW_COW_OFFAL, ModItems.RAW_COW_OFFAL)
                .addInput(TagCommon.CROPS_CHILI_PEPPER, TagCommon.CROPS_LETTUCE, TagCommon.CROPS_LETTUCE)
                .setCarrier(ModItems.COOKED_RICE)
                .setResult(ModItems.STIR_FRIED_BEEF_OFFAL_RICE_BOWL)
                .save(consumer);

        PotRecipeBuilder.builder()
                .addInput(TagCommon.RAW_BEEF, TagCommon.RAW_BEEF,
                        TagCommon.CROPS_CHILI_PEPPER, TagCommon.CROPS_CHILI_PEPPER)
                .setBowlCarrier()
                .setResult(ModItems.BRAISED_BEEF)
                .save(consumer);

        PotRecipeBuilder.builder()
                .addInput(TagCommon.RAW_BEEF, TagCommon.CROPS_CHILI_PEPPER, TagCommon.CROPS_CHILI_PEPPER)
                .setCarrier(ModItems.COOKED_RICE)
                .setResult(ModItems.BRAISED_BEEF_RICE_BOWL)
                .save(consumer);

        PotRecipeBuilder.builder()
                .addInput(TagCommon.RAW_BEEF, TagCommon.RAW_BEEF, TagCommon.RAW_BEEF, TagCommon.RAW_BEEF,
                        TagCommon.CROPS_CHILI_PEPPER, TagCommon.CROPS_CHILI_PEPPER,
                        TagCommon.CROPS_CHILI_PEPPER, TagCommon.CROPS_CHILI_PEPPER)
                .setBowlCarrier()
                .setResult(ModItems.BRAISED_BEEF, 2)
                .save(consumer, "braised_beef_2");

        PotRecipeBuilder.builder()
                .addInput(TagCommon.RAW_BEEF, TagCommon.RAW_BEEF, TagCommon.RAW_BEEF,
                        TagCommon.CROPS_CHILI_PEPPER, TagCommon.CROPS_CHILI_PEPPER,
                        TagCommon.CROPS_CHILI_PEPPER, TagCommon.CROPS_CHILI_PEPPER)
                .setCarrier(ModItems.COOKED_RICE)
                .setResult(ModItems.BRAISED_BEEF_RICE_BOWL, 2)
                .save(consumer, "braised_beef_rice_bowl_2");

        PotRecipeBuilder.builder()
                .addInput(ModItems.GREEN_CHILI, ModItems.GREEN_CHILI)
                .addInput(TagCommon.RAW_PORK, TagCommon.RAW_PORK, TagCommon.RAW_PORK)
                .setBowlCarrier()
                .setResult(ModItems.STIR_FRIED_PORK_WITH_PEPPERS)
                .save(consumer);

        PotRecipeBuilder.builder()
                .addInput(ModItems.GREEN_CHILI, ModItems.GREEN_CHILI)
                .addInput(TagCommon.RAW_PORK, TagCommon.RAW_PORK)
                .setCarrier(ModItems.COOKED_RICE)
                .setResult(ModItems.STIR_FRIED_PORK_WITH_PEPPERS_RICE_BOWL)
                .save(consumer);

        PotRecipeBuilder.builder()
                .addInput(Items.SUGAR, Items.SUGAR, Items.SUGAR)
                .addInput(TagCommon.RAW_PORK, TagCommon.RAW_PORK)
                .setBowlCarrier()
                .setResult(ModItems.SWEET_AND_SOUR_PORK)
                .save(consumer);

        PotRecipeBuilder.builder()
                .addInput(Items.SUGAR, Items.SUGAR)
                .addInput(TagCommon.RAW_PORK, TagCommon.RAW_PORK)
                .setCarrier(ModItems.COOKED_RICE)
                .setResult(ModItems.SWEET_AND_SOUR_PORK_RICE_BOWL)
                .save(consumer);

        PotRecipeBuilder.builder()
                .addInput(TagCommon.CROPS_LETTUCE, TagCommon.CROPS_LETTUCE, TagCommon.CROPS_TOMATO)
                .addInput(Items.CARROT, Items.POTATO)
                .setBowlCarrier()
                .setResult(ModItems.COUNTRY_STYLE_MIXED_VEGETABLES)
                .save(consumer);

        PotRecipeBuilder.builder()
                .addInput(TagCommon.MUSHROOMS, TagCommon.MUSHROOMS, TagCommon.RAW_PORK,
                        TagCommon.RAW_PORK, TagCommon.RAW_PORK, TagCommon.CROPS_CHILI_PEPPER)
                .setBowlCarrier()
                .setResult(ModItems.FISH_FLAVORED_SHREDDED_PORK)
                .save(consumer);

        PotRecipeBuilder.builder()
                .addInput(TagCommon.MUSHROOMS, TagCommon.MUSHROOMS, TagCommon.RAW_PORK,
                        TagCommon.RAW_PORK, TagCommon.CROPS_CHILI_PEPPER)
                .setCarrier(ModItems.COOKED_RICE)
                .setResult(ModItems.FISH_FLAVORED_SHREDDED_PORK_RICE_BOWL)
                .save(consumer);

        PotRecipeBuilder.builder()
                .addInput(Items.EGG, Items.EGG, TagCommon.COOKED_RICE)
                .setBowlCarrier()
                .setResult(ModItems.EGG_FRIED_RICE)
                .save(consumer);

        PotRecipeBuilder.builder()
                .addInput(Items.EGG, Items.EGG, Items.EGG, Items.EGG,
                        TagCommon.COOKED_RICE, TagCommon.COOKED_RICE)
                .setBowlCarrier()
                .setResult(ModItems.EGG_FRIED_RICE, 2)
                .save(consumer, "egg_fried_rice_2");

        PotRecipeBuilder.builder()
                .addInput(Items.EGG, Items.EGG, Items.EGG,
                        Items.EGG, Items.EGG, Items.EGG,
                        TagCommon.COOKED_RICE, TagCommon.COOKED_RICE, TagCommon.COOKED_RICE)
                .setBowlCarrier()
                .setResult(ModItems.EGG_FRIED_RICE, 3)
                .save(consumer, "egg_fried_rice_3");

        PotRecipeBuilder.builder()
                .addInput(Items.EGG, Items.EGG, TagCommon.CROPS_LETTUCE,
                        TagCommon.CROPS_LETTUCE, TagCommon.COOKED_RICE)
                .addInput(Items.CARROT).setBowlCarrier()
                .setResult(ModItems.DELICIOUS_EGG_FRIED_RICE)
                .save(consumer);
    }
}
