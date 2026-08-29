package dev.gigastudios.glaciergear.util;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

public class GlacierToolUtil {

    private static final float CHANCE = 0.15F;
    private static final float PERMAFROST_CHANCE = 0.30F;

    private GlacierToolUtil() {
    }

    /** "Chilling Touch": mining with glacier tools has a chance to freeze adjacent source water into ice. */
    public static void chillingTouch(Level level, BlockPos minedPos) {
        if (!(level instanceof ServerLevel) || level.random.nextFloat() >= CHANCE) {
            return;
        }
        for (Direction direction : Direction.values()) {
            BlockPos neighbor = minedPos.relative(direction);
            if (level.getBlockState(neighbor).is(Blocks.WATER) && level.getFluidState(neighbor).isSource()) {
                level.setBlockAndUpdate(neighbor, Blocks.ICE.defaultBlockState());
            }
        }
    }

    /** Permafrost tier "Chilling Touch": higher chance, and also upgrades Ice to Packed Ice, Packed Ice to Blue Ice. */
    public static void chillingTouchPermafrost(Level level, BlockPos minedPos) {
        if (!(level instanceof ServerLevel) || level.random.nextFloat() >= PERMAFROST_CHANCE) {
            return;
        }
        for (Direction direction : Direction.values()) {
            BlockPos neighbor = minedPos.relative(direction);
            if (level.getBlockState(neighbor).is(Blocks.WATER) && level.getFluidState(neighbor).isSource()) {
                level.setBlockAndUpdate(neighbor, Blocks.ICE.defaultBlockState());
            } else if (level.getBlockState(neighbor).is(Blocks.ICE)) {
                level.setBlockAndUpdate(neighbor, Blocks.PACKED_ICE.defaultBlockState());
            } else if (level.getBlockState(neighbor).is(Blocks.PACKED_ICE)) {
                level.setBlockAndUpdate(neighbor, Blocks.BLUE_ICE.defaultBlockState());
            }
        }
    }
}
