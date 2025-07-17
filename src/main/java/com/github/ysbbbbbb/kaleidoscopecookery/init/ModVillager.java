package com.github.ysbbbbbb.kaleidoscopecookery.init;

import com.github.ysbbbbbb.kaleidoscopecookery.KaleidoscopeCookery;
import com.google.common.collect.ImmutableSet;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@SuppressWarnings("all")
public class ModVillager {
    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSION = DeferredRegister.create(BuiltInRegistries.VILLAGER_PROFESSION, KaleidoscopeCookery.MOD_ID);

    public static final DeferredHolder<VillagerProfession, VillagerProfession> CHEF = VILLAGER_PROFESSION.register("chef", () -> new VillagerProfession("chef",
            poi -> poi.value() == ModPoi.STOVE.get(),
            poi -> poi.value() == ModPoi.STOVE.get(),
            ImmutableSet.of(), ImmutableSet.of(), SoundEvents.VILLAGER_WORK_BUTCHER));
}
