package com.github.ysbbbbbb.kaleidoscopecookery.api.recipe.soupbase;

import com.github.ysbbbbbb.kaleidoscopecookery.api.client.render.ISoupBaseRender;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public interface ISoupBase {
    /**
     * 注册 ID，不能重复
     *
     * @return 注册 ID
     */
    ResourceLocation getName();

    /**
     * 当放入该汤底后，冒出的粒子的颜色
     *
     * @return RGB 颜色值
     */
    int getBubbleColor();

    /**
     * 主要用于 JEI 显示，表示该汤底对应的物品
     */
    ItemStack getDisplayStack();

    /**
     * 在玩家拿着汤底右击汤锅时调用，用来判断手持物是否是当前对应的汤底
     *
     * @param stack 手持物
     * @return 如果是当前汤底则返回 true，否则返回 false
     */
    boolean isSoupBase(ItemStack stack);

    /**
     * 在玩家拿着汤底右击汤锅时调用，用来返还对应的容器
     *
     * @param level    当前世界
     * @param user     使用者
     * @param soupBase 汤底物品
     * @return 返回对应的容器物品，如果没有容器则返回 ItemStack.EMPTY
     */
    ItemStack getReturnContainer(Level level, LivingEntity user, ItemStack soupBase);

    /**
     * 判断是否是能取回汤底的容器物品
     *
     * @param stack 要判断的容器物品
     * @return 如果符合则返回 true，否则返回 false
     */
    boolean isContainer(ItemStack stack);

    /**
     * 在玩家拿着容器右击汤锅时调用，用来返还对应的汤底
     *
     * @param level     当前世界
     * @param user      使用者
     * @param container 汤底容器物品
     * @return 返回对应的汤底物品
     */
    ItemStack getReturnSoupBase(Level level, LivingEntity user, ItemStack container);

    /**
     * 获取对应的渲染器，只能在客户端调用
     *
     * @return ISoupBaseRender 返回渲染器实例，用于渲染汤底
     */
    ISoupBaseRender getRender();
}
