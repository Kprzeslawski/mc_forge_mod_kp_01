package com.kprzeslawski.examplemod.event;

import com.kprzeslawski.examplemod.ExampleMod;
import com.kprzeslawski.examplemod.entity.ModEntities;
import com.kprzeslawski.examplemod.entity.attributes.EnZombieAttributesBuilder;
import com.kprzeslawski.examplemod.entity.custom.EnZombie;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ExampleMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.EN_ZOMBIE_L1.get(), EnZombieAttributesBuilder.attributesL1().build());
        event.put(ModEntities.EN_ZOMBIE_L2.get(), EnZombieAttributesBuilder.attributesL2().build());
        event.put(ModEntities.EN_ZOMBIE_L3.get(), EnZombieAttributesBuilder.attributesL3().build());
        event.put(ModEntities.EN_ZOMBIE_L4.get(), EnZombieAttributesBuilder.attributesL4().build());
        event.put(ModEntities.EN_ZOMBIE_L5.get(), EnZombieAttributesBuilder.attributesL5().build());
        event.put(ModEntities.EN_ZOMBIE_L6.get(), EnZombieAttributesBuilder.attributesL6().build());

        event.put(ModEntities.EN_SKELETON_L1.get(), EnZombieAttributesBuilder.attributesL1().build());
        event.put(ModEntities.EN_SKELETON_L2.get(), EnZombieAttributesBuilder.attributesL2().build());
        event.put(ModEntities.EN_SKELETON_L3.get(), EnZombieAttributesBuilder.attributesL3().build());
        event.put(ModEntities.EN_SKELETON_L4.get(), EnZombieAttributesBuilder.attributesL4().build());
        event.put(ModEntities.EN_SKELETON_L5.get(), EnZombieAttributesBuilder.attributesL5().build());
        event.put(ModEntities.EN_SKELETON_L6.get(), EnZombieAttributesBuilder.attributesL6().build());
    }
}
