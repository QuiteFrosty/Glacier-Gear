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
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class GlacierGearModItems {

    public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, GlacierGearMod.MODID);

    public static final RegistryObject<Item> ICE_SHARD = register("ice_shard", IceShardItem::new);
    public static final RegistryObject<Item> ICE_CUBE = register("ice_cube", IceCubeItem::new);
    public static final RegistryObject<Item> GLACIER_ARMOR_HELMET = register("glacier_armor_helmet", GlacierArmorItem.Helmet::new);
    public static final RegistryObject<Item> GLACIER_ARMOR_CHESTPLATE = register("glacier_armor_chestplate", GlacierArmorItem.Chestplate::new);
    public static final RegistryObject<Item> GLACIER_ARMOR_LEGGINGS = register("glacier_armor_leggings", GlacierArmorItem.Leggings::new);
    public static final RegistryObject<Item> GLACIER_ARMOR_BOOTS = register("glacier_armor_boots", GlacierArmorItem.Boots::new);
    public static final RegistryObject<Item> ICE_EXTRACTOR = register("ice_extractor", IceExtractorItem::new);
    public static final RegistryObject<Item> GLACIER_PICKAXE = register("glacier_pickaxe", GlacierPickaxeItem::new);
    public static final RegistryObject<Item> GLACIER_AXE = register("glacier_axe", GlacierAxeItem::new);
    public static final RegistryObject<Item> GLACIER_SWORD = register("glacier_sword", GlacierSwordItem::new);
    public static final RegistryObject<Item> GLACIER_SHOVEL = register("glacier_shovel", GlacierShovelItem::new);
    public static final RegistryObject<Item> GLACIER_HOE = register("glacier_hoe", GlacierHoeItem::new);
    public static final RegistryObject<Item> PACKED_ICE_EXTRACTOR = register("packed_ice_extractor", PackedIceExtractorItem::new);

    private static <I extends Item> RegistryObject<I> register(String name, Function<Item.Properties, ? extends I> supplier) {
        return REGISTRY.register(name, () -> supplier.apply(new Item.Properties()));
    }
}
