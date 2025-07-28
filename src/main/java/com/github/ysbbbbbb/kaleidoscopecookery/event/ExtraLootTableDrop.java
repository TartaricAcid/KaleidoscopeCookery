package com.github.ysbbbbbb.kaleidoscopecookery.event;

import com.github.ysbbbbbb.kaleidoscopecookery.init.ModItems;
import com.github.ysbbbbbb.kaleidoscopecookery.init.tag.TagMod;
import com.github.ysbbbbbb.kaleidoscopecookery.loot.AdvanceBlockMatchTool;
import com.github.ysbbbbbb.kaleidoscopecookery.loot.AdvanceEntityMatchTool;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.fabricmc.fabric.api.loot.v2.LootTableSource;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.storage.loot.LootDataManager;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.EmptyLootItem;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.LootingEnchantFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

public class ExtraLootTableDrop implements LootTableEvents.Modify {
    private static final ResourceLocation HOGLIN = new ResourceLocation("minecraft", "entities/hoglin");
    private static final ResourceLocation PIG = new ResourceLocation("minecraft", "entities/pig");
    private static final ResourceLocation PIGLIN = new ResourceLocation("minecraft", "entities/piglin");
    private static final ResourceLocation PIGLIN_BRUTE = new ResourceLocation("minecraft", "entities/piglin_brute");
    private static final ResourceLocation GRASS = new ResourceLocation("minecraft", "blocks/grass");

    public static void register() {
        LootTableEvents.MODIFY.register(new ExtraLootTableDrop());
    }

    @Override
    public void modifyLootTable(ResourceManager resourceManager, LootDataManager lootManager,
                                ResourceLocation id, LootTable.Builder tableBuilder,
                                LootTableSource source) {
        if (id.equals(HOGLIN)) {
            addOilDrop(tableBuilder, 2);
        } else if (id.equals(PIG)) {
            addOilDrop(tableBuilder, 1);
        } else if (id.equals(PIGLIN)) {
            addOilDrop(tableBuilder, 2);
        } else if (id.equals(PIGLIN_BRUTE)) {
            addOilDrop(tableBuilder, 2);
        } else if (id.equals(GRASS)) {
            addSeedDrop(tableBuilder);
        }
    }

    private void addOilDrop(LootTable.Builder tableBuilder, int rolls) {
        // 厨具刀杀猪掉油
        ItemPredicate hasKnife = ItemPredicate.Builder.item().of(TagMod.KITCHEN_KNIFE).build();
        LootItemCondition.Builder toolMatches = AdvanceEntityMatchTool.toolMatches(EquipmentSlot.MAINHAND, hasKnife);
        var count = SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F));
        var looting = LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F));
        var oil = LootItem.lootTableItem(ModItems.OIL).apply(count).apply(looting);
        var empty = EmptyLootItem.emptyItem();

        tableBuilder.withPool(LootPool.lootPool()
                .setRolls(ConstantValue.exactly(rolls))
                .add(oil).add(empty).when(toolMatches));
    }

    private void addSeedDrop(LootTable.Builder tableBuilder) {
        // 穿戴草帽掉落番茄辣椒等种子
        var tomato = getSeed(ModItems.TOMATO_SEED);
        var chili = getSeed(ModItems.CHILI_SEED);
        var lettuce = getSeed(ModItems.LETTUCE_SEED);
        var rice = getSeed(ModItems.WILD_RICE_SEED);
        var empty = EmptyLootItem.emptyItem().setWeight(2);
        tableBuilder.withPool(LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1))
                .add(tomato).add(chili)
                .add(lettuce).add(rice)
                .add(empty));
    }

    private LootPoolSingletonContainer.Builder<?> getSeed(ItemLike item) {
        ItemPredicate hasHat = ItemPredicate.Builder.item().of(TagMod.STRAW_HAT).build();
        LootItemCondition.Builder hatMatches = AdvanceBlockMatchTool.toolMatches(EquipmentSlot.HEAD, hasHat);
        return LootItem.lootTableItem(item)
                .when(LootItemRandomChanceCondition.randomChance(0.125F)).when(hatMatches)
                .apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE, 2));
    }
}
