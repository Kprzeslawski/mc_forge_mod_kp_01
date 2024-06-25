package com.kprzeslawski.examplemod.entity.client;

import com.kprzeslawski.examplemod.ExampleMod;
import com.kprzeslawski.examplemod.entity.custom.EnZombie;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.ZombieModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.resources.ResourceLocation;

public class EnZombieRenderer extends HumanoidMobRenderer<EnZombie, ZombieModel<EnZombie>> {
    public EnZombieRenderer(EntityRendererProvider.Context p_174456_) {
        super(p_174456_,new ZombieModel<>(p_174456_.bakeLayer(ModelLayers.ZOMBIE)),2f);
    }

    @Override
    public ResourceLocation getTextureLocation(EnZombie enZombie) {
        return new ResourceLocation(ExampleMod.MOD_ID, "textures/entity/zombie/en_zombie.png");
    }

    @Override
    public void render(EnZombie pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {
        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}
