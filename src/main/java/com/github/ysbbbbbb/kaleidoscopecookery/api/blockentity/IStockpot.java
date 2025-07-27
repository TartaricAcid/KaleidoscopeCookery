package com.github.ysbbbbbb.kaleidoscopecookery.api.blockentity;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public interface IStockpot {
    /**
     * 起始状态。此时锅内没有任何东西，需要放入汤底
     */
    int PUT_SOUP_BASE = 0;
    /**
     * 放入汤底后，等待放入原料。此时锅内有汤底，但没有原料
     */
    int PUT_INGREDIENT = 1;
    /**
     * 放入原料后，锅开始烹饪
     */
    int COOKING = 2;
    /**
     * 烹饪完成。此时可以取出产品
     */
    int FINISHED = 3;

    /**
     * 获取当前状态
     *
     * @return 数字表示的状态
     */
    int getStatus();

    /**
     * 检查锅下方是否有热源
     *
     * @param level 使用者所处的 level
     * @return 如果有热源则返回 true，否则返回 false
     */
    boolean hasHeatSource(Level level);

    /**
     * 检查锅是否有盖子，盖子盖上后汤锅才会进行烹饪
     *
     * @return 如果有盖子则返回 true，否则返回 false
     */
    boolean hasLid();

    /**
     * 揭开、盖上锅盖
     *
     * @param level 使用者所处的 level
     * @param user  使用者
     * @param stack 使用者所持有的物品，如果是锅盖则盖上锅盖，如果是空物品则揭开锅盖
     * @return 如果操作成功则返回 true，否则返回 false
     */
    boolean onLitClick(Level level, LivingEntity user, ItemStack stack);

    /**
     * 添加汤底到锅中
     *
     * @param level  使用者所处的 level
     * @param user   使用者
     * @param bucket 要添加的汤底物品
     * @return 如果添加成功则返回 true，否则返回 false
     */
    boolean addSoupBase(Level level, LivingEntity user, ItemStack bucket);

    /**
     * 移除锅中的汤底
     *
     * @param level  使用者所处的 level
     * @param user   使用者
     * @param bucket 承装汤底的容器
     * @return 如果移除成功则返回 true，否则返回 false
     */
    boolean removeSoupBase(Level level, LivingEntity user, ItemStack bucket);

    /**
     * 添加原料到锅中
     *
     * @param level     使用者所处的 level
     * @param user      使用者
     * @param itemStack 要添加的原料
     * @return 如果添加成功则返回 true，否则返回 false
     */
    boolean addIngredient(Level level, LivingEntity user, ItemStack itemStack);

    /**
     * 移除锅中的原料
     *
     * @param level 使用者所处的 level
     * @param user  使用者
     * @return 如果移除成功则返回 true，否则返回 false
     */
    boolean removeIngredient(Level level, LivingEntity user);

    /**
     * 从锅中取出产品
     *
     * @param level 使用者所处的 level
     * @param user  使用者
     * @param stack 取出产物时所持有的物品
     * @return 如果取出成功则返回 true，否则返回 false
     */
    boolean takeOutProduct(Level level, LivingEntity user, ItemStack stack);
}
