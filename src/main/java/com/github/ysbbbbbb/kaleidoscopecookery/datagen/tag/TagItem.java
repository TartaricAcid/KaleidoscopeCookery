package com.github.ysbbbbbb.kaleidoscopecookery.datagen.tag;

import com.github.ysbbbbbb.kaleidoscopecookery.KaleidoscopeCookery;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class TagItem extends ItemTagsProvider {
    public static final TagKey<Item> LIT_STOVE = TagKey.create(Registries.ITEM, new ResourceLocation(KaleidoscopeCookery.MOD_ID, "lit_stove"));
    public static final TagKey<Item> EXTINGUISH_STOVE = TagKey.create(Registries.ITEM, new ResourceLocation(KaleidoscopeCookery.MOD_ID, "extinguish_stove"));

    public TagItem(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider,
                   CompletableFuture<TagLookup<Block>> pBlockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pLookupProvider, pBlockTags, KaleidoscopeCookery.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(LIT_STOVE).add(Items.FLINT_AND_STEEL, Items.FIRE_CHARGE);
        tag(EXTINGUISH_STOVE).addTag(ItemTags.SHOVELS);
    }
}
