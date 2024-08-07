package com.kprzeslawski.examplemod.worldgen.dimension;

import com.kprzeslawski.examplemod.ExampleMod;
import com.kprzeslawski.examplemod.worldgen.biome.ModBiomes;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.FlatLevelSource;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.flat.FlatLayerInfo;
import net.minecraft.world.level.levelgen.flat.FlatLevelGeneratorPreset;
import net.minecraft.world.level.levelgen.flat.FlatLevelGeneratorPresets;
import net.minecraft.world.level.levelgen.flat.FlatLevelGeneratorSettings;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.List;
import java.util.Optional;
import java.util.OptionalLong;

public class ModDimensions {
    public static final ResourceKey<LevelStem> MOD_KEY = ResourceKey.create(Registries.LEVEL_STEM,
            new ResourceLocation(ExampleMod.MOD_ID, "mod_key"));
    public static final ResourceKey<Level> MOD_LEVEL_KEY = ResourceKey.create(Registries.DIMENSION,
            new ResourceLocation(ExampleMod.MOD_ID, "mod_level_key"));
    public static final ResourceKey<DimensionType> MOD_DIM_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE,
            new ResourceLocation(ExampleMod.MOD_ID, "mod_dim_key"));


    public static void bootstrapType(BootstapContext<DimensionType> context) {
        context.register(MOD_DIM_TYPE, new DimensionType(
                OptionalLong.empty(), // fixedTime
                false, // hasSkylight
                false, // hasCeiling
                false, // ultraWarm
                false, // natural
                1.0, // coordinateScale
                true, // bedWorks
                false, // respawnAnchorWorks
                0, // minY
                16, // height
                16, // logicalHeight
                BlockTags.INFINIBURN_OVERWORLD, // infiniburn
                BuiltinDimensionTypes.OVERWORLD_EFFECTS, // effectsLocation
                .5f, // ambientLight
                new DimensionType.MonsterSettings(false, false, ConstantInt.of(0), 15)));
    }

    public static void bootstrapStem(BootstapContext<LevelStem> context) {
        HolderGetter<Biome> biomeRegistry = context.lookup(Registries.BIOME);
        HolderGetter<DimensionType> dimTypes = context.lookup(Registries.DIMENSION_TYPE);
        HolderGetter<NoiseGeneratorSettings> noiseGenSettings = context.lookup(Registries.NOISE_SETTINGS);
        HolderGetter<Biome> biomeHolderGetter = context.lookup(Registries.BIOME);
        HolderGetter<PlacedFeature> placedFeatureHolderGetter = context.lookup(Registries.PLACED_FEATURE);

        FlatLevelGeneratorSettings settings =
                new FlatLevelGeneratorSettings(Optional.empty(), biomeHolderGetter.getOrThrow(ModBiomes.TEST_BIOME), FlatLevelGeneratorSettings.createLakesList(placedFeatureHolderGetter));

        settings.getLayersInfo().add(new FlatLayerInfo(15, Blocks.BEDROCK));
        settings.getLayersInfo().add(new FlatLayerInfo(1, Blocks.COBBLESTONE));


//        NoiseBasedChunkGenerator wrappedChunkGenerator = new NoiseBasedChunkGenerator(
//                new FixedBiomeSource(biomeRegistry.getOrThrow(ModBiomes.TEST_BIOME)),
//                noiseGenSettings.getOrThrow(NoiseGeneratorSettings.AMPLIFIED));
//        NoiseBasedChunkGenerator noiseBasedChunkGenerator = new NoiseBasedChunkGenerator(
//                MultiNoiseBiomeSource.createFromList(
//                        new Climate.ParameterList<>(List.of(
//                                Pair.of(
//                                        Climate.parameters(0.1F, 0.2F, 0.0F, 0.2F, 0.0F, 0.0F, 0.0F), biomeRegistry.getOrThrow(Biomes.BIRCH_FOREST)),
//                                Pair.of(
//                                        Climate.parameters(0.3F, 0.6F, 0.1F, 0.1F, 0.0F, 0.0F, 0.0F), biomeRegistry.getOrThrow(Biomes.OCEAN)),
//                                Pair.of(
//                                        Climate.parameters(0.4F, 0.3F, 0.2F, 0.1F, 0.0F, 0.0F, 0.0F), biomeRegistry.getOrThrow(Biomes.DARK_FOREST))
//
//                        ))),
//                noiseGenSettings.getOrThrow(NoiseGeneratorSettings.AMPLIFIED));

        LevelStem stem = new LevelStem(dimTypes.getOrThrow(ModDimensions.MOD_DIM_TYPE), new FlatLevelSource(settings));

        context.register(MOD_KEY, stem);
    }
}