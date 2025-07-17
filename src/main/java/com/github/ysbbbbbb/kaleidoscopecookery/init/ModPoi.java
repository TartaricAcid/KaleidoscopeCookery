package com.github.ysbbbbbb.kaleidoscopecookery.init;

import com.github.ysbbbbbb.kaleidoscopecookery.KaleidoscopeCookery;
import com.google.common.collect.ImmutableSet;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModPoi {
    public static final DeferredRegister<PoiType> POI_TYPES = DeferredRegister.create(BuiltInRegistries.POINT_OF_INTEREST_TYPE, KaleidoscopeCookery.MOD_ID);

    public static final DeferredHolder<PoiType, PoiType> STOVE = POI_TYPES.register("stove",
            () -> new PoiType(ImmutableSet.copyOf(ModBlocks.STOVE.get().getStateDefinition().getPossibleStates()), 1, 1));
}
