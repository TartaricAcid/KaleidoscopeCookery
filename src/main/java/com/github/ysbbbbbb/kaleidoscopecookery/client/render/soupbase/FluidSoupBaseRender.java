package com.github.ysbbbbbb.kaleidoscopecookery.client.render.soupbase;

import com.github.ysbbbbbb.kaleidoscopecookery.api.client.render.ISoupBaseRender;
import com.github.ysbbbbbb.kaleidoscopecookery.blockentity.kitchen.StockpotBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandler;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;

public class FluidSoupBaseRender implements ISoupBaseRender {
    private final Fluid fluid;

    public FluidSoupBaseRender(Fluid fluid) {
        this.fluid = fluid;
    }

    @Override
    public void renderWhenPutIngredient(StockpotBlockEntity stockpot, float partialTick, PoseStack poseStack,
                                        MultiBufferSource buffer, int packedLight, int packedOverlay,
                                        float soupHeight) {
        ISoupBaseRender.renderSurface(getStillFluidSprite(fluid), getFluidColor(fluid), poseStack, buffer, packedLight, soupHeight);
    }

    @Override
    public void renderWhenCooking(StockpotBlockEntity stockpot, float partialTick, PoseStack poseStack,
                                  MultiBufferSource buffer, int packedLight, int packedOverlay,
                                  ResourceLocation cookingTexture, float soupHeight) {
        TextureAtlas atlas = Minecraft.getInstance().getModelManager().getAtlas(InventoryMenu.BLOCK_ATLAS);
        TextureAtlasSprite sprite = atlas.getSprite(cookingTexture);
        ISoupBaseRender.renderSurface(sprite, 0xFFFFFFFF, poseStack, buffer, packedLight, soupHeight);
    }

    @Override
    public void renderWhenFinished(StockpotBlockEntity stockpot, float partialTick, PoseStack poseStack,
                                   MultiBufferSource buffer, int packedLight, int packedOverlay,
                                   ResourceLocation finishedTexture, float soupHeight) {
        TextureAtlas atlas = Minecraft.getInstance().getModelManager().getAtlas(InventoryMenu.BLOCK_ATLAS);
        TextureAtlasSprite sprite = atlas.getSprite(finishedTexture);
        ISoupBaseRender.renderSurface(sprite, 0xFFFFFFFF, poseStack, buffer, packedLight, soupHeight);
    }

    private TextureAtlasSprite getStillFluidSprite(Fluid fluid) {
        FluidRenderHandler renderHandler = FluidRenderHandlerRegistry.INSTANCE.get(fluid);
        if (renderHandler != null) {
            FluidState fluidState = fluid.defaultFluidState();
            TextureAtlasSprite[] sprites = renderHandler.getFluidSprites(null, null, fluidState);
            if (sprites != null && sprites.length > 0) {
                return sprites[0];
            }
        }
        // 如果没有找到渲染处理器，使用默认水纹理作为后备
        TextureAtlas atlas = Minecraft.getInstance().getModelManager().getAtlas(InventoryMenu.BLOCK_ATLAS);
        return atlas.getSprite(new ResourceLocation("minecraft", "block/water_still"));
    }

    private int getFluidColor(Fluid fluid) {
        FluidRenderHandler renderHandler = FluidRenderHandlerRegistry.INSTANCE.get(fluid);
        if (renderHandler != null) {
            FluidState fluidState = fluid.defaultFluidState();
            return renderHandler.getFluidColor(null, null, fluidState);
        }
        // 默认颜色（白色）
        return 0xFFFFFFFF;
    }
}
