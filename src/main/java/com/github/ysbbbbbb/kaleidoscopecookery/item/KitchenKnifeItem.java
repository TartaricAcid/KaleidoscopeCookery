package com.github.ysbbbbbb.kaleidoscopecookery.item;

import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class KitchenKnifeItem extends SwordItem {
    public KitchenKnifeItem(Tier tier) {
        super(tier, new Properties().attributes(SwordItem.createAttributes(tier, 0, -2.0F)));
    }
}
