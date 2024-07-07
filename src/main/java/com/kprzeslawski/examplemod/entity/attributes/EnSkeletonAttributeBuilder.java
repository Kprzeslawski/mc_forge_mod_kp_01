package com.kprzeslawski.examplemod.entity.attributes;

import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;

public class EnSkeletonAttributeBuilder {
    public static AttributeSupplier.Builder attributesL1() {
        return Monster.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20)
                .add(Attributes.MOVEMENT_SPEED, 0.23000000417232513)
                .add(Attributes.ATTACK_DAMAGE, 3.0)
                .add(Attributes.ARMOR,2.0);
    }
    public static AttributeSupplier.Builder attributesL2() {
        return Monster.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 40)
                .add(Attributes.MOVEMENT_SPEED, 0.23000000417232513)
                .add(Attributes.ATTACK_DAMAGE, 3.0)
                .add(Attributes.ARMOR,3.0);
    }
    public static AttributeSupplier.Builder attributesL3() {
        return Monster.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 60)
                .add(Attributes.MOVEMENT_SPEED, 0.23000000417232513)
                .add(Attributes.ATTACK_DAMAGE, 4.0)
                .add(Attributes.ARMOR,3.0);
    }
    public static AttributeSupplier.Builder attributesL4() {
        return Monster.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 80)
                .add(Attributes.MOVEMENT_SPEED, 0.23000000417232513)
                .add(Attributes.ATTACK_DAMAGE, 4.0)
                .add(Attributes.ARMOR,4.0);
    }
    public static AttributeSupplier.Builder attributesL5() {
        return Monster.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 100)
                .add(Attributes.MOVEMENT_SPEED, 0.23000000417232513)
                .add(Attributes.ATTACK_DAMAGE, 5.0)
                .add(Attributes.ARMOR,4.0);
    }
    public static AttributeSupplier.Builder attributesL6() {
        return Monster.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 120)
                .add(Attributes.MOVEMENT_SPEED, 0.23000000417232513)
                .add(Attributes.ATTACK_DAMAGE, 7.0)
                .add(Attributes.ARMOR,5.0);
    }

}
