package com.github.ysbbbbbb.kaleidoscopecookery.datagen;

import com.github.ysbbbbbb.kaleidoscopecookery.init.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.datamaps.builtin.Compostable;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;

import java.util.concurrent.CompletableFuture;

public class DataMapGenerator extends DataMapProvider {
    private final Builder<Compostable, Item> builder;

    public DataMapGenerator(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
        this.builder = builder(NeoForgeDataMaps.COMPOSTABLES);
    }

    @Override
    protected void gather(HolderLookup.Provider provider) {
        addCompostable(ModItems.TOMATO_SEED, 0.3F);
        addCompostable(ModItems.CHILI_SEED, 0.3F);
        addCompostable(ModItems.LETTUCE_SEED, 0.3F);
        addCompostable(ModItems.WILD_RICE_SEED, 0.3F);
        addCompostable(ModItems.RICE_SEED, 0.3F);
        addCompostable(ModItems.TOMATO, 0.65F);
        addCompostable(ModItems.RED_CHILI, 0.65F);
        addCompostable(ModItems.GREEN_CHILI, 0.65F);
        addCompostable(ModItems.LETTUCE, 0.65F);
        addCompostable(ModItems.RICE_PANICLE, 0.65F);
        addCompostable(ModItems.CATERPILLAR, 1.0F);
    }

    private void addCompostable(DeferredItem<Item> item, float chance) {
        builder.add(item, new Compostable(chance, true), false);
    }
}
