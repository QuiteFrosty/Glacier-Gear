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
 * Full-set behavior for Glacier Gear: immunity to freeze damage, plus an automatic
 * "Glacial Pulse" AoE frost burst when the wearer drops below 30% health (30s cooldown).
 * This is the mechanical hook the original release never had - full-set armor was
 * otherwise indistinguishable from re-skinned diamond gear.
 */
public class GlacierArmorSetBonus {

    private static final Map<UUID, Integer> PULSE_COOLDOWN = new HashMap<>();
    private static final int COOLDOWN_TICKS = 600;
    private static final double HEALTH_THRESHOLD = 0.3D;
    private static final double RADIUS = 6.0D;
    private static final int PULSE_AMPLIFIER = 2;

    @SubscribeEvent
    public void onIncomingDamage(LivingIncomingDamageEvent event) {
        if (event.getEntity() instanceof Player player
                && event.getSource().is(DamageTypes.FREEZE)
                && isFullSetWorn(player)) {
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

        if (!isFullSetWorn(player) || player.getHealth() > player.getMaxHealth() * HEALTH_THRESHOLD) {
            return;
        }

        glacialPulse(player);
        PULSE_COOLDOWN.put(id, COOLDOWN_TICKS);
    }

    private void glacialPulse(Player player) {
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

    private static boolean isFullSetWorn(Player player) {
        return player.getItemBySlot(EquipmentSlot.HEAD).is(GlacierGearModItems.GLACIER_ARMOR_HELMET.get())
                && player.getItemBySlot(EquipmentSlot.CHEST).is(GlacierGearModItems.GLACIER_ARMOR_CHESTPLATE.get())
                && player.getItemBySlot(EquipmentSlot.LEGS).is(GlacierGearModItems.GLACIER_ARMOR_LEGGINGS.get())
                && player.getItemBySlot(EquipmentSlot.FEET).is(GlacierGearModItems.GLACIER_ARMOR_BOOTS.get());
    }
}
