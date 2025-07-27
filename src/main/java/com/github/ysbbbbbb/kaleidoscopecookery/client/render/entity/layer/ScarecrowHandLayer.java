package com.github.ysbbbbbb.kaleidoscopecookery.client.render.entity.layer;

import com.github.ysbbbbbb.kaleidoscopecookery.client.model.ScarecrowModel;
import com.github.ysbbbbbb.kaleidoscopecookery.client.render.entity.ScarecrowRender;
import com.github.ysbbbbbb.kaleidoscopecookery.entity.ScarecrowEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.LanternBlock;
import net.minecraft.world.level.block.state.BlockState;

public class ScarecrowHandLayer extends ItemInHandLayer<ScarecrowEntity, ScarecrowModel> {
    private final ItemInHandRenderer itemRenderer;
    private final BlockRenderDispatcher blockRenderer;

    public ScarecrowHandLayer(ScarecrowRender entityRenderer, ItemInHandRenderer itemRenderer, BlockRenderDispatcher blockRenderer) {
        super(entityRenderer, itemRenderer);
        this.itemRenderer = itemRenderer;
        this.blockRenderer = blockRenderer;
    }

    @Override
    protected void renderArmWithItem(LivingEntity entity, ItemStack stack, ItemDisplayContext context, HumanoidArm arm,
                                     PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        if (!stack.isEmpty()) {
            poseStack.pushPose();
            this.getParentModel().translateToHand(arm, poseStack);
            poseStack.mulPose(Axis.XP.rotationDegrees(-90.0F));
            poseStack.mulPose(Axis.YP.rotationDegrees(180.0F));
            boolean isLeft = arm == HumanoidArm.LEFT;
            if (isLeft) {
                if (stack.getItem() instanceof BlockItem blockItem && blockItem.getBlock() instanceof LanternBlock lanternBlock) {
                    poseStack.translate(-0.375, 0.375, -2);
                    poseStack.mulPose(Axis.XP.rotationDegrees(90));
                    BlockState blockState = lanternBlock.defaultBlockState();
                    poseStack.scale(0.75F, 0.75F, 0.75F);
                    this.blockRenderer.renderSingleBlock(blockState, poseStack, bufferSource, LightTexture.FULL_BRIGHT, OverlayTexture.NO_OVERLAY);
                    poseStack.popPose();
                }
            } else {
                poseStack.translate(0.125, 0, -1.375);
                poseStack.mulPose(Axis.ZP.rotationDegrees(-90));
                poseStack.mulPose(Axis.XP.rotationDegrees(85));
                poseStack.scale(0.75F, 0.75F, 0.75F);
                this.itemRenderer.renderItem(entity, stack, context, isLeft, poseStack, bufferSource, packedLight);
                poseStack.popPose();
            }
        }
    }
}
