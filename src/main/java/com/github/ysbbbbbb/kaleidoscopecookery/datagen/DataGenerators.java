package com.github.ysbbbbbb.kaleidoscopecookery.datagen;

import com.github.ysbbbbbb.kaleidoscopecookery.datagen.model.BlockModelGenerator;
import com.github.ysbbbbbb.kaleidoscopecookery.datagen.model.BlockStateGenerator;
import com.github.ysbbbbbb.kaleidoscopecookery.datagen.model.ItemModelGenerator;
import com.github.ysbbbbbb.kaleidoscopecookery.datagen.tag.TagBlock;
import com.github.ysbbbbbb.kaleidoscopecookery.datagen.tag.TagEntityType;
import com.github.ysbbbbbb.kaleidoscopecookery.datagen.tag.TagItem;
import com.github.ysbbbbbb.kaleidoscopecookery.datagen.tag.TagPoiType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        var generator = event.getGenerator();
        var registries = event.getLookupProvider();
        var vanillaPack = generator.getVanillaPack(true);
        var helper = event.getExistingFileHelper();
        var pack = generator.getPackOutput();

        var block = vanillaPack.addProvider(packOutput -> new TagBlock(packOutput, registries, helper));
        vanillaPack.addProvider(packOutput -> new TagItem(packOutput, registries, block.contentsGetter(), helper));
        vanillaPack.addProvider(packOutput -> new TagPoiType(packOutput, registries, helper));
        vanillaPack.addProvider(packOutput -> new TagEntityType(packOutput, registries, helper));

        generator.addProvider(event.includeServer(), new AdvancementGenerator(pack, registries, helper));
        generator.addProvider(event.includeServer(), new LootTableGenerator(pack, registries));
        generator.addProvider(event.includeServer(), new ModRecipeGenerator(pack, registries));
        generator.addProvider(event.includeServer(), new GlobalLootModifier(pack, registries));
        generator.addProvider(event.includeClient(), new ParticleDescriptionGenerator(pack, helper));
        generator.addProvider(event.includeClient(), new BlockModelGenerator(pack, helper));
        generator.addProvider(event.includeClient(), new BlockStateGenerator(pack, helper));
        generator.addProvider(event.includeClient(), new ItemModelGenerator(pack, helper));
        generator.addProvider(event.includeServer(), new SoundDefinitionsGenerator(pack, helper));
    }
}
