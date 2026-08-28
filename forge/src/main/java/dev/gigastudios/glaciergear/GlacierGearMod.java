package dev.gigastudios.glaciergear;

import dev.gigastudios.glaciergear.event.GlacierArmorSetBonus;
import dev.gigastudios.glaciergear.init.GlacierGearModEffects;
import dev.gigastudios.glaciergear.init.GlacierGearModItems;
import dev.gigastudios.glaciergear.init.GlacierGearModTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(GlacierGearMod.MODID)
public class GlacierGearMod {

    public static final String MODID = "glacier_gear";
    public static final Logger LOGGER = LogManager.getLogger(GlacierGearMod.class);

    public GlacierGearMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        GlacierGearModItems.REGISTRY.register(modEventBus);
        GlacierGearModTabs.REGISTRY.register(modEventBus);
        GlacierGearModEffects.REGISTRY.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(new GlacierArmorSetBonus());
    }
}
