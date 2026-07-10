package de.prwh.cobaltmod.core.block;

import de.prwh.cobaltmod.core.CobaltMod;
import de.prwh.cobaltmod.core.block.sapling.CobexTreeGrower;
import de.prwh.cobaltmod.core.block.sapling.TallCobexTreeGrower;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;


public class CMBlocks {

    // Blocks
    public static Block COBALT_ORE;
	public static Block HARDENED_COBALT_ORE;
    public static Block COBALT_GRASS_BLOCK;
    public static Block COBALT_BLOCK;
    public static Block COBEX_LOG;
    public static Block COBEX_PLANKS;
	public static Block COBEX_SAPLING;
	public static Block TALL_COBEX_SAPLING;
	public static Block COBEX_LEAVES;
	public static Block TALL_COBEX_LEAVES;
    public static Block BLUE_GRASS;

    public static Block CLEMATIS_FLOWER;
    public static Block RED_CABBAGE_CROP;
    public static Block COBALT_BRICK;
    public static Block COBALT_BRICK_STAIR;
    public static Block COBALT_BRICK_SLAB;
    public static Block COBALT_STONE;
    //	public static BlockPortal PORTAL_COBALDIS;
    public static Block COBEX_TORCH;
    public static Block COBEX_WALL_TORCH;

    public static Block BLUEBERRY_BUSH;
    public static Block RITUAL_STONE;
    public static Block ALTAR_OF_ASSOCIATION;
    public static Block COBEX_STAIR;

    public static Block COBEX_SLAB;

    public static Block BLUE_FIRE;
    public static Block BELL_FLOWER;
    public static Block BOUNCY_COBALT;
    public static Block CORRUPTED_STONE_FURNACE_BURNING;
    public static Block CORRUPTED_STONE_FURNACE_IDLE;
    public static Block COBEX_CHEST;
    public static Block COBEX_DOOR;
    public static Block COBALT_DOOR;
    public static Block PORTAL_FRAME;
    public static Block COBEX_WORK_BENCH;
    public static Block COBALT_BED;
    public static Block DARK_WATER;
    public static Block COBALT_RUNE;
    public static Block CARTHUN_ORE;
    public static Block CARTHUN_BLOCK;
    public static Block CARTHUN_BRICK;
    public static Block CARTHUN_BRICK_STAIR;

//	public static BlockSlab CARTHUN_BRICK_SLAB;

    public static Block GLOW_FLOWER;
    public static Block BLUE_VINE;
    public static Block WATER_THORN;
    public static Block CORRUPTED_STONE;
    public static Block COBALT_DIRT;
    public static Block FARMLAND;
    public static Block PODIUM;
    public static Block LOCKED_COBALT_CHEST;
    public static Block COBALT_CHEST;

    public static Block HARDENED_CORRUPTED_STONE;

    public static Block BLUISH_MUSHROOM;
    public static Block COBEX_DEAD_BUSH;

    public static Block NEUTRALIZER_BURNING;
    public static Block NEUTRALIZER_IDLE;

    private CMBlocks() {
    }

