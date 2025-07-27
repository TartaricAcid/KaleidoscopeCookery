package com.github.ysbbbbbb.kaleidoscopecookery.crafting.soupbase;

import com.github.ysbbbbbb.kaleidoscopecookery.api.client.render.ISoupBaseRender;
import com.github.ysbbbbbb.kaleidoscopecookery.api.recipe.soupbase.ISoupBase;
import com.github.ysbbbbbb.kaleidoscopecookery.client.render.soupbase.FluidSoupBaseRender;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariantAttributes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.phys.Vec3;

public class FluidSoupBase implements ISoupBase {
    protected final ResourceLocation name;
    protected final Item bucketItem;
    protected final Fluid fluid;
    protected final int bubbleColor;

    public FluidSoupBase(ResourceLocation name, Item bucketItem, int bubbleColor) {
        this.name = name;
        this.bucketItem = bucketItem;
        if (bucketItem instanceof BucketItem bucket) {
            this.fluid = bucket.content;
        } else {
            throw new IllegalArgumentException("Item must be a bucket item!");
        }
        this.bubbleColor = bubbleColor;
    }

    @Override
    public ResourceLocation getName() {
        return name;
    }

    @Override
    public int getBubbleColor() {
        return bubbleColor;
    }

    @Override
    public ItemStack getDisplayStack() {
        return bucketItem.getDefaultInstance();
    }

    @Override
    public boolean isSoupBase(ItemStack stack) {
        return stack.is(bucketItem);
    }

    @SuppressWarnings("all")
    @Override
    public ItemStack getReturnContainer(Level level, LivingEntity user, ItemStack soupBase) {
        FluidVariant fluidVariant = FluidVariant.of(fluid);
        SoundEvent sound = FluidVariantAttributes.getEmptySound(fluidVariant);
        if (sound != null) {
            Vec3 position = user.position();
            level.playSound(null, position.x(), position.y() + 0.5, position.z(),
                    sound, SoundSource.BLOCKS, 1, 1);
        }
        return new ItemStack(Items.BUCKET);
    }

    @Override
    public boolean isContainer(ItemStack stack) {
        return stack.is(Items.BUCKET);
    }

    @SuppressWarnings("all")
    @Override
    public ItemStack getReturnSoupBase(Level level, LivingEntity user, ItemStack container) {
        FluidVariant fluidVariant = FluidVariant.of(fluid);
        SoundEvent sound = FluidVariantAttributes.getFillSound(fluidVariant);
        if (sound != null) {
            Vec3 position = user.position();
            level.playSound(null, position.x(), position.y() + 0.5, position.z(),
                    sound, SoundSource.BLOCKS, 1, 1);
        }
        return this.bucketItem.getDefaultInstance();
    }

    public Fluid getFluid() {
        return fluid;
    }

    @Override
    public ISoupBaseRender getRender() {
        return new FluidSoupBaseRender(this.fluid);
    }
}
