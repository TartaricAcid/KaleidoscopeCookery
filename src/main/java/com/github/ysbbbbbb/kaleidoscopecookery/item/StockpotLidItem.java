package com.github.ysbbbbbb.kaleidoscopecookery.item;

import com.github.ysbbbbbb.kaleidoscopecookery.KaleidoscopeCookery;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.level.Level;

public class StockpotLidItem extends ShieldItem {
    public static final ResourceLocation USING_PROPERTY = ResourceLocation.fromNamespaceAndPath(KaleidoscopeCookery.MOD_ID, "using");
    private static final int NORMAL = 0;
    private static final int USING = 1;

    public StockpotLidItem() {
        super(new Properties().durability(120));
    }

    public static float getTexture(ItemStack stack, Level level, LivingEntity entity, int seed) {
        if (entity != null && entity.isUsingItem() && entity.getUseItem() == stack) {
            return USING;
        }
        return NORMAL;
    }
}
