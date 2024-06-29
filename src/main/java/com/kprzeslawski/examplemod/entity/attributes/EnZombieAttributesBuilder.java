package com.kprzeslawski.examplemod.entity.attributes;

import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;

public class EnZombieAttributesBuilder {

    public static AttributeSupplier.Builder baseEnZombieAttributes(){
        return Monster.createMobAttributes()
                .add(Attributes.FOLLOW_RANGE, 35.0)
                .add(Attributes.SPAWN_REINFORCEMENTS_CHANCE);
    }
    public static AttributeSupplier.Builder attributesL1() {
        return baseEnZombieAttributes()
                .add(Attributes.MAX_HEALTH, 20)
                .add(Attributes.MOVEMENT_SPEED, 0.23000000417232513)
                .add(Attributes.ATTACK_DAMAGE, 3.0)
                .add(Attributes.ARMOR,2.0);
    }
}
