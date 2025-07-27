package com.github.ysbbbbbb.kaleidoscopecookery.event;

import com.github.ysbbbbbb.kaleidoscopecookery.KaleidoscopeCookery;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.NestedLootTable;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

public class ExtraLootTableDrop {
    private static final ResourceLocation HOGLIN = ResourceLocation.fromNamespaceAndPath("minecraft", "entities/hoglin");
    private static final ResourceLocation PIG = ResourceLocation.fromNamespaceAndPath("minecraft", "entities/pig");
    private static final ResourceLocation PIGLIN = ResourceLocation.fromNamespaceAndPath("minecraft", "entities/piglin");
    private static final ResourceLocation PIGLIN_BRUTE = ResourceLocation.fromNamespaceAndPath("minecraft", "entities/piglin_brute");
    private static final ResourceLocation GRASS = ResourceLocation.fromNamespaceAndPath("minecraft", "blocks/grass");

    public static void register() {
        LootTableEvents.MODIFY.register((key, tableBuilder, source, wrapperLookup) -> {
            ResourceLocation id = key.location();
            if (id.equals(HOGLIN)) {
                addTable(tableBuilder, "hoglin", 2);
            } else if (id.equals(PIG)) {
                addTable(tableBuilder, "pig", 1);
            } else if (id.equals(PIGLIN)) {
                addTable(tableBuilder, "piglin", 2);
            } else if (id.equals(PIGLIN_BRUTE)) {
                addTable(tableBuilder, "piglin_brute", 2);
            } else if (id.equals(GRASS)) {
                addTable(tableBuilder, "straw_hat_seed_drop", 1);
            }
        });
    }

    private static void addTable(LootTable.Builder tableBuilder, String name, int rolls) {
        ResourceKey<LootTable> lootTableKey = ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.fromNamespaceAndPath(KaleidoscopeCookery.MOD_ID, name));
        NestedLootTable.Builder<?> builder = NestedLootTable.lootTableReference(lootTableKey);
        LootPool.Builder pool = LootPool.lootPool().setRolls(ConstantValue.exactly(rolls)).add(builder);
        tableBuilder.withPool(pool);
    }
}
