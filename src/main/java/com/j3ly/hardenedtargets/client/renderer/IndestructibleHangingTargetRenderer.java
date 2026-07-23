package com.j3ly.hardenedtargets.client.renderer;

import com.j3ly.hardenedtargets.block.entity.IndestructibleHangingTargetBlockEntity;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.tacz.guns.block.TargetBlock;
import com.tacz.guns.client.model.bedrock.BedrockModel;
import com.tacz.guns.client.model.bedrock.BedrockPart;
import com.tacz.guns.client.resource.InternalAssetLoader;
import com.tacz.guns.config.client.RenderConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class IndestructibleHangingTargetRenderer implements BlockEntityRenderer<IndestructibleHangingTargetBlockEntity> {
    private static final ResourceLocation INDESTRUCTIBLE_HANGING_TEXTURE = new ResourceLocation("hardenedtargets", "textures/entity/indestructible_target.png");

    public IndestructibleHangingTargetRenderer(BlockEntityRendererProvider.Context context) {}

    public static Optional<BedrockModel> getModel() {
        return InternalAssetLoader.getBedrockModel(InternalAssetLoader.TARGET_MODEL_LOCATION);
    }

    @Override
    public void render(IndestructibleHangingTargetBlockEntity be, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        getModel().ifPresent(model -> {
            BlockState state = be.getBlockState();
            Direction facing = state.getValue(TargetBlock.FACING);

            BedrockPart headNode = model.getNode("head");
            BedrockPart upperNode = model.getNode("target_upper");

            float rot = -Mth.lerp(partialTick, be.oRot, be.rot);
            upperNode.xRot = (float) Math.toRadians(rot);
            headNode.visible = false;

            poseStack.pushPose();
            poseStack.translate(0.5, 0.775, 0.5);
            poseStack.mulPose(Axis.YN.rotationDegrees(facing.get2DDataValue() * 90));
            poseStack.mulPose(Axis.ZN.rotationDegrees(180.0f));
            poseStack.mulPose(Axis.XP.rotationDegrees(180.0f));
            poseStack.translate(0, 1.275, 0.0125);

            RenderType renderType = RenderType.entityCutout(INDESTRUCTIBLE_HANGING_TEXTURE);
            model.render(poseStack, ItemDisplayContext.NONE, renderType, packedLight, packedOverlay);

            GameProfile owner = be.getOwner();
            if (owner != null) {
                poseStack.translate(0, -1.25, 0);
                poseStack.mulPose(Axis.XN.rotationDegrees(rot));

                Minecraft mc = Minecraft.getInstance();
                Map<MinecraftProfileTexture.Type, MinecraftProfileTexture> textures = mc.getSkinManager().getInsecureSkinInformation(owner);
                ResourceLocation skinLocation;
                if (textures.containsKey(MinecraftProfileTexture.Type.SKIN)) {
                    MinecraftProfileTexture tex = textures.get(MinecraftProfileTexture.Type.SKIN);
                    skinLocation = mc.getSkinManager().registerTexture(tex, MinecraftProfileTexture.Type.SKIN);
                } else {
                    UUID uuid = owner.getId();
                    skinLocation = DefaultPlayerSkin.getDefaultSkin(uuid != null ? uuid : UUID.nameUUIDFromBytes(new byte[0]));
                }

                headNode.visible = true;
                RenderType headRenderType = RenderType.entityTranslucent(skinLocation);
                headNode.render(poseStack, ItemDisplayContext.NONE,
                        bufferSource.getBuffer(headRenderType), packedLight, OverlayTexture.NO_OVERLAY);
            }

            poseStack.popPose();
        });
    }

    @Override
    public int getViewDistance() {
        return RenderConfig.TARGET_RENDER_DISTANCE.get();
    }

    @Override
    public boolean shouldRenderOffScreen(IndestructibleHangingTargetBlockEntity be) {
        return true;
    }
}
