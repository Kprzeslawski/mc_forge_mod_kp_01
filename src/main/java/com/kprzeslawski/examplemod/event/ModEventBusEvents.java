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
        event.put(ModEntities.EN_ZOMBIE.get(), EnZombieAttributesBuilder.createAttributes().build());
    }
}
