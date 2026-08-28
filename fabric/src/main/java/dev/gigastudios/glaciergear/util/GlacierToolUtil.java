package dev.gigastudios.glaciergear.util;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

public class GlacierToolUtil {

    private static final float CHANCE = 0.15F;

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
}
