package com.github.ysbbbbbb.kaleidoscopecookery.init.registry;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class FoodBiteAnimateTicks {
    public static final AnimateTick DARK_CUISINE_ANIMATE_TICK = new AnimateTick() {
        @Override
        public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
            double x = pos.getX() + 0.5;
            double y = pos.getY() + 0.5;
            double z = pos.getZ() + 0.5;

            level.addParticle(ParticleTypes.SMOKE,
                    x + random.nextDouble() / 3 * (random.nextBoolean() ? 1 : -1),
                    y + random.nextDouble() / 3,
                    z + random.nextDouble() / 3 * (random.nextBoolean() ? 1 : -1),
                    0, 0.02, 0);
        }
    };

    public static final AnimateTick SUSPICIOUS_STIR_FRY_ANIMATE_TICK = new AnimateTick() {
        @Override
        public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
            double x = pos.getX() + 0.5;
            double y = pos.getY() + 0.5;
            double z = pos.getZ() + 0.5;

            level.addParticle(ColorParticleOption.create(ParticleTypes.ENTITY_EFFECT, 104 / 255f, 54 / 255f, 128 / 255f),
                    x + random.nextDouble() / 5 * (random.nextBoolean() ? 1 : -1),
                    y + random.nextDouble() / 5,
                    z + random.nextDouble() / 5 * (random.nextBoolean() ? 1 : -1),
                    0.1, 0.1, 0.1);
        }
    };

    public interface AnimateTick {
        default void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        }
    }
}
