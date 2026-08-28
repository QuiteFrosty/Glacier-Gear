package dev.gigastudios.glaciergear.item;

import dev.gigastudios.glaciergear.util.GlacierCombatUtil;
import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.ToolMaterial;

public class GlacierAxeItem extends AxeItem {

    private static final ToolMaterial TOOL_MATERIAL = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 660,
            9.0f, 0.0f, 28,
            TagKey.create(Registries.ITEM, ResourceLocation.parse("glacier_gear:glacier_axe_repair_items")));

    public GlacierAxeItem(Item.Properties properties) {
        super(TOOL_MATERIAL, 17.0f, -2.0f, properties);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        boolean result = super.hurtEnemy(stack, target, attacker);
        GlacierCombatUtil.stackFrostbite(target);
        return result;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, context, tooltip, flag);
        tooltip.add(Component.translatable("item.glacier_gear.glacier_weapon.tooltip.frostbite").withStyle(ChatFormatting.AQUA));
    }
}
