package com.github.ysbbbbbb.kaleidoscopecookery.datagen.recipe;

import com.github.ysbbbbbb.kaleidoscopecookery.datagen.builder.StockpotRecipeBuilder;
import com.github.ysbbbbbb.kaleidoscopecookery.init.ModItems;
import com.github.ysbbbbbb.kaleidoscopecookery.init.ModSoupBases;
import com.github.ysbbbbbb.kaleidoscopecookery.init.tag.TagCommon;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;

import java.util.concurrent.CompletableFuture;

public class StockpotRecipeProvider extends ModRecipeProvider {
    public StockpotRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    public void buildRecipes(RecipeOutput consumer) {
        StockpotRecipeBuilder.builder()
                .addInput(TagCommon.SEEDS_RICE, TagCommon.SEEDS_RICE, TagCommon.SEEDS_RICE)
                .setFinishedTexture(modLoc("stockpot/rice_finished"))
                .setResult(ModItems.COOKED_RICE.get(), 3)
                .setFinishedBubbleColor(0xE9E3DB).setTime(300)
                .save(consumer, "rice_3");

        StockpotRecipeBuilder.builder()
                .addInput(TagCommon.SEEDS_RICE, TagCommon.SEEDS_RICE,
                        TagCommon.SEEDS_RICE, TagCommon.SEEDS_RICE)
                .setFinishedTexture(modLoc("stockpot/rice_finished"))
                .setResult(ModItems.COOKED_RICE.get(), 4)
                .setFinishedBubbleColor(0xE9E3DB).setTime(400)
                .save(consumer, "rice_4");

        StockpotRecipeBuilder.builder()
                .addInput(TagCommon.SEEDS_RICE, TagCommon.SEEDS_RICE, TagCommon.SEEDS_RICE,
                        TagCommon.SEEDS_RICE, TagCommon.SEEDS_RICE)
                .setFinishedTexture(modLoc("stockpot/rice_finished"))
                .setResult(ModItems.COOKED_RICE.get(), 5)
                .setFinishedBubbleColor(0xE9E3DB).setTime(500)
                .save(consumer, "rice_5");

        StockpotRecipeBuilder.builder()
                .addInput(Items.BONE, Items.BONE, Items.BONE, Items.BONE)
                .setResult(ModItems.PORK_BONE_SOUP.get())
                .save(consumer);

        StockpotRecipeBuilder.builder()
                .addInput(TagCommon.RAW_FISHES_SALMON, TagCommon.RAW_FISHES_SALMON, TagCommon.RAW_FISHES_SALMON)
                .addInput(Items.KELP, Items.BONE_MEAL)
                .setResult(ModItems.SEAFOOD_MISO_SOUP.get())
                .save(consumer, "seafood_miso_soup_salmon");

        StockpotRecipeBuilder.builder()
                .addInput(TagCommon.RAW_FISHES_COD, TagCommon.RAW_FISHES_COD, TagCommon.RAW_FISHES_COD)
                .addInput(Items.KELP, Items.BONE_MEAL)
                .setResult(ModItems.SEAFOOD_MISO_SOUP.get())
                .save(consumer, "seafood_miso_soup_cod");

        StockpotRecipeBuilder.builder()
                .addInput(Items.KELP, Items.BONE_MEAL)
                .setSoupBase(ModSoupBases.SALMON_BUCKET)
                .setResult(ModItems.SEAFOOD_MISO_SOUP.get(), 1)
                .save(consumer, "seafood_miso_soup_salmon_entity");

        StockpotRecipeBuilder.builder()
                .addInput(Items.KELP, Items.BONE_MEAL)
                .setSoupBase(ModSoupBases.COD_BUCKET)
                .setResult(ModItems.SEAFOOD_MISO_SOUP.get(), 1)
                .save(consumer, "seafood_miso_soup_cod_entity");

        StockpotRecipeBuilder.builder()
                .addInput(Items.ROTTEN_FLESH, Items.ROTTEN_FLESH,
                        Items.ROTTEN_FLESH, Items.ROTTEN_FLESH)
                .setSoupBase(ModSoupBases.LAVA)
                .addInput(Blocks.SCULK, Blocks.SCULK)
                .setResult(ModItems.FEARSOME_THICK_SOUP.get())
                .save(consumer);

        StockpotRecipeBuilder.builder()
                .addInput(Items.CARROT, Items.CARROT)
                .addInput(TagCommon.RAW_MUTTON, TagCommon.RAW_MUTTON)
                .setResult(ModItems.LAMB_AND_RADISH_SOUP.get())
                .save(consumer);

        StockpotRecipeBuilder.builder()
                .addInput(Items.POTATO, Items.POTATO, Items.POTATO)
                .addInput(TagCommon.RAW_BEEF, TagCommon.RAW_BEEF)
                .setResult(ModItems.BRAISED_BEEF_WITH_POTATOES.get())
                .save(consumer);

        StockpotRecipeBuilder.builder()
                .addInput(Items.RABBIT, Items.RABBIT)
                .addInput(Tags.Items.MUSHROOMS, Tags.Items.MUSHROOMS, Tags.Items.MUSHROOMS)
                .setResult(ModItems.WILD_MUSHROOM_RABBIT_SOUP.get())
                .save(consumer);

        StockpotRecipeBuilder.builder()
                .addInput(TagCommon.RAW_BEEF, TagCommon.RAW_BEEF,
                        TagCommon.RAW_BEEF, TagCommon.CROPS_TOMATO,
                        TagCommon.CROPS_TOMATO, TagCommon.CROPS_TOMATO)
                .setResult(ModItems.TOMATO_BEEF_BRISKET_SOUP.get())
                .save(consumer);

        StockpotRecipeBuilder.builder()
                .addInput(Items.PUFFERFISH, Items.PUFFERFISH, Items.PUFFERFISH, Items.SEAGRASS)
                .setResult(ModItems.PUFFERFISH_SOUP.get())
                .save(consumer);

        StockpotRecipeBuilder.builder()
                .addInput(Items.SEAGRASS)
                .setSoupBase(ModSoupBases.PUFFERFISH_BUCKET)
                .setResult(ModItems.PUFFERFISH_SOUP.get(), 1)
                .save(consumer, "pufferfish_soup_entity");

        StockpotRecipeBuilder.builder()
                .addInput(TagCommon.RAW_BEEF, TagCommon.RAW_BEEF, TagCommon.CROPS_TOMATO,
                        TagCommon.CROPS_TOMATO, TagCommon.CROPS_LETTUCE)
                .setResult(ModItems.BORSCHT.get())
                .save(consumer);

        StockpotRecipeBuilder.builder()
                .addInput(TagCommon.RAW_BEEF, TagCommon.RAW_BEEF, TagCommon.CROPS_TOMATO)
                .addInput(Items.BEETROOT, Items.BEETROOT)
                .setResult(ModItems.BORSCHT.get())
                .save(consumer, "borscht_beetroot");

        StockpotRecipeBuilder.builder()
                .addInput(TagCommon.RAW_BEEF, TagCommon.RAW_BEEF, TagCommon.RAW_BEEF,
                        TagCommon.RAW_BEEF, TagCommon.CROPS_LETTUCE, TagCommon.CROPS_LETTUCE)
                .setResult(ModItems.BEEF_MEATBALL_SOUP.get())
                .save(consumer);

        StockpotRecipeBuilder.builder()
                .addInput(TagCommon.RAW_CHICKEN, TagCommon.RAW_CHICKEN, TagCommon.RAW_CHICKEN,
                        Tags.Items.MUSHROOMS, Tags.Items.MUSHROOMS, Tags.Items.MUSHROOMS)
                .setResult(ModItems.CHICKEN_AND_MUSHROOM_STEW.get())
                .save(consumer);
    }
}
