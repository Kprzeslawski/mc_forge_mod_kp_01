package com.kprzeslawski.examplemod.datagen;

import com.kprzeslawski.examplemod.ExampleMod;
import com.kprzeslawski.examplemod.item.ModItems;
import com.kprzeslawski.examplemod.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends ItemTagsProvider {
    public ModItemTagGenerator(PackOutput p_275343_, CompletableFuture<HolderLookup.Provider> p_275729_,
                               CompletableFuture<TagLookup<Block>> p_275322_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_275343_, p_275729_, p_275322_, ExampleMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.TANGERINE_HELMET.get(),
                        ModItems.TANGERINE_CHESTPLATE.get(),
                        ModItems.TANGERINE_LEGGINGS.get(),
                        ModItems.TANGERINE_BOOTS.get());

        this.tag(ModTags.Items.ENERGY_CRYSTAL)
                .add(
                        ModItems.ESSENCE_T1.get(),
                        ModItems.ESSENCE_T2.get(),
                        ModItems.ESSENCE_T3.get(),
                        ModItems.ESSENCE_T4.get(),
                        ModItems.ESSENCE_T5.get(),
                        ModItems.ESSENCE_T6.get(),
                        ModItems.ESSENCE_T7.get()
                );

    }
}

