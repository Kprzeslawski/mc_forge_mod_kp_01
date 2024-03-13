package com.kprzeslawski.examplemod.item.modedItemClass.modedItemComponents;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.common.ForgeMod;

import java.util.UUID;

public class ReinforcedLevelProps {
    public double attack_dmg;
    public double attack_speed;
    public double range_bonus;

    public ReinforcedLevelProps(double attack_dmg, double attack_speed, double range_bonus) {
        this.attack_dmg = attack_dmg;
        this.attack_speed = attack_speed;
        this.range_bonus = range_bonus;
    }

    public Multimap<Attribute, AttributeModifier> convertToAttributeModifier(
            UUID damageUUID,
            UUID aSpeedUUID
    ){
        ImmutableMultimap.Builder<Attribute, AttributeModifier> res = ImmutableMultimap.builder();
        res.put(Attributes.ATTACK_SPEED, new AttributeModifier(aSpeedUUID, "Weapon modifier", this.attack_speed, AttributeModifier.Operation.ADDITION));
        res.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(damageUUID, "Weapon modifier", this.attack_dmg, AttributeModifier.Operation.ADDITION));
        res.put(ForgeMod.ENTITY_REACH.get(), new AttributeModifier(new UUID(1,1),"Weapon modifier", this.range_bonus,AttributeModifier.Operation.ADDITION));
        res.put(ForgeMod.BLOCK_REACH.get(), new AttributeModifier(new UUID(1,1),"Weapon modifier", this.range_bonus,AttributeModifier.Operation.ADDITION));
        return res.build();
    }
}