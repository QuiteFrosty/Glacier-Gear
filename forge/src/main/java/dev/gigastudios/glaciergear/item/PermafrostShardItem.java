package dev.gigastudios.glaciergear.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class PermafrostShardItem extends Item {
    public PermafrostShardItem(Item.Properties properties) {
        super(properties.rarity(Rarity.RARE));
    }
}
