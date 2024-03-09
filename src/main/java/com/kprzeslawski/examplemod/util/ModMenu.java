package com.kprzeslawski.examplemod.util;

import com.kprzeslawski.examplemod.ExampleMod;
import com.kprzeslawski.examplemod.util.menu.EnergizingStationMenu;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenu {

    public static final DeferredRegister<MenuType<?>> MENU =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, ExampleMod.MOD_ID);

    public static final RegistryObject<MenuType<EnergizingStationMenu>> ENERGIZING_STATION_MENU =
            MENU.register("energizing_station_menu",
                    () -> new MenuType<>(EnergizingStationMenu::new, FeatureFlags.DEFAULT_FLAGS));
    public static void register(IEventBus eventBus){
        MENU.register(eventBus);
    }
}
