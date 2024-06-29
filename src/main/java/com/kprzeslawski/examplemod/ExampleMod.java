package com.kprzeslawski.examplemod;

import com.kprzeslawski.examplemod.block.ModBlocks;
import com.kprzeslawski.examplemod.entity.ModEntities;
import com.kprzeslawski.examplemod.entity.client.EnZombieRenderer;
import com.kprzeslawski.examplemod.item.ModCreativeModTabs;
import com.kprzeslawski.examplemod.item.ModItems;
import com.kprzeslawski.examplemod.loot.ModLootModifiers;
import com.kprzeslawski.examplemod.util.ModMenu;
import com.kprzeslawski.examplemod.util.menu.EnergizedStationScreen;
import com.kprzeslawski.examplemod.world.ModMobEffects;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ExampleMod.MOD_ID)
public class ExampleMod
{
    public static final String MOD_ID = "examplemod";
//    private static final Logger LOGGER = LogUtils.getLogger();

    public ExampleMod()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModCreativeModTabs.register(modEventBus);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModMobEffects.register(modEventBus);
        ModLootModifiers.register(modEventBus);
        ModEntities.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
        ModMenu.register(modEventBus);
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {

    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            MenuScreens.register(ModMenu.ENERGIZING_STATION_MENU.get(), EnergizedStationScreen::new);
            EntityRenderers.register(ModEntities.EN_ZOMBIE_L1.get(), EnZombieRenderer::new);
            EntityRenderers.register(ModEntities.EN_ZOMBIE_L2.get(), EnZombieRenderer::new);
            EntityRenderers.register(ModEntities.EN_ZOMBIE_L3.get(), EnZombieRenderer::new);
            EntityRenderers.register(ModEntities.EN_ZOMBIE_L4.get(), EnZombieRenderer::new);
            EntityRenderers.register(ModEntities.EN_ZOMBIE_L5.get(), EnZombieRenderer::new);
            EntityRenderers.register(ModEntities.EN_ZOMBIE_L6.get(), EnZombieRenderer::new);
        }
    }
}
