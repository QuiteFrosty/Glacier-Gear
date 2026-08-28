package dev.gigastudios.glaciergear.init;

import dev.gigastudios.glaciergear.GlacierGearMod;
import dev.gigastudios.glaciergear.effect.FrostbiteEffect;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class GlacierGearModEffects {

    public static final DeferredRegister<MobEffect> REGISTRY =
            DeferredRegister.create(Registries.MOB_EFFECT, GlacierGearMod.MODID);

    public static final DeferredHolder<MobEffect, FrostbiteEffect> FROSTBITE =
            REGISTRY.register("frostbite", FrostbiteEffect::new);
}
