package dev.gigastudios.glaciergear.init;

import dev.gigastudios.glaciergear.GlacierGearMod;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class GlacierGearModBlocks {

    public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, GlacierGearMod.MODID);

    public static final RegistryObject<Block> PERMAFROST_ORE = REGISTRY.register("permafrost_ore",
            () -> new DropExperienceBlock(UniformInt.of(2, 5),
                    BlockBehaviour.Properties.of().mapColor(MapColor.ICE).strength(4.0f, 6.0f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> PACKED_PERMAFROST = REGISTRY.register("packed_permafrost",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.ICE).strength(0.8f).friction(0.98f)));

    public static final RegistryObject<Block> GLACIAL_CRYSTAL_BLOCK = REGISTRY.register("glacial_crystal_block",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.ICE).strength(1.5f).lightLevel(state -> 10).noOcclusion()));

    public static final RegistryObject<net.minecraft.world.item.Item> PERMAFROST_ORE_ITEM =
            GlacierGearModItems.registerBlockItem("permafrost_ore", PERMAFROST_ORE);
    public static final RegistryObject<net.minecraft.world.item.Item> PACKED_PERMAFROST_ITEM =
            GlacierGearModItems.registerBlockItem("packed_permafrost", PACKED_PERMAFROST);
    public static final RegistryObject<net.minecraft.world.item.Item> GLACIAL_CRYSTAL_BLOCK_ITEM =
            GlacierGearModItems.registerBlockItem("glacial_crystal_block", GLACIAL_CRYSTAL_BLOCK);
}
