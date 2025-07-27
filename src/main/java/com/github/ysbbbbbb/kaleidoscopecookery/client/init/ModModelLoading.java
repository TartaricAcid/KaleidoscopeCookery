package com.github.ysbbbbbb.kaleidoscopecookery.client.init;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;

@Environment(EnvType.CLIENT)
public class ModModelLoading {
    private static final String MODELS = "models/";
    private static final String MODELS_CHOPPING_BOARD = MODELS + "chopping_board";
    private static final String MODELS_CARPET = MODELS + "block/carpet";
    private static final String JSON = ".json";

    public static void register() {
        ModelLoadingPlugin.register(context -> {
            ResourceManager resourceManager = Minecraft.getInstance().getResourceManager();

            resourceManager.listResources(MODELS_CHOPPING_BOARD, id -> id.getPath().endsWith(JSON))
                    .keySet().stream().map(ModModelLoading::handleModelId).forEach(context::addModels);

            resourceManager.listResources(MODELS_CARPET, id -> id.getPath().endsWith(JSON))
                    .keySet().stream().map(ModModelLoading::handleModelId).forEach(context::addModels);
        });
    }

    private static ResourceLocation handleModelId(ResourceLocation input) {
        String namespace = input.getNamespace();
        String path = input.getPath();
        return new ResourceLocation(namespace, path.substring(MODELS.length(), path.length() - JSON.length()));
    }
}
