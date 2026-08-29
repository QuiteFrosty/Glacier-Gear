package dev.gigastudios.glaciergear.client;

import dev.gigastudios.glaciergear.GlacierGearMod;
import dev.gigastudios.glaciergear.init.GlacierGearModEntities;
import net.minecraft.client.renderer.entity.RabbitRenderer;
import net.minecraft.client.renderer.entity.StrayRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = GlacierGearMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class GlacierGearModEntityRenderers {

    private static final ResourceLocation FROST_WRAITH_TEXTURE =
            ResourceLocation.fromNamespaceAndPath("glacier_gear", "textures/entity/frost_wraith.png");
    private static final ResourceLocation SNOW_HARE_TEXTURE =
            ResourceLocation.fromNamespaceAndPath("glacier_gear", "textures/entity/snow_hare.png");

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(GlacierGearModEntities.FROST_WRAITH.get(), context -> new StrayRenderer(context) {
            @Override
            public ResourceLocation getTextureLocation(net.minecraft.world.entity.monster.Stray entity) {
                return FROST_WRAITH_TEXTURE;
            }
        });

        event.registerEntityRenderer(GlacierGearModEntities.SNOW_HARE.get(), context -> new RabbitRenderer(context) {
            @Override
            public ResourceLocation getTextureLocation(net.minecraft.world.entity.animal.Rabbit entity) {
                return SNOW_HARE_TEXTURE;
            }
        });
    }
}
