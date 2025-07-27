package com.github.ysbbbbbb.kaleidoscopecookery.event;

import com.github.ysbbbbbb.kaleidoscopecookery.entity.ai.CatLieOnBlockGoal;
import com.github.ysbbbbbb.kaleidoscopecookery.init.ModEffects;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.monster.Creeper;

public class EntityJoinWorldEvent {
    public static void register() {
        ServerEntityEvents.ENTITY_LOAD.register(EntityJoinWorldEvent::onEntityJoinWorld);
    }

    private static void onEntityJoinWorld(Entity entity, ServerLevel level) {
        if (entity instanceof Cat cat) {
            cat.goalSelector.addGoal(5, new CatLieOnBlockGoal(cat, 1.1, 8));
        } else if (entity instanceof Creeper creeper) {
            creeper.goalSelector.addGoal(3, new AvoidEntityGoal<>(creeper, LivingEntity.class, 6,
                    1, 1.2, e -> e.hasEffect(ModEffects.MUSTARD)));
        }
    }
}
