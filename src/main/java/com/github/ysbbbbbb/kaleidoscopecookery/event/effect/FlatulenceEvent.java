package com.github.ysbbbbbb.kaleidoscopecookery.event.effect;

import com.github.ysbbbbbb.kaleidoscopecookery.KaleidoscopeCookery;
import com.github.ysbbbbbb.kaleidoscopecookery.init.ModEffects;
import com.github.ysbbbbbb.kaleidoscopecookery.network.message.FlatulenceMessage;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import org.lwjgl.glfw.GLFW;

@EventBusSubscriber(modid = KaleidoscopeCookery.MOD_ID, value = Dist.CLIENT)
public class FlatulenceEvent {
    @SubscribeEvent
    public static void onShiftKeyPressed(InputEvent.Key event) {
        KeyMapping keyShift = Minecraft.getInstance().options.keyShift;
        if (keyShift.matches(event.getKey(), event.getScanCode())) {
            if (!isInGame()) {
                return;
            }
            if (event.getAction() != GLFW.GLFW_PRESS) {
                return;
            }
            LocalPlayer player = Minecraft.getInstance().player;
            if (player == null || player.isShiftKeyDown()) {
                return;
            }
            if (!player.hasEffect(ModEffects.FLATULENCE)) {
                return;
            }
            keyShift.consumeClick();
            player.addDeltaMovement(new Vec3(0, 0.75, 0));
            PacketDistributor.sendToServer(FlatulenceMessage.INSTANCE);
        }
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
