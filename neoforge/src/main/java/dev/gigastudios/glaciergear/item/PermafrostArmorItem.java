package dev.gigastudios.glaciergear.item;

import java.util.List;
import java.util.Map;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAssets;

/** Tier-2 armor: crafted by upgrading a Glacier piece with Permafrost Shards (see recipes). */
public abstract class PermafrostArmorItem extends ArmorItem {

    public static final ArmorMaterial ARMOR_MATERIAL = new ArmorMaterial(37,
            Map.of(ArmorType.BOOTS, 5, ArmorType.LEGGINGS, 13, ArmorType.CHESTPLATE, 15, ArmorType.HELMET, 5, ArmorType.BODY, 15),
            22, BuiltInRegistries.SOUND_EVENT.wrapAsHolder(SoundEvents.EMPTY), 2.0f, 0.0f,
            TagKey.create(Registries.ITEM, ResourceLocation.parse("glacier_gear:permafrost_armor_repair_items")),
            ResourceKey.create(EquipmentAssets.ROOT_ID, ResourceLocation.parse("glacier_gear:permafrost_armor")));

    private PermafrostArmorItem(ArmorType type, Item.Properties properties) {
        super(ARMOR_MATERIAL, type, properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, context, tooltip, flag);
        tooltip.add(Component.translatable("item.glacier_gear.permafrost_armor.tooltip.frostbite").withStyle(ChatFormatting.AQUA));
        tooltip.add(Component.translatable("item.glacier_gear.permafrost_armor.tooltip.set_bonus").withStyle(ChatFormatting.GRAY));
    }

    public static class Boots extends PermafrostArmorItem {
        public Boots(Item.Properties properties) {
            super(ArmorType.BOOTS, properties);
        }
    }

    public static class Leggings extends PermafrostArmorItem {
        public Leggings(Item.Properties properties) {
            super(ArmorType.LEGGINGS, properties);
        }
    }

    public static class Chestplate extends PermafrostArmorItem {
        public Chestplate(Item.Properties properties) {
            super(ArmorType.CHESTPLATE, properties);
        }
    }

    public static class Helmet extends PermafrostArmorItem {
        public Helmet(Item.Properties properties) {
            super(ArmorType.HELMET, properties);
        }
    }
}
