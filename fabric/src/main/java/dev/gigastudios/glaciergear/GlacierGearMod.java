package dev.gigastudios.glaciergear;

import dev.gigastudios.glaciergear.event.GlacierArmorSetBonus;
import dev.gigastudios.glaciergear.init.GlacierGearModBlocks;
import dev.gigastudios.glaciergear.init.GlacierGearModEffects;
import dev.gigastudios.glaciergear.init.GlacierGearModEntities;
import dev.gigastudios.glaciergear.init.GlacierGearModItems;
import dev.gigastudios.glaciergear.init.GlacierGearModTabs;
import dev.gigastudios.glaciergear.init.GlacierGearModWorldGen;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GlacierGearMod implements ModInitializer {

    public static final String MODID = "glacier_gear";
    public static final Logger LOGGER = LogManager.getLogger(GlacierGearMod.class);

    @Override
    public void onInitialize() {
        GlacierGearModBlocks.init();
        GlacierGearModItems.init();
        GlacierGearModEntities.init();
        GlacierGearModEffects.init();
        GlacierGearModTabs.init();
        GlacierGearModWorldGen.init();
        GlacierArmorSetBonus.register();
    }
}
