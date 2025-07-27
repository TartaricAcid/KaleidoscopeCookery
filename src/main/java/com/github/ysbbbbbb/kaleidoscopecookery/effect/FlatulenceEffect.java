package com.github.ysbbbbbb.kaleidoscopecookery.effect;

import com.github.ysbbbbbb.kaleidoscopecookery.init.ModAttachmentType;
import com.github.ysbbbbbb.kaleidoscopecookery.init.ModTrigger;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.phys.Vec3;

public class FlatulenceEffect extends BaseEffect {
    public FlatulenceEffect(int color) {
        super(color);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        // 每 10 秒检查一次距离
        return duration % 10 == 5;
    }

    @SuppressWarnings("all")
    @Override
    public void applyEffectTick(LivingEntity livingEntity, int amplifier) {
        if (livingEntity instanceof ServerPlayer serverPlayer) {
            if (!serverPlayer.hasAttached(ModAttachmentType.FLATULENCE_EFFECT_STARTING_POSITION)) {
                serverPlayer.setAttached(ModAttachmentType.FLATULENCE_EFFECT_STARTING_POSITION, serverPlayer.position());
            } else {
                Vec3 position = serverPlayer.getAttached(ModAttachmentType.FLATULENCE_EFFECT_STARTING_POSITION);
                // 检查坐标是否改变，触发触发器
                ModTrigger.FLATULENCE_FLY_HEIGHT.trigger(serverPlayer, position);
            }
        }
    }

    @SuppressWarnings("all")
    @Override
    public void removeAttributeModifiers(LivingEntity livingEntity, AttributeMap attributeMap, int amplifier) {
        super.removeAttributeModifiers(livingEntity, attributeMap, amplifier);
        // 效果消失时，重置记录
        if (livingEntity instanceof ServerPlayer serverPlayer && serverPlayer.hasAttached(ModAttachmentType.FLATULENCE_EFFECT_STARTING_POSITION)) {
            serverPlayer.removeAttached(ModAttachmentType.FLATULENCE_EFFECT_STARTING_POSITION);
        }
    }
}
