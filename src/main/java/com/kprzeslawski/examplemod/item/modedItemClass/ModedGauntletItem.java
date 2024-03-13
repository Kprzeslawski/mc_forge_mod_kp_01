package com.kprzeslawski.examplemod.item.modedItemClass;

import com.kprzeslawski.examplemod.item.modedItemClass.modedItemComponents.EnergizeUpgradeCost;
import com.kprzeslawski.examplemod.item.modedItemClass.modedItemComponents.ReinforcedLevelProps;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ModedGauntletItem extends ModedSwordItem{
    public ModedGauntletItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties, List<ReinforcedLevelProps> attributes, List<EnergizeUpgradeCost> upgradeIngredients) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties, attributes, upgradeIngredients);
    }

    public @NotNull UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.SPEAR;
    }

    public int getUseDuration(ItemStack pStack) {
        return 72000;
    }

    public void releaseUsing(ItemStack pStack, Level pLevel, LivingEntity pEntityLiving, int pTimeLeft) {
        if (pEntityLiving instanceof Player $$4) {
            int $$5 = this.getUseDuration(pStack) - pTimeLeft;
            if ($$5 >= 10) {
                int $$6 = EnchantmentHelper.getRiptide(pStack);
                    if (!pLevel.isClientSide) {
                        pStack.hurtAndBreak(1, $$4, (p_43388_) -> {
                            p_43388_.broadcastBreakEvent(pEntityLiving.getUsedItemHand());
                        });
                    }

                        float $$8 = $$4.getYRot();
                        float $$9 = $$4.getXRot();
                        float $$10 = -Mth.sin($$8 * 0.017453292F) * Mth.cos($$9 * 0.017453292F);
                        float $$11 = -Mth.sin($$9 * 0.017453292F);
                        float $$12 = Mth.cos($$8 * 0.017453292F) * Mth.cos($$9 * 0.017453292F);
                        float $$13 = Mth.sqrt($$10 * $$10 + $$11 * $$11 + $$12 * $$12);
                        float $$14 = 16.0F * ((1.0F + (float)$$6) / 4.0F);
                        $$10 *= $$14 / $$13;
                        $$11 *= $$14 / $$13;
                        $$12 *= $$14 / $$13;
                        $$4.push((double)$$10, (double)$$11, (double)$$12);
                        $$4.startAutoSpinAttack(20);

                        if ($$4.onGround()) {
                            float $$15 = 1.1999999F;
                            $$4.move(MoverType.SELF, new Vec3(0.0, 1.1999999284744263, 0.0));
                        }

                        pLevel.playSound((Player)null, $$4, SoundEvents.TRIDENT_RIPTIDE_1, SoundSource.PLAYERS, 1.0F, 1.0F);

            }
        }
    }

    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        ItemStack $$3 = pPlayer.getItemInHand(pHand);
        pPlayer.startUsingItem(pHand);
        return InteractionResultHolder.consume($$3);
    }
}
