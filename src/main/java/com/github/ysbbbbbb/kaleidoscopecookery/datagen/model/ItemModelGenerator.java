package com.github.ysbbbbbb.kaleidoscopecookery.datagen.model;

import com.github.ysbbbbbb.kaleidoscopecookery.KaleidoscopeCookery;
import com.github.ysbbbbbb.kaleidoscopecookery.init.ModItems;
import com.github.ysbbbbbb.kaleidoscopecookery.init.registry.FoodBiteRegistry;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.loaders.SeparateTransformsModelBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

public class ItemModelGenerator extends ItemModelProvider {
    public ItemModelGenerator(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, KaleidoscopeCookery.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        withExistingParent("stove", modLoc("block/stove"));
        withExistingParent("pot", modLoc("block/pot"));

        handheldItem(ModItems.IRON_KITCHEN_KNIFE.get());
        handheldItem(ModItems.GOLD_KITCHEN_KNIFE.get());
        handheldItem(ModItems.DIAMOND_KITCHEN_KNIFE.get());
        handheldItem(ModItems.NETHERITE_KITCHEN_KNIFE.get());
        handheldItem(ModItems.KITCHEN_SHOVEL.get());

        basicItem(ModItems.OIL.get());
        basicItem(ModItems.FRIED_EGG.get());
        basicItem(ModItems.SCARECROW.get());
        basicItem(ModItems.TOMATO.get());
        basicItem(ModItems.SCRAMBLE_EGG_WITH_TOMATOES.get());
        basicItem(ModItems.STRAW_HAT.get());
        basicItem(ModItems.STRAW_HAT_FLOWER.get());
        basicItem(ModItems.TOMATO_SEED.get());
        basicItem(ModItems.SASHIMI.get());
        basicItem(ModItems.RAW_LAMB_CHOPS.get());
        basicItem(ModItems.RAW_COW_OFFAL.get());
        basicItem(ModItems.RAW_PORK_BELLY.get());
        basicItem(ModItems.COOKED_LAMB_CHOPS.get());
        basicItem(ModItems.COOKED_COW_OFFAL.get());
        basicItem(ModItems.COOKED_PORK_BELLY.get());
        basicItem(ModItems.RED_CHILI.get());
        basicItem(ModItems.GREEN_CHILI.get());
        basicItem(ModItems.CHILI_SEED.get());
        basicItem(ModItems.LETTUCE.get());
        basicItem(ModItems.LETTUCE_SEED.get());
        basicItem(ModItems.CATERPILLAR.get());

        FoodBiteRegistry.FOOD_DATA_MAP.forEach((key, value) -> {
            Item item = ForgeRegistries.ITEMS.getValue(key);
            if (item != null) {
                basicItem(item);
            }
        });

        withExistingParent("cook_stool_oak", modLoc("block/cook_stool/oak"));
        withExistingParent("cook_stool_spruce", modLoc("block/cook_stool/spruce"));
        withExistingParent("cook_stool_acacia", modLoc("block/cook_stool/acacia"));
        withExistingParent("cook_stool_bamboo", modLoc("block/cook_stool/bamboo"));
        withExistingParent("cook_stool_birch", modLoc("block/cook_stool/birch"));
        withExistingParent("cook_stool_cherry", modLoc("block/cook_stool/cherry"));
        withExistingParent("cook_stool_crimson", modLoc("block/cook_stool/crimson"));
        withExistingParent("cook_stool_dark_oak", modLoc("block/cook_stool/dark_oak"));
        withExistingParent("cook_stool_jungle", modLoc("block/cook_stool/jungle"));
        withExistingParent("cook_stool_mangrove", modLoc("block/cook_stool/mangrove"));
        withExistingParent("cook_stool_warped", modLoc("block/cook_stool/warped"));

        withExistingParent("chopping_board", modLoc("block/chopping_board"));
        withExistingParent("oil_block", modLoc("block/oil_block"));

        ItemModelBuilder fruitBasketFull = new ItemModelBuilder(modLoc("fruit_basket"), this.existingFileHelper)
                .parent(new ModelFile.UncheckedModelFile(modLoc("item/fruit_basket_full")));
        ItemModelBuilder fruitBasketItem = new ItemModelBuilder(modLoc("fruit_basket"), this.existingFileHelper)
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", modLoc("item/fruit_basket"));
        ItemModelBuilder fruitBasketBlock = new ItemModelBuilder(modLoc("fruit_basket"), this.existingFileHelper)
                .parent(new ModelFile.UncheckedModelFile(modLoc("block/fruit_basket")));
        getBuilder("fruit_basket")
                .guiLight(BlockModel.GuiLight.FRONT)
                .customLoader(SeparateTransformsModelBuilder::begin).base(fruitBasketFull)
                .perspective(ItemDisplayContext.GROUND, fruitBasketBlock)
                .perspective(ItemDisplayContext.GUI, fruitBasketItem)
                .perspective(ItemDisplayContext.FIXED, fruitBasketItem)
                .perspective(ItemDisplayContext.GROUND, fruitBasketItem);
    }

    public ItemModelBuilder handheldItem(Item item) {
        return handheldItem(Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(item)));
    }

    public ItemModelBuilder handheldItem(ResourceLocation item) {
        return getBuilder(item.toString())
                .parent(new ModelFile.UncheckedModelFile("item/handheld"))
                .texture("layer0", new ResourceLocation(item.getNamespace(), "item/" + item.getPath()));
    }
}
