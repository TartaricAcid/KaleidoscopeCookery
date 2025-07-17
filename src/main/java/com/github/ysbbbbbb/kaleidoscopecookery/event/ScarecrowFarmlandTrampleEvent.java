package com.github.ysbbbbbb.kaleidoscopecookery.event;

import com.github.ysbbbbbb.kaleidoscopecookery.KaleidoscopeCookery;
import com.github.ysbbbbbb.kaleidoscopecookery.entity.ScarecrowEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.AABB;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.level.BlockEvent;

import java.util.List;

@EventBusSubscriber(modid = KaleidoscopeCookery.MOD_ID)
public class ScarecrowFarmlandTrampleEvent {
    @SubscribeEvent
    public static void onFarmlandTrampleEvent(BlockEvent.FarmlandTrampleEvent event) {
        LevelAccessor level = event.getLevel();
        BlockPos pos = event.getPos();
        if (level instanceof ServerLevel serverLevel) {
            List<ScarecrowEntity> entities = serverLevel.getEntitiesOfClass(ScarecrowEntity.class, new AABB(pos).inflate(16));
            if (!entities.isEmpty()) {
                event.setCanceled(true);
            }
        }
    }
}
