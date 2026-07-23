package com.j3ly.hardenedtargets.init;

import com.j3ly.hardenedtargets.HardenedTargetsMod;
import com.j3ly.hardenedtargets.block.HardenedTargetBlock;
import com.j3ly.hardenedtargets.block.IndestructibleTargetBlock;
import com.j3ly.hardenedtargets.block.UpsideDownHardenedTargetBlock;
import com.j3ly.hardenedtargets.block.UpsideDownIndestructibleTargetBlock;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, HardenedTargetsMod.MOD_ID);
    public static final RegistryObject<Block> REINFORCED_TARGET = BLOCKS.register("reinforced_target", HardenedTargetBlock::new);
    public static final RegistryObject<Block> INDESTRUCTIBLE_TARGET = BLOCKS.register("indestructible_target", IndestructibleTargetBlock::new);
    public static final RegistryObject<Block> UPSIDE_DOWN_REINFORCED_TARGET = BLOCKS.register("upside_down_reinforced_target", UpsideDownHardenedTargetBlock::new);
    public static final RegistryObject<Block> UPSIDE_DOWN_INDESTRUCTIBLE_TARGET = BLOCKS.register("upside_down_indestructible_target", UpsideDownIndestructibleTargetBlock::new);
}
