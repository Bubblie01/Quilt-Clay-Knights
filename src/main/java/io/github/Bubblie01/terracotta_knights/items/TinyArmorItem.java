package io.github.Bubblie01.terracotta_knights.items;

import io.github.Bubblie01.terracotta_knights.mixin.ItemAccessorMixin;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

//Code Created by Bubblie01 Under MPL 2.0 License
public class TinyArmorItem extends ArmorItem implements TerracottaItemFlag {

	public TinyArmorItem(ArmorMaterial material, ArmorSlot slot, Settings settings) {
		super(material, slot, settings);
		((ItemAccessorMixin)this).setMaxDamage(0);
		((ItemAccessorMixin)this).setMaxCount(64);
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		return TypedActionResult.success(user.getStackInHand(hand));
	}

	@Override
	public boolean isDamageable() {
		return false;
	}
}
