package de.prwh.cobaltmod.core.client;

import de.prwh.cobaltmod.core.CobaltMod;
import de.prwh.cobaltmod.core.block.CMBlocks;
import de.prwh.cobaltmod.core.config.ModConfig;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.particle.SuspendedTownParticle;
import net.minecraft.client.renderer.RenderType;

public class CobaltModClient implements ClientModInitializer {

	@Environment(EnvType.CLIENT)
	@Override
	public void onInitializeClient() {
		BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.cutout(),
			CMBlocks.COBEX_SAPLING,
			CMBlocks.TALL_COBEX_SAPLING,
			CMBlocks.BLUE_GRASS,
			CMBlocks.CLEMATIS_FLOWER,
			CMBlocks.BELL_FLOWER,
			CMBlocks.GLOW_FLOWER,
			CMBlocks.RED_CABBAGE_CROP,
			CMBlocks.COBEX_TORCH,
			CMBlocks.COBEX_WALL_TORCH,
			CMBlocks.BLUEBERRY_BUSH,
			CMBlocks.BLUE_VINE);

		ParticleFactoryRegistry.getInstance().register(CobaltMod.COBALT_AURA, SuspendedTownParticle.Provider::new);

		if (FabricLoader.getInstance().isModLoaded("cloth-config")) {
			AutoConfig.register(ModConfig.class, GsonConfigSerializer::new);
		}
	}
}
