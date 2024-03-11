package com.kprzeslawski.examplemod.util.menu;
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import java.util.List;
import java.util.Optional;
import javax.annotation.Nullable;

import com.kprzeslawski.examplemod.block.ModBlocks;
import com.kprzeslawski.examplemod.item.ModItems;
import com.kprzeslawski.examplemod.item.modedItemClass.ModedSwordItem;
import com.kprzeslawski.examplemod.util.ModMenu;
import com.kprzeslawski.examplemod.util.ModTags;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.SmithingRecipe;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class EnergizingStationMenu extends ItemCombinerMenu {

    public static final int TEMPLATE_SLOT_X_PLACEMENT = 8;
    public static final int BASE_SLOT_X_PLACEMENT = 26;
    public static final int ADDITIONAL_SLOT_X_PLACEMENT = 44;
    private static final int RESULT_SLOT_X_PLACEMENT = 98;
    public static final int SLOT_Y_PLACEMENT = 48;

    public EnergizingStationMenu(int pContainerId, Inventory pPlayerInventory) {
        this(pContainerId, pPlayerInventory, ContainerLevelAccess.NULL);
    }
    public EnergizingStationMenu(int pContainerId, Inventory pPlayerInventory, ContainerLevelAccess pAccess) {
        super(ModMenu.ENERGIZING_STATION_MENU.get(), pContainerId, pPlayerInventory, pAccess);
    }

    protected @NotNull ItemCombinerMenuSlotDefinition createInputSlotDefinitions() {
        return ItemCombinerMenuSlotDefinition.create().withSlot(0, 8, 48, (p_266643_) -> {
            return p_266643_.getOrCreateTag().contains(ModedSwordItem.ENERGIZE_TAG);
        }).withSlot(1, 26, 48, (p_286208_) -> {
            return p_286208_.is(ModTags.Items.ENERGY_CRYSTAL);
        }).withSlot(2, 44, 48, (p_286207_) -> {
            return p_286207_.is(ModTags.Items.ENERGY_CRYSTAL);
        }).withResultSlot(3, 98, 48).build();
    }

    protected boolean isValidBlock(BlockState pState) {
        return pState.is(ModBlocks.ENERGIZING_STATION_BLOCK.get());
    }

    protected boolean mayPickup(Player pPlayer, boolean pHasStack) {

        ItemStack $$1 = this.inputSlots.getItem(0);
        if(!($$1.getItem() instanceof ModedSwordItem)) return false;

        ModedSwordItem.EnergizeUpgradeCost upg =
                ((ModedSwordItem)$$1.getItem()).getNextUpgradeCost($$1);

        if(upg.upg_count == 0)return false;

        int have = 0;

        for(int i = 1; i < 3 ;i++)
            if(this.inputSlots.getItem(i).is(upg.upgrade_crystal))
                have += this.inputSlots.getItem(i).getCount();

        return have >= upg.upg_count;
    }

    protected void onTake(Player pPlayer, ItemStack pStack) {

        ItemStack $$1 = this.inputSlots.getItem(0);
        if(!($$1.getItem() instanceof ModedSwordItem))return;

        //work without it :)
        //may interfere with modsworditem oncrafted implementation
        //pStack.onCraftedBy(pPlayer.level(), pPlayer, pStack.getCount());

        ModedSwordItem.EnergizeUpgradeCost cost =
                ((ModedSwordItem)ModItems.SW_1.get())
                        .getNextUpgradeCost($$1);

        this.shrinkStackInSlot(0);

        int rem = cost.upg_count;

        rem = this.checkAndBalanceItems(2, rem, cost.upgrade_crystal);
        this.checkAndBalanceItems(1, rem, cost.upgrade_crystal);
    }

    private List<ItemStack> getRelevantItems() {
        return List.of(this.inputSlots.getItem(0), this.inputSlots.getItem(1), this.inputSlots.getItem(2));
    }

    private void shrinkStackInSlot(int pIndex) {
        ItemStack $$1 = this.inputSlots.getItem(pIndex);
        if (!$$1.isEmpty()) {
            $$1.shrink(1);
            this.inputSlots.setItem(pIndex, $$1);
        }
    }

    private int checkAndBalanceItems(int pIndex, int rem, Item req) {
        ItemStack $$1 = this.inputSlots.getItem(pIndex);
        if(!$$1.is(req))return rem;

        int min_take = Math.min(rem,$$1.getCount());
        rem -= min_take;
        $$1.shrink(min_take);
        this.inputSlots.setItem(pIndex, $$1);
        return rem;
    }


    public void createResult() {
        ItemStack inp_w = this.inputSlots.getItem(0);
        if(!(inp_w.getItem() instanceof ModedSwordItem)){
            this.resultSlots.setItem(0, ItemStack.EMPTY);
            return;
        }
        if(!((ModedSwordItem)inp_w.getItem()).isUpgradable(inp_w)){
            this.resultSlots.setItem(0, ItemStack.EMPTY);
            return;
        }

        ItemStack res = inp_w.copy();
        res.getOrCreateTag().putInt(
                ModedSwordItem.ENERGIZE_TAG,
                res.getOrCreateTag().getInt(ModedSwordItem.ENERGIZE_TAG) + 1
        );
        this.resultSlots.setItem(0, res);
    }

    public int getSlotToQuickMoveTo(ItemStack pStack) {
//        return (Integer)((Optional)this.recipes.stream().map((p_266640_) -> {
//            return findSlotMatchingIngredient(p_266640_, pStack);
//        }).filter(Optional::isPresent).findFirst().orElse(Optional.of(0))).get();
        return 0;
    }

    private static Optional<Integer> findSlotMatchingIngredient(SmithingRecipe pRecipe, ItemStack pStack) {
        if (pRecipe.isTemplateIngredient(pStack)) {
            return Optional.of(0);
        } else if (pRecipe.isBaseIngredient(pStack)) {
            return Optional.of(1);
        } else {
            return pRecipe.isAdditionIngredient(pStack) ? Optional.of(2) : Optional.empty();
        }
    }

    public boolean canTakeItemForPickAll(ItemStack pStack, Slot pSlot) {
        return pSlot.container != this.resultSlots && super.canTakeItemForPickAll(pStack, pSlot);
    }

    public boolean canMoveIntoInputSlots(ItemStack pStack) {
//        return this.recipes.stream().map((p_266647_) -> {
//            return findSlotMatchingIngredient(p_266647_, pStack);
//        }).anyMatch(Optional::isPresent);
        return false;
    }
}
