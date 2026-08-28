package dev.gigastudios.glaciergear.init;

import dev.gigastudios.glaciergear.GlacierGearMod;
import dev.gigastudios.glaciergear.effect.FrostbiteEffect;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;

public class GlacierGearModEffects {

    public static final ResourceKey<MobEffect> FROSTBITE_KEY = ResourceKey.create(Registries.MOB_EFFECT,
            ResourceLocation.fromNamespaceAndPath(GlacierGearMod.MODID, "frostbite"));

    public static final FrostbiteEffect FROSTBITE =
            Registry.register(BuiltInRegistries.MOB_EFFECT, FROSTBITE_KEY, new FrostbiteEffect());

    public static void init() {
    }

    public static Holder<MobEffect> frostbiteHolder() {
        return BuiltInRegistries.MOB_EFFECT.wrapAsHolder(FROSTBITE);
    }
}
