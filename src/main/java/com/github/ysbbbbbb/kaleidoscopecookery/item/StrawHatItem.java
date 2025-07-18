package com.github.ysbbbbbb.kaleidoscopecookery.item;

import com.github.ysbbbbbb.kaleidoscopecookery.KaleidoscopeCookery;
import com.github.ysbbbbbb.kaleidoscopecookery.client.model.StrawHatModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class StrawHatItem extends ArmorItem {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(KaleidoscopeCookery.MOD_ID, "textures/models/armor/straw_hat.png");
    private static final ResourceLocation TEXTURE_FLOWER = ResourceLocation.fromNamespaceAndPath(KaleidoscopeCookery.MOD_ID, "textures/models/armor/straw_hat_flower.png");

    private final boolean hasFlower;

    public StrawHatItem(boolean hasFlower) {
        super(ArmorMaterials.LEATHER, ArmorItem.Type.HELMET, new Item.Properties());
        this.hasFlower = hasFlower;
    }

    @Override
    @Nullable
    public ResourceLocation getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, ArmorMaterial.Layer layer, boolean innerModel) {
        if (this.hasFlower) {
            return TEXTURE_FLOWER;
        }
        return TEXTURE;
    }

    @Override
    @SuppressWarnings("all")
    @OnlyIn(Dist.CLIENT)
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private StrawHatModel cachedModel = null;

            @Override
            public int getArmorLayerTintColor(ItemStack stack, LivingEntity entity, ArmorMaterial.Layer layer, int layerIdx, int fallbackColor) {
                if (layerIdx == 0) {
                    return IClientItemExtensions.super.getArmorLayerTintColor(stack, entity, layer, layerIdx, fallbackColor);
                }
                // 必须返回 0，否则会渲染两次帽子
                return 0;
            }

            @Override
            public @NotNull Model getGenericArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<?> original) {
                if (cachedModel == null) {
                    cachedModel = new StrawHatModel(Minecraft.getInstance().getEntityModels().bakeLayer(StrawHatModel.LAYER_LOCATION));
                }
                ModelPart head = cachedModel.getHead();
                head.copyFrom(original.head);
                return cachedModel;
            }
        });
    }
}
