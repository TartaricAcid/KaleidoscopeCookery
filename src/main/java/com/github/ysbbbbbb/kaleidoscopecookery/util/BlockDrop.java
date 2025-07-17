package com.github.ysbbbbbb.kaleidoscopecookery.util;

import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;

public final class BlockDrop {
    private BlockDrop() {
        // 工具类禁止实例化
    }

    public static void popResource(Level level, BlockPos pos, double yOffset, ItemStack stack) {
        if (!level.isClientSide && !stack.isEmpty() && level.getGameRules().getBoolean(GameRules.RULE_DOBLOCKDROPS) && !level.restoringBlockSnapshots) {
            double itemHalfHeight = EntityType.ITEM.getHeight() / 2.0;
            double range = 0.1;
            double x = pos.getX() + 0.5 + Mth.nextDouble(level.random, -range, range);
            double y = pos.getY() + 0.5 + Mth.nextDouble(level.random, -range, range) - itemHalfHeight + yOffset;
            double z = pos.getZ() + 0.5 + Mth.nextDouble(level.random, -range, range);
            ItemEntity itemEntity = new ItemEntity(level, x, y, z, stack);
            itemEntity.setDefaultPickUpDelay();
            level.addFreshEntity(itemEntity);
        }
    }
}
