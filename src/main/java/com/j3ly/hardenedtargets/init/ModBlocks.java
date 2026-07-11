package com.j3ly.hardenedtargets.init;

import com.j3ly.hardenedtargets.HardenedTargetsMod;
import com.j3ly.hardenedtargets.block.HardenedTargetBlock;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, HardenedTargetsMod.MOD_ID);
    public static final RegistryObject<Block> REINFORCED_TARGET = BLOCKS.register("reinforced_target", HardenedTargetBlock::new);
}
