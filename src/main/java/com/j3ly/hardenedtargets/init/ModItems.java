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
<<<<<<< HEAD
    public static final RegistryObject<Item> UPSIDE_DOWN_REINFORCED_TARGET = ITEMS.register("upside_down_reinforced_target",
            () -> new BlockItem(ModBlocks.UPSIDE_DOWN_REINFORCED_TARGET.get(), new Item.Properties()));
    public static final RegistryObject<Item> UPSIDE_DOWN_INDESTRUCTIBLE_TARGET = ITEMS.register("upside_down_indestructible_target",
            () -> new BlockItem(ModBlocks.UPSIDE_DOWN_INDESTRUCTIBLE_TARGET.get(), new Item.Properties()));
=======
    public static final RegistryObject<Item> HANGING_TARGET = ITEMS.register("hanging_target",
            () -> new BlockItem(ModBlocks.HANGING_TARGET.get(), new Item.Properties()));
    public static final RegistryObject<Item> HARDENED_HANGING_TARGET = ITEMS.register("hardened_hanging_target",
            () -> new BlockItem(ModBlocks.HARDENED_HANGING_TARGET.get(), new Item.Properties()));
    public static final RegistryObject<Item> INDESTRUCTIBLE_HANGING_TARGET = ITEMS.register("indestructible_hanging_target",
            () -> new BlockItem(ModBlocks.INDESTRUCTIBLE_HANGING_TARGET.get(), new Item.Properties()));
>>>>>>> a2340ebf226668284f331a04fbdfbc0a31cfefe1
}
