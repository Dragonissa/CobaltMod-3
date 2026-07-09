package de.prwh.cobaltmod.core.block;

import de.prwh.cobaltmod.core.tag.CMBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class CMSaplingBlock extends SaplingBlock {

    public CMSaplingBlock(AbstractTreeGrower abstractTreeGrower, BlockBehaviour.Properties properties) {
        super(abstractTreeGrower, properties);
    }

	@Override
	protected boolean mayPlaceOn(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
		return blockState.is(CMBlockTags.DIRT) ;
	}
}
