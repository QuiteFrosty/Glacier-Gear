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
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.ToolMaterial;

public class PermafrostSwordItem extends SwordItem {

    private static final ToolMaterial TOOL_MATERIAL = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 900,
            10.0f, 0.0f, 32,
            TagKey.create(Registries.ITEM, ResourceLocation.parse("glacier_gear:permafrost_sword_repair_items")));

    private static final float FROZEN_TARGET_BONUS_DAMAGE = 4.0f;

    public PermafrostSwordItem(Item.Properties properties) {
        super(TOOL_MATERIAL, 14.0f, -1.0f, properties);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        boolean wasFrozenSolid = GlacierCombatUtil.isFrozenSolid(target);
        boolean result = super.hurtEnemy(stack, target, attacker);
        if (wasFrozenSolid) {
            target.hurt(target.damageSources().magic(), FROZEN_TARGET_BONUS_DAMAGE);
        }
        GlacierCombatUtil.stackFrostbitePermafrost(target);
        return result;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, context, tooltip, flag);
        tooltip.add(Component.translatable("item.glacier_gear.permafrost_weapon.tooltip.frostbite").withStyle(ChatFormatting.AQUA));
        tooltip.add(Component.translatable("item.glacier_gear.permafrost_weapon.tooltip.shatter").withStyle(ChatFormatting.AQUA));
    }
}
