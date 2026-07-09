package de.prwh.cobaltmod.core.block;

import de.prwh.cobaltmod.core.item.CMItems;
import de.prwh.cobaltmod.core.tag.CMBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

public class BlueBerryBushBlock extends SweetBerryBushBlock {
	public BlueBerryBushBlock(BlockBehaviour.Properties properties) {
		super(properties);
	}

	@Override
	protected boolean mayPlaceOn(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
		return blockState.is(CMBlockTags.DIRT) ;
	}
	public @NotNull ItemStack getCloneItemStack(BlockGetter blockGetter, BlockPos blockPos, BlockState blockState) {
		return new ItemStack(CMItems.BLUE_BERRY);
	}

	public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
		int i = (Integer)blockState.getValue(AGE);
		boolean bl = i == 3;
		if (!bl && player.getItemInHand(interactionHand).is(Items.BONE_MEAL)) {
			return InteractionResult.PASS;
		} else if (i > 1) {
			int j = 1 + level.random.nextInt(2);
			popResource(level, blockPos, new ItemStack(CMItems.BLUE_BERRY, j + (bl ? 1 : 0)));
			level.playSound((Player)null, blockPos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 0.8F + level.random.nextFloat() * 0.4F);
			BlockState blockState2 = (BlockState)blockState.setValue(AGE, 1);
			level.setBlock(blockPos, blockState2, 2);
			level.gameEvent(GameEvent.BLOCK_CHANGE, blockPos, GameEvent.Context.of(player, blockState2));
			return InteractionResult.sidedSuccess(level.isClientSide);
		} else {
			return super.use(blockState, level, blockPos, player, interactionHand, blockHitResult);
		}
	}
}
