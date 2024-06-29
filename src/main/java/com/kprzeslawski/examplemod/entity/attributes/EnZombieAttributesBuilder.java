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
    public static AttributeSupplier.Builder attributesL2() {
        return baseEnZombieAttributes()
                .add(Attributes.MAX_HEALTH, 30)
                .add(Attributes.MOVEMENT_SPEED, 0.23000000417232513)
                .add(Attributes.ATTACK_DAMAGE, 4.0)
                .add(Attributes.ARMOR,2.0);
    }
    public static AttributeSupplier.Builder attributesL3() {
        return baseEnZombieAttributes()
                .add(Attributes.MAX_HEALTH, 40)
                .add(Attributes.MOVEMENT_SPEED, 0.23000000417232513)
                .add(Attributes.ATTACK_DAMAGE, 5.0)
                .add(Attributes.ARMOR,3.0);
    }
    public static AttributeSupplier.Builder attributesL4() {
        return baseEnZombieAttributes()
                .add(Attributes.MAX_HEALTH, 60)
                .add(Attributes.MOVEMENT_SPEED, 0.33000000417232513)
                .add(Attributes.ATTACK_DAMAGE, 6.0)
                .add(Attributes.ARMOR,3.0);
    }
    public static AttributeSupplier.Builder attributesL5() {
        return baseEnZombieAttributes()
                .add(Attributes.MAX_HEALTH, 90)
                .add(Attributes.MOVEMENT_SPEED, 0.33000000417232513)
                .add(Attributes.ATTACK_DAMAGE, 8.0)
                .add(Attributes.ARMOR,5.0);
    }
    public static AttributeSupplier.Builder attributesL6() {
        return baseEnZombieAttributes()
                .add(Attributes.MAX_HEALTH, 120)
                .add(Attributes.MOVEMENT_SPEED, 0.43000000417232513)
                .add(Attributes.ATTACK_DAMAGE, 10.0)
                .add(Attributes.ARMOR,7.0);
    }

}
