package dev.gigastudios.glaciergear.event;

import dev.gigastudios.glaciergear.init.GlacierGearModItems;
import dev.gigastudios.glaciergear.util.GlacierCombatUtil;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

/**
 * Full-set behavior for both gear tiers, plus an automatic "Glacial Pulse" AoE frost burst below
 * 30% health. Permafrost's pulse is stronger (bigger radius, higher amplifier, shorter cooldown).
 *
 * Plain Fabric API has no vanilla damage-cancel event without a mixin, so freeze/fire immunity
 * work by resetting the wearer's frozen-tick counter / clearing fire every server tick instead of
 * cancelling the eventual damage - same end result for the "on fire" damage-over-time tick, though
 * unlike a real damage-cancel this can't stop a single instant hit (e.g. direct lava contact) that
 * lands the same tick the fire is set, which the NeoForge/Forge ports do block.
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

    public static void register() {
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            for (ServerPlayer player : server.getPlayerList().getPlayers()) {
                Tier tier = tierOf(player);
                if (tier == Tier.NONE) {
                    continue;
                }
                if (player.getTicksFrozen() > 0) {
                    player.setTicksFrozen(0);
                }
                if (tier == Tier.PERMAFROST && player.getRemainingFireTicks() > 0) {
                    player.clearFire();
                }
                tickPulseCooldown(player, tier);
            }
        });
    }

    private static void tickPulseCooldown(ServerPlayer player, Tier tier) {
        UUID id = player.getUUID();
        int cooldown = PULSE_COOLDOWN.getOrDefault(id, 0);
        if (cooldown > 0) {
            PULSE_COOLDOWN.put(id, cooldown - 1);
            return;
        }

        if (player.getHealth() > player.getMaxHealth() * HEALTH_THRESHOLD) {
            return;
        }

        boolean permafrost = tier == Tier.PERMAFROST;
        glacialPulse(player, permafrost ? PERMAFROST_RADIUS : GLACIER_RADIUS,
                permafrost ? PERMAFROST_PULSE_AMPLIFIER : GLACIER_PULSE_AMPLIFIER);
        PULSE_COOLDOWN.put(id, permafrost ? PERMAFROST_COOLDOWN_TICKS : GLACIER_COOLDOWN_TICKS);
    }

    private static void glacialPulse(ServerPlayer player, double radius, int amplifier) {
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

    private static Tier tierOf(ServerPlayer player) {
        if (isFullSet(player, GlacierGearModItems.PERMAFROST_ARMOR_HELMET, GlacierGearModItems.PERMAFROST_ARMOR_CHESTPLATE,
                GlacierGearModItems.PERMAFROST_ARMOR_LEGGINGS, GlacierGearModItems.PERMAFROST_ARMOR_BOOTS)) {
            return Tier.PERMAFROST;
        }
        if (isFullSet(player, GlacierGearModItems.GLACIER_ARMOR_HELMET, GlacierGearModItems.GLACIER_ARMOR_CHESTPLATE,
                GlacierGearModItems.GLACIER_ARMOR_LEGGINGS, GlacierGearModItems.GLACIER_ARMOR_BOOTS)) {
            return Tier.GLACIER;
        }
        return Tier.NONE;
    }

    private static boolean isFullSet(ServerPlayer player, Item helmet, Item chest, Item legs, Item boots) {
        return player.getItemBySlot(EquipmentSlot.HEAD).is(helmet)
                && player.getItemBySlot(EquipmentSlot.CHEST).is(chest)
                && player.getItemBySlot(EquipmentSlot.LEGS).is(legs)
                && player.getItemBySlot(EquipmentSlot.FEET).is(boots);
    }
}
