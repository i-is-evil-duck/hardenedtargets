package com.j3ly.hardenedtargets.block;

import com.j3ly.hardenedtargets.block.entity.NormalUpsideDownTargetBlockEntity;
import com.j3ly.hardenedtargets.init.ModBlockEntities;
import com.mojang.authlib.GameProfile;
import com.tacz.guns.block.TargetBlock;
import com.tacz.guns.entity.EntityKineticBullet;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
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
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.UUID;

public class NormalUpsideDownTargetBlock extends TargetBlock {
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        boolean isUpper = state.getValue(HALF) == DoubleBlockHalf.UPPER;
        boolean isX = state.getValue(FACING).getAxis() == net.minecraft.core.Direction.Axis.X;
        boolean stand = state.getValue(STAND);
        if (isUpper) {
            if (stand) {
                return isX ? BOX_BOTTOM_STAND_X : BOX_BOTTOM_STAND_Z;
            }
            return BOX_BOTTOM_DOWN;
        }
        return stand ? (isX ? BOX_UPPER_X : BOX_UPPER_Z) : Shapes.empty();
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        if (state.getValue(HALF) == DoubleBlockHalf.UPPER) {
            return new NormalUpsideDownTargetBlockEntity(pos, state);
        }
        return null;
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        if (state.getValue(HALF) == DoubleBlockHalf.UPPER && level.isClientSide && type == ModBlockEntities.NORMAL_UPSIDE_DOWN_TARGET_BE.get()) {
            return (lvl, pos, st, be) -> NormalUpsideDownTargetBlockEntity.clientTick(lvl, pos, st, (NormalUpsideDownTargetBlockEntity) be);
        }
        return null;
    }

    @Override
    public void onProjectileHit(Level level, BlockState state, BlockHitResult hitResult, Projectile projectile) {
        if (hitResult.getDirection().getOpposite() != state.getValue(FACING)) return;

        boolean isUpper = state.getValue(HALF) == DoubleBlockHalf.UPPER;
        BlockPos hitPos = isUpper ? hitResult.getBlockPos() : hitResult.getBlockPos().above();
        BlockState targetState = isUpper ? state : level.getBlockState(hitPos);

        if (level.getBlockEntity(hitPos) instanceof NormalUpsideDownTargetBlockEntity be) {
            be.hit(level, targetState, hitResult, isUpper);
        }
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        super.tick(state, level, pos, random);
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(level, pos, state, placer, stack);
        if (!level.isClientSide) {
            BlockPos abovePos = pos.above();
            BlockState aboveState = level.getBlockState(abovePos);
            if (aboveState.is(this)) {
                if (level.getBlockEntity(abovePos) instanceof NormalUpsideDownTargetBlockEntity be) {
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
        BlockEntity be = state.getValue(HALF) == DoubleBlockHalf.UPPER
                ? ((LevelAccessor) level).getBlockEntity(pos)
                : ((LevelAccessor) level).getBlockEntity(pos.above());
        if (be instanceof NormalUpsideDownTargetBlockEntity tbe && tbe.getCustomName() != null) {
            ItemStack stack = new ItemStack(this);
            stack.setHoverName(tbe.getCustomName());
            return stack;
        }
        return new ItemStack(this);
    }
}
