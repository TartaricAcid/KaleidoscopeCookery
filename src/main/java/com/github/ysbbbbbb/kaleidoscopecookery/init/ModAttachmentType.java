package com.github.ysbbbbbb.kaleidoscopecookery.init;

import com.github.ysbbbbbb.kaleidoscopecookery.KaleidoscopeCookery;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class ModAttachmentType {
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, KaleidoscopeCookery.MOD_ID);

    public static final Supplier<AttachmentType<Vec3>> FLATULENCE_EFFECT_STARTING_POSITION = ATTACHMENT_TYPES.register("flatulence_effect_starting_position",
            () -> AttachmentType.builder(() -> Vec3.ZERO).build());
}
