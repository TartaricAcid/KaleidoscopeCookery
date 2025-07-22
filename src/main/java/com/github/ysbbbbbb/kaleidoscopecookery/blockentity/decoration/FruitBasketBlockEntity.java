package com.github.ysbbbbbb.kaleidoscopecookery.blockentity.decoration;

import com.github.ysbbbbbb.kaleidoscopecookery.blockentity.BaseBlockEntity;
import com.github.ysbbbbbb.kaleidoscopecookery.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemHandlerHelper;
import net.neoforged.neoforge.items.ItemStackHandler;

public class FruitBasketBlockEntity extends BaseBlockEntity {
    public static final String ITEMS = "BasketItems";
    private final ItemStackHandler items = new ItemStackHandler(8);

    public FruitBasketBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlocks.FRUIT_BASKET_BE.get(), pPos, pBlockState);
    }

    public void putOn(ItemStack stack) {
        if (!stack.getItem().canFitInsideContainerItems()) {
            return;
        }
        ItemStack reminder = ItemHandlerHelper.insertItemStacked(this.items, stack.copy(), false);
        if (stack.getCount() != reminder.getCount()) {
            stack.shrink(stack.getCount() - reminder.getCount());
            if (this.level != null) {
                this.level.playSound(null, this.worldPosition, SoundEvents.ITEM_FRAME_ADD_ITEM, SoundSource.BLOCKS);
            }
            this.refresh();
        }
    }

    public void takeOut(Player player) {
        for (int i = 0; i < items.getSlots(); i++) {
            ItemStack stack = items.getStackInSlot(i);
            if (!stack.isEmpty()) {
                ItemStack extractItem = items.extractItem(i, items.getSlotLimit(i), false);
                ItemHandlerHelper.giveItemToPlayer(player, extractItem);
                if (this.level != null) {
                    this.level.playSound(null, this.worldPosition, SoundEvents.ITEM_FRAME_REMOVE_ITEM, SoundSource.BLOCKS);
                }
                this.refresh();
                return;
            }
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.put(ITEMS, this.items.serializeNBT(registries));
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        this.items.deserializeNBT(registries, tag.getCompound(ITEMS));
    }

    public ItemStackHandler getItems() {
        return items;
    }

    public void setItems(ItemStackHandler items, RegistryAccess access) {
        this.items.deserializeNBT(access, items.serializeNBT(access));
        this.refresh();
    }
}
