package com.github.ysbbbbbb.kaleidoscopecookery.init.registry;

import com.github.ysbbbbbb.kaleidoscopecookery.KaleidoscopeCookery;
import com.github.ysbbbbbb.kaleidoscopecookery.block.food.FoodBiteBlock;
import com.github.ysbbbbbb.kaleidoscopecookery.init.ModSoupBases;
import com.github.ysbbbbbb.kaleidoscopecookery.item.BowlFoodBlockItem;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.registries.RegisterEvent;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = KaleidoscopeCookery.MOD_ID)
public class CommonRegistry {
    @SubscribeEvent
    public static void onSetupEvent(FMLCommonSetupEvent event) {
        event.enqueueWork(ModSoupBases::registerAll);
    }

    @SubscribeEvent
    public static void onBlockRegistryEvent(RegisterEvent event) {
        if (event.getRegistry().equals(BuiltInRegistries.BLOCK)) {
            FoodBiteRegistry.FOOD_DATA_MAP.forEach((resourceLocation, data) ->
                    event.register(BuiltInRegistries.BLOCK.key(), resourceLocation,
                            () -> new FoodBiteBlock(data.blockFood(), data.maxBites(), data.animateTick())));
        }

        if (event.getRegistry().equals(BuiltInRegistries.ITEM)) {
            FoodBiteRegistry.FOOD_DATA_MAP.forEach((resourceLocation, data) -> {
                Block block = BuiltInRegistries.BLOCK.get(resourceLocation);
                event.register(BuiltInRegistries.ITEM.key(), resourceLocation,
                        () -> new BowlFoodBlockItem(block, data.itemFood()));
            });
        }
    }
}
