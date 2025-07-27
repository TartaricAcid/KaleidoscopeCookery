package com.github.ysbbbbbb.kaleidoscopecookery.blockentity.kitchen;

import com.github.ysbbbbbb.kaleidoscopecookery.api.blockentity.IKitchenwareRacks;
import com.github.ysbbbbbb.kaleidoscopecookery.blockentity.BaseBlockEntity;
import com.github.ysbbbbbb.kaleidoscopecookery.init.ModBlocks;
import com.github.ysbbbbbb.kaleidoscopecookery.util.ItemUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

public class KitchenwareRacksBlockEntity extends BaseBlockEntity implements IKitchenwareRacks {
    private static final String LEFT_ITEM = "LeftItem";
    private static final String RIGHT_ITEM = "RightItem";

    private ItemStack itemLeft = ItemStack.EMPTY;
    private ItemStack itemRight = ItemStack.EMPTY;

    public KitchenwareRacksBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlocks.KITCHENWARE_RACKS_BE, pPos, pBlockState);
    }

    @Override
    public boolean onClick(LivingEntity user, ItemStack stack, boolean isLeft) {
        ItemStack stackInRacks = isLeft ? itemLeft : itemRight;
        // 取出物品
        if (stack.isEmpty() && !stackInRacks.isEmpty()) {
            ItemUtils.getItemToLivingEntity(user, stackInRacks);
            if (isLeft) {
                itemLeft = ItemStack.EMPTY;
            } else {
                itemRight = ItemStack.EMPTY;
            }
            user.playSound(SoundEvents.ITEM_FRAME_REMOVE_ITEM, 1.0F, 1.0F);
            this.refresh();
            return true;
        }
        // 放入物品
        if (stack.isDamageableItem() && stackInRacks.isEmpty()) {
            if (isLeft) {
                itemLeft = stack.split(1);
            } else {
                itemRight = stack.split(1);
            }
            user.playSound(SoundEvents.ITEM_FRAME_ADD_ITEM, 1.0F, 1.0F);
            this.refresh();
            return true;
        }
        return false;
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.put(LEFT_ITEM, itemLeft.saveOptional(registries));
        tag.put(RIGHT_ITEM, itemRight.saveOptional(registries));
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        this.itemLeft = ItemStack.parseOptional(registries, tag.getCompound(LEFT_ITEM));
        this.itemRight = ItemStack.parseOptional(registries, tag.getCompound(RIGHT_ITEM));
    }

    @Override
    public ItemStack getItemLeft() {
        return itemLeft;
    }

    @Override
    public ItemStack getItemRight() {
        return itemRight;
    }
}
