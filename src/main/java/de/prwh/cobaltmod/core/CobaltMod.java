package de.prwh.cobaltmod.core;

import de.prwh.cobaltmod.core.api.CMReplace;
import de.prwh.cobaltmod.core.block.CMBlocks;
import de.prwh.cobaltmod.core.item.CMItems;
import de.prwh.cobaltmod.core.world.gen.treedecorator.LeavesBlueVineTreeDecorator;
import de.prwh.cobaltmod.mixin.TreeDecoratorTypeInvoker;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.kyrptonaught.customportalapi.api.CustomPortalBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CobaltMod implements ModInitializer {

	public static final String MOD_ID = "mod_cobalt";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	//ItemGroup
	public static final ResourceKey<CreativeModeTab> BLOCK_GROUP_KEY = ResourceKey.create(BuiltInRegistries.CREATIVE_MODE_TAB.key(), new ResourceLocation(MOD_ID, "block_group"));
	public static final CreativeModeTab BLOCK_GROUP = FabricItemGroup.builder()
		.icon(() -> new ItemStack(CMBlocks.COBALT_ORE))
		.title(Component.translatable("itemGroup.mod_cobalt.block_group"))
		.build();

	public static final ResourceKey<CreativeModeTab> ITEM_GROUP_KEY = ResourceKey.create(BuiltInRegistries.CREATIVE_MODE_TAB.key(), new ResourceLocation(MOD_ID, "item_group"));
	public static final CreativeModeTab ITEM_GROUP = FabricItemGroup.builder()
		.icon(() -> new ItemStack(CMItems.COBALT_INGOT))
		.title(Component.translatable("itemGroup.mod_cobalt.item_group"))
		.build();


	//Particle
	public static final SimpleParticleType COBALT_AURA = FabricParticleTypes.simple();

	//Trees
	public static final ResourceKey<ConfiguredFeature<?, ?>> COBEX = ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(MOD_ID, "cobex_tree"));
	public static final ResourceKey<ConfiguredFeature<?, ?>> TALL_COBEX = ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(MOD_ID, "tall_cobex_tree"));

	//TreeDecorator
	public static final TreeDecoratorType<LeavesBlueVineTreeDecorator> LEAVES_BLUE_VINE_TREE_DECORATOR = TreeDecoratorTypeInvoker.callRegister("mod_cobalt:leaves_blue_vine_tree_decorator", LeavesBlueVineTreeDecorator.CODEC);

	//FoodComponent
	public static final FoodProperties BLUE_BERRY = (new FoodProperties.Builder()).nutrition(1).saturationMod(0.1F).build();
	public static final FoodProperties RED_CABBAGE = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.3F).build();
	public static final FoodProperties COOKED_RED_CABBAGE = (new FoodProperties.Builder()).nutrition(4).saturationMod(0.6F).build();
	public static final FoodProperties COBALT_APPLE = (new FoodProperties.Builder()).nutrition(6).saturationMod(0.8F).build();

	//DamageType
	public static final ResourceKey<DamageType> COBALT_MAGIC_DAMAGE_TYPE = ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(MOD_ID, "cobalt_magic"));

	@Override
	public void onInitialize() {

		Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, BLOCK_GROUP_KEY, BLOCK_GROUP);
		Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, ITEM_GROUP_KEY, ITEM_GROUP);

		Registry.register(BuiltInRegistries.PARTICLE_TYPE, new ResourceLocation(MOD_ID, "cobalt_aura"), COBALT_AURA);

		CMBlocks.init();
		CMItems.init();

//		setTreeFeatureCobex(FeatureUtils.register("cobex", Feature.TREE, (createStraightBlobTree(CMBlocks.COBEX_LOG, CMBlocks.COBEX_LEAVES, 4, 2, 0, 2)).dirt(BlockStateProvider.simple(CMBlocks.COBALT_DIRT)).ignoreVines().build()));
//		setTreeFeatureTallCobex(FeatureUtils.register("tall_cobex", Feature.TREE, (createStraightBlobTree(CMBlocks.COBEX_LOG, CMBlocks.TALL_COBEX_LEAVES, 8, 2, 0, 2)).dirtProvider(BlockStateProvider.of(CMBlocks.COBALT_DIRT)).ignoreVines().build()));

		CustomPortalBuilder.beginPortal()
			.frameBlock(CMBlocks.PORTAL_FRAME)
			.lightWithItem(CMItems.FIRE_SHARD)
			.destDimID(new ResourceLocation(MOD_ID, "cobaldis"))
			.tintColor(7, 37, 94)
			.onlyLightInOverworld()
			.setPortalSearchYRange(50, 80)
			.setReturnPortalSearchYRange(50, 80)
			.registerPortal();

		CMReplace.addBlocks(CMBlocks.COBALT_DIRT, CMBlocks.COBALT_GRASS_BLOCK);
		CMReplace.addBlocks(Blocks.DIRT, CMBlocks.COBALT_GRASS_BLOCK);
		CMReplace.addBlocks(Blocks.GRASS_BLOCK, CMBlocks.COBALT_GRASS_BLOCK);
		CMReplace.addBlocks(Blocks.ACACIA_LOG, CMBlocks.COBEX_LOG);
		CMReplace.addBlocks(Blocks.BIRCH_LOG, CMBlocks.COBEX_LOG);
		CMReplace.addBlocks(Blocks.DARK_OAK_LOG, CMBlocks.COBEX_LOG);
		CMReplace.addBlocks(Blocks.JUNGLE_LOG, CMBlocks.COBEX_LOG);
		CMReplace.addBlocks(Blocks.OAK_LOG, CMBlocks.COBEX_LOG);
		CMReplace.addBlocks(Blocks.SPRUCE_LOG, CMBlocks.COBEX_LOG);
		CMReplace.addBlocks(Blocks.GRASS, CMBlocks.BLUE_GRASS);
		CMReplace.addBlocks(Blocks.TALL_GRASS, CMBlocks.BLUE_GRASS);

		CMReplace.addFlowers(CMBlocks.CLEMATIS_FLOWER);
		CMReplace.addFlowers(CMBlocks.BELL_FLOWER);
		CMReplace.addFlowers(CMBlocks.GLOW_FLOWER);
	}
}
