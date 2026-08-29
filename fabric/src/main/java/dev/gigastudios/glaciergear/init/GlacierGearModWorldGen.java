package dev.gigastudios.glaciergear.init;

import dev.gigastudios.glaciergear.GlacierGearMod;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

/**
 * Plain Fabric API has no data-driven biome modifier JSON (that's a NeoForge/Forge-only schema),
 * so ore placement and mob spawn additions are wired here in code instead - the Fabric-idiomatic
 * equivalent of data/glacier_gear/{neoforge,forge}/biome_modifier/*.json in the other two ports.
 */
public class GlacierGearModWorldGen {

    private static final ResourceKey<Biome>[] COLD_BIOMES = biomeKeys(
            "frozen_peaks", "ice_spikes", "snowy_plains", "snowy_taiga", "snowy_slopes", "grove");
    private static final ResourceKey<Biome>[] FROST_WRAITH_BIOMES = biomeKeys(
            "frozen_peaks", "ice_spikes", "snowy_plains", "frozen_ocean", "deep_frozen_ocean");
    private static final ResourceKey<Biome>[] SNOW_HARE_BIOMES = biomeKeys(
            "snowy_plains", "snowy_taiga", "snowy_slopes", "ice_spikes", "grove");

    public static void init() {
        ResourceKey<PlacedFeature> permafrostOre = ResourceKey.create(Registries.PLACED_FEATURE,
                ResourceLocation.fromNamespaceAndPath(GlacierGearMod.MODID, "permafrost_ore_placed"));

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(COLD_BIOMES),
                GenerationStep.Decoration.UNDERGROUND_ORES, permafrostOre);

        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(FROST_WRAITH_BIOMES),
                MobCategory.MONSTER, GlacierGearModEntities.FROST_WRAITH, 40, 1, 2);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(SNOW_HARE_BIOMES),
                MobCategory.CREATURE, GlacierGearModEntities.SNOW_HARE, 8, 2, 3);
    }

    @SafeVarargs
    private static ResourceKey<Biome>[] biomeKeys(String... names) {
        ResourceKey<Biome>[] keys = new ResourceKey[names.length];
        for (int i = 0; i < names.length; i++) {
            keys[i] = ResourceKey.create(Registries.BIOME, ResourceLocation.withDefaultNamespace(names[i]));
        }
        return keys;
    }
}
