package com.kprzeslawski.examplemod.item;

import com.kprzeslawski.examplemod.ExampleMod;
import com.kprzeslawski.examplemod.item.modedItemClass.*;
import com.kprzeslawski.examplemod.item.modedItemClass.modedItemComponents.EnergizeUpgradeCost;
import com.kprzeslawski.examplemod.item.modedItemClass.modedItemComponents.ReinforcedLevelProps;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Arrays;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ExampleMod.MOD_ID);

    public static final RegistryObject<Item> TANGERINE = ITEMS.register("tangerine",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> TANGERINE_UPGRADE = ITEMS.register("tangerine_upgrade",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> TANGERINE_INGOT = ITEMS.register("tangerine_ingot",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ESSENCE_T1 = ITEMS.register("essence_t1",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ESSENCE_T2 = ITEMS.register("essence_t2",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ESSENCE_T3 = ITEMS.register("essence_t3",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ESSENCE_T4 = ITEMS.register("essence_t4",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ESSENCE_T5 = ITEMS.register("essence_t5",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ESSENCE_T6 = ITEMS.register("essence_t6",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ESSENCE_T7 = ITEMS.register("essence_t7",
            () -> new Item(new Item.Properties()));


    public static final RegistryObject<Item> TANGERINE_HELMET = ITEMS.register("tangerine_helmet",
            () -> new ModedArmor(ModArmorMaterial.TANGERINE, ArmorItem.Type.HELMET, new Item.Properties(),Arrays.asList(
                            new ReinforcedLevelProps().aHp(0),
                            new ReinforcedLevelProps().aHp(1),
                            new ReinforcedLevelProps().aHp(2),
                            new ReinforcedLevelProps().aHp(3),
                            new ReinforcedLevelProps().aHp(4)
                    ),
                    Arrays.asList(
                            new EnergizeUpgradeCost(ModItems.ESSENCE_T1.get(),5),
                            new EnergizeUpgradeCost(ModItems.ESSENCE_T2.get(),5),
                            new EnergizeUpgradeCost(ModItems.ESSENCE_T3.get(),5),
                            new EnergizeUpgradeCost(ModItems.ESSENCE_T4.get(),5),
                            new EnergizeUpgradeCost(null,0)
                    )
                    ));
    public static final RegistryObject<Item> TANGERINE_CHESTPLATE = ITEMS.register("tangerine_chestplate",
            () -> new ModedArmor(ModArmorMaterial.TANGERINE, ArmorItem.Type.CHESTPLATE, new Item.Properties(),Arrays.asList(
                    new ReinforcedLevelProps().aHp(0),
                    new ReinforcedLevelProps().aHp(1),
                    new ReinforcedLevelProps().aHp(2),
                    new ReinforcedLevelProps().aHp(3),
                    new ReinforcedLevelProps().aHp(4)
            ),
                    Arrays.asList(
                            new EnergizeUpgradeCost(ModItems.ESSENCE_T1.get(),5),
                            new EnergizeUpgradeCost(ModItems.ESSENCE_T2.get(),5),
                            new EnergizeUpgradeCost(ModItems.ESSENCE_T3.get(),5),
                            new EnergizeUpgradeCost(ModItems.ESSENCE_T4.get(),5),
                            new EnergizeUpgradeCost(null,0)
                    )
            ));
    public static final RegistryObject<Item> TANGERINE_LEGGINGS = ITEMS.register("tangerine_leggings",
            () -> new ModedArmor(ModArmorMaterial.TANGERINE, ArmorItem.Type.LEGGINGS, new Item.Properties(),Arrays.asList(
                    new ReinforcedLevelProps().aHp(0),
                    new ReinforcedLevelProps().aHp(1),
                    new ReinforcedLevelProps().aHp(2),
                    new ReinforcedLevelProps().aHp(3),
                    new ReinforcedLevelProps().aHp(4)
            ),
                    Arrays.asList(
                            new EnergizeUpgradeCost(ModItems.ESSENCE_T1.get(),5),
                            new EnergizeUpgradeCost(ModItems.ESSENCE_T2.get(),5),
                            new EnergizeUpgradeCost(ModItems.ESSENCE_T3.get(),5),
                            new EnergizeUpgradeCost(ModItems.ESSENCE_T4.get(),5),
                            new EnergizeUpgradeCost(null,0)
                    )
            ));
    public static final RegistryObject<Item> TANGERINE_BOOTS = ITEMS.register("tangerine_boots",
            () -> new ModedArmor(ModArmorMaterial.TANGERINE, ArmorItem.Type.BOOTS, new Item.Properties(),Arrays.asList(
                    new ReinforcedLevelProps().aHp(0),
                    new ReinforcedLevelProps().aHp(1),
                    new ReinforcedLevelProps().aHp(2),
                    new ReinforcedLevelProps().aHp(3),
                    new ReinforcedLevelProps().aHp(4)
            ),
                    Arrays.asList(
                            new EnergizeUpgradeCost(ModItems.ESSENCE_T1.get(),5),
                            new EnergizeUpgradeCost(ModItems.ESSENCE_T2.get(),5),
                            new EnergizeUpgradeCost(ModItems.ESSENCE_T3.get(),5),
                            new EnergizeUpgradeCost(ModItems.ESSENCE_T4.get(),5),
                            new EnergizeUpgradeCost(null,0)
                    )
            ));

    public static final RegistryObject<Item> GAUNTLET = ITEMS.register("gauntlet",
            () -> new ModedGauntletItem(Tiers.NETHERITE, 3, 1f, new Item.Properties(), Arrays.asList(
                    new ReinforcedLevelProps().setBaseUUID().aD(15).aS(-3.9).aR(-2),
                    new ReinforcedLevelProps().setBaseUUID().aD(30).aS(-4).aR(-2), // -4(-3) is a threshold
                    new ReinforcedLevelProps().setBaseUUID().aD(40).aS(-4.9).aR(-2),
                    new ReinforcedLevelProps().setBaseUUID().aD(50).aS(-3.98).aR(-2)
            ),
                    Arrays.asList(
                            new EnergizeUpgradeCost(ModItems.ESSENCE_T1.get(),5),
                            new EnergizeUpgradeCost(ModItems.ESSENCE_T2.get(),5),
                            new EnergizeUpgradeCost(ModItems.ESSENCE_T3.get(),5),
                            new EnergizeUpgradeCost(null,0)
                    )
            ));
    public static final RegistryObject<Item> SW_1 = ITEMS.register("sw_1",
            () -> new ModedSwordItem(Tiers.NETHERITE, 3, 1f, new Item.Properties(), Arrays.asList(
                    new ReinforcedLevelProps().setBaseUUID().aD(1).aS(1).aR(1),
                    new ReinforcedLevelProps().setBaseUUID().aD(2).aS(1).aR(2),
                    new ReinforcedLevelProps().setBaseUUID().aD(3).aS(1).aR(3),
                    new ReinforcedLevelProps().setBaseUUID().aD(4).aS(1).aR(4)
            ),
                    Arrays.asList(
                            new EnergizeUpgradeCost(ModItems.ESSENCE_T1.get(),5),
                            new EnergizeUpgradeCost(ModItems.ESSENCE_T2.get(),5),
                            new EnergizeUpgradeCost(ModItems.ESSENCE_T3.get(),5),
                            new EnergizeUpgradeCost(null,0)
                    )
            ));

    public static final RegistryObject<Item> SW_2 = ITEMS.register("sw_2",
            () -> new ModedSamuraiSwordItem(Tiers.NETHERITE, 3, 1f, new Item.Properties(), Arrays.asList(
                    new ReinforcedLevelProps().setBaseUUID().aD(1).aS(1).aR(1),
                    new ReinforcedLevelProps().setBaseUUID().aD(2).aS(1).aR(2),
                    new ReinforcedLevelProps().setBaseUUID().aD(3).aS(1).aR(3),
                    new ReinforcedLevelProps().setBaseUUID().aD(4).aS(1).aR(4)
            ),
                    Arrays.asList(
                            new EnergizeUpgradeCost(ModItems.ESSENCE_T1.get(),5),
                            new EnergizeUpgradeCost(ModItems.ESSENCE_T2.get(),5),
                            new EnergizeUpgradeCost(ModItems.ESSENCE_T3.get(),5),
                            new EnergizeUpgradeCost(null,0)
                    )
            ));

    public static final RegistryObject<Item> SW_3 = ITEMS.register("sw_3",
            () -> new ModedEnderSwordItem(Tiers.NETHERITE, 3, 1f, new Item.Properties(), Arrays.asList(
                    new ReinforcedLevelProps().setBaseUUID().aD(1).aS(1).aR(1),
                    new ReinforcedLevelProps().setBaseUUID().aD(2).aS(1).aR(1),
                    new ReinforcedLevelProps().setBaseUUID().aD(3).aS(1).aR(1),
                    new ReinforcedLevelProps().setBaseUUID().aD(4).aS(1).aR(1)
            ),
                    Arrays.asList(
                            new EnergizeUpgradeCost(ModItems.ESSENCE_T1.get(),5),
                            new EnergizeUpgradeCost(ModItems.ESSENCE_T2.get(),5),
                            new EnergizeUpgradeCost(ModItems.ESSENCE_T3.get(),5),
                            new EnergizeUpgradeCost(null,0)
                    )
            ));

//    public static final RegistryObject<Item> TR_1 = ITEMS.register("tr_1",
//            () ->  new ModedTridentItem( new Item.Properties(),
//                            Arrays.asList(
//                            new ReinforcedLevelProps(1,1,1),
//                            new ReinforcedLevelProps(2,1,2),
//                            new ReinforcedLevelProps(3,1,3),
//                            new ReinforcedLevelProps(4,1,4)
//                    ),
//                    Arrays.asList(
//                            new EnergizeUpgradeCost(ModItems.ESSENCE_T1.get(),5),
//                            new EnergizeUpgradeCost(ModItems.ESSENCE_T2.get(),5),
//                            new EnergizeUpgradeCost(ModItems.ESSENCE_T3.get(),5),
//                            new EnergizeUpgradeCost(null,0)
//                    )
//            ));


    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }

}
