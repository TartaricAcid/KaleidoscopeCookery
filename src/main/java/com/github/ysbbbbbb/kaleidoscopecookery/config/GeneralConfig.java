package com.github.ysbbbbbb.kaleidoscopecookery.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class GeneralConfig {
    public static ForgeConfigSpec init() {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.push("cookery");
        builder.pop();
        return builder.build();
    }
}
