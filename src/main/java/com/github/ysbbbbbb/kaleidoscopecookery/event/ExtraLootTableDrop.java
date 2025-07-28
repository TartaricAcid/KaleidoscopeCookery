package com.github.ysbbbbbb.kaleidoscopecookery.event;

import com.github.ysbbbbbb.kaleidoscopecookery.init.ModItems;
import com.github.ysbbbbbb.kaleidoscopecookery.init.tag.TagMod;
import com.github.ysbbbbbb.kaleidoscopecookery.loot.AdvanceBlockMatchTool;
import com.github.ysbbbbbb.kaleidoscopecookery.loot.AdvanceEntityMatchTool;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.EmptyLootItem;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.EnchantedCountIncreaseFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

public class ExtraLootTableDrop {
    private static final ResourceLocation HOGLIN = ResourceLocation.fromNamespaceAndPath("minecraft", "entities/hoglin");
    private static final ResourceLocation PIG = ResourceLocation.fromNamespaceAndPath("minecraft", "entities/pig");
    private static final ResourceLocation PIGLIN = ResourceLocation.fromNamespaceAndPath("minecraft", "entities/piglin");
    private static final ResourceLocation PIGLIN_BRUTE = ResourceLocation.fromNamespaceAndPath("minecraft", "entities/piglin_brute");
    private static final ResourceLocation GRASS = ResourceLocation.fromNamespaceAndPath("minecraft", "blocks/short_grass");

    public static void register() {
        LootTableEvents.MODIFY.register((key, tableBuilder, source, wrapperLookup) -> {
            ResourceLocation id = key.location();
            if (id.equals(HOGLIN)) {
                addOilDrop(tableBuilder, 2, wrapperLookup);
            } else if (id.equals(PIG)) {
                addOilDrop(tableBuilder, 1, wrapperLookup);
            } else if (id.equals(PIGLIN)) {
                addOilDrop(tableBuilder, 2, wrapperLookup);
            } else if (id.equals(PIGLIN_BRUTE)) {
                addOilDrop(tableBuilder, 2, wrapperLookup);
            } else if (id.equals(GRASS)) {
                addSeedDrop(tableBuilder, wrapperLookup);
            }
        });
    }

    private static void addOilDrop(LootTable.Builder tableBuilder, int rolls, HolderLookup.Provider registries) {
        // 厨具刀杀猪掉油
        ItemPredicate hasKnife = ItemPredicate.Builder.item().of(TagMod.KITCHEN_KNIFE).build();
        LootItemCondition.Builder toolMatches = AdvanceEntityMatchTool.toolMatches(EquipmentSlot.MAINHAND, hasKnife);
        var count = SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F));
        var looting = EnchantedCountIncreaseFunction.lootingMultiplier(registries, UniformGenerator.between(0.0F, 1.0F));
        var oil = LootItem.lootTableItem(ModItems.OIL).apply(count).apply(looting);
        var empty = EmptyLootItem.emptyItem();

        tableBuilder.withPool(LootPool.lootPool()
                .setRolls(ConstantValue.exactly(rolls))
                .add(oil).add(empty).when(toolMatches));
    }

    private static void addSeedDrop(LootTable.Builder tableBuilder, HolderLookup.Provider registries) {
        HolderLookup.RegistryLookup<Enchantment> enchantment = registries.lookupOrThrow(Registries.ENCHANTMENT);
        // 穿戴草帽掉落番茄辣椒等种子
        var tomato = getSeed(ModItems.TOMATO_SEED, enchantment);
        var chili = getSeed(ModItems.CHILI_SEED, enchantment);
        var lettuce = getSeed(ModItems.LETTUCE_SEED, enchantment);
        var rice = getSeed(ModItems.WILD_RICE_SEED, enchantment);
        var empty = EmptyLootItem.emptyItem().setWeight(2);
        tableBuilder.withPool(LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1))
                .add(tomato).add(chili)
                .add(lettuce).add(rice)
                .add(empty));
    }

    private static LootPoolSingletonContainer.Builder<?> getSeed(ItemLike item, HolderLookup.RegistryLookup<Enchantment> enchantment) {
        ItemPredicate hasHat = ItemPredicate.Builder.item().of(TagMod.STRAW_HAT).build();
        LootItemCondition.Builder hatMatches = AdvanceBlockMatchTool.toolMatches(EquipmentSlot.HEAD, hasHat);
        return LootItem.lootTableItem(item)
                .when(LootItemRandomChanceCondition.randomChance(0.125F)).when(hatMatches)
                .apply(ApplyBonusCount.addUniformBonusCount(enchantment.getOrThrow(Enchantments.FORTUNE), 2));
    }
}
