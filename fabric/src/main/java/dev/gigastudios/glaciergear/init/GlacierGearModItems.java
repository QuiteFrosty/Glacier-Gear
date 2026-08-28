package dev.gigastudios.glaciergear.init;

import dev.gigastudios.glaciergear.GlacierGearMod;
import dev.gigastudios.glaciergear.item.GlacierArmorItem;
import dev.gigastudios.glaciergear.item.GlacierAxeItem;
import dev.gigastudios.glaciergear.item.GlacierHoeItem;
import dev.gigastudios.glaciergear.item.GlacierPickaxeItem;
import dev.gigastudios.glaciergear.item.GlacierShovelItem;
import dev.gigastudios.glaciergear.item.GlacierSwordItem;
import dev.gigastudios.glaciergear.item.IceCubeItem;
import dev.gigastudios.glaciergear.item.IceExtractorItem;
import dev.gigastudios.glaciergear.item.IceShardItem;
import dev.gigastudios.glaciergear.item.PackedIceExtractorItem;
import java.util.function.Function;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class GlacierGearModItems {

    public static final Item ICE_SHARD = register("ice_shard", IceShardItem::new);
    public static final Item ICE_CUBE = register("ice_cube", IceCubeItem::new);
    public static final Item GLACIER_ARMOR_HELMET = register("glacier_armor_helmet", GlacierArmorItem.Helmet::new);
    public static final Item GLACIER_ARMOR_CHESTPLATE = register("glacier_armor_chestplate", GlacierArmorItem.Chestplate::new);
    public static final Item GLACIER_ARMOR_LEGGINGS = register("glacier_armor_leggings", GlacierArmorItem.Leggings::new);
    public static final Item GLACIER_ARMOR_BOOTS = register("glacier_armor_boots", GlacierArmorItem.Boots::new);
    public static final Item ICE_EXTRACTOR = register("ice_extractor", IceExtractorItem::new);
    public static final Item GLACIER_PICKAXE = register("glacier_pickaxe", GlacierPickaxeItem::new);
    public static final Item GLACIER_AXE = register("glacier_axe", GlacierAxeItem::new);
    public static final Item GLACIER_SWORD = register("glacier_sword", GlacierSwordItem::new);
    public static final Item GLACIER_SHOVEL = register("glacier_shovel", GlacierShovelItem::new);
    public static final Item GLACIER_HOE = register("glacier_hoe", GlacierHoeItem::new);
    public static final Item PACKED_ICE_EXTRACTOR = register("packed_ice_extractor", PackedIceExtractorItem::new);

    // Forces this class (and therefore every field above) to class-load from GlacierGearMod#onInitialize.
    public static void init() {
    }

    private static <I extends Item> I register(String name, Function<Item.Properties, I> factory) {
        ResourceKey<Item> key = ResourceKey.create(Registries.ITEM,
                ResourceLocation.fromNamespaceAndPath(GlacierGearMod.MODID, name));
        I item = factory.apply(new Item.Properties().setId(key));
        return Registry.register(BuiltInRegistries.ITEM, key, item);
    }
}
