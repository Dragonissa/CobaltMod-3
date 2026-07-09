package de.prwh.cobaltmod.core.block;

import de.prwh.cobaltmod.core.tag.CMBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;

import java.util.OptionalInt;

public class CMLeavesBlock extends LeavesBlock {
    public CMLeavesBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

	@Override
	public void tick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
		serverLevel.setBlock(blockPos, updateDistance(blockState, serverLevel, blockPos), 3);
	}

	@Override
	public BlockState updateShape(BlockState blockState, Direction direction, BlockState blockState2, LevelAccessor levelAccessor, BlockPos blockPos, BlockPos blockPos2) {
		if ((Boolean)blockState.getValue(WATERLOGGED)) {
			levelAccessor.scheduleTick(blockPos, Fluids.WATER, Fluids.WATER.getTickDelay(levelAccessor));
		}

		int i = getDistanceAt(blockState2) + 1;
		if (i != 1 || (Integer)blockState.getValue(DISTANCE) != i) {
			levelAccessor.scheduleTick(blockPos, this, 1);
		}

		return blockState;
	}

	private static BlockState updateDistance(BlockState blockState, LevelAccessor levelAccessor, BlockPos blockPos) {
		int i = 7;
		BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos();

		for(Direction direction : Direction.values()) {
			mutableBlockPos.setWithOffset(blockPos, direction);
			i = Math.min(i, getDistanceAt(levelAccessor.getBlockState(mutableBlockPos)) + 1);
			if (i == 1) {
				break;
			}
		}

		return (BlockState)blockState.setValue(DISTANCE, i);
	}

	private static int getDistanceAt(BlockState blockState) {
		return getOptionalDistanceAt(blockState).orElse(7);
	}

	public static OptionalInt getOptionalDistanceAt(BlockState blockState) {
		if (blockState.is(CMBlockTags.COBEX_LOGS)) {
			return OptionalInt.of(0);
		} else {
			return blockState.hasProperty(DISTANCE) ? OptionalInt.of((Integer)blockState.getValue(DISTANCE)) : OptionalInt.empty();
		}
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
		FluidState fluidState = blockPlaceContext.getLevel().getFluidState(blockPlaceContext.getClickedPos());
		BlockState blockState = (BlockState)((BlockState)this.defaultBlockState().setValue(PERSISTENT, true)).setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER);
		return updateDistance(blockState, blockPlaceContext.getLevel(), blockPlaceContext.getClickedPos());
	}
}
