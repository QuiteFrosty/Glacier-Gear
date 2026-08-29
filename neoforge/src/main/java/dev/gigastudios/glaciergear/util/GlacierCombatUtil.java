package dev.gigastudios.glaciergear.util;

import dev.gigastudios.glaciergear.init.GlacierGearModEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;

public class GlacierCombatUtil {

    private static final int MAX_AMPLIFIER = 3;
    private static final int DURATION_TICKS = 200;

    private GlacierCombatUtil() {
    }

    /** Called on weapon hit: escalates the target's Frostbite by one stack. */
    public static void stackFrostbite(LivingEntity target) {
        MobEffectInstance current = target.getEffect(GlacierGearModEffects.FROSTBITE);
        int amplifier = current != null ? Math.min(current.getAmplifier() + 1, MAX_AMPLIFIER) : 0;
        target.addEffect(new MobEffectInstance(GlacierGearModEffects.FROSTBITE, DURATION_TICKS, amplifier));
    }

    /** Called by Glacial Pulse: applies Frostbite directly at a fixed amplifier. */
    public static void applyFrostbite(LivingEntity target, int amplifier) {
        target.addEffect(new MobEffectInstance(GlacierGearModEffects.FROSTBITE, DURATION_TICKS,
                Math.min(amplifier, MAX_AMPLIFIER)));
    }

    /** Called on Permafrost weapon hit: same as stackFrostbite(), but skips the weak (0) stage. */
    public static void stackFrostbitePermafrost(LivingEntity target) {
        MobEffectInstance current = target.getEffect(GlacierGearModEffects.FROSTBITE);
        int amplifier = current != null ? Math.min(current.getAmplifier() + 1, MAX_AMPLIFIER) : 1;
        target.addEffect(new MobEffectInstance(GlacierGearModEffects.FROSTBITE, DURATION_TICKS, amplifier));
    }

    /** True if the target is at max Frostbite ("frozen solid") - Permafrost weapons deal bonus damage to these. */
    public static boolean isFrozenSolid(LivingEntity target) {
        MobEffectInstance current = target.getEffect(GlacierGearModEffects.FROSTBITE);
        return current != null && current.getAmplifier() >= MAX_AMPLIFIER;
    }
}
