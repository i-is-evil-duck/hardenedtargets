package com.j3ly.hardenedtargets.init;

import com.j3ly.hardenedtargets.HardenedTargetsMod;
import com.j3ly.hardenedtargets.block.entity.HardenedTargetBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, HardenedTargetsMod.MOD_ID);
    public static final RegistryObject<BlockEntityType<HardenedTargetBlockEntity>> REINFORCED_TARGET_BE =
            BLOCK_ENTITIES.register("reinforced_target",
                    () -> BlockEntityType.Builder.of(HardenedTargetBlockEntity::new, ModBlocks.REINFORCED_TARGET.get()).build(null));
}
