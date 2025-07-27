package com.github.ysbbbbbb.kaleidoscopecookery.crafting.container;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class StockpotInput extends SimpleInput {
    private final ResourceLocation soupBase;

    public StockpotInput(List<ItemStack> items, ResourceLocation soupBase) {
        super(items);
        this.soupBase = soupBase;
    }

    public ResourceLocation getSoupBase() {
        return soupBase;
    }
}
