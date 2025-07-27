package com.github.ysbbbbbb.kaleidoscopecookery.client.render.block;

import com.github.ysbbbbbb.kaleidoscopecookery.blockentity.kitchen.KitchenwareRacksBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class KitchenwareRacksBlockEntityRender implements BlockEntityRenderer<KitchenwareRacksBlockEntity> {
    private final BlockEntityRendererProvider.Context context;

    public KitchenwareRacksBlockEntityRender(BlockEntityRendererProvider.Context context) {
        this.context = context;
    }

    @Override
    public void render(KitchenwareRacksBlockEntity blockEntity, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        ItemStack itemLeft = blockEntity.getItemLeft();
        ItemStack itemRight = blockEntity.getItemRight();

        int rotation = blockEntity.getBlockState().getValue(BlockStateProperties.HORIZONTAL_FACING).get2DDataValue() * 90;
        poseStack.translate(0.5, 0, 0.5);
        poseStack.mulPose(Axis.YN.rotationDegrees(rotation));

        if (!itemLeft.isEmpty()) {
            poseStack.pushPose();
            poseStack.translate(-0.2, 0.4375, -0.3);
            this.renderItem(blockEntity, poseStack, bufferSource, packedLight, packedOverlay, itemLeft);
            poseStack.popPose();
        }

        if (!itemRight.isEmpty()) {
            poseStack.pushPose();
            poseStack.translate(0.2, 0.4375, -0.3);
            this.renderItem(blockEntity, poseStack, bufferSource, packedLight, packedOverlay, itemRight);
            poseStack.popPose();
        }
    }

    private void renderItem(KitchenwareRacksBlockEntity blockEntity, PoseStack poseStack, MultiBufferSource bufferSource,
                            int packedLight, int packedOverlay, ItemStack itemStack) {
        poseStack.scale(0.75f, 0.75f, 0.75f);
        poseStack.mulPose(Axis.XN.rotationDegrees(180));
        poseStack.mulPose(Axis.YN.rotationDegrees(-25));
        poseStack.mulPose(Axis.ZN.rotationDegrees(45));
        context.getItemRenderer().renderStatic(itemStack, ItemDisplayContext.FIXED, packedLight, packedOverlay,
                poseStack, bufferSource, blockEntity.getLevel(), 0);
    }
}
