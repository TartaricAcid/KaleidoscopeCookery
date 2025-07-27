package com.github.ysbbbbbb.kaleidoscopecookery.init;

import com.github.ysbbbbbb.kaleidoscopecookery.KaleidoscopeCookery;
import com.github.ysbbbbbb.kaleidoscopecookery.effect.*;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;

public class ModEffects {
    public static final MobEffect FLATULENCE = new FlatulenceEffect(0xFFC6C6);
    public static final MobEffect TUNDRA_STRIDER = new BaseEffect(0xA1F8FC);
    public static final MobEffect WARMTH = new WarmthEffect(0xFF5F0E);
    public static final MobEffect SATIATED_SHIELD = new BaseEffect(0xFF1313);
    public static final MobEffect VIGOR = new VigorEffect(0x84C322);
    public static final MobEffect SULFUR = new SulfurEffect(0xE8B75E);
    public static final MobEffect MUSTARD = new BaseEffect(0x5A6D09);
    public static final MobEffect PRESERVATION = new BaseEffect(0xAEC639);

    public static void registerEffects() {
        Registry.register(BuiltInRegistries.MOB_EFFECT, new ResourceLocation(KaleidoscopeCookery.MOD_ID, "flatulence"), FLATULENCE);
        Registry.register(BuiltInRegistries.MOB_EFFECT, new ResourceLocation(KaleidoscopeCookery.MOD_ID, "tundra_strider"), TUNDRA_STRIDER);
        Registry.register(BuiltInRegistries.MOB_EFFECT, new ResourceLocation(KaleidoscopeCookery.MOD_ID, "warmth"), WARMTH);
        Registry.register(BuiltInRegistries.MOB_EFFECT, new ResourceLocation(KaleidoscopeCookery.MOD_ID, "satiated_shield"), SATIATED_SHIELD);
        Registry.register(BuiltInRegistries.MOB_EFFECT, new ResourceLocation(KaleidoscopeCookery.MOD_ID, "vigor"), VIGOR);
        Registry.register(BuiltInRegistries.MOB_EFFECT, new ResourceLocation(KaleidoscopeCookery.MOD_ID, "sulfur"), SULFUR);
        Registry.register(BuiltInRegistries.MOB_EFFECT, new ResourceLocation(KaleidoscopeCookery.MOD_ID, "mustard"), MUSTARD);
        Registry.register(BuiltInRegistries.MOB_EFFECT, new ResourceLocation(KaleidoscopeCookery.MOD_ID, "preservation"), PRESERVATION);
    }
}
