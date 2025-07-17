package com.github.ysbbbbbb.kaleidoscopecookery.event;

import com.github.ysbbbbbb.kaleidoscopecookery.KaleidoscopeCookery;
import com.github.ysbbbbbb.kaleidoscopecookery.init.ModVillager;
import com.github.ysbbbbbb.kaleidoscopecookery.init.registry.FoodBiteRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.BasicItemListing;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;
import net.neoforged.neoforge.registries.DeferredItem;

import static com.github.ysbbbbbb.kaleidoscopecookery.init.ModItems.*;
import static net.minecraft.world.item.Items.EMERALD;

@EventBusSubscriber(modid = KaleidoscopeCookery.MOD_ID)
public class ModTradesEvent {
    @SubscribeEvent
    public static void onTradesEvent(VillagerTradesEvent event) {
        if (event.getType() == ModVillager.CHEF.get()) {
            addNoviceTrades(event);
            addApprenticeTrades(event);
            addJourneymanTrades(event);
            addExpertTrades(event);
            addMasterTrades(event);
        }
    }

    // 新手交易（等级1）
    private static void addNoviceTrades(VillagerTradesEvent event) {
        addTrade(event, 1, TOMATO.get(), 12, EMERALD, 1, 16, 2, 0.05f);
        addTrade(event, 1, RED_CHILI.get(), 12, EMERALD, 1, 16, 2, 0.05f);
        addTrade(event, 1, GREEN_CHILI.get(), 8, EMERALD, 1, 12, 2, 0.1f);
        addTrade(event, 1, CATERPILLAR.get(), 1, EMERALD, 1, 12, 2, 0.1f);
    }

    // 学徒交易（等级2）
    private static void addApprenticeTrades(VillagerTradesEvent event) {
        addTrade(event, 2, EMERALD, 4, KITCHEN_SHOVEL.get(), 1, 12, 4, 0.1f);
        addTrade(event, 2, EMERALD, 8, IRON_KITCHEN_KNIFE.get(), 1, 12, 4, 0.2f);
        addTrade(event, 2, EMERALD, 3, STOCKPOT_LID.get(), 1, 16, 4, 0.1f);
    }

    // 老手交易（等级3）
    private static void addJourneymanTrades(VillagerTradesEvent event) {
        addTrade(event, 3, DELICIOUS_EGG_FRIED_RICE.get(), 1, EMERALD, 3, 16, 5, 0.05f);
        addTrade(event, 3, SUSPICIOUS_STEW_RICE_BOWL.get(), 3, EMERALD, 1, 16, 5, 0.05f);
        addTrade(event, 3, FoodBiteRegistry.getItem(FoodBiteRegistry.DARK_CUISINE), 5, EMERALD, 2, 16, 5, 0.1f);
    }

    // 专家交易（等级4）
    private static void addExpertTrades(VillagerTradesEvent event) {
        // 汤类交易
        addTrade(event, 4, PORK_BONE_SOUP.get(), 1, EMERALD, 1, 16, 10, 0.1f);
        addTrade(event, 4, PUFFERFISH_SOUP.get(), 1, EMERALD, 4, 16, 10, 0.1f);
        addTrade(event, 4, SEAFOOD_MISO_SOUP.get(), 1, EMERALD, 1, 16, 10, 0.1f);
        addTrade(event, 4, LAMB_AND_RADISH_SOUP.get(), 1, EMERALD, 2, 16, 10, 0.1f);
        addTrade(event, 4, BRAISED_BEEF_WITH_POTATOES.get(), 1, EMERALD, 2, 16, 10, 0.1f);
        addTrade(event, 4, WILD_MUSHROOM_RABBIT_SOUP.get(), 1, EMERALD, 3, 16, 10, 0.1f);
        addTrade(event, 4, TOMATO_BEEF_BRISKET_SOUP.get(), 1, EMERALD, 2, 16, 10, 0.1f);
        addTrade(event, 4, BORSCHT.get(), 1, EMERALD, 2, 16, 10, 0.1f);
        addTrade(event, 4, BEEF_MEATBALL_SOUP.get(), 1, EMERALD, 3, 16, 10, 0.1f);
        addTrade(event, 4, FEARSOME_THICK_SOUP.get(), 1, EMERALD, 5, 16, 10, 0.1f);
    }

    // 大师交易（等级5）
    private static void addMasterTrades(VillagerTradesEvent event) {
        var lookup = event.getRegistryAccess().lookupOrThrow(Registries.ENCHANTMENT);
        ItemStack enchantedDiamondKnife = new ItemStack(DIAMOND_KITCHEN_KNIFE.get());
        enchantedDiamondKnife.enchant(lookup.getOrThrow(Enchantments.UNBREAKING), 3);
        enchantedDiamondKnife.enchant(lookup.getOrThrow(Enchantments.SHARPNESS), 2);
        addTrade(event, 5, EMERALD, 35, enchantedDiamondKnife, 1, 5, 0, 0.2f);
    }

    // 辅助方法：添加交易的通用方法
    private static void addTrade(VillagerTradesEvent event, int level, Object inputItem, int inputCount,
                                 Object outputItem, int outputCount, int maxUses,
                                 int villagerXp, float priceMultiplier) {
        ItemStack input = createItemStack(inputItem, inputCount);
        ItemStack output = createItemStack(outputItem, outputCount);
        BasicItemListing listing = new BasicItemListing(input, output, maxUses, villagerXp, priceMultiplier);
        event.getTrades().get(level).add(listing);
    }

    /**
     * 辅助方法：创建 ItemStack 的通用方法
     */
    private static ItemStack createItemStack(Object item, int count) {
        if (item instanceof Item itemInstance) {
            return new ItemStack(itemInstance, count);
        } else if (item instanceof DeferredItem<?> registryObject) {
            return new ItemStack(registryObject.get(), count);
        } else if (item instanceof ItemStack itemStack) {
            return itemStack.copyWithCount(count);
        }
        throw new IllegalArgumentException("Unsupported item type: " + item.getClass());
    }
}
