package com.j3ly.hardenedtargets.init;

import com.j3ly.hardenedtargets.HardenedTargetsMod;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, HardenedTargetsMod.MOD_ID);
    public static final RegistryObject<Item> REINFORCED_TARGET = ITEMS.register("reinforced_target",
            () -> new BlockItem(ModBlocks.REINFORCED_TARGET.get(), new Item.Properties()));
    public static final RegistryObject<Item> INDESTRUCTIBLE_TARGET = ITEMS.register("indestructible_target",
            () -> new BlockItem(ModBlocks.INDESTRUCTIBLE_TARGET.get(), new Item.Properties()));
    public static final RegistryObject<Item> UPSIDE_DOWN_REINFORCED_TARGET = ITEMS.register("upside_down_reinforced_target",
            () -> new BlockItem(ModBlocks.UPSIDE_DOWN_REINFORCED_TARGET.get(), new Item.Properties()));
    public static final RegistryObject<Item> UPSIDE_DOWN_INDESTRUCTIBLE_TARGET = ITEMS.register("upside_down_indestructible_target",
            () -> new BlockItem(ModBlocks.UPSIDE_DOWN_INDESTRUCTIBLE_TARGET.get(), new Item.Properties()));
}
