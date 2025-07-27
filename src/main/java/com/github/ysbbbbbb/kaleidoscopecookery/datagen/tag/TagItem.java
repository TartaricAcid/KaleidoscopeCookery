package com.github.ysbbbbbb.kaleidoscopecookery.datagen.tag;

import com.github.ysbbbbbb.kaleidoscopecookery.KaleidoscopeCookery;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class TagItem {
    public static final TagKey<Item> POT_INGREDIENT = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(KaleidoscopeCookery.MOD_ID, "pot_ingredient"));
}