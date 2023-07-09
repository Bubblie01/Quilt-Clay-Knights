package io.github.Bubblie01.terracotta_knights;

import io.github.Bubblie01.terracotta_knights.entities.TinyArrowEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class TinyArrowItem extends ArrowItem {
	public TinyArrowItem(Settings settings) {
		super(settings);
	}

	@Override
	public PersistentProjectileEntity createArrow(World world, ItemStack stack, LivingEntity shooter) {
		TinyArrowEntity arrowEntity = new TinyArrowEntity(TinyArrowEntity.TINY_ARROW,shooter, world);
		return arrowEntity;
	}
}
