package de.prwh.cobaltmod.core.block.sapling;

import de.prwh.cobaltmod.core.CobaltMod;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import org.jetbrains.annotations.Nullable;

public class TallCobexTreeGrower extends AbstractTreeGrower {
	public TallCobexTreeGrower() {
	}

	@Override
	protected @Nullable ResourceKey<net.minecraft.world.level.levelgen.feature.ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource randomSource, boolean bl) {
		return CobaltMod.TALL_COBEX;
	}
}
