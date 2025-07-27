package com.github.ysbbbbbb.kaleidoscopecookery.client.render.entity.layer;

import com.github.ysbbbbbb.kaleidoscopecookery.client.model.ScarecrowModel;
import com.github.ysbbbbbb.kaleidoscopecookery.entity.ScarecrowEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.ParrotModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ParrotRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Parrot;

public class ScarecrowParrotOnShoulderLayer extends RenderLayer<ScarecrowEntity, ScarecrowModel> {
    private final ParrotModel model;

    public ScarecrowParrotOnShoulderLayer(RenderLayerParent<ScarecrowEntity, ScarecrowModel> renderer, EntityModelSet modelSet) {
        super(renderer);
        this.model = new ParrotModel(modelSet.bakeLayer(ModelLayers.PARROT));
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource source, int packedLight, ScarecrowEntity scarecrow, float limbSwing,
                       float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        CompoundTag tag = scarecrow.getShoulderEntity();
        if (tag.isEmpty()) {
            return;
        }
        EntityType.byString(tag.getString("id")).filter(type -> type == EntityType.PARROT).ifPresent(type -> {
            poseStack.pushPose();
            poseStack.translate(0.625F, -1.675F, 0.0625F);
            Parrot.Variant variant = Parrot.Variant.byId(tag.getInt("Variant"));
            VertexConsumer vertexconsumer = source.getBuffer(this.model.renderType(ParrotRenderer.getVariantTexture(variant)));
            this.model.renderOnShoulder(poseStack, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY,
                    limbSwing, limbSwingAmount, netHeadYaw, headPitch, scarecrow.tickCount);
            poseStack.popPose();
        });
    }
}
