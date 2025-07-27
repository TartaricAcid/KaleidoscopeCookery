package com.github.ysbbbbbb.kaleidoscopecookery.effect;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Phantom;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class SulfurEffect extends BaseEffect {
    public SulfurEffect(int color) {
        super(color);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration % 5 == 0;
    }

    @Override
    public void applyEffectTick(LivingEntity livingEntity, int amplifier) {
        AABB aabb = new AABB(livingEntity.blockPosition()).inflate(8, 16, 8);
        List<Phantom> list = livingEntity.level().getEntitiesOfClass(Phantom.class, aabb);
        for (Phantom phantom : list) {
            if (livingEntity.equals(phantom.getTarget())) {
                phantom.setTarget(null);
            }
        }
    }
}
