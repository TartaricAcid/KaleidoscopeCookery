package com.github.ysbbbbbb.kaleidoscopecookery.init.tag;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

/**
 * 按照社区统一规范，使用的 Tag
 * <p>
 * 蔬菜类的有两种，本模组遵循农夫乐事规范，采用 crops/前缀
 */
public interface TagCommon {
    TagKey<Item> CROPS_CHILI_PEPPER = itemTag("crops/chilipepper");
    TagKey<Item> CROPS_TOMATO = itemTag("crops/tomato");
    TagKey<Item> CROPS_LETTUCE = itemTag("crops/lettuce");
    TagKey<Item> CROPS_RICE = itemTag("crops/rice");

    TagKey<Item> VEGETABLES_CHILI_PEPPER = itemTag("vegetables/chilipepper");
    TagKey<Item> VEGETABLES_TOMATO = itemTag("vegetables/tomato");
    TagKey<Item> VEGETABLES_LETTUCE = itemTag("vegetables/lettuce");

    TagKey<Item> SEEDS_CHILI_PEPPER = itemTag("seeds/chilipepper");
    TagKey<Item> SEEDS_TOMATO = itemTag("seeds/tomato");
    TagKey<Item> SEEDS_LETTUCE = itemTag("seeds/lettuce");
    TagKey<Item> SEEDS_RICE = itemTag("seeds/rice");

    TagKey<Item> GRAIN_RICE = itemTag("grain/rice");

    TagKey<Item> COOKED_BEEF = itemTag("cooked_beef");
    TagKey<Item> COOKED_PORK = itemTag("cooked_pork");
    TagKey<Item> COOKED_MUTTON = itemTag("cooked_mutton");
    TagKey<Item> COOKED_EGGS = itemTag("cooked_eggs");
    TagKey<Item> COOKED_RICE = itemTag("cooked_rice");

    TagKey<Item> RAW_BEEF = itemTag("raw_beef");
    TagKey<Item> RAW_CHICKEN = itemTag("raw_chicken");
    TagKey<Item> RAW_PORK = itemTag("raw_pork");
    TagKey<Item> RAW_MUTTON = itemTag("raw_mutton");
    TagKey<Item> EGGS = itemTag("eggs");
    TagKey<Item> RAW_FISHES_COD = itemTag("raw_fishes/cod");
    TagKey<Item> RAW_FISHES_SALMON = itemTag("raw_fishes/salmon");
    TagKey<Item> RAW_FISHES_TROPICAL = itemTag("raw_fishes/tropical_fish");

    TagKey<Item> MUSHROOMS = itemTag("mushroom");

    static TagKey<Item> itemTag(String name) {
        return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", name));
    }

    static TagKey<Block> blockTag(String name) {
        return TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("c", name));
    }
}
