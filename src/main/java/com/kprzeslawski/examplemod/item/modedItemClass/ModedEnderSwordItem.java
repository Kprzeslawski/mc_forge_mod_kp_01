package com.kprzeslawski.examplemod.item.modedItemClass;

import com.kprzeslawski.examplemod.item.modedItemClass.modedItemComponents.EnergizeUpgradeCost;
import com.kprzeslawski.examplemod.item.modedItemClass.modedItemComponents.ReinforcedLevelProps;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.UseAnim;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ModedEnderSwordItem  extends ModedSwordItem {
    public ModedEnderSwordItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties, List<ReinforcedLevelProps> attributes, List<EnergizeUpgradeCost> upgradeIngredients) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties, attributes, upgradeIngredients);
    }

    public @NotNull UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.NONE;
    }

    public int getUseDuration(ItemStack pStack) {
        return 3000;
    }
}
