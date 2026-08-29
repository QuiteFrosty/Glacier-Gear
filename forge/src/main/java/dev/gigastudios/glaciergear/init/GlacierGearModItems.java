package dev.gigastudios.glaciergear.init;

import dev.gigastudios.glaciergear.GlacierGearMod;
import dev.gigastudios.glaciergear.item.FrostLinedLeatherItem;
import dev.gigastudios.glaciergear.item.GlacierArmorItem;
import dev.gigastudios.glaciergear.item.GlacierAxeItem;
import dev.gigastudios.glaciergear.item.GlacierHoeItem;
import dev.gigastudios.glaciergear.item.GlacierPickaxeItem;
import dev.gigastudios.glaciergear.item.GlacierShovelItem;
import dev.gigastudios.glaciergear.item.GlacierSwordItem;
import dev.gigastudios.glaciergear.item.IceCubeItem;
import dev.gigastudios.glaciergear.item.IceExtractorItem;
import dev.gigastudios.glaciergear.item.IceFurItem;
import dev.gigastudios.glaciergear.item.IceShardItem;
import dev.gigastudios.glaciergear.item.PackedIceExtractorItem;
import dev.gigastudios.glaciergear.item.PermafrostArmorItem;
import dev.gigastudios.glaciergear.item.PermafrostAxeItem;
import dev.gigastudios.glaciergear.item.PermafrostHoeItem;
import dev.gigastudios.glaciergear.item.PermafrostPickaxeItem;
import dev.gigastudios.glaciergear.item.PermafrostShardItem;
import dev.gigastudios.glaciergear.item.PermafrostShovelItem;
import dev.gigastudios.glaciergear.item.PermafrostSwordItem;
import java.util.function.Function;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
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

    // --- 3.0.0: Permafrost tier + new materials ---
    public static final RegistryObject<Item> PERMAFROST_SHARD = register("permafrost_shard", PermafrostShardItem::new);
    public static final RegistryObject<Item> ICE_FUR = register("ice_fur", IceFurItem::new);
    public static final RegistryObject<Item> FROST_LINED_LEATHER = register("frost_lined_leather", FrostLinedLeatherItem::new);
    public static final RegistryObject<Item> PERMAFROST_ARMOR_HELMET = register("permafrost_armor_helmet", PermafrostArmorItem.Helmet::new);
    public static final RegistryObject<Item> PERMAFROST_ARMOR_CHESTPLATE = register("permafrost_armor_chestplate", PermafrostArmorItem.Chestplate::new);
    public static final RegistryObject<Item> PERMAFROST_ARMOR_LEGGINGS = register("permafrost_armor_leggings", PermafrostArmorItem.Leggings::new);
    public static final RegistryObject<Item> PERMAFROST_ARMOR_BOOTS = register("permafrost_armor_boots", PermafrostArmorItem.Boots::new);
    public static final RegistryObject<Item> PERMAFROST_PICKAXE = register("permafrost_pickaxe", PermafrostPickaxeItem::new);
    public static final RegistryObject<Item> PERMAFROST_AXE = register("permafrost_axe", PermafrostAxeItem::new);
    public static final RegistryObject<Item> PERMAFROST_SWORD = register("permafrost_sword", PermafrostSwordItem::new);
    public static final RegistryObject<Item> PERMAFROST_SHOVEL = register("permafrost_shovel", PermafrostShovelItem::new);
    public static final RegistryObject<Item> PERMAFROST_HOE = register("permafrost_hoe", PermafrostHoeItem::new);

    private static <I extends Item> RegistryObject<I> register(String name, Function<Item.Properties, ? extends I> supplier) {
        return REGISTRY.register(name, () -> supplier.apply(new Item.Properties()));
    }

    static <B extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<B> block) {
        return REGISTRY.register(name, () -> new net.minecraft.world.item.BlockItem(block.get(), new Item.Properties()));
    }
}
