package com.github.ysbbbbbb.kaleidoscopecookery.datagen.tag;

import com.github.ysbbbbbb.kaleidoscopecookery.KaleidoscopeCookery;
import com.github.ysbbbbbb.kaleidoscopecookery.init.ModBlocks;
import com.github.ysbbbbbb.kaleidoscopecookery.init.tag.TagMod;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class TagBlock extends BlockTagsProvider {
    public TagBlock(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, KaleidoscopeCookery.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.STOVE.get(), ModBlocks.POT.get());
        this.tag(BlockTags.MINEABLE_WITH_AXE).add(
                ModBlocks.COOK_STOOL_OAK.get(), ModBlocks.COOK_STOOL_SPRUCE.get(),
                ModBlocks.COOK_STOOL_ACACIA.get(), ModBlocks.COOK_STOOL_BAMBOO.get(),
                ModBlocks.COOK_STOOL_BIRCH.get(), ModBlocks.COOK_STOOL_CHERRY.get(),
                ModBlocks.COOK_STOOL_CRIMSON.get(), ModBlocks.COOK_STOOL_DARK_OAK.get(),
                ModBlocks.COOK_STOOL_JUNGLE.get(), ModBlocks.COOK_STOOL_MANGROVE.get(),
                ModBlocks.COOK_STOOL_WARPED.get(),

                ModBlocks.CHAIR_OAK.get(), ModBlocks.CHAIR_SPRUCE.get(),
                ModBlocks.CHAIR_ACACIA.get(), ModBlocks.CHAIR_BAMBOO.get(),
                ModBlocks.CHAIR_BIRCH.get(), ModBlocks.CHAIR_CHERRY.get(),
                ModBlocks.CHAIR_CRIMSON.get(), ModBlocks.CHAIR_DARK_OAK.get(),
                ModBlocks.CHAIR_JUNGLE.get(), ModBlocks.CHAIR_MANGROVE.get(),
                ModBlocks.CHAIR_WARPED.get(),

                ModBlocks.TABLE_OAK.get(), ModBlocks.TABLE_SPRUCE.get(),
                ModBlocks.TABLE_ACACIA.get(), ModBlocks.TABLE_BAMBOO.get(),
                ModBlocks.TABLE_BIRCH.get(), ModBlocks.TABLE_CHERRY.get(),
                ModBlocks.TABLE_CRIMSON.get(), ModBlocks.TABLE_DARK_OAK.get(),
                ModBlocks.TABLE_JUNGLE.get(), ModBlocks.TABLE_MANGROVE.get(),
                ModBlocks.TABLE_WARPED.get(),

                ModBlocks.KITCHENWARE_RACKS.get(), ModBlocks.CHOPPING_BOARD.get());
        this.tag(BlockTags.MINEABLE_WITH_HOE).add(ModBlocks.STRAW_BLOCK.get());
        this.tag(TagMod.TUNDRA_STRIDER_SPEED_BLOCKS).add(
                Blocks.SNOW, Blocks.SNOW_BLOCK, Blocks.POWDER_SNOW,
                Blocks.PACKED_ICE, Blocks.ICE, Blocks.FROSTED_ICE, Blocks.BLUE_ICE
        );
        this.tag(TagMod.WARMTH_HEAT_SOURCE_BLOCKS).add(Blocks.FIRE, Blocks.SOUL_FIRE, Blocks.LAVA, Blocks.MAGMA_BLOCK);
        this.tag(TagMod.CAT_LIE_ON_BLOCKS).add(ModBlocks.FRUIT_BASKET.get(),
                ModBlocks.CHAIR_OAK.get(), ModBlocks.CHAIR_SPRUCE.get(),
                ModBlocks.CHAIR_ACACIA.get(), ModBlocks.CHAIR_BAMBOO.get(),
                ModBlocks.CHAIR_BIRCH.get(), ModBlocks.CHAIR_CHERRY.get(),
                ModBlocks.CHAIR_CRIMSON.get(), ModBlocks.CHAIR_DARK_OAK.get(),
                ModBlocks.CHAIR_JUNGLE.get(), ModBlocks.CHAIR_MANGROVE.get(),
                ModBlocks.CHAIR_WARPED.get());
        this.tag(TagMod.HEAT_SOURCE_BLOCKS_WITHOUT_LIT).add(Blocks.FIRE, Blocks.SOUL_FIRE, Blocks.LAVA, Blocks.MAGMA_BLOCK);
    }
}