    public static void init() {
        COBALT_ORE = registerBlock("cobalt_ore", new DropExperienceBlock(FabricBlockSettings.create().sounds(SoundType.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F)));
		HARDENED_COBALT_ORE = registerBlock("hardened_cobalt_ore", new DropExperienceBlock(FabricBlockSettings.copy(COBALT_ORE).requiresCorrectToolForDrops().strength(4.5F, 3.0F)));
		CORRUPTED_STONE = registerBlock("corrupted_stone", new Block(FabricBlockSettings.create().sounds(SoundType.STONE).requiresCorrectToolForDrops().strength(1.5F, 3.0F)));
        COBALT_GRASS_BLOCK = registerBlock("cobalt_grass_block", new CobaltGrassBlock(FabricBlockSettings.create().sounds(SoundType.GRASS).ticksRandomly().strength(0.6F)));
        COBALT_DIRT = registerBlock("cobalt_dirt", new Block(FabricBlockSettings.create().strength(0.5F).sounds(SoundType.GRAVEL)));
        COBALT_BLOCK = registerBlock("cobalt_block", new Block(FabricBlockSettings.create().requiresTool().strength(5.0F, 6.0F).sounds(SoundType.METAL)));
        COBEX_LOG = registerBlock("cobex_log", new RotatedPillarBlock(FabricBlockSettings.create().strength(2.0F).sounds(SoundType.WOOD)));
        COBEX_PLANKS = registerBlock("cobex_planks", new Block(FabricBlockSettings.create().strength(2.0F).sounds(SoundType.WOOD)));
        COBEX_LEAVES = registerBlock("cobex_leaves", createLeavesBlock());
		TALL_COBEX_LEAVES = registerBlock("tall_cobex_leaves", createLeavesBlock());
		COBEX_SAPLING = registerBlock("cobex_sapling", new CMSaplingBlock(new CobexTreeGrower(), FabricBlockSettings.create().noCollision().ticksRandomly().breakInstantly().sounds(SoundType.GRASS).nonOpaque()));
		TALL_COBEX_SAPLING = registerBlock("tall_cobex_sapling", new CMSaplingBlock(new TallCobexTreeGrower(), FabricBlockSettings.create().noCollision().ticksRandomly().breakInstantly().sounds(SoundType.GRASS).nonOpaque()));
		BLUE_GRASS = registerBlock("blue_grass", new CMFernBlock(FabricBlockSettings.create().replaceable().noCollision().breakInstantly().sounds(SoundType.GRASS).nonOpaque().offsetType(BlockBehaviour.OffsetType.XZ)));
        CLEMATIS_FLOWER = registerBlock("clematis_flower", new CMFernBlock(FabricBlockSettings.create().noCollision().breakInstantly().sounds(SoundType.GRASS).nonOpaque().offsetType(BlockBehaviour.OffsetType.XZ)));
        BELL_FLOWER = registerBlock("bell_flower", new CMFernBlock(FabricBlockSettings.create().noCollision().breakInstantly().sounds(SoundType.GRASS).nonOpaque().offsetType(BlockBehaviour.OffsetType.XZ)));
        GLOW_FLOWER = registerBlock("glow_flower", new CMFernBlock(FabricBlockSettings.create().noCollision().breakInstantly().sounds(SoundType.GRASS).nonOpaque().luminance(10).offsetType(BlockBehaviour.OffsetType.XZ)));
        RED_CABBAGE_CROP = registerBlock("red_cabbage_crop", new RedCabbageBlock(FabricBlockSettings.create().noCollision().ticksRandomly().breakInstantly().sounds(SoundType.CROP)), false, true);
        FARMLAND = registerBlock("farmland", new CMFarmlandBlock(FabricBlockSettings.create().ticksRandomly().strength(0.6F).sounds(SoundType.GRAVEL).blockVision(Blocks::always).suffocates(Blocks::always)));
        COBEX_TORCH = registerBlock("cobex_torch", new TorchBlock(FabricBlockSettings.create().noCollision().breakInstantly().luminance(14).sounds(SoundType.WOOD), ParticleTypes.FLAME), false, false);
        COBEX_WALL_TORCH = registerBlock("cobex_wall_torch", new WallTorchBlock(FabricBlockSettings.create().noCollision().breakInstantly().luminance(14).sounds(SoundType.WOOD).dropsLike(COBEX_TORCH), ParticleTypes.FLAME), false, false);
        COBEX_STAIR = registerBlock("cobex_stairs", new StairBlock(COBEX_PLANKS.defaultBlockState(), FabricBlockSettings.copy(COBEX_PLANKS)));
        COBEX_SLAB = registerBlock("cobex_slab", new SlabBlock(FabricBlockSettings.create().strength(2.0F, 3.0F).sounds(SoundType.WOOD)));
        COBALT_BRICK = registerBlock("cobalt_brick", new Block(FabricBlockSettings.create().strength(2.0F).sounds(SoundType.STONE)));
        COBALT_BRICK_STAIR = registerBlock("cobalt_brick_stairs", new StairBlock(COBALT_BRICK.defaultBlockState(), FabricBlockSettings.copy(COBALT_BRICK)));
        COBALT_BRICK_SLAB = registerBlock("cobalt_brick_slab", new SlabBlock(FabricBlockSettings.create().strength(2.0F, 3.0F).sounds(SoundType.STONE)));
        PORTAL_FRAME = registerBlock("portal_frame", new Block(FabricBlockSettings.create().strength(-1.0F, 3600000.0F).dropsNothing().sound(SoundType.STONE)));
        HARDENED_CORRUPTED_STONE = registerBlock("hardened_corrupted_stone", new Block(FabricBlockSettings.copy(CORRUPTED_STONE)));
		BLUEBERRY_BUSH = registerBlock("blueberry_bush", new BlueBerryBushBlock(FabricBlockSettings.create().ticksRandomly().noCollision().sounds(SoundType.SWEET_BERRY_BUSH)), false, false);
//
		BLUE_VINE = registerBlock("blue_vine", new VineBlock(FabricBlockSettings.copy(Blocks.VINE)));
		//COBEX_CHEST = addBlock("cobex_chest", new ChestBlock(FabricBlockSettings.of(Material.WOOD).strength(2.5F).sounds(BlockSoundGroup.WOOD), () -> BlockEntityType.CHEST));
	}

    private static <T extends Block> T registerBlock(String name, T block) {
        return registerBlock(name, block, true, true);
    }

    private static <T extends Block> T registerBlock(String name, T block, boolean addToItemGroup, boolean registerBlockItem) {
        Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(CobaltMod.MOD_ID, name), block);

		if (addToItemGroup) {
			addToItemGroup(block);
		}

        FabricItemSettings itemSettings = new FabricItemSettings();
        if (registerBlockItem) {
            Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(CobaltMod.MOD_ID, name), new BlockItem(block, itemSettings));
        }
        return block;
    }

	private static void addToItemGroup(Block block) {
		ItemGroupEvents.modifyEntriesEvent(CobaltMod.BLOCK_GROUP_KEY).register(content -> {
			content.accept(block);
		});
	}

    private static CMLeavesBlock createLeavesBlock() {
        return new CMLeavesBlock(FabricBlockSettings.create().strength(0.2F).ticksRandomly().nonOpaque().allowsSpawning(Blocks::never).suffocates(Blocks::never).blockVision(Blocks::never).sound(SoundType.GRASS));
    }
}
