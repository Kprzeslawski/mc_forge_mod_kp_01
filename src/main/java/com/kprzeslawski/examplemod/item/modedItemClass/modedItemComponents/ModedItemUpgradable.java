package com.kprzeslawski.examplemod.item.modedItemClass.modedItemComponents;

import com.google.common.collect.Multimap;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public interface ModedItemUpgradable {
    public static final String ENERGIZE_TAG = "ENERGIZE_LEVEL";
    public List<? extends Multimap<Attribute, AttributeModifier>> modifiers = null;
    public List<EnergizeUpgradeCost> upgrade_ingredients = null;

    public boolean isUpgradable(ItemStack itemStack);
    public EnergizeUpgradeCost getNextUpgradeCost(ItemStack itemStack);

}
