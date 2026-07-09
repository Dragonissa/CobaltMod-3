package de.prwh.cobaltmod.core.api;

import java.util.*;

import de.prwh.cobaltmod.core.CobaltMod;
import de.prwh.cobaltmod.core.block.CMBlocks;
import de.prwh.cobaltmod.core.block.CobaltGrassBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.protocol.game.ClientboundMoveEntityPacket;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.TallFlowerBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;

public class CMReplace {

	private static final HashMap<Block, Block> map = new HashMap<>();
	private static final List<Block> listFlowers = new ArrayList<>();
	private static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.AXIS;

	private CMReplace() {
		throw new IllegalStateException("Utility class");
	}

	/***
	 * Gets the map of blocks
	 *
	 * @return map
	 */
	public static Map<Block, Block> getSpread() {
		return map;
	}

	/***
	 * Gets the list of flowers
	 *
	 * @return list_flowers
	 */
	public static List<Block> getFlowers() {
		return listFlowers;
	}

	/***
	 * Adds the target and replace block to the list
	 *
	 * @param target
	 *            = the block that gets replaced
	 * @param replace
	 *            = the block which should be used for the replacement
	 */
	public static void addBlocks(Block target, Block replace) {
		getSpread().put(target, replace);
	}

	/***
	 * Adds the flower to the flower list
	 *
	 * @param flower
	 *            = the block that gets added to the flower list
	 */
	public static void addFlowers(Block flower) {
		getFlowers().add(flower);
	}


	private static BlockState getReplacementBlock(Block block) {
		if (!CMReplace.getSpread().isEmpty() && CMReplace.getSpread().containsKey(block)) {
			return CMReplace.getSpread().get(block).defaultBlockState();
		}
		return block.defaultBlockState();
	}

	private static BlockState getReplacementFlower(Level level) {
		if (!CMReplace.getFlowers().isEmpty()) {
			return CMReplace.getFlowers().get(level.random.nextInt(CMReplace.getFlowers().size())).defaultBlockState();
		}
		return Blocks.AIR.defaultBlockState();
	}

	private static boolean setBlockState(Level level, BlockPos blockPos, BlockState blockStateNew) {
		return setBlockState(level, blockPos, blockStateNew, level.getBlockState(blockPos).getBlock());
	}

	private static boolean setBlockState(Level level, BlockPos blockPos, BlockState blockStateNew, Block blockOverride) {
		CobaltMod.LOGGER.info("replacing {} with {}", blockOverride.getName(), blockStateNew.getBlock().getName());
		if (CMReplace.getSpread().containsKey(blockOverride)) {
//			if (blockStateNew.getBlock() instanceof CobaltGrassBlock && (!canSpread(blockStateNew, level, blockPos))) {
//				level.setBlockState(blockPos, CMBlocks.COBALT_DIRT.defaultBlockState());
//					return false;
//			}
//			level.setBlockState(blockPos, blockStateNew);
			return true;
		}
		return false;
	}

	/***
	 * Replaces the give block with
	 *
	 * @param level = level
	 * @param blockPos = position of the block that should be replaced
	 */
	public static void replaceBlock(Level level, BlockPos blockPos) {
//		Block block = level.getBlockState(blockPos).getBlock();
//		Block blockUp = level.getBlockState(blockPos.above()).getBlock();
//		Block blockBelow = level.getBlockState(blockPos.below()).getBlock();
//		BlockState blockStateReplace = level.getBlockState(blockPos);
//
//		if (block instanceof RotatedPillarBlock) {
//			setBlockState(level, blockPos, getReplacementBlock(block).with(AXIS, blockStateReplace.get(Properties.AXIS)));
//		} else if (block instanceof FlowerBlock || block instanceof TallFlowerBlock) {
//			CobaltMod.LOGGER.info("Direct replacing");
//			if (setBlockState(level, blockPos.below(), getReplacementBlock(blockBelow))) {
//				world.setBlockState(blockPos, getReplacementFlower(world));
//			}
//		} else if (block instanceof FernBlock || block instanceof TallPlantBlock) {
//			CobaltMod.LOGGER.info("Direct replacing");
//			setBlockState(world, blockPos, Blocks.AIR.getDefaultState());
//			setBlockState(world, blockPos.below(), getReplacementBlock(blockBelow));
//			setBlockState(world, blockPos, getReplacementBlock(block), block);
//		} else {
//			if (blockUp instanceof FlowerBlock) {
//				world.setBlockState(blockPos.up(), Blocks.AIR.getDefaultState());
//				setBlockState(world, blockPos, getReplacementBlock(block));
//				world.setBlockState(blockPos.up(), getReplacementFlower(world));
//			} else if (blockUp instanceof TallFlowerBlock) {
//				CobaltMod.LOGGER.info("Indirect replacing TallFlowerBlock");
//				world.setBlockState(blockPos.up(), Blocks.AIR.getDefaultState());
//				setBlockState(world, blockPos, getReplacementBlock(block));
//				world.setBlockState(blockPos.up(), getReplacementFlower(world));
//			} else if (blockUp instanceof FernBlock) {
//				world.setBlockState(blockPos.up(), Blocks.AIR.getDefaultState());
//				setBlockState(world, blockPos, getReplacementBlock(block));
//				setBlockState(world, blockPos.up(), getReplacementBlock(blockUp), blockUp);
//			} else if (blockUp instanceof TallPlantBlock) {
//				CobaltMod.LOGGER.info("Indirect replacing TallPlantBlock");
//				world.setBlockState(blockPos.up().up(), Blocks.AIR.getDefaultState());
//				world.setBlockState(blockPos.up(), Blocks.AIR.getDefaultState());
//				setBlockState(world, blockPos, getReplacementBlock(block));
//				setBlockState(world, blockPos.up(), getReplacementBlock(blockUp), blockUp);
//			} else {
//				setBlockState(world, blockPos, getReplacementBlock(block));
//			}
//		}
	}

}
