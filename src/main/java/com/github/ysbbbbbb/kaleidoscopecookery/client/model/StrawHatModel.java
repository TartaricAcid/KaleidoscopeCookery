package com.github.ysbbbbbb.kaleidoscopecookery.client.model;

import com.github.ysbbbbbb.kaleidoscopecookery.KaleidoscopeCookery;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class StrawHatModel extends EntityModel<Entity> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(KaleidoscopeCookery.MOD_ID, "straw_hat"), "main");
    private final ModelPart head;

    public StrawHatModel(ModelPart root) {
        this.head = root.getChild("head");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0436F, 0.0436F, 0.0436F));

        PartDefinition bone = head.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(-10, 17).addBox(-7.5F, -4.0F, -7.5F, 15.0F, 0.0F, 15.0F, new CubeDeformation(0.01F))
                .texOffs(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 4.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(0, 42).addBox(-4.0F, -7.25F, -4.0F, 8.0F, 4.0F, 8.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(0.3F, 0.5F, 0.0F, -0.0873F, 0.0F, -0.0873F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        poseStack.scale(1.275f, 1.275f, 1.275f);
        head.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    public ModelPart getHead() {
        return head;
    }
}
