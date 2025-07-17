package com.github.ysbbbbbb.kaleidoscopecookery.event;

import com.github.ysbbbbbb.kaleidoscopecookery.KaleidoscopeCookery;
import com.github.ysbbbbbb.kaleidoscopecookery.advancements.critereon.ModEventTriggerType;
import com.github.ysbbbbbb.kaleidoscopecookery.blockentity.decoration.FruitBasketBlockEntity;
import com.github.ysbbbbbb.kaleidoscopecookery.init.ModItems;
import com.github.ysbbbbbb.kaleidoscopecookery.init.ModTrigger;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

@EventBusSubscriber(modid = KaleidoscopeCookery.MOD_ID)
public class RightClickEvent {
    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        Level level = event.getLevel();
        BlockPos pos = event.getPos();
        Player player = event.getEntity();
        InteractionHand hand = event.getHand();
        if (player.isSecondaryUseActive() && hand == InteractionHand.MAIN_HAND
            && level.getBlockEntity(pos) instanceof FruitBasketBlockEntity fruitBasketBlock) {
            fruitBasketBlock.takeOut(player);
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onRightClickEntity(PlayerInteractEvent.EntityInteract event) {
        Player player = event.getEntity();
        Entity target = event.getTarget();
        Level level = event.getLevel();
        if (target instanceof Chicken chicken && chicken.isBaby()
            && player.getMainHandItem().is(ModItems.CATERPILLAR.get())) {
            // 让鸡瞬间成年
            chicken.setAge(0);
            // 加一些特性和音效
            if (level instanceof ServerLevel serverLevel) {
                serverLevel.sendParticles(ParticleTypes.HEART,
                        chicken.getX(),
                        chicken.getY() + 0.25,
                        chicken.getZ(),
                        5,
                        0.2, 0.1, 0.2, 0.1);
                serverLevel.playSound(null, chicken.getX(), chicken.getY(), chicken.getZ(),
                        SoundEvents.PARROT_EAT, chicken.getSoundSource(),
                        1.0F, 1.0F + (serverLevel.random.nextFloat() - serverLevel.random.nextFloat()) * 0.2F);
            }
            player.getMainHandItem().shrink(1);
            ModTrigger.EVENT.get().trigger(player, ModEventTriggerType.USE_CATERPILLAR_FEED_CHICKEN);
        }
    }
}
