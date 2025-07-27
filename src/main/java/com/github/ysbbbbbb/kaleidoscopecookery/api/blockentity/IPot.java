package com.github.ysbbbbbb.kaleidoscopecookery.api.blockentity;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public interface IPot {
    /**
     * 锅的起始状态。此时可以放入原料
     */
    int PUT_INGREDIENT = 0;
    /**
     * 放入原料后，锅开始烹饪
     */
    int COOKING = 1;
    /**
     * 烹饪完成。此时可以取出产品
     */
    int FINISHED = 2;
    /**
     * 烹饪超时，此时只能取出黑暗料理
     */
    int BURNT = 3;

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
     * 执行放油逻辑
     * <p>
     * 注意这个方法没有检查当前方块是否有油
     *
     * @param level 使用者所处的 level
     * @param user  使用者
     * @param stack 油，或者带油的锅铲
     * @return 如果放油成功则返回 true，否则返回 false
     */
    boolean onPlaceOil(Level level, LivingEntity user, ItemStack stack);

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
     * 锅铲点击锅时的逻辑
     *
     * @param level  使用者所处的 level
     * @param user   使用者
     * @param shovel 锅铲
     */
    void onShovelHit(Level level, LivingEntity user, ItemStack shovel);

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
