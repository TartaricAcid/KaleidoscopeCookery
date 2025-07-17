package com.github.ysbbbbbb.kaleidoscopecookery.api.blockentity;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public interface IKitchenwareRacks {
    /**
     * 处理实体点击厨具架
     *
     * @param user   使用者
     * @param stack  如果是 ItemStack.EMPTY 则表示从厨具架取出物品，否则表示将物品放入厨具架
     * @param isLeft 放在左边还是右边
     * @return 如果成功处理点击则返回 true，否则返回 false
     */
    boolean onClick(LivingEntity user, ItemStack stack, boolean isLeft);

    /**
     * 获取厨具架左侧的物品
     *
     * @return 如果厨具架为空，返回 ItemStack.EMPTY
     */
    ItemStack getItemLeft();

    /**
     * 获取厨具架右侧的物品
     *
     * @return 如果厨具架为空，返回 ItemStack.EMPTY
     */
    ItemStack getItemRight();
}
