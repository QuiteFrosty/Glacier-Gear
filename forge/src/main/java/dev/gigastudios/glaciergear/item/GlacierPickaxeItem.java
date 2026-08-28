package dev.gigastudios.glaciergear.item;

import dev.gigastudios.glaciergear.util.GlacierToolUtil;
import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class GlacierPickaxeItem extends PickaxeItem {

    private static final ToolMaterial TOOL_MATERIAL = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 660,
            9.0f, 0.0f, 28,
            TagKey.create(Registries.ITEM, ResourceLocation.parse("glacier_gear:glacier_pickaxe_repair_items")));

    public GlacierPickaxeItem(Item.Properties properties) {
        super(TOOL_MATERIAL, 7.0f, -2.0f, properties);
    }

    @Override
    public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity entity) {
        boolean result = super.mineBlock(stack, level, state, pos, entity);
        GlacierToolUtil.chillingTouch(level, pos);
        return result;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, context, tooltip, flag);
        tooltip.add(Component.translatable("item.glacier_gear.glacier_tool.tooltip.chilling_touch").withStyle(ChatFormatting.AQUA));
    }
}
