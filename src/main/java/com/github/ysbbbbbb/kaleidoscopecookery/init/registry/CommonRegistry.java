package com.github.ysbbbbbb.kaleidoscopecookery.init.registry;

import com.github.ysbbbbbb.kaleidoscopecookery.KaleidoscopeCookery;
import com.github.ysbbbbbb.kaleidoscopecookery.block.food.FoodBiteBlock;
import com.github.ysbbbbbb.kaleidoscopecookery.init.ModItems;
import com.github.ysbbbbbb.kaleidoscopecookery.init.ModSoupBases;
import com.github.ysbbbbbb.kaleidoscopecookery.item.BowlFoodBlockItem;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ComposterBlock;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.registries.RegisterEvent;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = KaleidoscopeCookery.MOD_ID)
public class CommonRegistry {
    @SubscribeEvent
    public static void onSetupEvent(FMLCommonSetupEvent event) {
        event.enqueueWork(CommonRegistry::addComposter);
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

    private static void addComposter() {
        ComposterBlock.COMPOSTABLES.put(ModItems.TOMATO_SEED.get(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(ModItems.CHILI_SEED.get(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(ModItems.LETTUCE_SEED.get(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(ModItems.WILD_RICE_SEED.get(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(ModItems.RICE_SEED.get(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(ModItems.TOMATO.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(ModItems.RED_CHILI.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(ModItems.GREEN_CHILI.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(ModItems.LETTUCE.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(ModItems.RICE_PANICLE.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(ModItems.CATERPILLAR.get(), 1.0F);
    }
}
