package io.github.Bubblie01.terracotta_knights.items;

import io.github.Bubblie01.terracotta_knights.TerracottaRegistry;
import io.github.Bubblie01.terracotta_knights.entities.TinyArrowEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

//Code Created by Bubblie01 Under MPL 2.0 License
public class TinyArrowItem extends ArrowItem implements TerracottaItemFlag {
	public TinyArrowItem(Settings settings) {
		super(settings);
	}

	@Override
	public PersistentProjectileEntity createArrow(World world, ItemStack stack, LivingEntity shooter) {
		TinyArrowEntity arrowEntity = new TinyArrowEntity(TerracottaRegistry.TINY_ARROW,shooter, world);
		return arrowEntity;
	}
}
