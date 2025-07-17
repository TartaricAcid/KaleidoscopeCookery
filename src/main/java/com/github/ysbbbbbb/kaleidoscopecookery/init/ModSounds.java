package com.github.ysbbbbbb.kaleidoscopecookery.init;

import com.github.ysbbbbbb.kaleidoscopecookery.KaleidoscopeCookery;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, KaleidoscopeCookery.MOD_ID);

    public static final DeferredHolder<SoundEvent, SoundEvent> BLOCK_STOCKPOT = registerSound("block.stockpot");
    public static final DeferredHolder<SoundEvent, SoundEvent> BLOCK_PADDY = registerSound("block.paddy");
    public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_FART = registerSound("entity.fart");

    private static DeferredHolder<SoundEvent, SoundEvent> registerSound(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createFixedRangeEvent(ResourceLocation.fromNamespaceAndPath(KaleidoscopeCookery.MOD_ID, name), 16.0F));
    }
}
