package dev.gigastudios.glaciergear.init;

import dev.gigastudios.glaciergear.GlacierGearMod;
import net.fabricmc.fabric.api.item.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

public class GlacierGearModTabs {

    public static final ResourceKey<CreativeModeTab> GLACIER_GEAR_KEY = ResourceKey.create(Registries.CREATIVE_MODE_TAB,
            ResourceLocation.fromNamespaceAndPath(GlacierGearMod.MODID, "glacier_gear"));

    public static final CreativeModeTab GLACIER_GEAR = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, GLACIER_GEAR_KEY,
            FabricItemGroup.builder()
                    .title(Component.translatable("item_group.glacier_gear.glacier_gear"))
                    .icon(() -> new ItemStack((ItemLike) GlacierGearModItems.ICE_CUBE))
                    .displayItems((parameters, tabData) -> {
                        tabData.accept((ItemLike) GlacierGearModItems.ICE_SHARD);
                        tabData.accept((ItemLike) GlacierGearModItems.ICE_CUBE);
                        tabData.accept((ItemLike) GlacierGearModItems.GLACIER_ARMOR_HELMET);
                        tabData.accept((ItemLike) GlacierGearModItems.GLACIER_ARMOR_CHESTPLATE);
                        tabData.accept((ItemLike) GlacierGearModItems.GLACIER_ARMOR_LEGGINGS);
                        tabData.accept((ItemLike) GlacierGearModItems.GLACIER_ARMOR_BOOTS);
                        tabData.accept((ItemLike) GlacierGearModItems.ICE_EXTRACTOR);
                        tabData.accept((ItemLike) GlacierGearModItems.GLACIER_PICKAXE);
                        tabData.accept((ItemLike) GlacierGearModItems.GLACIER_AXE);
                        tabData.accept((ItemLike) GlacierGearModItems.GLACIER_SWORD);
                        tabData.accept((ItemLike) GlacierGearModItems.GLACIER_SHOVEL);
                        tabData.accept((ItemLike) GlacierGearModItems.GLACIER_HOE);
                        tabData.accept((ItemLike) GlacierGearModItems.PACKED_ICE_EXTRACTOR);

                        tabData.accept((ItemLike) GlacierGearModItems.PERMAFROST_SHARD);
                        tabData.accept((ItemLike) GlacierGearModItems.ICE_FUR);
                        tabData.accept((ItemLike) GlacierGearModItems.FROST_LINED_LEATHER);
                        tabData.accept((ItemLike) GlacierGearModItems.PERMAFROST_ARMOR_HELMET);
                        tabData.accept((ItemLike) GlacierGearModItems.PERMAFROST_ARMOR_CHESTPLATE);
                        tabData.accept((ItemLike) GlacierGearModItems.PERMAFROST_ARMOR_LEGGINGS);
                        tabData.accept((ItemLike) GlacierGearModItems.PERMAFROST_ARMOR_BOOTS);
                        tabData.accept((ItemLike) GlacierGearModItems.PERMAFROST_PICKAXE);
                        tabData.accept((ItemLike) GlacierGearModItems.PERMAFROST_AXE);
                        tabData.accept((ItemLike) GlacierGearModItems.PERMAFROST_SWORD);
                        tabData.accept((ItemLike) GlacierGearModItems.PERMAFROST_SHOVEL);
                        tabData.accept((ItemLike) GlacierGearModItems.PERMAFROST_HOE);

                        tabData.accept((ItemLike) GlacierGearModBlocks.PERMAFROST_ORE_ITEM);
                        tabData.accept((ItemLike) GlacierGearModBlocks.PACKED_PERMAFROST_ITEM);
                        tabData.accept((ItemLike) GlacierGearModBlocks.GLACIAL_CRYSTAL_BLOCK_ITEM);

                        tabData.accept((ItemLike) GlacierGearModEntities.FROST_WRAITH_SPAWN_EGG);
                        tabData.accept((ItemLike) GlacierGearModEntities.SNOW_HARE_SPAWN_EGG);
                    })
                    .build());

    public static void init() {
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.COMBAT).register(entries -> {
            entries.accept((ItemLike) GlacierGearModItems.GLACIER_ARMOR_HELMET);
            entries.accept((ItemLike) GlacierGearModItems.GLACIER_ARMOR_CHESTPLATE);
            entries.accept((ItemLike) GlacierGearModItems.GLACIER_ARMOR_LEGGINGS);
            entries.accept((ItemLike) GlacierGearModItems.GLACIER_ARMOR_BOOTS);
            entries.accept((ItemLike) GlacierGearModItems.GLACIER_SWORD);
            entries.accept((ItemLike) GlacierGearModItems.PERMAFROST_ARMOR_HELMET);
            entries.accept((ItemLike) GlacierGearModItems.PERMAFROST_ARMOR_CHESTPLATE);
            entries.accept((ItemLike) GlacierGearModItems.PERMAFROST_ARMOR_LEGGINGS);
            entries.accept((ItemLike) GlacierGearModItems.PERMAFROST_ARMOR_BOOTS);
            entries.accept((ItemLike) GlacierGearModItems.PERMAFROST_SWORD);
        });
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(entries -> {
            entries.accept((ItemLike) GlacierGearModItems.ICE_EXTRACTOR);
            entries.accept((ItemLike) GlacierGearModItems.GLACIER_PICKAXE);
            entries.accept((ItemLike) GlacierGearModItems.GLACIER_AXE);
            entries.accept((ItemLike) GlacierGearModItems.GLACIER_SHOVEL);
            entries.accept((ItemLike) GlacierGearModItems.GLACIER_HOE);
            entries.accept((ItemLike) GlacierGearModItems.PACKED_ICE_EXTRACTOR);
            entries.accept((ItemLike) GlacierGearModItems.PERMAFROST_PICKAXE);
            entries.accept((ItemLike) GlacierGearModItems.PERMAFROST_AXE);
            entries.accept((ItemLike) GlacierGearModItems.PERMAFROST_SHOVEL);
            entries.accept((ItemLike) GlacierGearModItems.PERMAFROST_HOE);
        });
    }
}
