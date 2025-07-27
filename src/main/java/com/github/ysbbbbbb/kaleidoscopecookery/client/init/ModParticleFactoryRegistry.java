package com.github.ysbbbbbb.kaleidoscopecookery.client.init;

import com.github.ysbbbbbb.kaleidoscopecookery.client.particle.CookingParticle;
import com.github.ysbbbbbb.kaleidoscopecookery.client.particle.StockpotParticle;
import com.github.ysbbbbbb.kaleidoscopecookery.init.ModParticles;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;

@Environment(EnvType.CLIENT)
public class ModParticleFactoryRegistry {
    public static void register() {
        ParticleFactoryRegistry.getInstance().register(ModParticles.COOKING, CookingParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.STOCKPOT, StockpotParticle.Provider::new);
    }
}
