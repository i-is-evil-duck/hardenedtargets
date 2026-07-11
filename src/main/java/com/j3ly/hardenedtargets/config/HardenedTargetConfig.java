package com.j3ly.hardenedtargets.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class HardenedTargetConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.DoubleValue MAX_HEALTH;
    public static final ForgeConfigSpec.IntValue RESET_TICKS;

    static {
        BUILDER.push("hardened_target");
        MAX_HEALTH = BUILDER
                .comment("How much damage the reinforced target can take before falling over (20.0 = same as a player)")
                .defineInRange("maxHealth", 20.0, 1.0, 1000.0);
        RESET_TICKS = BUILDER
                .comment("How many ticks before the target stands back up (20 ticks = 1 second)")
                .defineInRange("resetTicks", 100, 20, 600);
        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
