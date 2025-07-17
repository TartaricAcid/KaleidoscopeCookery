package com.github.ysbbbbbb.kaleidoscopecookery.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class GeneralConfig {
    public static ModConfigSpec init() {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();
        builder.push("cookery");
        builder.pop();
        return builder.build();
    }
}
