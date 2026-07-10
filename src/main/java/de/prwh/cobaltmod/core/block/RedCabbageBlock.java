package de.prwh.cobaltmod.core.block;

import de.prwh.cobaltmod.core.item.CMItems;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class RedCabbageBlock extends CMCropBlock {
    public RedCabbageBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

	@Override
	protected ItemLike getBaseSeedId() {
		return CMItems.RED_CABBAGE_SEEDS;
	}
}
