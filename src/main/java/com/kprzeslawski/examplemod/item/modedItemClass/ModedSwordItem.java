package com.kprzeslawski.examplemod.item.modedItemClass;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.kprzeslawski.examplemod.item.modedItemClass.modedItemComponents.EnergizeUpgradeCost;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeMod;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public class ModedSwordItem extends SwordItem {

    public static final String ENERGIZE_TAG = "ENERGIZE_LEVEL";
    private final List<? extends Multimap<Attribute, AttributeModifier>> modifiers;
    public final List<EnergizeUpgradeCost> upgrade_ingredients;

    public ModedSwordItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties, List<ReinforcedLevelProps> attributes, List<EnergizeUpgradeCost> upgradeIngredients) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
        if(attributes.size() != upgradeIngredients.size())
            throw new RuntimeException("Incorrect definition of upgrade props and ingredients in modSwordItemClass");

        modifiers = attributes.stream()
                .map(arg -> {
                    ImmutableMultimap.Builder<Attribute, AttributeModifier> res = ImmutableMultimap.builder();
                    res.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", arg.attack_speed, AttributeModifier.Operation.ADDITION));
                    res.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", arg.attack_dmg, AttributeModifier.Operation.ADDITION));

                    res.put(ForgeMod.ENTITY_REACH.get(), new AttributeModifier(new UUID(1,1),"Weapon modifier r", arg.range_bonus,AttributeModifier.Operation.ADDITION));
                    res.put(ForgeMod.BLOCK_REACH.get(), new AttributeModifier(new UUID(1,1),"Weapon modifier r", arg.range_bonus,AttributeModifier.Operation.ADDITION));
                    return res.build();
                }).toList();
        
        upgrade_ingredients = upgradeIngredients;
    }

    public @NotNull ItemStack getDefaultInstance() {
        ItemStack instance = new ItemStack(this);
        instance.getOrCreateTag().putInt(ENERGIZE_TAG,1);

        return instance;
    }

    public @NotNull ItemStack getInstance(int en_level) {
        ItemStack instance = new ItemStack(this);
        instance.getOrCreateTag().putInt(ENERGIZE_TAG, en_level);

        return instance;
    }

    @Override
    public void onCraftedBy(ItemStack pStack, @NotNull Level pLevel, Player pPlayer) {
        pStack.getOrCreateTag().putInt(ENERGIZE_TAG,1);
        super.onCraftedBy(pStack, pLevel, pPlayer);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        if(slot != EquipmentSlot.MAINHAND)
            return super.getDefaultAttributeModifiers(slot);

        int reinforce_level = stack.getOrCreateTag().getInt(ENERGIZE_TAG);

        if(reinforce_level > modifiers.size() || reinforce_level < 1)return super.getDefaultAttributeModifiers(slot);

        return modifiers.get(reinforce_level - 1);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {

        if(pStack.getOrCreateTag().getInt(ENERGIZE_TAG) > 0)
            pTooltipComponents.add(
                    Component.literal("Energized Lv. " + pStack.getOrCreateTag().getInt(ENERGIZE_TAG))
                            .withStyle(ChatFormatting.DARK_PURPLE)
                            .withStyle(ChatFormatting.ITALIC)
            );

        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    public boolean isUpgradable(ItemStack itemStack){
        return itemStack.getOrCreateTag().getInt(ENERGIZE_TAG) > 0 &&
            itemStack.getOrCreateTag().getInt(ENERGIZE_TAG) < upgrade_ingredients.size();
    }
    public EnergizeUpgradeCost getNextUpgradeCost(ItemStack itemStack){
        if(!isUpgradable(itemStack))
            new EnergizeUpgradeCost(null,0);
        return upgrade_ingredients.get(itemStack.getOrCreateTag().getInt(ENERGIZE_TAG)-1);
    }

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
