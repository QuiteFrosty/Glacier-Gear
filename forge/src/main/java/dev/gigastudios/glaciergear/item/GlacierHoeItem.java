package dev.gigastudios.glaciergear.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;

public class GlacierHoeItem extends HoeItem {

    private static final ToolMaterial TOOL_MATERIAL = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 660,
            9.0f, 0.0f, 28,
            TagKey.create(Registries.ITEM, ResourceLocation.parse("glacier_gear:glacier_hoe_repair_items")));

    public GlacierHoeItem(Item.Properties properties) {
        super(TOOL_MATERIAL, 1.0f, 2.0f, properties);
    }
}
