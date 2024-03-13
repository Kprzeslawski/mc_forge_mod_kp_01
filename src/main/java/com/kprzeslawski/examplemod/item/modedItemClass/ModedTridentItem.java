package com.kprzeslawski.examplemod.item.modedItemClass;

import com.google.common.collect.Multimap;
import com.kprzeslawski.examplemod.item.modedItemClass.modedItemComponents.EnergizeUpgradeCost;
import com.kprzeslawski.examplemod.item.modedItemClass.modedItemComponents.ModedItemUpgradable;
import com.kprzeslawski.examplemod.item.modedItemClass.modedItemComponents.ReinforcedLevelProps;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.TridentItem;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ModedTridentItem extends TridentItem implements ModedItemUpgradable {

    private final List<? extends Multimap<Attribute, AttributeModifier>> modifiers;
    public final List<EnergizeUpgradeCost> upgrade_ingredients;

    public ModedTridentItem(Properties pProperties, List<ReinforcedLevelProps> attributes, List<EnergizeUpgradeCost> upgradeIngredients) {
        super(pProperties);
        if(attributes.size() != upgradeIngredients.size())
            throw new RuntimeException("Incorrect definition of upgrade props and ingredients in modSwordItemClass");

        this.modifiers = attributes.stream().map(
                arg -> arg.convertToAttributeModifier(BASE_ATTACK_DAMAGE_UUID,BASE_ATTACK_SPEED_UUID)
        ).toList();
        this.upgrade_ingredients = upgradeIngredients;
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
    @Override
    public boolean isNUpgradable(ItemStack itemStack) {
        return itemStack.getOrCreateTag().getInt(ENERGIZE_TAG) <= 0 ||
                itemStack.getOrCreateTag().getInt(ENERGIZE_TAG) >= upgrade_ingredients.size();
    }

    @Override
    public EnergizeUpgradeCost getNextUpgradeCost(ItemStack itemStack) {
        if(isNUpgradable(itemStack))
            new EnergizeUpgradeCost(null,0);
        return upgrade_ingredients.get(itemStack.getOrCreateTag().getInt(ENERGIZE_TAG)-1);
    }



    public UseAnim getUseAnimation(ItemStack pStack) {
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
                if ($$6 <= 0 || $$4.isInWaterOrRain()) {
                    if (!pLevel.isClientSide) {
                        pStack.hurtAndBreak(1, $$4, (p_43388_) -> {
                            p_43388_.broadcastBreakEvent(pEntityLiving.getUsedItemHand());
                        });
                        if ($$6 == 0) {
                            ThrownTrident $$7 = new ThrownTrident(pLevel, $$4, pStack);
                            $$7.shootFromRotation($$4, $$4.getXRot(), $$4.getYRot(), 0.0F, 2.5F + (float)$$6 * 0.5F, 1.0F);
                            if ($$4.getAbilities().instabuild) {
                                $$7.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
                            }

                            pLevel.addFreshEntity($$7);
                            pLevel.playSound((Player)null, $$7, SoundEvents.TRIDENT_THROW, SoundSource.PLAYERS, 1.0F, 1.0F);
                            if (!$$4.getAbilities().instabuild) {
                                $$4.getInventory().removeItem(pStack);
                            }
                        }
                    }

                    $$4.awardStat(Stats.ITEM_USED.get(this));
                    if ($$6 > 0) {
                        float $$8 = $$4.getYRot();
                        float $$9 = $$4.getXRot();
                        float $$10 = -Mth.sin($$8 * 0.017453292F) * Mth.cos($$9 * 0.017453292F);
                        float $$11 = -Mth.sin($$9 * 0.017453292F);
                        float $$12 = Mth.cos($$8 * 0.017453292F) * Mth.cos($$9 * 0.017453292F);
                        float $$13 = Mth.sqrt($$10 * $$10 + $$11 * $$11 + $$12 * $$12);
                        float $$14 = 3.0F * ((1.0F + (float)$$6) / 4.0F);
                        $$10 *= $$14 / $$13;
                        $$11 *= $$14 / $$13;
                        $$12 *= $$14 / $$13;
                        $$4.push((double)$$10, (double)$$11, (double)$$12);
                        $$4.startAutoSpinAttack(20);
                        if ($$4.onGround()) {
                            float $$15 = 1.1999999F;
                            $$4.move(MoverType.SELF, new Vec3(0.0, 1.1999999284744263, 0.0));
                        }

                        SoundEvent $$18;
                        if ($$6 >= 3) {
                            $$18 = SoundEvents.TRIDENT_RIPTIDE_3;
                        } else if ($$6 == 2) {
                            $$18 = SoundEvents.TRIDENT_RIPTIDE_2;
                        } else {
                            $$18 = SoundEvents.TRIDENT_RIPTIDE_1;
                        }

                        pLevel.playSound((Player)null, $$4, $$18, SoundSource.PLAYERS, 1.0F, 1.0F);
                    }

                }
            }
        }
    }

    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        ItemStack $$3 = pPlayer.getItemInHand(pHand);
//        if ($$3.getDamageValue() >= $$3.getMaxDamage() - 1) {
//            return InteractionResultHolder.fail($$3);
//        } else if (EnchantmentHelper.getRiptide($$3) > 0 && !pPlayer.isInWaterOrRain()) {
//            return InteractionResultHolder.fail($$3);
//        } else {
            pPlayer.startUsingItem(pHand);
            return InteractionResultHolder.consume($$3);
//        }
    }
}
