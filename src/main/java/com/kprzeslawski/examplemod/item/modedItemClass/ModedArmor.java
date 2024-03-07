package com.kprzeslawski.examplemod.item.modedItemClass;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.Util;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraftforge.common.ForgeMod;

import java.util.EnumMap;
import java.util.UUID;

public class ModedArmor extends ArmorItem {
    private static final EnumMap<Type, UUID> ARMOR_MODIFIER_UUID_PER_TYPE = Util.make(new EnumMap<>(ArmorItem.Type.class), (p_266744_) -> {
        p_266744_.put(ArmorItem.Type.BOOTS, UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B"));
        p_266744_.put(ArmorItem.Type.LEGGINGS, UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D"));
        p_266744_.put(ArmorItem.Type.CHESTPLATE, UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E"));
        p_266744_.put(ArmorItem.Type.HELMET, UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150"));
    });
    protected final ArmorItem.Type type;
    private final int defense;
    private final float toughness;
    protected final float knockbackResistance;
    protected final ArmorMaterial material;
    private final Multimap<Attribute, AttributeModifier> defaultModifiers;
    public ModedArmor(ArmorMaterial pMaterial, Type pType, Properties pProperties) {
        super(pMaterial, pType, pProperties);
        this.material = pMaterial;
        this.type = pType;
        this.defense = pMaterial.getDefenseForType(pType);
        this.toughness = pMaterial.getToughness();
        this.knockbackResistance = pMaterial.getKnockbackResistance();
        DispenserBlock.registerBehavior(this, DISPENSE_ITEM_BEHAVIOR);
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        UUID uuid = ARMOR_MODIFIER_UUID_PER_TYPE.get(pType);
        builder.put(Attributes.ARMOR, new AttributeModifier(uuid, "Armor modifier", (double)this.defense, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(uuid, "Armor toughness", (double)this.toughness, AttributeModifier.Operation.ADDITION));
        if (this.knockbackResistance > 0) {
            builder.put(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(uuid, "Armor knockback resistance", (double)this.knockbackResistance, AttributeModifier.Operation.ADDITION));
        }
        switch(this.type){
            case BOOTS:
                builder.put(ForgeMod.STEP_HEIGHT_ADDITION.get(), new AttributeModifier(uuid,"Boots step bonus",0.5f,AttributeModifier.Operation.ADDITION));
                builder.put(Attributes.MAX_HEALTH, new AttributeModifier(uuid, "Bonus hp modifier", 4, AttributeModifier.Operation.ADDITION));
                break;
            case LEGGINGS:
                builder.put(Attributes.MAX_HEALTH, new AttributeModifier(uuid, "Bonus hp modifier", 6, AttributeModifier.Operation.ADDITION));
                break;
            case CHESTPLATE:
                builder.put(Attributes.MAX_HEALTH, new AttributeModifier(uuid, "Bonus hp modifier", 6, AttributeModifier.Operation.ADDITION));
                break;
            case HELMET:
                builder.put(ForgeMod.ENTITY_REACH.get(), new AttributeModifier(uuid,"Helmet reach bonus",1f,AttributeModifier.Operation.ADDITION));
                builder.put(ForgeMod.BLOCK_REACH.get(), new AttributeModifier(uuid,"Helmet reach bonus",1f,AttributeModifier.Operation.ADDITION));
                builder.put(Attributes.MAX_HEALTH, new AttributeModifier(uuid, "Bonus hp modifier", 4, AttributeModifier.Operation.ADDITION));
                break;
        }

        this.defaultModifiers = builder.build();
    }

    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot pEquipmentSlot) {
        return pEquipmentSlot == this.type.getSlot() ? this.defaultModifiers : super.getDefaultAttributeModifiers(pEquipmentSlot);
    }
}
