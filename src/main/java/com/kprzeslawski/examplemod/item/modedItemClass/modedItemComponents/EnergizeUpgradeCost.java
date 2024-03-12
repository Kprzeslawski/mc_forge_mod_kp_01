package com.kprzeslawski.examplemod.item.modedItemClass.modedItemComponents;

import net.minecraft.world.item.Item;
import org.jetbrains.annotations.Nullable;

public class EnergizeUpgradeCost {
    @Nullable
    public Item upgrade_crystal;
    public int upg_count;

    public EnergizeUpgradeCost(@Nullable Item item, int i) {
        upgrade_crystal = item;
        upg_count = i;
    }
}
