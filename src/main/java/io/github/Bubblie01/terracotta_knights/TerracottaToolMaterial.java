package io.github.Bubblie01.terracotta_knights;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class TerracottaToolMaterial implements ToolMaterial {

	public static final TerracottaToolMaterial INSTANCE = new TerracottaToolMaterial();

	@Override
	public int getDurability() {
		return 0;
	}

	@Override
	public float getMiningSpeedMultiplier() {
		return 0;
	}

	@Override
	public float getAttackDamage() {
		return 0;
	}

	@Override
	public int getMiningLevel() {
		return 0;
	}

	@Override
	public int getEnchantability() {
		return 0;
	}

	@Override
	public Ingredient getRepairIngredient() {
		return null;
	}
}
