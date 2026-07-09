package de.prwh.cobaltmod.core.block.sapling;

import de.prwh.cobaltmod.core.CobaltMod;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

public class CobexTreeGrower extends AbstractTreeGrower {
    public CobexTreeGrower() {
    }

	@Override
	protected @Nullable ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource randomSource, boolean bl) {
		return CobaltMod.COBEX;
	}
}


