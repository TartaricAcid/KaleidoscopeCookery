package com.github.ysbbbbbb.kaleidoscopecookery.init;

import com.github.ysbbbbbb.kaleidoscopecookery.KaleidoscopeCookery;
import com.github.ysbbbbbb.kaleidoscopecookery.particle.StockpotParticleOptions;
import com.mojang.serialization.Codec;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ModParticles {
    public static final SimpleParticleType COOKING = FabricParticleTypes.simple();
    public static final ModParticleType<StockpotParticleOptions> STOCKPOT = new ModParticleType<>(false, StockpotParticleOptions.DESERIALIZER, StockpotParticleOptions.CODEC);

    public static void registerParticles() {
        Registry.register(BuiltInRegistries.PARTICLE_TYPE, new ResourceLocation(KaleidoscopeCookery.MOD_ID, "cooking_particle"), COOKING);
        Registry.register(BuiltInRegistries.PARTICLE_TYPE, new ResourceLocation(KaleidoscopeCookery.MOD_ID, "stockpot_particle"), STOCKPOT);
    }

    @SuppressWarnings("deprecation")
    public static class ModParticleType<T extends ParticleOptions> extends ParticleType<T> {
        private final Codec<T> codec;

        public ModParticleType(boolean overrideLimiter, ParticleOptions.Deserializer<T> deserializer, Codec<T> codec) {
            super(overrideLimiter, deserializer);
            this.codec = codec;
        }

        @Override
        public @NotNull Codec<T> codec() {
            return this.codec;
        }
    }
}
