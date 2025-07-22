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
            () -> new PoiType(ImmutableSet.copyOf(
                    ModBlocks.STOVE.get()
                            .getStateDefinition()
                            .getPossibleStates()
            ), 1, 1));

    public static final DeferredHolder<PoiType, PoiType> POT = POI_TYPES.register("pot",
            () -> new PoiType(ImmutableSet.copyOf(
                    ModBlocks.POT.get()
                            .getStateDefinition()
                            .getPossibleStates()
            ), 1, 1));

    public static final DeferredHolder<PoiType, PoiType> STOCKPOT = POI_TYPES.register("stockpot",
            () -> new PoiType(ImmutableSet.copyOf(
                    ModBlocks.STOCKPOT.get()
                            .getStateDefinition()
                            .getPossibleStates()
            ), 1, 1));

    public static final DeferredHolder<PoiType, PoiType> CHOPPING_BOARD = POI_TYPES.register("chopping_board",
            () -> new PoiType(ImmutableSet.copyOf(
                    ModBlocks.CHOPPING_BOARD.get()
                            .getStateDefinition()
                            .getPossibleStates()
            ), 1, 1));
}
