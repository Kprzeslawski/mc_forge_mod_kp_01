package com.kprzeslawski.examplemod.datagen;

import com.kprzeslawski.examplemod.ExampleMod;
import com.kprzeslawski.examplemod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
//    private static final List<ItemLike> SAPPHIRE_SMELTABLES = List.of(ModItems.RAW_SAPPHIRE.get(),
//            ModBlocks.SAPPHIRE_ORE.get(), ModBlocks.DEEPSLATE_SAPPHIRE_ORE.get(), ModBlocks.NETHER_SAPPHIRE_ORE.get(),
//            ModBlocks.END_STONE_SAPPHIRE_ORE.get());

    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(@NotNull Consumer<FinishedRecipe> pWriter) {

                ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.ESSENCE_T2.get())
                        .requires(ModItems.ESSENCE_T1.get(),9)
                        .unlockedBy(getHasName(ModItems.ESSENCE_T1.get()), has(ModItems.ESSENCE_T1.get()))
                        .save(pWriter);
                ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.ESSENCE_T3.get())
                        .requires(ModItems.ESSENCE_T2.get(),9)
                        .unlockedBy(getHasName(ModItems.ESSENCE_T2.get()), has(ModItems.ESSENCE_T2.get()))
                        .save(pWriter);
                ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.ESSENCE_T4.get())
                        .requires(ModItems.ESSENCE_T3.get(),9)
                        .unlockedBy(getHasName(ModItems.ESSENCE_T3.get()), has(ModItems.ESSENCE_T3.get()))
                        .save(pWriter);
                ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.ESSENCE_T5.get())
                        .requires(ModItems.ESSENCE_T4.get(),5)
                        .requires(Items.LAPIS_LAZULI,2)
                        .requires(Items.REDSTONE,2)
                        .unlockedBy(getHasName(ModItems.ESSENCE_T4.get()), has(ModItems.ESSENCE_T4.get()))
                        .save(pWriter);
                ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.ESSENCE_T6.get())
                        .requires(ModItems.ESSENCE_T5.get(),5)
                        .requires(Items.DIAMOND,4)
                        .unlockedBy(getHasName(ModItems.ESSENCE_T5.get()), has(ModItems.ESSENCE_T5.get()))
                        .save(pWriter);
                ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.ESSENCE_T7.get())
                        .requires(ModItems.ESSENCE_T6.get(),5)
                        .requires(Items.NETHERITE_SCRAP,4)
                        .unlockedBy(getHasName(ModItems.ESSENCE_T6.get()), has(ModItems.ESSENCE_T6.get()))
                        .save(pWriter);

                ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.TANGERINE_UPGRADE.get(),2)
                .pattern("STS")
                .pattern("SCS")
                .pattern("SSS")
                .define('S', ModItems.TANGERINE.get())
                .define('T', ModItems.TANGERINE_UPGRADE.get())
                .define('C', Items.END_STONE)
                .unlockedBy(getHasName(ModItems.TANGERINE_UPGRADE.get()), has(ModItems.TANGERINE_UPGRADE.get()))
                .save(pWriter);

                ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.TANGERINE_INGOT.get())
                        .requires(ModItems.TANGERINE.get(),4)
                        .requires(Items.NETHER_STAR)
                        .unlockedBy(getHasName(ModItems.TANGERINE.get()), has(ModItems.TANGERINE.get()))
                        .save(pWriter);

                SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.TANGERINE_UPGRADE.get()),
                        Ingredient.of(Items.NETHERITE_BOOTS.asItem()),
                        Ingredient.of(ModItems.TANGERINE_INGOT.get()),
                        RecipeCategory.MISC,
                        ModItems.TANGERINE_BOOTS.get()
                ).unlocks(getHasName(ModItems.TANGERINE_UPGRADE.get()), has(ModItems.TANGERINE_UPGRADE.get()))
                        .save(pWriter,"tangerine_upgrade_boots");

                SmithingTransformRecipeBuilder.smithing(
                                Ingredient.of(ModItems.TANGERINE_UPGRADE.get()),
                                Ingredient.of(Items.NETHERITE_LEGGINGS.asItem()),
                                Ingredient.of(ModItems.TANGERINE_INGOT.get()),
                                RecipeCategory.MISC,
                                ModItems.TANGERINE_LEGGINGS.get()
                        ).unlocks(getHasName(ModItems.TANGERINE_UPGRADE.get()), has(ModItems.TANGERINE_UPGRADE.get()))
                        .save(pWriter,"tangerine_upgrade_leggings");

                SmithingTransformRecipeBuilder.smithing(
                                Ingredient.of(ModItems.TANGERINE_UPGRADE.get()),
                                Ingredient.of(Items.NETHERITE_CHESTPLATE.asItem()),
                                Ingredient.of(ModItems.TANGERINE_INGOT.get()),
                                RecipeCategory.MISC,
                                ModItems.TANGERINE_CHESTPLATE.get()
                        ).unlocks(getHasName(ModItems.TANGERINE_UPGRADE.get()), has(ModItems.TANGERINE_UPGRADE.get()))
                        .save(pWriter,"tangerine_upgrade_chestplate");

                SmithingTransformRecipeBuilder.smithing(
                                Ingredient.of(ModItems.TANGERINE_UPGRADE.get()),
                                Ingredient.of(Items.NETHERITE_HELMET.asItem()),
                                Ingredient.of(ModItems.TANGERINE_INGOT.get()),
                                RecipeCategory.MISC,
                                ModItems.TANGERINE_HELMET.get()
                        ).unlocks(getHasName(ModItems.TANGERINE_UPGRADE.get()), has(ModItems.TANGERINE_UPGRADE.get()))
                        .save(pWriter,"tangerine_upgrade_helmet");


        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SW_1.get())
                .pattern(" C ")
                .pattern(" C ")
                .pattern(" S ")
                .define('C', ModItems.ESSENCE_T3.get())
                .define('S', Items.STICK)
                .unlockedBy(getHasName(ModItems.ESSENCE_T3.get()), has(ModItems.ESSENCE_T3.get()))
                .save(pWriter);

//        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.SAPPHIRE.get(), 9)
//                .requires(ModBlocks.SAPPHIRE_BLOCK.get())
//                .unlockedBy(getHasName(ModBlocks.SAPPHIRE_BLOCK.get()), has(ModBlocks.SAPPHIRE_BLOCK.get()))
//                .save(pWriter);
    }

    protected static void oreSmelting(@NotNull Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(@NotNull Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static void oreCooking(@NotNull Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult,
                    pExperience, pCookingTime, pCookingSerializer)
                    .group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pFinishedRecipeConsumer,  ExampleMod.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }
}
