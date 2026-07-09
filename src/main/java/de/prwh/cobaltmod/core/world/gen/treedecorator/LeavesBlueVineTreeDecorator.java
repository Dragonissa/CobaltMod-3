package de.prwh.cobaltmod.core.world.gen.treedecorator;

import com.mojang.serialization.Codec;
import de.prwh.cobaltmod.core.CobaltMod;
import de.prwh.cobaltmod.core.block.CMBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.VineBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.levelgen.feature.treedecorators.LeaveVineDecorator;

import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

import java.util.Random;


public class LeavesBlueVineTreeDecorator extends LeaveVineDecorator {

	public static final LeavesBlueVineTreeDecorator INSTANCE = new LeavesBlueVineTreeDecorator(new Random().nextFloat());
	// Our constructor doesn't have any arguments, so we create a unit codec that returns the singleton instance
	public static final Codec<LeavesBlueVineTreeDecorator> CODEC = Codec.unit(() -> INSTANCE);
	private final float probability;

	public LeavesBlueVineTreeDecorator(float f) {
		super(f);
		this.probability = f;
	}

	@Override
	protected TreeDecoratorType<?> type() {
		return CobaltMod.LEAVES_BLUE_VINE_TREE_DECORATOR;
	}

	@Override
	public void place(TreeDecorator.Context context) {
		RandomSource randomSource = context.random();
		context.leaves().forEach((blockPos) -> {
			if (randomSource.nextFloat() < this.probability) {
				BlockPos blockPos2 = blockPos.west();
				if (context.isAir(blockPos2)) {
					addHangingVine(blockPos2, VineBlock.EAST, context);
				}
			}

			if (randomSource.nextFloat() < this.probability) {
				BlockPos blockPos2 = blockPos.east();
				if (context.isAir(blockPos2)) {
					addHangingVine(blockPos2, VineBlock.WEST, context);
				}
			}

			if (randomSource.nextFloat() < this.probability) {
				BlockPos blockPos2 = blockPos.north();
				if (context.isAir(blockPos2)) {
					addHangingVine(blockPos2, VineBlock.SOUTH, context);
				}
			}

			if (randomSource.nextFloat() < this.probability) {
				BlockPos blockPos2 = blockPos.south();
				if (context.isAir(blockPos2)) {
					addHangingVine(blockPos2, VineBlock.NORTH, context);
				}
			}

		});
	}

	private static void addHangingVine(BlockPos blockPos, BooleanProperty booleanProperty, TreeDecorator.Context context) {
		placeVine(context, blockPos, booleanProperty);
		int i = 4;

		for(BlockPos var4 = blockPos.below(); context.isAir(var4) && i > 0; --i) {
			placeVine(context, var4, booleanProperty);
			var4 = var4.below();
		}

	}

	private static void placeVine(TreeDecorator.Context context, BlockPos pos, BooleanProperty property) {
		BlockState vineState = CMBlocks.BLUE_VINE.defaultBlockState().setValue(property, true);
		context.setBlock(pos, vineState);
	}
}
