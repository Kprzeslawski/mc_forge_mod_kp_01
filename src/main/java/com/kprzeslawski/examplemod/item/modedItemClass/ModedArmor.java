package com.kprzeslawski.examplemod.item.modedItemClass;


import com.google.common.collect.Multimap;
import com.kprzeslawski.examplemod.item.modedItemClass.modedItemComponents.EnergizeUpgradeCost;
import com.kprzeslawski.examplemod.item.modedItemClass.modedItemComponents.ModedItemUpgradable;
import com.kprzeslawski.examplemod.item.modedItemClass.modedItemComponents.ReinforcedLevelProps;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.EnumMap;
import java.util.List;
import java.util.UUID;

public class ModedArmor extends ArmorItem implements ModedItemUpgradable {
    private static final EnumMap<Type, UUID> ARMOR_MODIFIER_UUID_PER_TYPE = Util.make(new EnumMap<>(ArmorItem.Type.class), (p_266744_) -> {
        p_266744_.put(ArmorItem.Type.BOOTS, UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B"));
        p_266744_.put(ArmorItem.Type.LEGGINGS, UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D"));
        p_266744_.put(ArmorItem.Type.CHESTPLATE, UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E"));
        p_266744_.put(ArmorItem.Type.HELMET, UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150"));
    });
    protected final ArmorItem.Type type;
    protected final ArmorMaterial material;
    private final List<Multimap<Attribute, AttributeModifier>> modifiers;
    public final List<EnergizeUpgradeCost> upgrade_ingredients;



    public ModedArmor(ArmorMaterial pMaterial, Type pType, Properties pProperties, List<ReinforcedLevelProps> attributes, List<EnergizeUpgradeCost> upgradeIngredients) {
        super(pMaterial, pType, pProperties);
        this.material = pMaterial;
        this.type = pType;

        UUID uuid = ARMOR_MODIFIER_UUID_PER_TYPE.get(pType);
        DispenserBlock.registerBehavior(this, DISPENSE_ITEM_BEHAVIOR);

        this.upgrade_ingredients = upgradeIngredients;

        if(attributes.size() != upgradeIngredients.size())
            throw new RuntimeException("Incorrect definition of upgrade props and ingredients in modSwordItemClass");

        this.modifiers = attributes.stream().map(
                arg -> {
                    arg.aArm((double)pMaterial.getDefenseForType(pType));
                    arg.aArmT((double)pMaterial.getToughness());
                    arg.aKr(pMaterial.getKnockbackResistance());
                    switch(this.type){
                        case BOOTS:
                            arg.aSh(0.5f);
                            break;
                        case LEGGINGS, CHESTPLATE:
                            arg.aHp(2.f);
                            break;
                        case HELMET:
                            arg.aR(0.5f);
                            break;
                    }
                    return arg.setArmorUUID(uuid).getMap();
                }
        ).toList();
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        if(slot != this.type.getSlot())
            return super.getDefaultAttributeModifiers(slot);

        int reinforce_level = stack.getOrCreateTag().getInt(ENERGIZE_TAG);
        if(reinforce_level > modifiers.size() || reinforce_level < 1)return super.getDefaultAttributeModifiers(slot);

        return modifiers.get(reinforce_level - 1);
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
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {

        if(pStack.getOrCreateTag().getInt(ENERGIZE_TAG) > 0)
            pTooltipComponents.add(
                    Component.literal("Energized Lv. " + pStack.getOrCreateTag().getInt(ENERGIZE_TAG))
                            .withStyle(ChatFormatting.DARK_PURPLE)
                            .withStyle(ChatFormatting.ITALIC)
            );

        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    @Override
    public boolean isNUpgradable(ItemStack itemStack){
        return itemStack.getOrCreateTag().getInt(ENERGIZE_TAG) <= 0 ||
                itemStack.getOrCreateTag().getInt(ENERGIZE_TAG) >= upgrade_ingredients.size();
    }

    @Override
    public EnergizeUpgradeCost getNextUpgradeCost(ItemStack itemStack){
        if(isNUpgradable(itemStack))
            new EnergizeUpgradeCost(null,0);
        return upgrade_ingredients.get(itemStack.getOrCreateTag().getInt(ENERGIZE_TAG)-1);
    }
}
