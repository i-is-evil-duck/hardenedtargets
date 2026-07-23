package com.j3ly.hardenedtargets.init;

import com.j3ly.hardenedtargets.HardenedTargetsMod;
import com.j3ly.hardenedtargets.block.entity.HardenedHangingTargetBlockEntity;
import com.j3ly.hardenedtargets.block.entity.HardenedTargetBlockEntity;
import com.j3ly.hardenedtargets.block.entity.HangingTargetBlockEntity;
import com.j3ly.hardenedtargets.block.entity.IndestructibleHangingTargetBlockEntity;
import com.j3ly.hardenedtargets.block.entity.IndestructibleTargetBlockEntity;
import com.j3ly.hardenedtargets.block.entity.UpsideDownHardenedTargetBlockEntity;
import com.j3ly.hardenedtargets.block.entity.UpsideDownIndestructibleTargetBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, HardenedTargetsMod.MOD_ID);
    public static final RegistryObject<BlockEntityType<HardenedTargetBlockEntity>> REINFORCED_TARGET_BE =
            BLOCK_ENTITIES.register("reinforced_target",
                    () -> BlockEntityType.Builder.of(HardenedTargetBlockEntity::new, ModBlocks.REINFORCED_TARGET.get()).build(null));
    public static final RegistryObject<BlockEntityType<IndestructibleTargetBlockEntity>> INDESTRUCTIBLE_TARGET_BE =
            BLOCK_ENTITIES.register("indestructible_target",
                    () -> BlockEntityType.Builder.of(IndestructibleTargetBlockEntity::new, ModBlocks.INDESTRUCTIBLE_TARGET.get()).build(null));
<<<<<<< HEAD
    public static final RegistryObject<BlockEntityType<UpsideDownHardenedTargetBlockEntity>> UPSIDE_DOWN_REINFORCED_TARGET_BE =
            BLOCK_ENTITIES.register("upside_down_reinforced_target",
                    () -> BlockEntityType.Builder.of(UpsideDownHardenedTargetBlockEntity::new, ModBlocks.UPSIDE_DOWN_REINFORCED_TARGET.get()).build(null));
    public static final RegistryObject<BlockEntityType<UpsideDownIndestructibleTargetBlockEntity>> UPSIDE_DOWN_INDESTRUCTIBLE_TARGET_BE =
            BLOCK_ENTITIES.register("upside_down_indestructible_target",
                    () -> BlockEntityType.Builder.of(UpsideDownIndestructibleTargetBlockEntity::new, ModBlocks.UPSIDE_DOWN_INDESTRUCTIBLE_TARGET.get()).build(null));
=======
    public static final RegistryObject<BlockEntityType<HangingTargetBlockEntity>> HANGING_TARGET_BE =
            BLOCK_ENTITIES.register("hanging_target",
                    () -> BlockEntityType.Builder.of(HangingTargetBlockEntity::new, ModBlocks.HANGING_TARGET.get()).build(null));
    public static final RegistryObject<BlockEntityType<HardenedHangingTargetBlockEntity>> HARDENED_HANGING_TARGET_BE =
            BLOCK_ENTITIES.register("hardened_hanging_target",
                    () -> BlockEntityType.Builder.of(HardenedHangingTargetBlockEntity::new, ModBlocks.HARDENED_HANGING_TARGET.get()).build(null));
    public static final RegistryObject<BlockEntityType<IndestructibleHangingTargetBlockEntity>> INDESTRUCTIBLE_HANGING_TARGET_BE =
            BLOCK_ENTITIES.register("indestructible_hanging_target",
                    () -> BlockEntityType.Builder.of(IndestructibleHangingTargetBlockEntity::new, ModBlocks.INDESTRUCTIBLE_HANGING_TARGET.get()).build(null));
>>>>>>> a2340ebf226668284f331a04fbdfbc0a31cfefe1
}
