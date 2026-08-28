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
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

/**
 * Full-set behavior for Glacier Gear: immunity to freeze damage, plus an automatic
 * "Glacial Pulse" AoE frost burst when the wearer drops below 30% health (30s cooldown).
 *
 * Plain Fabric API has no vanilla damage-cancel event without a mixin, so freeze immunity
 * here works by resetting the wearer's frozen-tick counter every server tick instead of
 * cancelling the eventual damage - same end result, no mixin required.
 */
public class GlacierArmorSetBonus {

    private static final Map<UUID, Integer> PULSE_COOLDOWN = new HashMap<>();
    private static final int COOLDOWN_TICKS = 600;
    private static final double HEALTH_THRESHOLD = 0.3D;
    private static final double RADIUS = 6.0D;
    private static final int PULSE_AMPLIFIER = 2;

    public static void register() {
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            for (ServerPlayer player : server.getPlayerList().getPlayers()) {
                if (!isFullSetWorn(player)) {
                    continue;
                }
                if (player.getTicksFrozen() > 0) {
                    player.setTicksFrozen(0);
                }
                tickPulseCooldown(player);
            }
        });
    }

    private static void tickPulseCooldown(ServerPlayer player) {
        UUID id = player.getUUID();
        int cooldown = PULSE_COOLDOWN.getOrDefault(id, 0);
        if (cooldown > 0) {
            PULSE_COOLDOWN.put(id, cooldown - 1);
            return;
        }

        if (player.getHealth() > player.getMaxHealth() * HEALTH_THRESHOLD) {
            return;
        }

        glacialPulse(player);
        PULSE_COOLDOWN.put(id, COOLDOWN_TICKS);
    }

    private static void glacialPulse(ServerPlayer player) {
        Level level = player.level();
        if (!(level instanceof ServerLevel serverLevel)) {
            return;
        }

        AABB area = player.getBoundingBox().inflate(RADIUS);
        for (LivingEntity target : level.getEntitiesOfClass(LivingEntity.class, area,
                e -> e != player && e.isAlive() && e instanceof Enemy)) {
            GlacierCombatUtil.applyFrostbite(target, PULSE_AMPLIFIER);
            Vec3 knock = target.position().subtract(player.position());
            if (knock.lengthSqr() > 1.0E-4) {
                knock = knock.normalize().scale(0.6D);
                target.push(knock.x, 0.2D, knock.z);
            }
        }

        serverLevel.sendParticles(ParticleTypes.SNOWFLAKE, player.getX(), player.getY() + 1.0D, player.getZ(),
                40, RADIUS / 2.0D, 1.0D, RADIUS / 2.0D, 0.02D);
        level.playSound(null, player.blockPosition(), SoundEvents.GLASS_BREAK, SoundSource.PLAYERS, 1.0F, 1.4F);
    }

    private static boolean isFullSetWorn(ServerPlayer player) {
        return player.getItemBySlot(EquipmentSlot.HEAD).is(GlacierGearModItems.GLACIER_ARMOR_HELMET)
                && player.getItemBySlot(EquipmentSlot.CHEST).is(GlacierGearModItems.GLACIER_ARMOR_CHESTPLATE)
                && player.getItemBySlot(EquipmentSlot.LEGS).is(GlacierGearModItems.GLACIER_ARMOR_LEGGINGS)
                && player.getItemBySlot(EquipmentSlot.FEET).is(GlacierGearModItems.GLACIER_ARMOR_BOOTS);
    }
}
