package com.github.ysbbbbbb.kaleidoscopecookery.init;

import com.github.ysbbbbbb.kaleidoscopecookery.KaleidoscopeCookery;
import com.google.common.collect.ImmutableSet;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.npc.VillagerProfession;

@SuppressWarnings("all")
public class ModVillager {
    public static final VillagerProfession CHEF = new VillagerProfession("chef",
            poi -> poi.value() == ModPoi.STOVE,
            poi -> poi.value() == ModPoi.STOVE,
            ImmutableSet.of(), ImmutableSet.of(), SoundEvents.VILLAGER_WORK_BUTCHER);

    public static void registerVillagerProfessions() {
        Registry.register(BuiltInRegistries.VILLAGER_PROFESSION, ResourceLocation.fromNamespaceAndPath(KaleidoscopeCookery.MOD_ID, "chef"), CHEF);
    }
}
