package dev.gigastudios.glaciergear.procedures;

import dev.gigastudios.glaciergear.init.GlacierGearModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;

public class IceExtractorBlockDestroyedWithToolProcedure {
    public static void execute(LevelAccessor world, double x, double y, double z) {
        if (world.getBlockState(BlockPos.containing(x, y, z)) == Blocks.ICE.defaultBlockState() && world instanceof ServerLevel) {
            ServerLevel level = (ServerLevel) world;
            ItemEntity entityToSpawn = new ItemEntity((Level) level, x + 0.5, y + 0.5, z + 0.5,
                    new ItemStack((ItemLike) GlacierGearModItems.ICE_SHARD.get()));
            entityToSpawn.setPickUpDelay(10);
            level.addFreshEntity((Entity) entityToSpawn);
        }
    }
}
