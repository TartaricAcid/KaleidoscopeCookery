package com.github.ysbbbbbb.kaleidoscopecookery.api.blockentity;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public interface IShawarmaSpit {
    /**
     * 尝试往旋风烤肉架上放置烹饪物品
     *
     * @param level       使用者所处的 level
     * @param cookingItem 打算放入的烹饪物品
     * @return 是否成功放置
     */
    boolean onPutCookingItem(Level level, ItemStack cookingItem);

    /**
     * 尝试从旋风烤肉架上取出烹饪好的物品（也可能是没有烹调好的）
     *
     * @param level 使用者所处的 level
     * @param user  使用者实体
     * @return 是否成功取出
     */
    boolean onTakeCookedItem(Level level, LivingEntity user);
}
