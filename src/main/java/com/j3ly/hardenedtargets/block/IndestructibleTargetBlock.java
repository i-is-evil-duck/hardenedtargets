package com.j3ly.hardenedtargets.block;

import com.j3ly.hardenedtargets.block.entity.IndestructibleTargetBlockEntity;
import com.j3ly.hardenedtargets.init.ModBlockEntities;
import com.mojang.authlib.GameProfile;
import com.tacz.guns.block.TargetBlock;
import com.tacz.guns.entity.EntityKineticBullet;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

import javax.annotation.Nullable;
import java.util.UUID;

public class IndestructibleTargetBlock extends TargetBlock {
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        if (state.getValue(HALF) == DoubleBlockHalf.LOWER) {
            return new IndestructibleTargetBlockEntity(pos, state);
        }
        return null;
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        if (state.getValue(HALF) == DoubleBlockHalf.LOWER && level.isClientSide && type == ModBlockEntities.INDESTRUCTIBLE_TARGET_BE.get()) {
            return (lvl, pos, st, be) -> IndestructibleTargetBlockEntity.clientTick(lvl, pos, st, (IndestructibleTargetBlockEntity) be);
        }
        return null;
    }

    @Override
    public void onProjectileHit(Level level, BlockState state, BlockHitResult hitResult, Projectile projectile) {
        if (hitResult.getDirection().getOpposite() != state.getValue(FACING)) return;

        boolean isUpper = state.getValue(HALF) == DoubleBlockHalf.UPPER;
        BlockPos hitPos = isUpper ? hitResult.getBlockPos().below() : hitResult.getBlockPos();

        float damage = 0;
        if (projectile instanceof EntityKineticBullet bullet) {
            damage = bullet.getDamage(hitResult.getLocation());
        }

        if (level.getBlockEntity(hitPos) instanceof IndestructibleTargetBlockEntity be) {
            be.hit(level, damage);
        }

        if (!level.isClientSide && projectile.getOwner() instanceof Player player) {
            if (level.getBlockEntity(hitPos) instanceof IndestructibleTargetBlockEntity be) {
                player.displayClientMessage(
                        Component.literal(String.format("Damage: %.1f", be.getAccumulatedDamage())),
                        true);
            }
        }
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(level, pos, state, placer, stack);
        if (!level.isClientSide) {
            BlockPos belowPos = pos.below();
            BlockState belowState = level.getBlockState(belowPos);
            if (belowState.is(this)) {
                if (level.getBlockEntity(belowPos) instanceof IndestructibleTargetBlockEntity be) {
                    if (stack.hasCustomHoverName()) {
                        be.setOwner(new GameProfile((UUID) null, stack.getHoverName().getString()));
                        be.setCustomName(stack.getHoverName());
                    }
                    be.refresh();
                }
            }
        }
    }

    @Override
    public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter level, BlockPos pos, Player player) {
        BlockEntity be = state.getValue(HALF) == DoubleBlockHalf.LOWER
                ? ((LevelAccessor) level).getBlockEntity(pos)
                : ((LevelAccessor) level).getBlockEntity(pos.below());
        if (be instanceof IndestructibleTargetBlockEntity ibe && ibe.getCustomName() != null) {
            ItemStack stack = new ItemStack(this);
            stack.setHoverName(ibe.getCustomName());
            return stack;
        }
        return new ItemStack(this);
    }
}
