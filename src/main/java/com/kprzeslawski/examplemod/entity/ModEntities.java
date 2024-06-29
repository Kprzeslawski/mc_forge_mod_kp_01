package com.kprzeslawski.examplemod.entity;

import com.kprzeslawski.examplemod.ExampleMod;
import com.kprzeslawski.examplemod.entity.custom.EnZombie;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, ExampleMod.MOD_ID);

    public static final RegistryObject<EntityType<EnZombie>> EN_ZOMBIE_L1 =
            ENTITY_TYPES.register("en_zombie_l1", () -> EntityType.Builder.of(EnZombie::new, MobCategory.MONSTER)
                    .sized(1f,2f).build("en_zombie_l1"));
    public static final RegistryObject<EntityType<EnZombie>> EN_ZOMBIE_L2 =
            ENTITY_TYPES.register("en_zombie_l2", () -> EntityType.Builder.of(EnZombie::new, MobCategory.MONSTER)
                    .sized(1f,2f).build("en_zombie_l2"));
    public static final RegistryObject<EntityType<EnZombie>> EN_ZOMBIE_L3 =
            ENTITY_TYPES.register("en_zombie_l3", () -> EntityType.Builder.of(EnZombie::new, MobCategory.MONSTER)
                    .sized(1f,2f).build("en_zombie_l3"));
    public static final RegistryObject<EntityType<EnZombie>> EN_ZOMBIE_L4 =
            ENTITY_TYPES.register("en_zombie_l4", () -> EntityType.Builder.of(EnZombie::new, MobCategory.MONSTER)
                    .sized(1f,2f).build("en_zombie_l4"));
    public static final RegistryObject<EntityType<EnZombie>> EN_ZOMBIE_L5 =
            ENTITY_TYPES.register("en_zombie_l5", () -> EntityType.Builder.of(EnZombie::new, MobCategory.MONSTER)
                    .sized(1f,2f).build("en_zombie_l5"));
    public static final RegistryObject<EntityType<EnZombie>> EN_ZOMBIE_L6 =
            ENTITY_TYPES.register("en_zombie_l6", () -> EntityType.Builder.of(EnZombie::new, MobCategory.MONSTER)
                    .sized(1f,2f).build("en_zombie_l6"));



    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
