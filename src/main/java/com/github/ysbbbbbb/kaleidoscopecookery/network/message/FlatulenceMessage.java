package com.github.ysbbbbbb.kaleidoscopecookery.network.message;

import com.github.ysbbbbbb.kaleidoscopecookery.KaleidoscopeCookery;
import com.github.ysbbbbbb.kaleidoscopecookery.init.ModEffects;
import com.github.ysbbbbbb.kaleidoscopecookery.init.ModSounds;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class FlatulenceMessage implements CustomPacketPayload {
    public static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(KaleidoscopeCookery.MOD_ID, "flatulence");
    public static final CustomPacketPayload.Type<FlatulenceMessage> TYPE = new CustomPacketPayload.Type<>(ID);
    public static final FlatulenceMessage INSTANCE = new FlatulenceMessage();
    public static final StreamCodec<ByteBuf, FlatulenceMessage> STREAM_CODEC = StreamCodec.unit(INSTANCE);

    private FlatulenceMessage() {
    }

    public static void handle(FlatulenceMessage message, IPayloadContext context) {
        if (context.flow().isServerbound()) {
            context.enqueueWork(() -> onEffect(context));
        }
    }

    private static void onEffect(IPayloadContext context) {
        Player player = context.player();
        if (player instanceof ServerPlayer serverPlayer && player.hasEffect(ModEffects.FLATULENCE)) {
            ServerLevel serverLevel = serverPlayer.serverLevel();
            serverLevel.sendParticles(ParticleTypes.CLOUD,
                    player.getX(), player.getY() + 0.25, player.getZ(),
                    10, 0.25, 0.25, 0.25, 0.1);
            serverLevel.playSound(null, player.blockPosition(), ModSounds.ENTITY_FART.get(),
                    SoundSource.PLAYERS, 1.0F, 0.8F + (float) Math.random() * 0.4F);
        }
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
