package com.kprzeslawski.examplemod.entity.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.Level;

public class EnZombie extends Zombie {
    public String displayedName;
    public EnZombie(EntityType<? extends Zombie> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        displayedName = getName().getString();
        this.setCustomName(this.getNameAndHealthComponent());
        this.setCustomNameVisible(true);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMobAttributes()
                .add(Attributes.FOLLOW_RANGE, 35.0)
                .add(Attributes.MAX_HEALTH, 20)
                .add(Attributes.MOVEMENT_SPEED, 0.23000000417232513)
                .add(Attributes.ATTACK_DAMAGE, 3.0)
                .add(Attributes.ARMOR,2.0)
                .add(Attributes.SPAWN_REINFORCEMENTS_CHANCE);
    }

    @Override
    public boolean hurt(DamageSource pSource, float pAmount) {
        boolean doHurt = super.hurt(pSource, pAmount);
        this.setCustomName(this.getNameAndHealthComponent());
        return doHurt;
    }

    public Component getNameAndHealthComponent(){
        return Component
                .literal(displayedName + " ")
                .withStyle(ChatFormatting.AQUA)
                .append(getHealthComponent());
    }

    public Component getHealthComponent(){
        float currentHealth = this.getHealth();
        float maxHealth = this.getMaxHealth();
        ChatFormatting healthColor;
        float percentageHealth = currentHealth / maxHealth;
        if(percentageHealth > 0.7) healthColor = ChatFormatting.GREEN;
        else if (percentageHealth > 0.3) healthColor = ChatFormatting.GOLD;
        else healthColor = ChatFormatting.RED;

        return Component.literal(healthString()).withStyle(healthColor);
    }
    public String healthString(){
        return this.getHealth() + "/" + this.getMaxHealth();
    }



}
