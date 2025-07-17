package com.github.ysbbbbbb.kaleidoscopecookery.init;

import com.github.ysbbbbbb.kaleidoscopecookery.KaleidoscopeCookery;
import com.github.ysbbbbbb.kaleidoscopecookery.item.FruitBasketItem;
import com.mojang.serialization.Codec;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModDataComponents {
    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENT_TYPES = DeferredRegister.create(BuiltInRegistries.DATA_COMPONENT_TYPE, KaleidoscopeCookery.MOD_ID);

    public static final Supplier<DataComponentType<FruitBasketItem.ItemContainer>> FRUIT_BASKET_ITEMS = DATA_COMPONENT_TYPES.register("fruit_basket_items", () ->
            DataComponentType.<FruitBasketItem.ItemContainer>builder()
                    .persistent(FruitBasketItem.ItemContainer.CODEC)
                    .networkSynchronized(FruitBasketItem.ItemContainer.STREAM_CODEC)
                    .build());

    public static final Supplier<DataComponentType<Boolean>> KITCHEN_SHOVEL_HAS_OIL = DATA_COMPONENT_TYPES.register("kitchen_shovel_has_oil", () ->
            DataComponentType.<Boolean>builder()
                    .persistent(Codec.BOOL)
                    .networkSynchronized(ByteBufCodecs.BOOL)
                    .build());
}
