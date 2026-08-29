package dev.gigastudios.glaciergear.client;

import dev.gigastudios.glaciergear.init.GlacierGearModEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.renderer.entity.RabbitRenderer;
import net.minecraft.client.renderer.entity.StrayRenderer;
import net.minecraft.resources.ResourceLocation;

public class GlacierGearModClient implements ClientModInitializer {

    private static final ResourceLocation FROST_WRAITH_TEXTURE =
            ResourceLocation.fromNamespaceAndPath("glacier_gear", "textures/entity/frost_wraith.png");
    private static final ResourceLocation SNOW_HARE_TEXTURE =
            ResourceLocation.fromNamespaceAndPath("glacier_gear", "textures/entity/snow_hare.png");

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(GlacierGearModEntities.FROST_WRAITH, context -> new StrayRenderer(context) {
            @Override
            public ResourceLocation getTextureLocation(net.minecraft.world.entity.monster.Stray entity) {
                return FROST_WRAITH_TEXTURE;
            }
        });

        EntityRendererRegistry.register(GlacierGearModEntities.SNOW_HARE, context -> new RabbitRenderer(context) {
            @Override
            public ResourceLocation getTextureLocation(net.minecraft.world.entity.animal.Rabbit entity) {
                return SNOW_HARE_TEXTURE;
            }
        });
    }
}
