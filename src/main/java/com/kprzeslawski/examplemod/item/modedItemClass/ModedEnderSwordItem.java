package com.kprzeslawski.examplemod.item.modedItemClass;

import com.kprzeslawski.examplemod.item.modedItemClass.modedItemComponents.EnergizeUpgradeCost;
import com.kprzeslawski.examplemod.item.modedItemClass.modedItemComponents.ReinforcedLevelProps;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
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

    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        ItemStack $$3 = pPlayer.getItemInHand(pHand);
        pPlayer.getCooldowns().addCooldown(this, 40);

        double d0 = pPlayer.getX();
        double d1 = pPlayer.getY();
        double d2 = pPlayer.getZ();

        float ry = pPlayer.getYRot();
        float rx = pPlayer.getXRot();

        for(int i = 0; i < 16; i++){
            double destX = d0 + (16 - i) * -Mth.sin(ry * 0.017453292F) * Mth.cos(rx * 0.017453292F);
            double destY = d1 + Math.max((16 - i) * -Mth.sin(rx * 0.017453292F),0.F);
            double destZ = d2 + (16 - i) * Mth.cos(ry * 0.017453292F) * Mth.cos(rx * 0.017453292F);

            if(pPlayer.randomTeleport(destX,destY,destZ,true))
                break;
        }

        return InteractionResultHolder.consume($$3);
    }

}
