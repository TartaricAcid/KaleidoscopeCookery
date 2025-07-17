package com.github.ysbbbbbb.kaleidoscopecookery.client.render.block;

import com.github.ysbbbbbb.kaleidoscopecookery.KaleidoscopeCookery;
import com.github.ysbbbbbb.kaleidoscopecookery.blockentity.decoration.ChairBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;

import java.util.function.Function;

public class ChairBlockEntityRender implements BlockEntityRenderer<ChairBlockEntity> {
    private static final Function<DyeColor, ModelResourceLocation> CACHE_MODEL = Util.memoize(color -> {
        ResourceLocation location = ResourceLocation.fromNamespaceAndPath(KaleidoscopeCookery.MOD_ID, "block/carpet/chair/" + color.getName());
        return ModelResourceLocation.standalone(location);
    });

    private final BlockEntityRendererProvider.Context context;

    public ChairBlockEntityRender(BlockEntityRendererProvider.Context context) {
        this.context = context;
    }

    @Override
    public void render(ChairBlockEntity chair, float pPartialTick, PoseStack poseStack,
                       MultiBufferSource buffer, int packedLight, int packedOverlay) {
        ItemRenderer itemRenderer = this.context.getItemRenderer();
        ModelResourceLocation cacheModel = CACHE_MODEL.apply(chair.getColor());

        poseStack.pushPose();
        int rotation = chair.getBlockState().getValue(HorizontalDirectionalBlock.FACING).getOpposite().get2DDataValue();
        poseStack.translate(0.5, 0, 0.5);
        poseStack.mulPose(Axis.YP.rotationDegrees(-rotation * 90));
        poseStack.translate(-0.5, 0, -0.5);
        BakedModel model = itemRenderer.getItemModelShaper().getModelManager().getModel(cacheModel);
        RenderType renderType = RenderType.entityCutoutNoCull(InventoryMenu.BLOCK_ATLAS);
        VertexConsumer vertexConsumer = ItemRenderer.getFoilBufferDirect(buffer, renderType, true, false);
        itemRenderer.renderModelLists(model, ItemStack.EMPTY, packedLight, packedOverlay, poseStack, vertexConsumer);
        poseStack.popPose();
    }
}
