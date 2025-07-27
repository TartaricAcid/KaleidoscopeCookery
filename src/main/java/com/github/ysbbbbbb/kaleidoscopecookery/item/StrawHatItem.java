package com.github.ysbbbbbb.kaleidoscopecookery.item;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.Item;

public class StrawHatItem extends ArmorItem {
    private final boolean hasFlower;

    public StrawHatItem(boolean hasFlower) {
        super(ArmorMaterials.LEATHER, ArmorItem.Type.HELMET, new Item.Properties().stacksTo(1));
        this.hasFlower = hasFlower;
    }

    public boolean hasFlower() {
        return hasFlower;
    }
}
