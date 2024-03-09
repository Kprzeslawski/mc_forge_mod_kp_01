package com.kprzeslawski.examplemod.util.menu;
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import java.util.List;
import java.util.Optional;
import javax.annotation.Nullable;

import com.kprzeslawski.examplemod.block.ModBlocks;
import com.kprzeslawski.examplemod.util.ModMenu;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SmithingRecipe;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class EnergizingStationMenu extends ItemCombinerMenu {
    public static final int TEMPLATE_SLOT = 0;
    public static final int BASE_SLOT = 1;
    public static final int ADDITIONAL_SLOT = 2;
    public static final int RESULT_SLOT = 3;
    public static final int TEMPLATE_SLOT_X_PLACEMENT = 8;
    public static final int BASE_SLOT_X_PLACEMENT = 26;
    public static final int ADDITIONAL_SLOT_X_PLACEMENT = 44;
    private static final int RESULT_SLOT_X_PLACEMENT = 98;
    public static final int SLOT_Y_PLACEMENT = 48;
    @Nullable
    private SmithingRecipe selectedRecipe;

    public EnergizingStationMenu(int pContainerId, Inventory pPlayerInventory) {
        this(pContainerId, pPlayerInventory, ContainerLevelAccess.NULL);
    }
    public EnergizingStationMenu(int pContainerId, Inventory pPlayerInventory, ContainerLevelAccess pAccess) {
        super(ModMenu.ENERGIZING_STATION_MENU.get(), pContainerId, pPlayerInventory, pAccess);
    }

//    public EnergizingStationMenu(@org.jetbrains.annotations.Nullable MenuType<?> pType, int pContainerId, Inventory pPlayerInventory, ContainerLevelAccess pAccess) {
//        super(pType, pContainerId, pPlayerInventory, pAccess);
//    }

    protected ItemCombinerMenuSlotDefinition createInputSlotDefinitions() {
        return ItemCombinerMenuSlotDefinition.create().withSlot(0, 8, 48, (p_266643_) -> {
            return true;
        }).withSlot(1, 26, 48, (p_286208_) -> {
            return true;
        }).withSlot(2, 44, 48, (p_286207_) -> {
            return true;
        }).withResultSlot(3, 98, 48).build();
    }

    protected boolean isValidBlock(BlockState pState) {
        return pState.is(ModBlocks.ENERGIZING_STATION_BLOCK.get());
    }

    protected boolean mayPickup(Player pPlayer, boolean pHasStack) {
        return this.selectedRecipe != null;
    }

    protected void onTake(Player pPlayer, ItemStack pStack) {
        pStack.onCraftedBy(pPlayer.level(), pPlayer, pStack.getCount());
        this.resultSlots.awardUsedRecipes(pPlayer, this.getRelevantItems());
        this.shrinkStackInSlot(0);
        this.shrinkStackInSlot(1);
        this.shrinkStackInSlot(2);
        this.access.execute((p_40263_, p_40264_) -> {
            p_40263_.levelEvent(1044, p_40264_, 0);
        });
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

    public void createResult() {

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
