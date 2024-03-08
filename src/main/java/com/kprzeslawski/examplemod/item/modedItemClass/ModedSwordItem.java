package com.kprzeslawski.examplemod.item.modedItemClass;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraftforge.common.ForgeMod;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.UUID;

public class ModedSwordItem extends SwordItem {

    public static class ReinforcedLevelProps {
            public double attack_dmg;
            public double attack_speed;
            public double range_bonus;

        public ReinforcedLevelProps(double attack_dmg, double attack_speed, double range_bonus) {
            this.attack_dmg = attack_dmg;
            this.attack_speed = attack_speed;
            this.range_bonus = range_bonus;
        }
    }
    private final List<ReinforcedLevelProps> attributes;

    public ModedSwordItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties, List<ReinforcedLevelProps> attributes) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
        this.attributes = attributes;
    }

    public @NotNull ItemStack getDefaultInstance() {
        ItemStack instance = new ItemStack(this);
        instance.getOrCreateTag().putInt("REINFORCE_LEVEL",1);

        return instance;
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        if(slot != EquipmentSlot.MAINHAND)
            return super.getDefaultAttributeModifiers(slot);

        int reinforce_level = stack.getOrCreateTag().getInt("REINFORCE_LEVEL");

        if(reinforce_level > attributes.size())return super.getDefaultAttributeModifiers(slot);

        ReinforcedLevelProps props = attributes.get(reinforce_level-1);

        Multimap<Attribute, AttributeModifier> res = ArrayListMultimap.create();
        res.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", props.attack_speed, AttributeModifier.Operation.ADDITION));
        res.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", props.attack_dmg, AttributeModifier.Operation.ADDITION));

        res.put(ForgeMod.ENTITY_REACH.get(), new AttributeModifier(new UUID(1,1),"Weapon modifier", props.range_bonus,AttributeModifier.Operation.ADDITION));
        res.put(ForgeMod.BLOCK_REACH.get(), new AttributeModifier(new UUID(1,1),"Weapon modifier", props.range_bonus,AttributeModifier.Operation.ADDITION));

        return res;
    }


// TODO:
//  1.in progression add item ability to upgrade via essence + test stats change on craft and def_instance
//  2.add to constructor map of stats on each level
//  3.


//
//    @Override
//    public void onCraftedBy(ItemStack pStack, Level pLevel, Player pPlayer) {
//        super.onCraftedBy(pStack, pLevel, pPlayer);
//        if(!pStack.getOrCreateTag().contains("Item_modifiers", 9))
//            pStack.addTagElement("Item_modifiers", new ListTag());
//
//        ListTag listtag = pStack.getOrCreateTag().getList("Item_modifiers", 10);
//
//        listtag.add(store_modifier("AD",DamageRandomizer.getRandDmg(6,6)));
//    }
//
//    public static CompoundTag store_modifier(String key, int value){
//        CompoundTag compoundtag = new CompoundTag();
//        compoundtag.putInt(key, value);
//        compoundtag.putInt("hp",5);
//        return compoundtag;
//    }
//
//    @Override
//    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
//        if(slot != EquipmentSlot.MAINHAND)return super.getDefaultAttributeModifiers(slot);
//
//        Multimap<Attribute, AttributeModifier> res = ArrayListMultimap.create();
//        res.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", 0.2, AttributeModifier.Operation.ADDITION));
//
//
//        if(!stack.getOrCreateTag().contains("Item_modifiers", 9)) {
//            res.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", (double)123, AttributeModifier.Operation.ADDITION));
//            return res;
//        }
//        ListTag listtag = stack.getOrCreateTag().getList("Item_modifiers", 10);
//
//        int dmg = listtag.getCompound(0).getInt("AD");
//
//        res.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", (double)dmg, AttributeModifier.Operation.ADDITION));
//        res.put(Attributes.MAX_HEALTH, new AttributeModifier(new UUID(44,44), "Weapon modifier", (double)dmg, AttributeModifier.Operation.ADDITION));
//        res.put(ForgeMod.STEP_HEIGHT_ADDITION.get(), new AttributeModifier(new UUID(1,1), "Weapon modifier", (double)100, AttributeModifier.Operation.ADDITION));
//
//        return res;
//
//    }
}
