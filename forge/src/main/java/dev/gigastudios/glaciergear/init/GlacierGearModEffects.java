package dev.gigastudios.glaciergear.init;

import dev.gigastudios.glaciergear.GlacierGearMod;
import dev.gigastudios.glaciergear.effect.FrostbiteEffect;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class GlacierGearModEffects {

    public static final DeferredRegister<MobEffect> REGISTRY =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, GlacierGearMod.MODID);

    public static final RegistryObject<FrostbiteEffect> FROSTBITE = REGISTRY.register("frostbite", FrostbiteEffect::new);

    // NOTE (verify locally): RegistryObject#getHolder() is the Forge accessor for the Holder<MobEffect>
    // that MobEffectInstance/LivingEntity#getEffect now require. If your Forge version names this
    // differently, this is the one line to fix.
    public static Holder<MobEffect> frostbiteHolder() {
        return FROSTBITE.getHolder().orElseThrow();
    }
}
