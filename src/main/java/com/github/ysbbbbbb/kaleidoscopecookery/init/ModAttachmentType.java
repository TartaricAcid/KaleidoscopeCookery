package com.github.ysbbbbbb.kaleidoscopecookery.init;

import com.github.ysbbbbbb.kaleidoscopecookery.KaleidoscopeCookery;
import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;

@SuppressWarnings("all")
public class ModAttachmentType {
    public static final AttachmentType<Vec3> FLATULENCE_EFFECT_STARTING_POSITION = AttachmentRegistry.createDefaulted(
            ResourceLocation.fromNamespaceAndPath(KaleidoscopeCookery.MOD_ID, "flatulence_effect_starting_position"),
            () -> Vec3.ZERO);
}
