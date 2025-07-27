package com.github.ysbbbbbb.kaleidoscopecookery.client.render.block;

import com.github.ysbbbbbb.kaleidoscopecookery.blockentity.kitchen.PotBlockEntity;
import com.github.ysbbbbbb.kaleidoscopecookery.client.resources.ItemRenderReplacer;
import com.github.ysbbbbbb.kaleidoscopecookery.client.resources.ItemRenderReplacerReloadListener;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

import java.util.List;

public class PotBlockEntityRender implements BlockEntityRenderer<PotBlockEntity> {
    private final BlockEntityRendererProvider.Context context;

    public PotBlockEntityRender(BlockEntityRendererProvider.Context context) {
        this.context = context;
    }

    @Override
    public void render(PotBlockEntity pot, float partialTick, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay) {
        RandomSource source = RandomSource.create(pot.getSeed());
        PotBlockEntity.StirFryAnimationData data = pot.animationData;
        long time = System.currentTimeMillis() - data.timestamp;

        if (data.preSeed == -1L) {
            data.preSeed = pot.getSeed();
        }
        if (data.preSeed != pot.getSeed()) {
            data.preSeed = pot.getSeed();
            if (time > 1000) {
                data.timestamp = System.currentTimeMillis();
                data.randomHeights = new float[9];
                for (int i = 0; i < 9; i++) {
                    data.randomHeights[i] = 0.25f + source.nextFloat() * 1;
                }
            }
        }

        ItemRenderer itemRenderer = this.context.getItemRenderer();
        int rotation = pot.getBlockState().getValue(BlockStateProperties.HORIZONTAL_FACING).get2DDataValue() * 90;

        poseStack.pushPose();
        poseStack.translate(0.5, 0.1, 0.5);
        poseStack.mulPose(Axis.YN.rotationDegrees(rotation));
        poseStack.mulPose(Axis.XN.rotationDegrees(90));
        poseStack.scale(0.5f, 0.5f, 0.5f);

        // 炒菜阶段，或者炒完，但是需要碗才能装的菜，只渲染原材料
        boolean showInputs = pot.getStatus() != PotBlockEntity.FINISHED && pot.getStatus() != PotBlockEntity.BURNT;
        if (showInputs || pot.hasCarrier()) {
            List<ItemStack> items = pot.getInputs();
            for (int i = 0; i < items.size(); i++) {
                ItemStack item = items.get(i);
                if (!item.isEmpty()) {
                    renderItem(pot, poseStack, buffer, packedLight, packedOverlay, source, i, time, data, itemRenderer, item);
                    poseStack.translate(0, 0, 0.025);
                }
            }
        } else {
            // 结束阶段，并且不需要碗的菜，直接渲染结果
            renderItem(pot, poseStack, buffer, packedLight, packedOverlay, source, 0, time, data, itemRenderer, pot.getResult());
        }

        poseStack.popPose();
    }

    private void renderItem(PotBlockEntity pot, PoseStack poseStack, MultiBufferSource buffer,
                            int packedLight, int packedOverlay, RandomSource source, int index,
                            long time, PotBlockEntity.StirFryAnimationData data,
                            ItemRenderer itemRenderer, ItemStack item) {
        poseStack.pushPose();

        int count = 90 + source.nextInt(90);
        poseStack.mulPose(Axis.ZN.rotationDegrees(index * count));
        if (time < 1000) {
            poseStack.translate(0, 0, data.randomHeights[index] * Mth.sin(Mth.PI * time / 1000f));
            poseStack.mulPose(Axis.XN.rotationDegrees(720f / 1000 * time));
        }
        // 焦糊程度，菜变黑
        if (pot.getStatus() == PotBlockEntity.BURNT) {
            int tick = pot.getCurrentTick();
            int burntLevel = Mth.clamp(tick / 25, 0, 16);
            packedLight = OverlayTexture.u(burntLevel);
        }

        BakedModel model = ItemRenderReplacer.getModel(pot.getLevel(), item, ItemRenderReplacerReloadListener.INSTANCE.pot());
        itemRenderer.render(item, ItemDisplayContext.FIXED, false, poseStack, buffer, packedLight, packedOverlay, model);

        poseStack.popPose();
    }
}
