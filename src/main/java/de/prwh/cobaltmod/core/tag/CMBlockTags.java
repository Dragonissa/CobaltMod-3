package de.prwh.cobaltmod.core.tag;

import de.prwh.cobaltmod.core.CobaltMod;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;


public final class CMBlockTags {
    public static final TagKey<Block> LEAVES = register("leaves");
    public static final TagKey<Block> LOGS = register("logs");
    public static final TagKey<Block> LOGS_THAT_BURN = register("logs_that_burn");
    public static final TagKey<Block> COBEX_LOGS = register("cobex_logs");
    public static final TagKey<Block> DIRT = register("dirt");
    public static final TagKey<Block> FARMLAND = register("farmland");

    private CMBlockTags() {
    }

    private static TagKey<Block> register(String id) {
        return TagKey.create(BuiltInRegistries.BLOCK.key(), new ResourceLocation(CobaltMod.MOD_ID, id));
    }
}
