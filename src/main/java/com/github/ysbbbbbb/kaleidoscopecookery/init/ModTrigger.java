package com.github.ysbbbbbb.kaleidoscopecookery.init;

import com.github.ysbbbbbb.kaleidoscopecookery.KaleidoscopeCookery;
import com.github.ysbbbbbb.kaleidoscopecookery.advancements.critereon.ModEventTrigger;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.critereon.DistanceTrigger;
import net.minecraft.resources.ResourceLocation;

public class ModTrigger {
    public static ModEventTrigger EVENT;
    public static DistanceTrigger FLATULENCE_FLY_HEIGHT;

    public static void init() {
        EVENT = CriteriaTriggers.register(modLoc("mod_event"), new ModEventTrigger());
        FLATULENCE_FLY_HEIGHT = CriteriaTriggers.register(modLoc("flatulence_fly_height"), new DistanceTrigger());
    }

    private static String modLoc(String id) {
        return ResourceLocation.fromNamespaceAndPath(KaleidoscopeCookery.MOD_ID, id).toString();
    }
}
