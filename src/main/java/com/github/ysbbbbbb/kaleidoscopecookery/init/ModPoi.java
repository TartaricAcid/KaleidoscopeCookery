package com.github.ysbbbbbb.kaleidoscopecookery.init;

import com.github.ysbbbbbb.kaleidoscopecookery.KaleidoscopeCookery;
import com.google.common.collect.ImmutableSet;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.village.poi.PoiType;

public class ModPoi {
    public static final PoiType STOVE = new PoiType(ImmutableSet.copyOf(
            ModBlocks.STOVE.getStateDefinition().getPossibleStates()
    ), 1, 1);

    public static final PoiType POT = new PoiType(ImmutableSet.copyOf(
            ModBlocks.POT.getStateDefinition().getPossibleStates()
    ), 1, 1);

    public static final PoiType STOCKPOT = new PoiType(ImmutableSet.copyOf(
            ModBlocks.STOCKPOT.getStateDefinition().getPossibleStates()
    ), 1, 1);

    public static final PoiType CHOPPING_BOARD = new PoiType(ImmutableSet.copyOf(
            ModBlocks.CHOPPING_BOARD.getStateDefinition().getPossibleStates()
    ), 1, 1);

    public static void registerPoiTypes() {
        Registry.register(BuiltInRegistries.POINT_OF_INTEREST_TYPE, ResourceLocation.fromNamespaceAndPath(KaleidoscopeCookery.MOD_ID, "stove"), STOVE);
        Registry.register(BuiltInRegistries.POINT_OF_INTEREST_TYPE, ResourceLocation.fromNamespaceAndPath(KaleidoscopeCookery.MOD_ID, "pot"), POT);
        Registry.register(BuiltInRegistries.POINT_OF_INTEREST_TYPE, ResourceLocation.fromNamespaceAndPath(KaleidoscopeCookery.MOD_ID, "stockpot"), STOCKPOT);
        Registry.register(BuiltInRegistries.POINT_OF_INTEREST_TYPE, ResourceLocation.fromNamespaceAndPath(KaleidoscopeCookery.MOD_ID, "chopping_board"), CHOPPING_BOARD);
    }
}
