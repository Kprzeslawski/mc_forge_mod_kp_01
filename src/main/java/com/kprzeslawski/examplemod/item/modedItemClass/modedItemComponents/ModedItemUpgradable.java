package com.kprzeslawski.examplemod.item.modedItemClass.modedItemComponents;

import net.minecraft.world.item.ItemStack;

public interface ModedItemUpgradable {
    public static final String ENERGIZE_TAG = "ENERGIZE_LEVEL";

    public boolean isNUpgradable(ItemStack itemStack);
    public EnergizeUpgradeCost getNextUpgradeCost(ItemStack itemStack);

}
