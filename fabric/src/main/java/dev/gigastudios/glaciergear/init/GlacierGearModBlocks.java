package dev.gigastudios.glaciergear.init;

import dev.gigastudios.glaciergear.GlacierGearMod;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

public class GlacierGearModBlocks {

    public static final Block PERMAFROST_ORE = registerBlock("permafrost_ore",
            new DropExperienceBlock(UniformInt.of(2, 5),
                    BlockBehaviour.Properties.of().mapColor(MapColor.ICE).strength(4.0f, 6.0f).requiresCorrectToolForDrops()));

    public static final Block PACKED_PERMAFROST = registerBlock("packed_permafrost",
            new Block(BlockBehaviour.Properties.of().mapColor(MapColor.ICE).strength(0.8f).friction(0.98f)));

    public static final Block GLACIAL_CRYSTAL_BLOCK = registerBlock("glacial_crystal_block",
            new Block(BlockBehaviour.Properties.of().mapColor(MapColor.ICE).strength(1.5f).lightLevel(state -> 10).noOcclusion()));

    public static final Item PERMAFROST_ORE_ITEM = registerBlockItem("permafrost_ore", PERMAFROST_ORE);
    public static final Item PACKED_PERMAFROST_ITEM = registerBlockItem("packed_permafrost", PACKED_PERMAFROST);
    public static final Item GLACIAL_CRYSTAL_BLOCK_ITEM = registerBlockItem("glacial_crystal_block", GLACIAL_CRYSTAL_BLOCK);

    public static void init() {
    }

    private static Block registerBlock(String name, Block block) {
        ResourceKey<Block> key = ResourceKey.create(Registries.BLOCK,
                ResourceLocation.fromNamespaceAndPath(GlacierGearMod.MODID, name));
        return Registry.register(BuiltInRegistries.BLOCK, key, block);
    }

    private static Item registerBlockItem(String name, Block block) {
        ResourceKey<Item> key = ResourceKey.create(Registries.ITEM,
                ResourceLocation.fromNamespaceAndPath(GlacierGearMod.MODID, name));
        return Registry.register(BuiltInRegistries.ITEM, key, new BlockItem(block, new Item.Properties().setId(key)));
    }
}
