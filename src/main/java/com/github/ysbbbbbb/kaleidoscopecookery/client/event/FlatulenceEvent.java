package com.github.ysbbbbbb.kaleidoscopecookery.client.event;

import com.github.ysbbbbbb.kaleidoscopecookery.init.ModEffects;
import com.github.ysbbbbbb.kaleidoscopecookery.network.message.FlatulenceMessage;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.phys.Vec3;

@Environment(EnvType.CLIENT)
public class FlatulenceEvent {
    private static boolean wasShiftPressed = false;

    public static void register() {
        ClientTickEvents.END_CLIENT_TICK.register(FlatulenceEvent::onClientTick);
    }

    private static void onClientTick(Minecraft client) {
        KeyMapping keyShift = client.options.keyShift;
        boolean isShiftPressed = keyShift.isDown();

        // 检测 Shift 键的按下事件（从未按下变为按下）
        if (isShiftPressed && !wasShiftPressed) {
            if (!isInGame()) {
                return;
            }
            LocalPlayer player = client.player;
            if (player == null) {
                return;
            }
            if (!player.hasEffect(ModEffects.FLATULENCE)) {
                return;
            }
            keyShift.consumeClick();
            player.addDeltaMovement(new Vec3(0, 0.75, 0));
            if (ClientPlayNetworking.canSend(FlatulenceMessage.TYPE)) {
                ClientPlayNetworking.send(new FlatulenceMessage());
            }
        }
        wasShiftPressed = isShiftPressed;
    }

    private static boolean isInGame() {
        Minecraft mc = Minecraft.getInstance();
        // 不能是加载界面
        if (mc.getOverlay() != null) {
            return false;
        }
        // 不能打开任何 GUI
        if (mc.screen != null) {
            return false;
        }
        // 当前窗口捕获鼠标操作
        if (!mc.mouseHandler.isMouseGrabbed()) {
            return false;
        }
        // 选择了当前窗口
        return mc.isWindowActive();
    }
}
