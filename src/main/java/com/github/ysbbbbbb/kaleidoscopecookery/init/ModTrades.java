package com.github.ysbbbbbb.kaleidoscopecookery.init;

import com.github.ysbbbbbb.kaleidoscopecookery.init.registry.FoodBiteRegistry;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;

import static com.github.ysbbbbbb.kaleidoscopecookery.init.ModItems.*;
import static net.minecraft.world.item.Items.EMERALD;

public class ModTrades {
    public static void registerTrades() {
        // 注册厨师村民的交易
        TradeOfferHelper.registerVillagerOffers(ModVillager.CHEF, 1, ModTrades::addNoviceTrades);
        TradeOfferHelper.registerVillagerOffers(ModVillager.CHEF, 2, ModTrades::addApprenticeTrades);
        TradeOfferHelper.registerVillagerOffers(ModVillager.CHEF, 3, ModTrades::addJourneymanTrades);
        TradeOfferHelper.registerVillagerOffers(ModVillager.CHEF, 4, ModTrades::addExpertTrades);
        TradeOfferHelper.registerVillagerOffers(ModVillager.CHEF, 5, ModTrades::addMasterTrades);
    }

    // 新手交易（等级1）
    private static void addNoviceTrades(java.util.List<VillagerTrades.ItemListing> factories) {
        factories.add(createTrade(TOMATO, 12, EMERALD, 1, 16, 2, 0.05f));
        factories.add(createTrade(LETTUCE, 16, EMERALD, 1, 16, 2, 0.05f));
        factories.add(createTrade(RICE_SEED, 20, EMERALD, 1, 16, 2, 0.05f));
        factories.add(createTrade(RED_CHILI, 12, EMERALD, 1, 16, 2, 0.05f));
        factories.add(createTrade(GREEN_CHILI, 8, EMERALD, 1, 12, 2, 0.1f));
        factories.add(createTrade(CATERPILLAR, 1, EMERALD, 1, 12, 2, 0.1f));
    }

    // 学徒交易（等级2）
    private static void addApprenticeTrades(java.util.List<VillagerTrades.ItemListing> factories) {
        factories.add(createTrade(EMERALD, 4, KITCHEN_SHOVEL, 1, 12, 4, 0.1f));
        factories.add(createTrade(EMERALD, 8, IRON_KITCHEN_KNIFE, 1, 12, 4, 0.2f));
        factories.add(createTrade(EMERALD, 3, STOCKPOT_LID, 1, 16, 4, 0.1f));
    }

    // 老手交易（等级3）
    private static void addJourneymanTrades(java.util.List<VillagerTrades.ItemListing> factories) {
        factories.add(createTrade(DELICIOUS_EGG_FRIED_RICE, 1, EMERALD, 3, 16, 5, 0.05f));
        factories.add(createTrade(SUSPICIOUS_STIR_FRY_RICE_BOWL, 3, EMERALD, 1, 16, 5, 0.05f));
        factories.add(createTrade(FoodBiteRegistry.getItem(FoodBiteRegistry.DARK_CUISINE), 5, EMERALD, 2, 16, 5, 0.1f));
    }

    // 专家交易（等级4）
    private static void addExpertTrades(java.util.List<VillagerTrades.ItemListing> factories) {
        // 汤类交易
        factories.add(createTrade(PORK_BONE_SOUP, 1, EMERALD, 1, 16, 10, 0.1f));
        factories.add(createTrade(PUFFERFISH_SOUP, 1, EMERALD, 4, 16, 10, 0.1f));
        factories.add(createTrade(SEAFOOD_MISO_SOUP, 1, EMERALD, 1, 16, 10, 0.1f));
        factories.add(createTrade(LAMB_AND_RADISH_SOUP, 1, EMERALD, 2, 16, 10, 0.1f));
        factories.add(createTrade(BRAISED_BEEF_WITH_POTATOES, 1, EMERALD, 2, 16, 10, 0.1f));
        factories.add(createTrade(WILD_MUSHROOM_RABBIT_SOUP, 1, EMERALD, 3, 16, 10, 0.1f));
        factories.add(createTrade(TOMATO_BEEF_BRISKET_SOUP, 1, EMERALD, 2, 16, 10, 0.1f));
        factories.add(createTrade(BORSCHT, 1, EMERALD, 2, 16, 10, 0.1f));
        factories.add(createTrade(BEEF_MEATBALL_SOUP, 1, EMERALD, 3, 16, 10, 0.1f));
        factories.add(createTrade(FEARSOME_THICK_SOUP, 1, EMERALD, 5, 16, 10, 0.1f));
    }

    // 大师交易（等级5）
    private static void addMasterTrades(java.util.List<VillagerTrades.ItemListing> factories) {
        factories.add(new VillagerTrades.EnchantedItemForEmeralds(DIAMOND_KITCHEN_KNIFE, 8, 3, 30, 0.2f));
    }

    private static VillagerTrades.ItemListing createTrade(Item input, int inputCount, Item output, int outputCount,
                                                          int maxTrades, int xp, float priceMultiplier) {
        ItemCost inputStack = new ItemCost(input, inputCount);
        ItemStack outputStack = new ItemStack(output, outputCount);
        return (trader, random) -> new MerchantOffer(inputStack, outputStack, maxTrades, xp, priceMultiplier);
    }
}
