package com.kprzeslawski.examplemod.item.modedItemClass;

import com.google.common.collect.Multimap;
import com.kprzeslawski.examplemod.item.modedItemClass.modedItemComponents.EnergizeUpgradeCost;
import com.kprzeslawski.examplemod.item.modedItemClass.modedItemComponents.ModedItemUpgradable;
import com.kprzeslawski.examplemod.item.modedItemClass.modedItemComponents.ReinforcedLevelProps;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public class ModedSwordItem extends SwordItem implements ModedItemUpgradable {

    private final List<Multimap<Attribute, AttributeModifier>> modifiers;
    public final List<EnergizeUpgradeCost> upgrade_ingredients;

    public ModedSwordItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties, List<ReinforcedLevelProps> attributes, List<EnergizeUpgradeCost> upgradeIngredients) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
        if(attributes.size() != upgradeIngredients.size())
            throw new RuntimeException("Incorrect definition of upgrade props and ingredients in modSwordItemClass");

        this.modifiers = attributes.stream().map(ReinforcedLevelProps::getMap).toList();
        this.upgrade_ingredients = upgradeIngredients;
    }

    public static UUID getAUUID(){
        return BASE_ATTACK_DAMAGE_UUID;
    }

    public static UUID getASUUID(){
        return BASE_ATTACK_DAMAGE_UUID;
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

    public boolean isNUpgradable(ItemStack itemStack){
        return itemStack.getOrCreateTag().getInt(ENERGIZE_TAG) <= 0 ||
                itemStack.getOrCreateTag().getInt(ENERGIZE_TAG) >= upgrade_ingredients.size();
    }
    public EnergizeUpgradeCost getNextUpgradeCost(ItemStack itemStack){
        if(isNUpgradable(itemStack))
            new EnergizeUpgradeCost(null,0);
        return upgrade_ingredients.get(itemStack.getOrCreateTag().getInt(ENERGIZE_TAG)-1);
    }

}
