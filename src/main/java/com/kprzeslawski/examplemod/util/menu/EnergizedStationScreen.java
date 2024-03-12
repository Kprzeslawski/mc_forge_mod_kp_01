package com.kprzeslawski.examplemod.util.menu;

import com.kprzeslawski.examplemod.ExampleMod;
import com.kprzeslawski.examplemod.item.modedItemClass.modedItemComponents.EnergizeUpgradeCost;
import com.kprzeslawski.examplemod.item.modedItemClass.modedItemComponents.ModedItemUpgradable;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class EnergizedStationScreen extends AbstractContainerScreen<EnergizingStationMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(ExampleMod.MOD_ID,"textures/gui/energizing_station_gui.png");
    public EnergizedStationScreen(EnergizingStationMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    //protected void init() {}

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float v, int i, int i1) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        guiGraphics.blit(TEXTURE, x, y, 0, 0, imageWidth, imageHeight);

        ItemStack $$1 = ((EnergizingStationMenu)this.menu).getSlot(0).getItem();

        if(!$$1.isEmpty()){
            EnergizeUpgradeCost upg =
                    ((ModedItemUpgradable)$$1.getItem()).getNextUpgradeCost($$1);

            if(upg.upg_count == 0 || upg.upgrade_crystal == null)return;
            guiGraphics.drawString(this.font, "Required amount", x + 8, y + 20,3618615,false);
            guiGraphics.renderItem(new ItemStack(upg.upgrade_crystal, upg.upg_count), x+91, y+16);
            guiGraphics.drawString(this.font, String.valueOf(upg.upg_count), x + 108, y + 20, 3618615, false);

            if(!((EnergizingStationMenu)this.menu).possible_to_pickup())
                guiGraphics.blit(TEXTURE, x + 101, y + 46, this.imageWidth, 0, 28, 21);
        }
    }

    @Override
    public void render(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, delta);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }
}
