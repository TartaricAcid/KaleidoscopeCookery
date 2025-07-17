package com.github.ysbbbbbb.kaleidoscopecookery.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

public class CookingParticle extends TextureSheetParticle {
    private final SpriteSet sprites;

    protected CookingParticle(ClientLevel level, double pX, double pY, double pZ, double xSpeed, double ySpeed, double zSpeed, SpriteSet sprites) {
        super(level, pX, pY, pZ, xSpeed, ySpeed, zSpeed);
        this.friction = 0.96F;
        this.speedUpWhenYMotionIsBlocked = true;
        this.sprites = sprites;
        this.xd *= 0.1F;
        this.yd *= 0.1F;
        this.zd *= 0.1F;
        this.rCol = 1;
        this.gCol = 1;
        this.bCol = 1;
        this.quadSize *= 0.75f;
        this.lifetime = 50;
        this.setSpriteFromAge(sprites);
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    @Override
    public float getQuadSize(float scaleFactor) {
        return this.quadSize * Mth.clamp((this.age + scaleFactor) / this.lifetime * 32.0F, 0, 1);
    }

    @Override
    public void tick() {
        super.tick();
        this.setSpriteFromAge(this.sprites);
    }

    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Provider(SpriteSet pSprites) {
            this.sprites = pSprites;
        }

        @Override
        public CookingParticle createParticle(@NotNull SimpleParticleType option, @NotNull ClientLevel world,
                                              double x, double y, double z,
                                              double xSpeed, double ySpeed, double zSpeed) {
            return new CookingParticle(world, x, y, z, xSpeed, ySpeed, zSpeed, this.sprites);
        }
    }
}
