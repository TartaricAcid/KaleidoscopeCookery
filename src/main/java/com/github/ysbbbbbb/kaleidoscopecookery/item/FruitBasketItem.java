package com.github.ysbbbbbb.kaleidoscopecookery.item;

import com.github.ysbbbbbb.kaleidoscopecookery.init.ModBlocks;
import com.github.ysbbbbbb.kaleidoscopecookery.init.ModDataComponents;
import com.github.ysbbbbbb.kaleidoscopecookery.inventory.tooltip.ItemContainerTooltip;
import com.mojang.serialization.Codec;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;

import java.util.Optional;

public class FruitBasketItem extends BlockItem {
    public FruitBasketItem() {
        super(ModBlocks.FRUIT_BASKET, new Properties().stacksTo(1));
    }

    @Override
    public Optional<TooltipComponent> getTooltipImage(ItemStack stack) {
        if (stack.has(ModDataComponents.FRUIT_BASKET_ITEMS)) {
            ItemContainer handler = stack.get(ModDataComponents.FRUIT_BASKET_ITEMS);
            return Optional.of(new ItemContainerTooltip(handler.items));
        }
        return Optional.empty();
    }

    @Override
    public boolean canFitInsideContainerItems() {
        return false;
    }

    public record ItemContainer(NonNullList<ItemStack> items) {
        public static final Codec<ItemContainer> CODEC = ItemStack.OPTIONAL_CODEC.listOf().xmap(
                list -> {
                    NonNullList<ItemStack> handler = NonNullList.withSize(8, ItemStack.EMPTY);
                    for (int i = 0; i < Math.min(list.size(), handler.size()); i++) {
                        handler.set(i, list.get(i));
                    }
                    return new ItemContainer(handler);
                },
                ItemContainer::items
        );

        public static final StreamCodec<RegistryFriendlyByteBuf, ItemContainer> STREAM_CODEC = new StreamCodec<>() {
            @Override
            public ItemContainer decode(RegistryFriendlyByteBuf buffer) {
                CompoundTag compoundTag = buffer.readNbt();
                NonNullList<ItemStack> handler = NonNullList.withSize(8, ItemStack.EMPTY);
                if (compoundTag != null) {
                    ContainerHelper.loadAllItems(compoundTag, handler, buffer.registryAccess());
                }
                return new ItemContainer(handler);
            }

            @Override
            public void encode(RegistryFriendlyByteBuf buffer, ItemContainer value) {
                CompoundTag compoundTag = ContainerHelper.saveAllItems(new CompoundTag(), value.items, buffer.registryAccess());
                buffer.writeNbt(compoundTag);
            }
        };
    }
}
