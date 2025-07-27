package com.github.ysbbbbbb.kaleidoscopecookery.datagen.advancement;

import com.github.ysbbbbbb.kaleidoscopecookery.KaleidoscopeCookery;
import com.github.ysbbbbbb.kaleidoscopecookery.init.ModTrigger;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.critereon.DistancePredicate;
import net.minecraft.advancements.critereon.DistanceTrigger;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.LocationPredicate;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;

public class AdvancementTools {
    public static final ResourceLocation BG = new ResourceLocation(KaleidoscopeCookery.MOD_ID, "textures/advancement/background.png");

    public static Advancement.Builder makeTask(ItemLike item, String key) {
        MutableComponent title = Component.translatable("advancements.kaleidoscope_cookery.%s.title".formatted(key));
        MutableComponent desc = Component.translatable("advancements.kaleidoscope_cookery.%s.description".formatted(key));
        return Advancement.Builder.advancement().display(item, title, desc, BG,
                FrameType.TASK, true, true, false);
    }

    public static Advancement.Builder makeChallenge(ItemLike item, String key) {
        MutableComponent title = Component.translatable("advancements.kaleidoscope_cookery.%s.title".formatted(key));
        MutableComponent desc = Component.translatable("advancements.kaleidoscope_cookery.%s.description".formatted(key));
        return Advancement.Builder.advancement().display(item, title, desc, BG,
                FrameType.CHALLENGE, true, true, true);
    }

    public static Advancement.Builder makeGoal(ItemLike item, String key) {
        MutableComponent title = Component.translatable("advancements.kaleidoscope_cookery.%s.title".formatted(key));
        MutableComponent desc = Component.translatable("advancements.kaleidoscope_cookery.%s.description".formatted(key));
        return Advancement.Builder.advancement().display(item, title, desc, BG,
                FrameType.GOAL, true, true, false);
    }

    public static DistanceTrigger.TriggerInstance flatulenceFlyHeight(EntityPredicate.Builder player, DistancePredicate distance, LocationPredicate startPosition) {
        return new DistanceTrigger.TriggerInstance(ModTrigger.FLATULENCE_FLY_HEIGHT.getId(), EntityPredicate.wrap(player.build()), startPosition, distance);
    }

    public static String modLoc(String id) {
        return new ResourceLocation(KaleidoscopeCookery.MOD_ID, id).toString();
    }
}
