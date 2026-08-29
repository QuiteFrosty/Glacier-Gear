package dev.gigastudios.glaciergear.init;

import dev.gigastudios.glaciergear.GlacierGearMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class GlacierGearModTabs {

    public static final DeferredRegister<CreativeModeTab> REGISTRY =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, GlacierGearMod.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> GLACIER_GEAR = REGISTRY.register("glacier_gear",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("item_group.glacier_gear.glacier_gear"))
                    .icon(() -> new ItemStack((ItemLike) GlacierGearModItems.ICE_CUBE.get()))
                    .displayItems((parameters, tabData) -> {
                        tabData.accept((ItemLike) GlacierGearModItems.ICE_SHARD.get());
                        tabData.accept((ItemLike) GlacierGearModItems.ICE_CUBE.get());
                        tabData.accept((ItemLike) GlacierGearModItems.GLACIER_ARMOR_HELMET.get());
                        tabData.accept((ItemLike) GlacierGearModItems.GLACIER_ARMOR_CHESTPLATE.get());
                        tabData.accept((ItemLike) GlacierGearModItems.GLACIER_ARMOR_LEGGINGS.get());
                        tabData.accept((ItemLike) GlacierGearModItems.GLACIER_ARMOR_BOOTS.get());
                        tabData.accept((ItemLike) GlacierGearModItems.ICE_EXTRACTOR.get());
                        tabData.accept((ItemLike) GlacierGearModItems.GLACIER_PICKAXE.get());
                        tabData.accept((ItemLike) GlacierGearModItems.GLACIER_AXE.get());
                        tabData.accept((ItemLike) GlacierGearModItems.GLACIER_SWORD.get());
                        tabData.accept((ItemLike) GlacierGearModItems.GLACIER_SHOVEL.get());
                        tabData.accept((ItemLike) GlacierGearModItems.GLACIER_HOE.get());
                        tabData.accept((ItemLike) GlacierGearModItems.PACKED_ICE_EXTRACTOR.get());

                        tabData.accept((ItemLike) GlacierGearModItems.PERMAFROST_SHARD.get());
                        tabData.accept((ItemLike) GlacierGearModItems.ICE_FUR.get());
                        tabData.accept((ItemLike) GlacierGearModItems.FROST_LINED_LEATHER.get());
                        tabData.accept((ItemLike) GlacierGearModItems.PERMAFROST_ARMOR_HELMET.get());
                        tabData.accept((ItemLike) GlacierGearModItems.PERMAFROST_ARMOR_CHESTPLATE.get());
                        tabData.accept((ItemLike) GlacierGearModItems.PERMAFROST_ARMOR_LEGGINGS.get());
                        tabData.accept((ItemLike) GlacierGearModItems.PERMAFROST_ARMOR_BOOTS.get());
                        tabData.accept((ItemLike) GlacierGearModItems.PERMAFROST_PICKAXE.get());
                        tabData.accept((ItemLike) GlacierGearModItems.PERMAFROST_AXE.get());
                        tabData.accept((ItemLike) GlacierGearModItems.PERMAFROST_SWORD.get());
                        tabData.accept((ItemLike) GlacierGearModItems.PERMAFROST_SHOVEL.get());
                        tabData.accept((ItemLike) GlacierGearModItems.PERMAFROST_HOE.get());

                        tabData.accept((ItemLike) GlacierGearModBlocks.PERMAFROST_ORE_ITEM.get());
                        tabData.accept((ItemLike) GlacierGearModBlocks.PACKED_PERMAFROST_ITEM.get());
                        tabData.accept((ItemLike) GlacierGearModBlocks.GLACIAL_CRYSTAL_BLOCK_ITEM.get());

                        tabData.accept((ItemLike) GlacierGearModEntities.FROST_WRAITH_SPAWN_EGG.get());
                        tabData.accept((ItemLike) GlacierGearModEntities.SNOW_HARE_SPAWN_EGG.get());
                    })
                    .build());

    @SubscribeEvent
    public static void buildTabContentsVanilla(BuildCreativeModeTabContentsEvent tabData) {
        if (tabData.getTabKey() == CreativeModeTabs.COMBAT) {
            tabData.accept((ItemLike) GlacierGearModItems.GLACIER_ARMOR_HELMET.get());
            tabData.accept((ItemLike) GlacierGearModItems.GLACIER_ARMOR_CHESTPLATE.get());
            tabData.accept((ItemLike) GlacierGearModItems.GLACIER_ARMOR_LEGGINGS.get());
            tabData.accept((ItemLike) GlacierGearModItems.GLACIER_ARMOR_BOOTS.get());
            tabData.accept((ItemLike) GlacierGearModItems.GLACIER_SWORD.get());
            tabData.accept((ItemLike) GlacierGearModItems.PERMAFROST_ARMOR_HELMET.get());
            tabData.accept((ItemLike) GlacierGearModItems.PERMAFROST_ARMOR_CHESTPLATE.get());
            tabData.accept((ItemLike) GlacierGearModItems.PERMAFROST_ARMOR_LEGGINGS.get());
            tabData.accept((ItemLike) GlacierGearModItems.PERMAFROST_ARMOR_BOOTS.get());
            tabData.accept((ItemLike) GlacierGearModItems.PERMAFROST_SWORD.get());
        } else if (tabData.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            tabData.accept((ItemLike) GlacierGearModItems.ICE_EXTRACTOR.get());
            tabData.accept((ItemLike) GlacierGearModItems.GLACIER_PICKAXE.get());
            tabData.accept((ItemLike) GlacierGearModItems.GLACIER_AXE.get());
            tabData.accept((ItemLike) GlacierGearModItems.GLACIER_SHOVEL.get());
            tabData.accept((ItemLike) GlacierGearModItems.GLACIER_HOE.get());
            tabData.accept((ItemLike) GlacierGearModItems.PACKED_ICE_EXTRACTOR.get());
            tabData.accept((ItemLike) GlacierGearModItems.PERMAFROST_PICKAXE.get());
            tabData.accept((ItemLike) GlacierGearModItems.PERMAFROST_AXE.get());
            tabData.accept((ItemLike) GlacierGearModItems.PERMAFROST_SHOVEL.get());
            tabData.accept((ItemLike) GlacierGearModItems.PERMAFROST_HOE.get());
        }
    }
}
