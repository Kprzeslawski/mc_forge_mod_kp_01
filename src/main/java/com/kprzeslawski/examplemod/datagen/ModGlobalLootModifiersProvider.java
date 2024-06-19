package com.kprzeslawski.examplemod.datagen;

import com.kprzeslawski.examplemod.ExampleMod;
import com.kprzeslawski.examplemod.item.ModItems;
import com.kprzeslawski.examplemod.loot.AddItemModifier;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;

public class ModGlobalLootModifiersProvider extends GlobalLootModifierProvider {
    public ModGlobalLootModifiersProvider(PackOutput output) {
        super(output, ExampleMod.MOD_ID);
    }

    @Override
    protected void start() {
        add("template_from_chests", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("chests/end_city_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(0.2f).build()
        }, ModItems.TANGERINE_UPGRADE.get()));

        add("t1_crystal", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("entities/zombie")).build(),
                LootItemRandomChanceCondition.randomChance(0.6f).build()
        }, ModItems.ESSENCE_T1.get()));



    }
}
