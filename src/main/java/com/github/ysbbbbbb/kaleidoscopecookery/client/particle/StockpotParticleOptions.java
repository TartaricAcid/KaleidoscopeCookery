package com.github.ysbbbbbb.kaleidoscopecookery.client.particle;

import com.github.ysbbbbbb.kaleidoscopecookery.init.ModParticles;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ScalableParticleOptionsBase;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ExtraCodecs;
import org.joml.Vector3f;

public class StockpotParticleOptions extends ScalableParticleOptionsBase {
    public static final MapCodec<StockpotParticleOptions> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                    ExtraCodecs.VECTOR3F.fieldOf("color").forGetter(StockpotParticleOptions::getColor),
                    SCALE.fieldOf("scale").forGetter(StockpotParticleOptions::getScale)
            ).apply(instance, StockpotParticleOptions::new)
    );
    public static final StreamCodec<RegistryFriendlyByteBuf, StockpotParticleOptions> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VECTOR3F, StockpotParticleOptions::getColor,
            ByteBufCodecs.FLOAT, StockpotParticleOptions::getScale,
            StockpotParticleOptions::new
    );

    private final Vector3f color;

    public StockpotParticleOptions(Vector3f color, float scale) {
        super(scale);
        this.color = color;
    }

    public Vector3f getColor() {
        return this.color;
    }

    @Override
    public ParticleType<StockpotParticleOptions> getType() {
        return ModParticles.STOCKPOT.get();
    }
}
