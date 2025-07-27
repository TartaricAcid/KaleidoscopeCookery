package com.github.ysbbbbbb.kaleidoscopecookery.event.effect;

import com.github.ysbbbbbb.kaleidoscopecookery.init.ModEffects;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.level.ServerPlayer;

import static com.github.ysbbbbbb.kaleidoscopecookery.init.ModAttachmentType.FLATULENCE_EFFECT_STARTING_POSITION;

public class FlatulenceServerEvent {
    public static void register() {
        // 由于 Fabric 没有直接的药水效果移除事件，我们需要使用其他方式监听
        // 可以通过 ServerTickEvents 定期检查药水效果状态
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            for (ServerPlayer player : server.getPlayerList().getPlayers()) {
                checkFlatulenceEffect(player);
            }
        });
    }

    @SuppressWarnings("all")
    private static void checkFlatulenceEffect(ServerPlayer player) {
        // 如果玩家有附着数据但没有药水效果，说明效果被移除了
        if (player.hasAttached(FLATULENCE_EFFECT_STARTING_POSITION) && !player.hasEffect(ModEffects.FLATULENCE)) {
            player.removeAttached(FLATULENCE_EFFECT_STARTING_POSITION);
        }
    }
}
