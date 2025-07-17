package com.github.ysbbbbbb.kaleidoscopecookery.effect;

import com.github.ysbbbbbb.kaleidoscopecookery.init.ModTrigger;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.phys.Vec3;

public class FlatulenceEffect extends BaseEffect {
    public static final String FLATULENCE_EFFECT_STARTING_POSITION = "FlatulenceEffectStartingPosition";

    public FlatulenceEffect(int color) {
        super(color);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        // 最后 3 tick 移除记录
        if (duration < 5) {

        }
        // 每 10 秒检查一次距离
        return duration % 10 == 5;
    }

    @Override
    public boolean applyEffectTick(LivingEntity livingEntity, int amplifier) {
        if (livingEntity instanceof ServerPlayer serverPlayer) {
            CompoundTag data = serverPlayer.getPersistentData();
            if (!data.contains(FLATULENCE_EFFECT_STARTING_POSITION)) {
                // 开始记录坐标
                BlockPos position = serverPlayer.blockPosition();
                data.put(FLATULENCE_EFFECT_STARTING_POSITION, NbtUtils.writeBlockPos(position));
            } else {
                NbtUtils.readBlockPos(data, FLATULENCE_EFFECT_STARTING_POSITION).ifPresent(blockPos -> {
                    // 检查坐标是否改变，触发触发器
                    ModTrigger.FLATULENCE_FLY_HEIGHT.get().trigger(serverPlayer, new Vec3(blockPos.getX(), blockPos.getY(), blockPos.getZ()));
                });
            }
        }
        return true;
    }

    @Override
    public void removeAttributeModifiers(AttributeMap attributeMap) {
        super.removeAttributeModifiers(attributeMap);
    }
}
