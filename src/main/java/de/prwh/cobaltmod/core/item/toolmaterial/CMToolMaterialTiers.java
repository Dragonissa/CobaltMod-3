package de.prwh.cobaltmod.core.item.toolmaterial;

import de.prwh.cobaltmod.core.block.CMBlocks;
import de.prwh.cobaltmod.core.item.CMItems;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public enum CMToolMaterialTiers implements Tier {

	Cobex(1, 150, 1.0f, 1.0f, 10, () -> Ingredient.of(CMBlocks.COBEX_PLANKS)),
	Cobalt(6, 2000, 10.0f, 4.0f, 20, () -> Ingredient.of(CMItems.COBALT_INGOT));

	private final int level;
	private final int uses;
	private final float speed;
	private final float damage;
	private final int enchantmentValue;
	private final LazyLoadedValue<Ingredient> repairIngredient;

	private CMToolMaterialTiers(int j, int k, float f, float g, int l, Supplier<Ingredient> supplier) {
		this.level = j;
		this.uses = k;
		this.speed = f;
		this.damage = g;
		this.enchantmentValue = l;
		this.repairIngredient = new LazyLoadedValue(supplier);
	}

	public int getUses() {
		return this.uses;
	}

	public float getSpeed() {
		return this.speed;
	}

	public float getAttackDamageBonus() {
		return this.damage;
	}

	public int getLevel() {
		return this.level;
	}

	public int getEnchantmentValue() {
		return this.enchantmentValue;
	}

	public @NotNull Ingredient getRepairIngredient() {
		return this.repairIngredient.get();
	}
}
