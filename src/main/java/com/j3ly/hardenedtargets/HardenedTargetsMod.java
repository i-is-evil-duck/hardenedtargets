package com.j3ly.hardenedtargets;

import com.j3ly.hardenedtargets.config.HardenedTargetConfig;
import com.j3ly.hardenedtargets.init.ModBlockEntities;
import com.j3ly.hardenedtargets.init.ModBlocks;
import com.j3ly.hardenedtargets.init.ModCreativeTabs;
import com.j3ly.hardenedtargets.init.ModItems;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(HardenedTargetsMod.MOD_ID)
public class HardenedTargetsMod {
    public static final String MOD_ID = "hardenedtargets";

    public HardenedTargetsMod() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, HardenedTargetConfig.SPEC, MOD_ID + "-common.toml");
        ModBlocks.BLOCKS.register(modBus);
        ModBlockEntities.BLOCK_ENTITIES.register(modBus);
        ModItems.ITEMS.register(modBus);
        ModCreativeTabs.CREATIVE_MODE_TABS.register(modBus);
        MinecraftForge.EVENT_BUS.register(this);
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
            modBus.addListener((EntityRenderersEvent.RegisterRenderers event) -> {
                event.registerBlockEntityRenderer(ModBlockEntities.REINFORCED_TARGET_BE.get(),
                        com.j3ly.hardenedtargets.client.renderer.HardenedTargetRenderer::new);
                event.registerBlockEntityRenderer(ModBlockEntities.INDESTRUCTIBLE_TARGET_BE.get(),
                        com.j3ly.hardenedtargets.client.renderer.IndestructibleTargetRenderer::new);
                event.registerBlockEntityRenderer(ModBlockEntities.UPSIDE_DOWN_REINFORCED_TARGET_BE.get(),
                        com.j3ly.hardenedtargets.client.renderer.UpsideDownHardenedTargetRenderer::new);
                event.registerBlockEntityRenderer(ModBlockEntities.UPSIDE_DOWN_INDESTRUCTIBLE_TARGET_BE.get(),
                        com.j3ly.hardenedtargets.client.renderer.UpsideDownIndestructibleTargetRenderer::new);
            });
        });
    }
}
