package de.prwh.cobaltmod.core.block;

import de.prwh.cobaltmod.core.CobaltMod;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class CobaltGrassBlock extends CMSpreadingBlock {

    static final String CRITERION = "step_on_block";

    public CobaltGrassBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
	public void animateTick(BlockState blockState, Level level, BlockPos blockPos, RandomSource randomSource) {
		super.animateTick(blockState, level, blockPos, randomSource);
		if (randomSource.nextInt(10) == 0) {
			level.addParticle(CobaltMod.COBALT_AURA, (double)blockPos.getX() + randomSource.nextDouble(), (double)blockPos.getY() + 1.1, (double)blockPos.getZ() + randomSource.nextDouble(), (double)0.0F, (double)0.0F, (double)0.0F);
		}

	}

	@Override
	public void stepOn(Level level, BlockPos blockPos, BlockState blockState, Entity entity) {
		if (!level.isClientSide && entity instanceof LivingEntity livingEntity) {
			//TODO adjust for different boot types - Api?
			if (!EnchantmentHelper.hasFrostWalker(livingEntity)) {
				return;
			}
			entity.hurt(level.damageSources().magic(), 1.0F);
		}
		super.stepOn(level, blockPos, blockState, entity);
	}
}
