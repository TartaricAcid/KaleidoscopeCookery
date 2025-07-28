package com.github.ysbbbbbb.kaleidoscopecookery.init;

import com.github.ysbbbbbb.kaleidoscopecookery.KaleidoscopeCookery;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.level.block.Block;

public class ModPoi {
    public static final PoiType STOVE = registerPoiType("stove", ModBlocks.STOVE);
    public static final PoiType POT = registerPoiType("pot", ModBlocks.POT);
    public static final PoiType STOCKPOT = registerPoiType("stockpot", ModBlocks.STOCKPOT);
    public static final PoiType CHOPPING_BOARD = registerPoiType("chopping_board", ModBlocks.CHOPPING_BOARD);

    private static PoiType registerPoiType(String name, Block block) {
        return PointOfInterestHelper.register(ResourceLocation.fromNamespaceAndPath(KaleidoscopeCookery.MOD_ID, name), 1, 1, block);
    }

    public static void registerPoiTypes() {
        // 确保类被加载
    }
}
