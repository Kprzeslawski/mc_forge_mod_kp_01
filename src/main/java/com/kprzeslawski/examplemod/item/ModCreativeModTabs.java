package com.kprzeslawski.examplemod.item;

import com.kprzeslawski.examplemod.ExampleMod;
import com.kprzeslawski.examplemod.block.ModBlocks;
import com.kprzeslawski.examplemod.item.modedItemClass.ModedSwordItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ExampleMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> MOD_ITEMS_TAB = CREATIVE_MODE_TABS.register("mod_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.TANGERINE.get()))
                    .title(Component.translatable("creativetab.modTab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.ESSENCE_T1.get());
                        pOutput.accept(ModItems.ESSENCE_T2.get());
                        pOutput.accept(ModItems.ESSENCE_T3.get());
                        pOutput.accept(ModItems.ESSENCE_T4.get());
                        pOutput.accept(ModItems.ESSENCE_T5.get());
                        pOutput.accept(ModItems.ESSENCE_T6.get());
                        pOutput.accept(ModItems.ESSENCE_T7.get());

                        pOutput.accept(ModItems.TANGERINE.get());
                        pOutput.accept(ModItems.TANGERINE_INGOT.get());
                        pOutput.accept(ModItems.TANGERINE_UPGRADE.get());
                        pOutput.accept(ModBlocks.END_STONE_TANGERINE_ORE.get());

                        pOutput.accept(ModItems.TANGERINE_HELMET.get());
                        pOutput.accept(ModItems.TANGERINE_CHESTPLATE.get());
                        pOutput.accept(ModItems.TANGERINE_LEGGINGS.get());
                        pOutput.accept(ModItems.TANGERINE_BOOTS.get());

                        pOutput.accept(ModItems.SW_1.get().getDefaultInstance());
                        pOutput.accept(((ModedSwordItem)ModItems.SW_1.get()).getInstance(2));
                        pOutput.accept(((ModedSwordItem)ModItems.SW_1.get()).getInstance(3));
                        pOutput.accept(((ModedSwordItem)ModItems.SW_1.get()).getInstance(4));

                        pOutput.accept(ModBlocks.ENERGIZING_STATION_BLOCK.get());
                    })
                    .build()
    );

    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }

}
