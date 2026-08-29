package dev.gigastudios.glaciergear.init;

import dev.gigastudios.glaciergear.GlacierGearMod;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class GlacierGearModBlocks {

    public static final DeferredRegister.Blocks REGISTRY = DeferredRegister.createBlocks(GlacierGearMod.MODID);

    public static final DeferredBlock<Block> PERMAFROST_ORE = REGISTRY.register("permafrost_ore",
            () -> new DropExperienceBlock(UniformInt.of(2, 5),
                    BlockBehaviour.Properties.of().mapColor(MapColor.ICE).strength(4.0f, 6.0f).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> PACKED_PERMAFROST = REGISTRY.register("packed_permafrost",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.ICE).strength(0.8f).friction(0.98f)));

    public static final DeferredBlock<Block> GLACIAL_CRYSTAL_BLOCK = REGISTRY.register("glacial_crystal_block",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.ICE).strength(1.5f).lightLevel(state -> 10).noOcclusion()));

    public static final DeferredItem<Item> PERMAFROST_ORE_ITEM = GlacierGearModItems.registerBlockItem("permafrost_ore", PERMAFROST_ORE);
    public static final DeferredItem<Item> PACKED_PERMAFROST_ITEM = GlacierGearModItems.registerBlockItem("packed_permafrost", PACKED_PERMAFROST);
    public static final DeferredItem<Item> GLACIAL_CRYSTAL_BLOCK_ITEM = GlacierGearModItems.registerBlockItem("glacial_crystal_block", GLACIAL_CRYSTAL_BLOCK);
}
