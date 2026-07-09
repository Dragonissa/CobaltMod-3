package de.prwh.cobaltmod.core.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.Nullable;


public class CMFarmlandBlock extends FarmBlock {
    public CMFarmlandBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

	public static void turnToDirt(@Nullable Entity entity, BlockState blockState, Level level, BlockPos blockPos) {
		BlockState blockState2 = pushEntitiesUp(blockState, CMBlocks.COBALT_DIRT.defaultBlockState(), level, blockPos);
		level.setBlockAndUpdate(blockPos, blockState2);
		level.gameEvent(GameEvent.BLOCK_CHANGE, blockPos, GameEvent.Context.of(entity, blockState2));
	}

	public void fallOn(Level level, BlockState blockState, BlockPos blockPos, Entity entity, float f) {
		if (!level.isClientSide && level.random.nextFloat() < f - 0.5F && entity instanceof LivingEntity && (entity instanceof Player || level.getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING)) && entity.getBbWidth() * entity.getBbWidth() * entity.getBbHeight() > 0.512F) {
			turnToDirt(entity, blockState, level, blockPos);
		}
	}

	public void tick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
		if (!blockState.canSurvive(serverLevel, blockPos)) {
			turnToDirt((Entity)null, blockState, serverLevel, blockPos);
		}
	}

	public void randomTick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
		int i = (Integer)blockState.getValue(MOISTURE);
		if (!isNearWater(serverLevel, blockPos) && !serverLevel.isRainingAt(blockPos.above())) {
			if (i > 0) {
				serverLevel.setBlock(blockPos, (BlockState)blockState.setValue(MOISTURE, i - 1), 2);
			} else if (!shouldMaintainFarmland(serverLevel, blockPos)) {
				turnToDirt((Entity)null, blockState, serverLevel, blockPos);
			}
		} else if (i < 7) {
			serverLevel.setBlock(blockPos, (BlockState)blockState.setValue(MOISTURE, 7), 2);
		}
	}

	private static boolean shouldMaintainFarmland(BlockGetter blockGetter, BlockPos blockPos) {
		return blockGetter.getBlockState(blockPos.above()).is(BlockTags.MAINTAINS_FARMLAND);
	}

	private static boolean isNearWater(LevelReader levelReader, BlockPos blockPos) {
		for(BlockPos blockPos2 : BlockPos.betweenClosed(blockPos.offset(-4, 0, -4), blockPos.offset(4, 1, 4))) {
			if (levelReader.getFluidState(blockPos2).is(FluidTags.WATER)) {
				return true;
			}
		}

		return false;
	}
}
