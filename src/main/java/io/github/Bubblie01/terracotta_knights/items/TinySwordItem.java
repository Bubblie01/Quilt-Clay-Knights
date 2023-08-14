package io.github.Bubblie01.terracotta_knights.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

//Code Created by Bubblie01 Under MPL 2.0 License
public class TinySwordItem extends SwordItem implements TerracottaItemFlag {

	private final float attackDamage;
	public TinySwordItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Item.Settings settings) {
		super(toolMaterial, attackDamage, attackSpeed, settings);
		this.attackDamage = (float)attackDamage + toolMaterial.getAttackDamage();
	}


	@Override
	public float getAttackDamage() {
		return attackDamage;
	}
}
