package com.github.ysbbbbbb.kaleidoscopecookery.event.effect;

import com.github.ysbbbbbb.kaleidoscopecookery.KaleidoscopeCookery;
import com.github.ysbbbbbb.kaleidoscopecookery.init.ModEffects;
import com.github.ysbbbbbb.kaleidoscopecookery.init.tag.TagMod;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent;

import static net.minecraft.world.effect.MobEffectCategory.HARMFUL;

@EventBusSubscriber(modid = KaleidoscopeCookery.MOD_ID)
public class PreservationEvent {
    @SubscribeEvent
    public static void onEatFood(LivingEntityUseItemEvent.Finish event) {
        ItemStack stack = event.getItem();
        LivingEntity entity = event.getEntity();
        if (entity.hasEffect(ModEffects.PRESERVATION) && stack.is(TagMod.PRESERVATION_FOOD)) {
            FoodProperties foodProperties = stack.getFoodProperties(entity);
            if (foodProperties == null) {
                return;
            }
            for (var effectPair : foodProperties.effects()) {
                Holder<MobEffect> effect = effectPair.effect().getEffect();
                if (effect.value().getCategory() == HARMFUL) {
                    entity.removeEffect(effect);
                }
            }
        }
    }
}
