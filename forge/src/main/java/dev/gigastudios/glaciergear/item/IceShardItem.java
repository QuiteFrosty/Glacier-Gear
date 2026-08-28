package dev.gigastudios.glaciergear.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class IceShardItem extends Item {
    public IceShardItem(Item.Properties properties) {
        super(properties.rarity(Rarity.UNCOMMON));
    }
}
