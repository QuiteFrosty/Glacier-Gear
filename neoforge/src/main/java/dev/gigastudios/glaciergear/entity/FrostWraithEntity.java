package dev.gigastudios.glaciergear.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Stray;
import net.minecraft.world.level.Level;

/**
 * Hostile ice mob. Reuses Stray's model, AI, and combat behavior entirely (already thematically
 * "cold" - a slowness-inflicting skeleton archer) with a different texture, spawn set, and drops.
 * Deliberately not a from-scratch custom mob: new model geometry is the highest-risk thing to
 * write blind without being able to render and check it in-game.
 */
public class FrostWraithEntity extends Stray {
    public FrostWraithEntity(EntityType<? extends Stray> type, Level level) {
        super(type, level);
    }
}
