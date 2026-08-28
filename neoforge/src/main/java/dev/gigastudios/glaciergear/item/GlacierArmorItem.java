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
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public abstract class GlacierArmorItem extends ArmorItem {

    public static final ArmorMaterial ARMOR_MATERIAL = new ArmorMaterial(30,
            Map.of(ArmorType.BOOTS, 4, ArmorType.LEGGINGS, 10, ArmorType.CHESTPLATE, 12, ArmorType.HELMET, 4, ArmorType.BODY, 12),
            18, BuiltInRegistries.SOUND_EVENT.wrapAsHolder(SoundEvents.EMPTY), 0.0f, 0.0f,
            TagKey.create(Registries.ITEM, ResourceLocation.parse("glacier_gear:glacier_armor_repair_items")),
            ResourceKey.create(EquipmentAssets.ROOT_ID, ResourceLocation.parse("glacier_gear:glacier_armor")));

    @SubscribeEvent
    public static void registerItemExtensions(RegisterClientExtensionsEvent event) {
        IClientItemExtensions extensions = new IClientItemExtensions() {
        };
        event.registerItem(extensions, GlacierGearModItems.GLACIER_ARMOR_HELMET.get());
        event.registerItem(extensions, GlacierGearModItems.GLACIER_ARMOR_CHESTPLATE.get());
        event.registerItem(extensions, GlacierGearModItems.GLACIER_ARMOR_LEGGINGS.get());
        event.registerItem(extensions, GlacierGearModItems.GLACIER_ARMOR_BOOTS.get());
    }

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
