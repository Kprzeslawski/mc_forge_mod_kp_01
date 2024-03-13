package com.kprzeslawski.examplemod.item.modedItemClass.modedItemComponents;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.kprzeslawski.examplemod.item.modedItemClass.ModedSwordItem;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.common.ForgeMod;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ReinforcedLevelProps {
    private Map<String,Double> map;
    private UUID damageUUID = UUID.randomUUID();
    private UUID aSpeedUUID = UUID.randomUUID();
    private UUID armorUUID = UUID.randomUUID();

    private static final String AD_KEY = "ad";
    private static final String AS_KEY = "as";
    private static final String AR_KEY = "ar";
    private static final String HP_KEY = "hp";
    private static final String ARM_KEY = "arm";
    private static final String ARM_T_KEY = "at";
    private static final String KR_KEY = "kr";
    private static final String SH_KEY = "sh";
    public ReinforcedLevelProps(){
        map = new HashMap<>();
    }

    public ReinforcedLevelProps setBaseUUID(){
        this.damageUUID = ModedSwordItem.getAUUID();
        this.aSpeedUUID = ModedSwordItem.getASUUID();
        return this;
    }

    public ReinforcedLevelProps setArmorUUID(UUID armorUUID) {
        this.armorUUID = armorUUID;
        return this;
    }

    public Multimap<Attribute, AttributeModifier> getMap(){

        ImmutableMultimap.Builder<Attribute, AttributeModifier> res = ImmutableMultimap.builder();

        //also define how they are displayed (in which order)

        if(map.containsKey(AD_KEY))res.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(damageUUID, "Weapon modifier", map.get(AD_KEY), AttributeModifier.Operation.ADDITION));
        if(map.containsKey(AS_KEY))res.put(Attributes.ATTACK_SPEED, new AttributeModifier(aSpeedUUID, "Weapon modifier", map.get(AS_KEY), AttributeModifier.Operation.ADDITION));

        if(map.containsKey(ARM_KEY))res.put(Attributes.ARMOR, new AttributeModifier(armorUUID, "Armor modifier", map.get(ARM_KEY), AttributeModifier.Operation.ADDITION));
        if(map.containsKey(ARM_T_KEY))res.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(armorUUID, "Armor modifier", map.get(ARM_T_KEY), AttributeModifier.Operation.ADDITION));
        if(map.containsKey(HP_KEY) && map.get(HP_KEY) > 0)res.put(Attributes.MAX_HEALTH, new AttributeModifier(armorUUID,"Armor modifier", map.get(HP_KEY),AttributeModifier.Operation.ADDITION));

        if(map.containsKey(AR_KEY)){
            res.put(ForgeMod.ENTITY_REACH.get(), new AttributeModifier(new UUID(1,1),"Weapon modifier", map.get(AR_KEY),AttributeModifier.Operation.ADDITION));
            res.put(ForgeMod.BLOCK_REACH.get(), new AttributeModifier(new UUID(1,1),"Weapon modifier", map.get(AR_KEY),AttributeModifier.Operation.ADDITION));
        }
        if(map.containsKey(KR_KEY) && map.get(KR_KEY) > 0)res.put(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(armorUUID, "Armor modifier", map.get(KR_KEY), AttributeModifier.Operation.ADDITION));
        if(map.containsKey(SH_KEY))res.put(ForgeMod.STEP_HEIGHT_ADDITION.get(), new AttributeModifier(armorUUID, "Armor modifier", map.get(SH_KEY), AttributeModifier.Operation.ADDITION));

        return res.build();
    }

    public ReinforcedLevelProps aD(double ad){
        if(map.containsKey(AD_KEY))map.replace(AD_KEY, ad + map.get(AD_KEY));
        else map.put(AD_KEY,ad);
        return this;
    }

    public ReinforcedLevelProps aS(double as){
        if(map.containsKey(AS_KEY))map.replace(AS_KEY, as + map.get(AS_KEY));
        else map.put(AS_KEY,as);
        return this;
    }

    public ReinforcedLevelProps aR(double ar){
        if(map.containsKey(AR_KEY))map.replace(AR_KEY, ar + map.get(AR_KEY));
        else map.put(AR_KEY,ar);
        return this;
    }
    public ReinforcedLevelProps aHp(double hp){
        if(map.containsKey(HP_KEY))map.replace(HP_KEY, hp + map.get(HP_KEY));
        else map.put(HP_KEY,hp);
        return this;
    }
    public ReinforcedLevelProps aArm(double arm){
        if(map.containsKey(ARM_KEY))map.replace(ARM_KEY, arm + map.get(ARM_KEY));
        else map.put(ARM_KEY,arm);
        return this;
    }
    public ReinforcedLevelProps aArmT(double armT){
        if(map.containsKey(ARM_T_KEY))map.replace(ARM_T_KEY, armT + map.get(ARM_T_KEY));
        else map.put(ARM_T_KEY,armT);
        return this;
    }
    public ReinforcedLevelProps aKr(double kr){
        if(map.containsKey(KR_KEY))map.replace(KR_KEY, kr + map.get(KR_KEY));
        else map.put(KR_KEY,kr);
        return this;
    }
    public ReinforcedLevelProps aSh(double sh){
        if(map.containsKey(SH_KEY))map.replace(SH_KEY, sh + map.get(SH_KEY));
        else map.put(SH_KEY,sh);
        return this;
    }
}