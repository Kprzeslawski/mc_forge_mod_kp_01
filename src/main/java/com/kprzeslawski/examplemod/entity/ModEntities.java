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

    public static final RegistryObject<EntityType<EnZombie>> EN_ZOMBIE =
            ENTITY_TYPES.register("en_zombie", () -> EntityType.Builder.of(EnZombie::new, MobCategory.MONSTER)
                    .sized(1f,2f).build("en_zombie"));



    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
