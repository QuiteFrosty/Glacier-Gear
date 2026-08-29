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
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = GlacierGearMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class GlacierGearModEntities {

    public static final DeferredRegister<EntityType<?>> REGISTRY =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, GlacierGearMod.MODID);

    public static final RegistryObject<EntityType<FrostWraithEntity>> FROST_WRAITH = REGISTRY.register(
            "frost_wraith",
            () -> EntityType.Builder.of(FrostWraithEntity::new, MobCategory.MONSTER)
                    .sized(0.6f, 1.99f)
                    .build("frost_wraith"));

    public static final RegistryObject<EntityType<SnowHareEntity>> SNOW_HARE = REGISTRY.register(
            "snow_hare",
            () -> EntityType.Builder.of(SnowHareEntity::new, MobCategory.CREATURE)
                    .sized(0.4f, 0.5f)
                    .build("snow_hare"));

    public static final RegistryObject<Item> FROST_WRAITH_SPAWN_EGG = GlacierGearModItems.REGISTRY.register(
            "frost_wraith_spawn_egg", () -> new SpawnEggItem(FROST_WRAITH.get(), new Item.Properties()));

    public static final RegistryObject<Item> SNOW_HARE_SPAWN_EGG = GlacierGearModItems.REGISTRY.register(
            "snow_hare_spawn_egg", () -> new SpawnEggItem(SNOW_HARE.get(), new Item.Properties()));

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(FROST_WRAITH.get(), Stray.createAttributes().build());
        event.put(SNOW_HARE.get(), Rabbit.createAttributes().build());
    }

    // NOTE (verify locally): Forge historically wired spawn placement via
    // FMLCommonSetupEvent#enqueueWork calling SpawnPlacements.register(...) directly rather than
    // NeoForge's SpawnPlacementRegisterEvent. If this exact call site doesn't compile, move it into
    // GlacierGearMod's FMLCommonSetupEvent handler using that pattern instead.
    public static void registerSpawnPlacements() {
        net.minecraft.world.entity.SpawnPlacements.register(FROST_WRAITH.get(), SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
        net.minecraft.world.entity.SpawnPlacements.register(SNOW_HARE.get(), SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Rabbit::checkRabbitSpawnRules);
    }
}
