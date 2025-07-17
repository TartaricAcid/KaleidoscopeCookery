package com.github.ysbbbbbb.kaleidoscopecookery.event.effect;

import com.github.ysbbbbbb.kaleidoscopecookery.KaleidoscopeCookery;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;

import static com.github.ysbbbbbb.kaleidoscopecookery.effect.FlatulenceEffect.FLATULENCE_EFFECT_STARTING_POSITION;

@EventBusSubscriber(modid = KaleidoscopeCookery.MOD_ID)
public class FlatulenceServerEvent {
    @SubscribeEvent
    public static void onMobEffectEvent(MobEffectEvent.Remove event) {
        if (event.getEntity() instanceof ServerPlayer serverPlayer) {
            CompoundTag data = serverPlayer.getPersistentData();
            data.remove(FLATULENCE_EFFECT_STARTING_POSITION);
        }
    }
}
