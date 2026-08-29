package dev.gigastudios.glaciergear.event;

import dev.gigastudios.glaciergear.init.GlacierGearModItems;
import dev.gigastudios.glaciergear.util.GlacierCombatUtil;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

/**
 * Full-set behavior for both gear tiers: immunity to freeze damage (Permafrost also grants fire
 * immunity), plus an automatic "Glacial Pulse" AoE frost burst when the wearer drops below 30%
 * health. Permafrost's pulse is stronger (bigger radius, higher amplifier, shorter cooldown) than
 * Glacier's - a reason to actually chase the upgrade instead of stopping at tier 1.
 */
public class GlacierArmorSetBonus {

    private static final Map<UUID, Integer> PULSE_COOLDOWN = new HashMap<>();
    private static final double HEALTH_THRESHOLD = 0.3D;

    private static final int GLACIER_COOLDOWN_TICKS = 600;
    private static final double GLACIER_RADIUS = 6.0D;
    private static final int GLACIER_PULSE_AMPLIFIER = 2;

    private static final int PERMAFROST_COOLDOWN_TICKS = 400;
    private static final double PERMAFROST_RADIUS = 8.0D;
    private static final int PERMAFROST_PULSE_AMPLIFIER = 3;

    @SubscribeEvent
    public void onIncomingDamage(LivingIncomingDamageEvent event) {
        if (!(event.getEntity() instanceof Player player)) {
            return;
        }
        Tier tier = tierOf(player);
        if (tier == Tier.NONE) {
            return;
        }
        if (event.getSource().is(DamageTypes.FREEZE)) {
            event.setCanceled(true);
        } else if (tier == Tier.PERMAFROST && (event.getSource().is(DamageTypes.IN_FIRE)
                || event.getSource().is(DamageTypes.ON_FIRE) || event.getSource().is(DamageTypes.LAVA))) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onPlayerTick(PlayerTickEvent.Post event) {
        Player player = event.getEntity();
        if (player.level().isClientSide) {
            return;
        }

        UUID id = player.getUUID();
        int cooldown = PULSE_COOLDOWN.getOrDefault(id, 0);
        if (cooldown > 0) {
            PULSE_COOLDOWN.put(id, cooldown - 1);
            return;
        }

        Tier tier = tierOf(player);
        if (tier == Tier.NONE || player.getHealth() > player.getMaxHealth() * HEALTH_THRESHOLD) {
            return;
        }

        boolean permafrost = tier == Tier.PERMAFROST;
        glacialPulse(player, permafrost ? PERMAFROST_RADIUS : GLACIER_RADIUS,
                permafrost ? PERMAFROST_PULSE_AMPLIFIER : GLACIER_PULSE_AMPLIFIER);
        PULSE_COOLDOWN.put(id, permafrost ? PERMAFROST_COOLDOWN_TICKS : GLACIER_COOLDOWN_TICKS);
    }

    private void glacialPulse(Player player, double radius, int amplifier) {
        Level level = player.level();
        if (!(level instanceof ServerLevel serverLevel)) {
            return;
        }

        AABB area = player.getBoundingBox().inflate(radius);
        for (LivingEntity target : level.getEntitiesOfClass(LivingEntity.class, area,
                e -> e != player && e.isAlive() && e instanceof Enemy)) {
            GlacierCombatUtil.applyFrostbite(target, amplifier);
            Vec3 knock = target.position().subtract(player.position());
            if (knock.lengthSqr() > 1.0E-4) {
                knock = knock.normalize().scale(0.6D);
                target.push(knock.x, 0.2D, knock.z);
            }
        }

        serverLevel.sendParticles(ParticleTypes.SNOWFLAKE, player.getX(), player.getY() + 1.0D, player.getZ(),
                40, radius / 2.0D, 1.0D, radius / 2.0D, 0.02D);
        level.playSound(null, player.blockPosition(), SoundEvents.GLASS_BREAK, SoundSource.PLAYERS, 1.0F, 1.4F);
    }

    private enum Tier { NONE, GLACIER, PERMAFROST }

    private static Tier tierOf(Player player) {
        if (isFullSet(player, GlacierGearModItems.PERMAFROST_ARMOR_HELMET.get(), GlacierGearModItems.PERMAFROST_ARMOR_CHESTPLATE.get(),
                GlacierGearModItems.PERMAFROST_ARMOR_LEGGINGS.get(), GlacierGearModItems.PERMAFROST_ARMOR_BOOTS.get())) {
            return Tier.PERMAFROST;
        }
        if (isFullSet(player, GlacierGearModItems.GLACIER_ARMOR_HELMET.get(), GlacierGearModItems.GLACIER_ARMOR_CHESTPLATE.get(),
                GlacierGearModItems.GLACIER_ARMOR_LEGGINGS.get(), GlacierGearModItems.GLACIER_ARMOR_BOOTS.get())) {
            return Tier.GLACIER;
        }
        return Tier.NONE;
    }

    private static boolean isFullSet(Player player, net.minecraft.world.item.Item helmet, net.minecraft.world.item.Item chest,
            net.minecraft.world.item.Item legs, net.minecraft.world.item.Item boots) {
        return player.getItemBySlot(EquipmentSlot.HEAD).is(helmet)
                && player.getItemBySlot(EquipmentSlot.CHEST).is(chest)
                && player.getItemBySlot(EquipmentSlot.LEGS).is(legs)
                && player.getItemBySlot(EquipmentSlot.FEET).is(boots);
    }
}
