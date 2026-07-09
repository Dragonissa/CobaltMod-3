package de.prwh.cobaltmod.core.item;


import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.mojang.datafixers.util.Pair;
import de.prwh.cobaltmod.core.block.CMBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class CMHoeItem extends HoeItem {

	protected static final Map<Block, Pair<Predicate<UseOnContext>, Consumer<UseOnContext>>> TILLABLES;

	public CMHoeItem(Tier tier, int i, float f, Properties properties) {
		super(tier, i, f, properties);
	}

	public InteractionResult useOn(UseOnContext useOnContext) {
		Level level = useOnContext.getLevel();
		BlockPos blockPos = useOnContext.getClickedPos();
		Pair<Predicate<UseOnContext>, Consumer<UseOnContext>> pair = (Pair)TILLABLES.get(level.getBlockState(blockPos).getBlock());
		if (pair == null) {
			return InteractionResult.PASS;
		} else {
			Predicate<UseOnContext> predicate = (Predicate)pair.getFirst();
			Consumer<UseOnContext> consumer = (Consumer)pair.getSecond();
			if (predicate.test(useOnContext)) {
				Player player = useOnContext.getPlayer();
				level.playSound(player, blockPos, SoundEvents.HOE_TILL, SoundSource.BLOCKS, 1.0F, 1.0F);
				if (!level.isClientSide) {
					consumer.accept(useOnContext);
					if (player != null) {
						useOnContext.getItemInHand().hurtAndBreak(1, player, (playerx) -> playerx.broadcastBreakEvent(useOnContext.getHand()));
					}
				}

				return InteractionResult.sidedSuccess(level.isClientSide);
			} else {
				return InteractionResult.PASS;
			}
		}
	}

	static {
			TILLABLES = Maps.newHashMap(ImmutableMap.of(Blocks.GRASS_BLOCK, Pair.of(HoeItem::onlyIfAirAbove, changeIntoState(Blocks.FARMLAND.defaultBlockState())),
				Blocks.DIRT_PATH, Pair.of(HoeItem::onlyIfAirAbove, changeIntoState(Blocks.FARMLAND.defaultBlockState())),
				Blocks.DIRT, Pair.of(HoeItem::onlyIfAirAbove, changeIntoState(Blocks.FARMLAND.defaultBlockState())),
				Blocks.COARSE_DIRT, Pair.of(HoeItem::onlyIfAirAbove, changeIntoState(Blocks.DIRT.defaultBlockState())),
				Blocks.ROOTED_DIRT, Pair.of((Predicate)(useOnContext) -> true, changeIntoStateAndDropItem(Blocks.DIRT.defaultBlockState(), Items.HANGING_ROOTS)),
				CMBlocks.COBALT_GRASS_BLOCK, Pair.of(CMHoeItem::onlyIfAirAbove, changeIntoState(CMBlocks.FARMLAND.defaultBlockState())),
				CMBlocks.COBALT_DIRT, Pair.of(CMHoeItem::onlyIfAirAbove, changeIntoState(CMBlocks.FARMLAND.defaultBlockState()))
			));
	}

}
