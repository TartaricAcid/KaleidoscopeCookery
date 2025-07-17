package com.github.ysbbbbbb.kaleidoscopecookery.client.init;

import com.github.ysbbbbbb.kaleidoscopecookery.KaleidoscopeCookery;
import com.github.ysbbbbbb.kaleidoscopecookery.client.particle.CookingParticle;
import com.github.ysbbbbbb.kaleidoscopecookery.client.particle.StockpotParticle;
import com.github.ysbbbbbb.kaleidoscopecookery.init.ModParticles;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;

@EventBusSubscriber(modid = KaleidoscopeCookery.MOD_ID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class ParticleFactoryRegistry {
    @SubscribeEvent
    public static void onRegisterParticleFactory(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ModParticles.COOKING.get(), CookingParticle.Provider::new);
        event.registerSpriteSet(ModParticles.STOCKPOT.get(), StockpotParticle.Provider::new);
    }
}
