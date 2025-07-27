package com.github.ysbbbbbb.kaleidoscopecookery.event;

import com.github.ysbbbbbb.kaleidoscopecookery.KaleidoscopeCookery;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.fabricmc.fabric.api.loot.v2.LootTableSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.level.storage.loot.LootDataManager;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.entries.LootTableReference;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

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
    }

    private void addTable(LootTable.Builder tableBuilder, String name, int rolls) {
        ResourceLocation id = new ResourceLocation(KaleidoscopeCookery.MOD_ID, name);
        LootPoolSingletonContainer.Builder<?> builder = LootTableReference.lootTableReference(id);
        LootPool.Builder pool = LootPool.lootPool().setRolls(ConstantValue.exactly(rolls)).add(builder);
        tableBuilder.withPool(pool);
    }
}
