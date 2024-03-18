package com.kprzeslawski.examplemod.world;

import com.kprzeslawski.examplemod.ExampleMod;
import com.kprzeslawski.examplemod.world.effect.ModMobEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.UUID;

public class ModMobEffects {

    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, ExampleMod.MOD_ID);

    public static final RegistryObject<MobEffect> BONUS_ARMOR =
            MOB_EFFECTS.register("bonus_armor", () -> (new ModMobEffect(
                    MobEffectCategory.BENEFICIAL, 14270531
            )).addAttributeModifier(Attributes.ARMOR, UUID.randomUUID().toString() , 4 , AttributeModifier.Operation.ADDITION));
    public static void register(IEventBus eventBus){
        MOB_EFFECTS.register(eventBus);
    }

}
