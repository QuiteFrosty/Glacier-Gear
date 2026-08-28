package dev.gigastudios.glaciergear.item;

import dev.gigastudios.glaciergear.procedures.PackedIceExtractorBlockDestroyedWithToolProcedure;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;

public class PackedIceExtractorItem extends PickaxeItem {

    private static final ToolMaterial TOOL_MATERIAL = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 100,
            4.0f, 0.0f, 2,
            TagKey.create(Registries.ITEM, ResourceLocation.parse("glacier_gear:packed_ice_extractor_repair_items")));

    public PackedIceExtractorItem(Item.Properties properties) {
        super(TOOL_MATERIAL, 3.0f, -3.0f, properties);
    }

    /*
     * The 2.0.0 release shipped this procedure class unused: PackedIceExtractorItem never
     * overrode mineBlock(), so breaking packed ice never dropped the bonus ice shard the item's
     * own name and recipe promised. That's fixed here.
     */
    @Override
    public boolean mineBlock(ItemStack itemstack, Level world, BlockState blockstate, BlockPos pos, LivingEntity entity) {
        boolean retval = super.mineBlock(itemstack, world, blockstate, pos, entity);
        PackedIceExtractorBlockDestroyedWithToolProcedure.execute((LevelAccessor) world, pos.getX(), pos.getY(), pos.getZ());
        return retval;
    }
}
