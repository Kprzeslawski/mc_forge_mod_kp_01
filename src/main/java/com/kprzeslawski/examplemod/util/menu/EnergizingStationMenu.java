package com.kprzeslawski.examplemod.util.menu;
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import com.kprzeslawski.examplemod.block.ModBlocks;
import com.kprzeslawski.examplemod.item.ModItems;
import com.kprzeslawski.examplemod.item.modedItemClass.ModedSwordItem;
import com.kprzeslawski.examplemod.item.modedItemClass.modedItemComponents.EnergizeUpgradeCost;
import com.kprzeslawski.examplemod.util.ModMenu;
import com.kprzeslawski.examplemod.util.ModTags;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class EnergizingStationMenu extends ItemCombinerMenu {

    public EnergizingStationMenu(int pContainerId, Inventory pPlayerInventory) {
        this(pContainerId, pPlayerInventory, ContainerLevelAccess.NULL);
    }
    public EnergizingStationMenu(int pContainerId, Inventory pPlayerInventory, ContainerLevelAccess pAccess) {
        super(ModMenu.ENERGIZING_STATION_MENU.get(), pContainerId, pPlayerInventory, pAccess);
    }

    protected @NotNull ItemCombinerMenuSlotDefinition createInputSlotDefinitions() {
        return ItemCombinerMenuSlotDefinition.create()
                .withSlot(0, 8, 48, (p_266643_) -> p_266643_.getOrCreateTag().contains(ModedSwordItem.ENERGIZE_TAG))
                .withSlot(1, 62, 40, (p_286208_) -> p_286208_.is(ModTags.Items.ENERGY_CRYSTAL))
                .withSlot(2, 80, 40, (p_286207_) -> p_286207_.is(ModTags.Items.ENERGY_CRYSTAL))
                .withSlot(3, 62, 58, (p_286207_) -> p_286207_.is(ModTags.Items.ENERGY_CRYSTAL))
                .withSlot(4, 80, 58, (p_286207_) -> p_286207_.is(ModTags.Items.ENERGY_CRYSTAL))
                .withResultSlot(5, 134, 48).build();
    }

    protected boolean isValidBlock(BlockState pState) {
        return pState.is(ModBlocks.ENERGIZING_STATION_BLOCK.get());
    }

    protected boolean mayPickup(@NotNull Player pPlayer, boolean pHasStack) {
        return possible_to_pickup();
    }

    protected boolean possible_to_pickup(){
        ItemStack $$1 = this.inputSlots.getItem(0);
        if(!($$1.getItem() instanceof ModedSwordItem)) return false;

        EnergizeUpgradeCost upg =
                ((ModedSwordItem)$$1.getItem()).getNextUpgradeCost($$1);

        if(upg.upg_count == 0)return false;

        int have = 0;

        for(int i = 1; i < 5 ;i++)
            if(this.inputSlots.getItem(i).is(upg.upgrade_crystal))
                have += this.inputSlots.getItem(i).getCount();

        return have >= upg.upg_count;
    }

    protected void onTake(@NotNull Player pPlayer, @NotNull ItemStack pStack) {

        ItemStack $$1 = this.inputSlots.getItem(0);
        if(!($$1.getItem() instanceof ModedSwordItem))return;

        EnergizeUpgradeCost cost =
                ((ModedSwordItem)ModItems.SW_1.get())
                        .getNextUpgradeCost($$1);

        $$1.shrink(1);
        this.inputSlots.setItem(0, $$1);

        int rem = cost.upg_count;

        for(int i = 4; i > 0; i--)
            rem = this.checkAndBalanceItems(i, rem, cost.upgrade_crystal);
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

    public boolean canTakeItemForPickAll(@NotNull ItemStack pStack, Slot pSlot) {
        return pSlot.container != this.resultSlots && super.canTakeItemForPickAll(pStack, pSlot);
    }
}
