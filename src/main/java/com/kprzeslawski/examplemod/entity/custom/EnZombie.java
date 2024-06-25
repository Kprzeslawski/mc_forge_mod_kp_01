package com.kprzeslawski.examplemod.entity.custom;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.Level;

public class EnZombie extends Zombie {
    public EnZombie(EntityType<? extends Zombie> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

}
