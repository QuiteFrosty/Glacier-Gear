package dev.gigastudios.glaciergear.item;

import dev.gigastudios.glaciergear.init.GlacierGearModItems;
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

// NOTE (verify locally - highest-risk file in this port): Mojang's 1.21.2+ equipment/armor-render
// rewrite (ArmorMaterial/EquipmentAssets, the assets/glacier_gear/equipment/glacier_armor.json layer
// file) is new enough that Forge's adoption of it for 1.21.4 may differ from NeoForge's. The original
// NeoForge build also registered client render extensions here via RegisterClientExtensionsEvent
// (net.neoforged.neoforge.client.extensions.common); Forge's equivalent lives under
// net.minecraftforge.client.extensions.common.IClientItemExtensions / RegisterClientExtensionsEvent
// if the equipment JSON alone doesn't drive rendering under Forge - add it back here if armor
// textures don't show up in a local test client.
public abstract class GlacierArmorItem extends ArmorItem {

    public static final ArmorMaterial ARMOR_MATERIAL = new ArmorMaterial(30,
            Map.of(ArmorType.BOOTS, 4, ArmorType.LEGGINGS, 10, ArmorType.CHESTPLATE, 12, ArmorType.HELMET, 4, ArmorType.BODY, 12),
            18, BuiltInRegistries.SOUND_EVENT.wrapAsHolder(SoundEvents.EMPTY), 0.0f, 0.0f,
            TagKey.create(Registries.ITEM, ResourceLocation.parse("glacier_gear:glacier_armor_repair_items")),
            ResourceKey.create(EquipmentAssets.ROOT_ID, ResourceLocation.parse("glacier_gear:glacier_armor")));

    private GlacierArmorItem(ArmorType type, Item.Properties properties) {
        super(ARMOR_MATERIAL, type, properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, context, tooltip, flag);
        tooltip.add(Component.translatable("item.glacier_gear.glacier_armor.tooltip.frostbite").withStyle(ChatFormatting.AQUA));
        tooltip.add(Component.translatable("item.glacier_gear.glacier_armor.tooltip.set_bonus").withStyle(ChatFormatting.GRAY));
    }

    public static class Boots extends GlacierArmorItem {
        public Boots(Item.Properties properties) {
            super(ArmorType.BOOTS, properties);
        }
    }

    public static class Leggings extends GlacierArmorItem {
        public Leggings(Item.Properties properties) {
            super(ArmorType.LEGGINGS, properties);
        }
    }

    public static class Chestplate extends GlacierArmorItem {
        public Chestplate(Item.Properties properties) {
            super(ArmorType.CHESTPLATE, properties);
        }
    }

    public static class Helmet extends GlacierArmorItem {
        public Helmet(Item.Properties properties) {
            super(ArmorType.HELMET, properties);
        }
    }
}
