package com.github.ysbbbbbb.kaleidoscopecookery.client.render.item;

import com.github.ysbbbbbb.kaleidoscopecookery.KaleidoscopeCookery;
import com.github.ysbbbbbb.kaleidoscopecookery.client.model.StrawHatModel;
import com.github.ysbbbbbb.kaleidoscopecookery.item.StrawHatItem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class StrawHatArmorRenderer implements ArmorRenderer {
    private static final ResourceLocation NORMAL = ResourceLocation.fromNamespaceAndPath(KaleidoscopeCookery.MOD_ID, "textures/models/armor/straw_hat.png");
    private static final ResourceLocation FLOWER = ResourceLocation.fromNamespaceAndPath(KaleidoscopeCookery.MOD_ID, "textures/models/armor/straw_hat_flower.png");
    private StrawHatModel cachedModel = null;

    @Override
    public void render(PoseStack matrices, MultiBufferSource vertexConsumers, ItemStack stack, LivingEntity entity,
                       EquipmentSlot slot, int light, HumanoidModel<LivingEntity> contextModel) {
        if (cachedModel == null) {
            cachedModel = new StrawHatModel(Minecraft.getInstance().getEntityModels().bakeLayer(StrawHatModel.LAYER_LOCATION));
        }
        ModelPart head = cachedModel.getHead();
        ResourceLocation texture = getArmorTexture(stack);
        head.copyFrom(contextModel.head);
        ArmorRenderer.renderPart(matrices, vertexConsumers, light, stack, cachedModel, texture);
    }

    public ResourceLocation getArmorTexture(ItemStack stack) {
        if (stack.getItem() instanceof StrawHatItem hatItem && hatItem.hasFlower()) {
            return FLOWER;
        }
        return NORMAL;
    }
}
