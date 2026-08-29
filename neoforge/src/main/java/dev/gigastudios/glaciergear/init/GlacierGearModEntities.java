package dev.gigastudios.glaciergear.init;

import dev.gigastudios.glaciergear.GlacierGearMod;
import dev.gigastudios.glaciergear.entity.FrostWraithEntity;
import dev.gigastudios.glaciergear.entity.SnowHareEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Stray;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.SpawnPlacementRegisterEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class GlacierGearModEntities {

    public static final DeferredRegister<EntityType<?>> REGISTRY =
            DeferredRegister.create(net.minecraft.core.registries.Registries.ENTITY_TYPE, GlacierGearMod.MODID);

    public static final DeferredHolder<EntityType<?>, EntityType<FrostWraithEntity>> FROST_WRAITH = REGISTRY.register(
            "frost_wraith",
            () -> EntityType.Builder.of(FrostWraithEntity::new, MobCategory.MONSTER)
                    .sized(0.6f, 1.99f)
                    .build("frost_wraith"));

    public static final DeferredHolder<EntityType<?>, EntityType<SnowHareEntity>> SNOW_HARE = REGISTRY.register(
            "snow_hare",
            () -> EntityType.Builder.of(SnowHareEntity::new, MobCategory.CREATURE)
                    .sized(0.4f, 0.5f)
                    .build("snow_hare"));

    public static final DeferredItem<Item> FROST_WRAITH_SPAWN_EGG = GlacierGearModItems.REGISTRY.registerItem(
            "frost_wraith_spawn_egg",
            properties -> new net.minecraft.world.item.SpawnEggItem(FROST_WRAITH.get(), properties),
            new Item.Properties());

    public static final DeferredItem<Item> SNOW_HARE_SPAWN_EGG = GlacierGearModItems.REGISTRY.registerItem(
            "snow_hare_spawn_egg",
            properties -> new net.minecraft.world.item.SpawnEggItem(SNOW_HARE.get(), properties),
            new Item.Properties());

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(FROST_WRAITH.get(), Stray.createAttributes().build());
        event.put(SNOW_HARE.get(), Rabbit.createAttributes().build());
    }

    @SubscribeEvent
    public static void registerSpawnPlacement(SpawnPlacementRegisterEvent event) {
        event.register(FROST_WRAITH.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Monster::checkMonsterSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(SNOW_HARE.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Rabbit::checkRabbitSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
    }
}
