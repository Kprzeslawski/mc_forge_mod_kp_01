package com.kprzeslawski.examplemod.item.modedItemClass.modedItemComponents;

import com.google.common.collect.Multimap;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public interface ModedItemUpgradable {

    public final List<? extends Multimap<Attribute, AttributeModifier>> modifiers = null;
    public final List<EnergizeUpgradeCost> upgrade_ingredients = null;

    public boolean isUpgradable(ItemStack itemStack);
    public EnergizeUpgradeCost getNextUpgradeCost(ItemStack itemStack);

}
