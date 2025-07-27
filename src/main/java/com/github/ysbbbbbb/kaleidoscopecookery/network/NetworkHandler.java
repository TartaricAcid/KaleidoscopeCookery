package com.github.ysbbbbbb.kaleidoscopecookery.network;

import com.github.ysbbbbbb.kaleidoscopecookery.network.message.FlatulenceMessage;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

public class NetworkHandler {
    public static void init() {
        PayloadTypeRegistry.playC2S().register(FlatulenceMessage.TYPE, FlatulenceMessage.STREAM_CODEC);

        ServerPlayNetworking.registerGlobalReceiver(FlatulenceMessage.TYPE, new FlatulenceMessage());
    }
}
