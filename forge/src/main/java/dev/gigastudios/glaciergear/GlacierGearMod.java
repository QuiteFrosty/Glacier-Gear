package dev.gigastudios.glaciergear;

import dev.gigastudios.glaciergear.event.GlacierArmorSetBonus;
import dev.gigastudios.glaciergear.init.GlacierGearModBlocks;
import dev.gigastudios.glaciergear.init.GlacierGearModEffects;
import dev.gigastudios.glaciergear.init.GlacierGearModEntities;
import dev.gigastudios.glaciergear.init.GlacierGearModItems;
import dev.gigastudios.glaciergear.init.GlacierGearModTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(GlacierGearMod.MODID)
public class GlacierGearMod {

    public static final String MODID = "glacier_gear";
    public static final Logger LOGGER = LogManager.getLogger(GlacierGearMod.class);

    public GlacierGearMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        GlacierGearModBlocks.REGISTRY.register(modEventBus);
        GlacierGearModItems.REGISTRY.register(modEventBus);
        GlacierGearModEntities.REGISTRY.register(modEventBus);
        GlacierGearModTabs.REGISTRY.register(modEventBus);
        GlacierGearModEffects.REGISTRY.register(modEventBus);

        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(new GlacierArmorSetBonus());
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(GlacierGearModEntities::registerSpawnPlacements);
    }
}
