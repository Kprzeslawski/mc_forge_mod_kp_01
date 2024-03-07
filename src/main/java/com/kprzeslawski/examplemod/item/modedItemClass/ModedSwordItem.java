package com.kprzeslawski.examplemod.item.modedItemClass;

import com.google.common.collect.Multimap;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import org.jetbrains.annotations.NotNull;

public class ModedSwordItem extends SwordItem {
    public ModedSwordItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    public @NotNull ItemStack getDefaultInstance() {
        return new ItemStack(this);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        return super.getAttributeModifiers(slot, stack);
    }
// TODO in progression add item ability to upgrade via essence
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
