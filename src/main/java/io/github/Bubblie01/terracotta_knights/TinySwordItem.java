package io.github.Bubblie01.terracotta_knights;

import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;

public class TinySwordItem extends SwordItem {

	public TinySwordItem(ToolMaterial toolMaterial, int i, float f, Settings settings) {
		super(toolMaterial, i, f, settings);
	}

	@Override
	public float getAttackDamage() {
		return 1.0F;
	}
}
