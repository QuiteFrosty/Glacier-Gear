package dev.gigastudios.glaciergear.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.level.Level;

/** Passive ice mob. Reuses Rabbit's model/AI/breeding entirely, with a different texture and drop. */
public class SnowHareEntity extends Rabbit {
    public SnowHareEntity(EntityType<? extends Rabbit> type, Level level) {
        super(type, level);
    }
}
