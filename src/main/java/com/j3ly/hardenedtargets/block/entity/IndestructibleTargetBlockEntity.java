package com.j3ly.hardenedtargets.block.entity;

import com.j3ly.hardenedtargets.init.ModBlockEntities;
import com.mojang.authlib.GameProfile;
import com.tacz.guns.init.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Nameable;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import javax.annotation.Nullable;

public class IndestructibleTargetBlockEntity extends BlockEntity implements Nameable {
    public float rot;
    public float oRot;
    private float accumulatedDamage;
    private long lastHitTime;
    private GameProfile owner;
    private Component customName;
    private static final long RESET_DELAY = 50;

    public IndestructibleTargetBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.INDESTRUCTIBLE_TARGET_BE.get(), pos, state);
    }

    public static void clientTick(Level level, BlockPos pos, BlockState state, IndestructibleTargetBlockEntity be) {
        be.oRot = be.rot;
        be.rot = Math.max(be.rot - 18.0f, 0.0f);
    }

    public void hit(Level level, float damage) {
        long gameTime = level.getGameTime();
        if (gameTime - lastHitTime > RESET_DELAY) {
            accumulatedDamage = 0;
        }
        lastHitTime = gameTime;
        accumulatedDamage += Math.max(0, damage);
        rot = 30.0f;
        float pitch = 0.9f + level.random.nextFloat() * 0.1f;
        level.playSound(null, worldPosition, ModSounds.TARGET_HIT.get(), SoundSource.BLOCKS, 8.0f, pitch);
        setChanged();
    }

    public float getAccumulatedDamage() {
        return accumulatedDamage;
    }

    @Nullable
    public GameProfile getOwner() {
        return owner;
    }

    public void setOwner(GameProfile profile) {
        owner = profile;
        setChanged();
    }

    @Override
    public Component getName() {
        return customName != null ? customName : Component.translatable("block.hardenedtargets.indestructible_target");
    }

    @Nullable
    @Override
    public Component getCustomName() {
        return customName;
    }

    public void setCustomName(Component name) {
        customName = name;
        setChanged();
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        accumulatedDamage = tag.getFloat("AccumulatedDamage");
        if (tag.contains("Owner", 10)) {
            owner = NbtUtils.readGameProfile(tag.getCompound("Owner"));
        }
        if (tag.contains("CustomName", 8)) {
            customName = Component.Serializer.fromJson(tag.getString("CustomName"));
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putFloat("AccumulatedDamage", accumulatedDamage);
        if (owner != null) {
            CompoundTag ownerTag = new CompoundTag();
            NbtUtils.writeGameProfile(ownerTag, owner);
            tag.put("Owner", ownerTag);
        }
        if (customName != null) {
            tag.putString("CustomName", Component.Serializer.toJson(customName));
        }
    }

    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag() {
        return saveWithoutMetadata();
    }

    public void refresh() {
        setChanged();
        if (level != null) {
            BlockState state = level.getBlockState(worldPosition);
            level.sendBlockUpdated(worldPosition, state, state, 3);
        }
    }

    @Override
    public AABB getRenderBoundingBox() {
        return new AABB(worldPosition.offset(-2, 0, -2), worldPosition.offset(2, 2, 2));
    }
}
