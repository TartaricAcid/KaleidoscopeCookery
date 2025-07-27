package com.github.ysbbbbbb.kaleidoscopecookery.particle;

import com.github.ysbbbbbb.kaleidoscopecookery.init.ModParticles;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.particles.DustParticleOptionsBase;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.ExtraCodecs;
import org.joml.Vector3f;

@SuppressWarnings("deprecation")
public class StockpotParticleOptions extends DustParticleOptionsBase {
    public static final Codec<StockpotParticleOptions> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            ExtraCodecs.VECTOR3F.fieldOf("color").forGetter(options -> options.color),
            Codec.FLOAT.fieldOf("scale").forGetter(options -> options.scale)
    ).apply(instance, StockpotParticleOptions::new));

    public static final ParticleOptions.Deserializer<StockpotParticleOptions> DESERIALIZER = new ParticleOptions.Deserializer<>() {
        @Override
        public StockpotParticleOptions fromCommand(ParticleType<StockpotParticleOptions> type, StringReader reader) throws CommandSyntaxException {
            Vector3f color = DustParticleOptionsBase.readVector3f(reader);
            reader.expect(' ');
            float scale = reader.readFloat();
            return new StockpotParticleOptions(color, scale);
        }

        @Override
        public StockpotParticleOptions fromNetwork(ParticleType<StockpotParticleOptions> type, FriendlyByteBuf buffer) {
            Vector3f color = DustParticleOptionsBase.readVector3f(buffer);
            float scale = buffer.readFloat();
            return new StockpotParticleOptions(color, scale);
        }
    };

    public StockpotParticleOptions(Vector3f color, float scale) {
        super(color, scale);
    }

    @Override
    public ParticleType<StockpotParticleOptions> getType() {
        return ModParticles.STOCKPOT;
    }
}
