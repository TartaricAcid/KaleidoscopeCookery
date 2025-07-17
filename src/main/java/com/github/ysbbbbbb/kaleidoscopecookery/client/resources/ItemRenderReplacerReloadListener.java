package com.github.ysbbbbbb.kaleidoscopecookery.client.resources;

import com.github.ysbbbbbb.kaleidoscopecookery.KaleidoscopeCookery;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mojang.serialization.JsonOps;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.IoSupplier;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class ItemRenderReplacerReloadListener implements ResourceManagerReloadListener {
    public static final ItemRenderReplacer INSTANCE = new ItemRenderReplacer();

    private static final ResourceLocation FILE_PATH = ResourceLocation.fromNamespaceAndPath(KaleidoscopeCookery.MOD_ID, "models/item_render_replacer.json");

    @Override
    public void onResourceManagerReload(ResourceManager resourceManager) {
        resourceManager.listPacks().forEach((packResources) -> {
            IoSupplier<InputStream> resource = packResources.getResource(PackType.CLIENT_RESOURCES, FILE_PATH);
            if (resource == null) {
                return;
            }
            try (InputStream inputStream = resource.get();
                 InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {
                JsonElement jsonElement = JsonParser.parseReader(reader);
                var result = ItemRenderReplacer.CODEC.parse(JsonOps.INSTANCE, jsonElement);
                if (result.result().isPresent()) {
                    INSTANCE.addAll(result.result().get());
                    KaleidoscopeCookery.LOGGER.info("Successfully loaded item render replacer data");
                } else if (result.error().isPresent()) {
                    KaleidoscopeCookery.LOGGER.error("Failed to parse item render replacer data: {}", result.error().get().message());
                }
            } catch (Exception e) {
                KaleidoscopeCookery.LOGGER.error("Failed to load item render replacer resource", e);
            }
        });
    }
}
