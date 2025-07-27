package com.github.ysbbbbbb.kaleidoscopecookery.init;

import com.github.ysbbbbbb.kaleidoscopecookery.KaleidoscopeCookery;
import com.github.ysbbbbbb.kaleidoscopecookery.particle.StockpotParticleOptions;
import com.mojang.serialization.MapCodec;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ModParticles {
    public static final SimpleParticleType COOKING = FabricParticleTypes.simple();
    public static final ModParticleType<StockpotParticleOptions> STOCKPOT = new ModParticleType<>(false, StockpotParticleOptions.CODEC, StockpotParticleOptions.STREAM_CODEC);

    public static void registerParticles() {
        Registry.register(BuiltInRegistries.PARTICLE_TYPE, ResourceLocation.fromNamespaceAndPath(KaleidoscopeCookery.MOD_ID, "cooking_particle"), COOKING);
        Registry.register(BuiltInRegistries.PARTICLE_TYPE, ResourceLocation.fromNamespaceAndPath(KaleidoscopeCookery.MOD_ID, "stockpot_particle"), STOCKPOT);
    }

    @SuppressWarnings("deprecation")
    public static class ModParticleType<T extends ParticleOptions> extends ParticleType<T> {
        private final MapCodec<T> codec;
        private final StreamCodec<? super RegistryFriendlyByteBuf, T> streamCodec;

        public ModParticleType(boolean overrideLimiter, MapCodec<T> codec, StreamCodec<? super RegistryFriendlyByteBuf, T> streamCodec) {
            super(overrideLimiter);
            this.codec = codec;
            this.streamCodec = streamCodec;
        }

        @Override
        public @NotNull MapCodec<T> codec() {
            return this.codec;
        }

        @Override
        public StreamCodec<? super RegistryFriendlyByteBuf, T> streamCodec() {
            return this.streamCodec;
        }
    }
}
