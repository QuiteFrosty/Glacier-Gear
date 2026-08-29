package dev.gigastudios.glaciergear.init;

import dev.gigastudios.glaciergear.GlacierGearMod;
import dev.gigastudios.glaciergear.entity.FrostWraithEntity;
import dev.gigastudios.glaciergear.entity.SnowHareEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Stray;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.levelgen.Heightmap;

public class GlacierGearModEntities {

    public static final EntityType<FrostWraithEntity> FROST_WRAITH = register("frost_wraith",
            EntityType.Builder.of(FrostWraithEntity::new, MobCategory.MONSTER).sized(0.6f, 1.99f));

    public static final EntityType<SnowHareEntity> SNOW_HARE = register("snow_hare",
            EntityType.Builder.of(SnowHareEntity::new, MobCategory.CREATURE).sized(0.4f, 0.5f));

    public static final Item FROST_WRAITH_SPAWN_EGG = registerSpawnEgg("frost_wraith_spawn_egg", FROST_WRAITH);
    public static final Item SNOW_HARE_SPAWN_EGG = registerSpawnEgg("snow_hare_spawn_egg", SNOW_HARE);

    public static void init() {
        FabricDefaultAttributeRegistry.register(FROST_WRAITH, Stray.createAttributes());
        FabricDefaultAttributeRegistry.register(SNOW_HARE, Rabbit.createAttributes());

        // NOTE (verify locally): calling vanilla SpawnPlacements.register directly from
        // onInitialize is a common simple-mod pattern on Fabric, but if it throws (the registry
        // may be frozen depending on load order), move this into a FabricLoader entrypoint that
        // runs earlier, or use whatever spawn-placement helper your Fabric API version ships.
        SpawnPlacements.register(FROST_WRAITH, SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
        SpawnPlacements.register(SNOW_HARE, SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Rabbit::checkRabbitSpawnRules);
    }

    private static <E extends net.minecraft.world.entity.Entity> EntityType<E> register(String name, EntityType.Builder<E> builder) {
        ResourceKey<EntityType<?>> key = ResourceKey.create(Registries.ENTITY_TYPE,
                ResourceLocation.fromNamespaceAndPath(GlacierGearMod.MODID, name));
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, key, builder.build(key));
    }

    private static Item registerSpawnEgg(String name, EntityType<? extends net.minecraft.world.entity.Mob> type) {
        ResourceKey<Item> key = ResourceKey.create(Registries.ITEM,
                ResourceLocation.fromNamespaceAndPath(GlacierGearMod.MODID, name));
        return Registry.register(BuiltInRegistries.ITEM, key, new SpawnEggItem(type, new Item.Properties().setId(key)));
    }
}
