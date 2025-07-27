package com.github.ysbbbbbb.kaleidoscopecookery.event.effect;

import com.github.ysbbbbbb.kaleidoscopecookery.init.ModEffects;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class SatiatedShieldEvent {
    public static void register() {
        ServerLivingEntityEvents.ALLOW_DAMAGE.register(SatiatedShieldEvent::onPlayerHurt);
    }

    // FIXME 目前机制还有很大问题
    private static boolean onPlayerHurt(LivingEntity entity, DamageSource damageSource, float amount) {
        if (entity.isInvulnerableTo(damageSource)) {
            return false;
        }
        if (entity instanceof Player player && player.getFoodData().getFoodLevel() > 0 && player.hasEffect(ModEffects.SATIATED_SHIELD)) {
            if (player.invulnerableTime < 10) {
                int level = player.getFoodData().getFoodLevel() - Math.round(amount);
                player.getFoodData().setFoodLevel(Math.max(0, level));
            }
            player.invulnerableTime = 20;
            return false;
        }
        return true;
    }
}
