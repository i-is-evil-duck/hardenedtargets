package com.j3ly.hardenedtargets.init;

import com.j3ly.hardenedtargets.HardenedTargetsMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, HardenedTargetsMod.MOD_ID);
    public static final RegistryObject<CreativeModeTab> TAB = CREATIVE_MODE_TABS.register("hardened_targets",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.REINFORCED_TARGET.get()))
                    .title(Component.translatable("itemGroup.hardenedtargets"))
                    .displayItems((params, output) -> {
                        output.accept(ModItems.REINFORCED_TARGET.get());
                        output.accept(ModItems.INDESTRUCTIBLE_TARGET.get());
                        output.accept(ModItems.HANGING_TARGET.get());
                        output.accept(ModItems.HARDENED_HANGING_TARGET.get());
                        output.accept(ModItems.INDESTRUCTIBLE_HANGING_TARGET.get());
                    })
                    .build());
}
