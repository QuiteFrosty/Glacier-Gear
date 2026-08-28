package dev.gigastudios.glaciergear.effect;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

/**
 * The Glacier Gear "hook": stacking harmful effect applied by glacier weapons and Glacial Pulse.
 * Amplifier 0-2 slows the target further each stack; amplifier 3 ("frozen solid") pins it in place.
 */
public class FrostbiteEffect extends MobEffect {

    private static final ResourceLocation SLOW_MODIFIER_ID =
            ResourceLocation.fromNamespaceAndPath("glacier_gear", "frostbite_slow");

    public FrostbiteEffect() {
        super(MobEffectCategory.HARMFUL, 0x9AD6E8);
        this.addAttributeModifier(Attributes.MOVEMENT_SPEED, SLOW_MODIFIER_ID, -0.15D,
                AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        if (amplifier >= 3) {
            entity.setDeltaMovement(0.0D, entity.getDeltaMovement().y, 0.0D);
            entity.hasImpulse = true;
        }
        entity.hurt(entity.damageSources().freeze(), 1.0F);
        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int tickCount, int amplifier) {
        int interval = Math.max(10, 50 - amplifier * 12);
        return tickCount % interval == 0;
    }
}
