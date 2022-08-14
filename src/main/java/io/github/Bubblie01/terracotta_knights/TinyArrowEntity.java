package io.github.Bubblie01.terracotta_knights;

import io.github.Bubblie01.terracotta_knights.mixin.EntityTypeAccessor;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.*;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Potions;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

import static io.github.Bubblie01.terracotta_knights.TerracottaRegistry.TINY_ARROW_ITEM;

public class TinyArrowEntity extends PersistentProjectileEntity {
	public static final EntityType<TinyArrowEntity> TINY_ARROW = EntityTypeAccessor.callRegister("tiny_arrow", EntityType.Builder.create(TinyArrowEntity::new,SpawnGroup.MISC));

	protected TinyArrowEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
		super(entityType, world);
	}

	protected TinyArrowEntity(EntityType<? extends PersistentProjectileEntity> entityType, double d, double e, double f, World world) {
		super(entityType, d, e, f, world);
	}

	protected TinyArrowEntity(EntityType<? extends PersistentProjectileEntity> entityType, LivingEntity livingEntity, World world) {
		super(entityType, livingEntity, world);
	}

	public static PersistentProjectileEntity createArrowProjectile(LivingEntity entity, ItemStack stack, float damageModifier) {
		TinyArrowItem arrowItem = (TinyArrowItem) (stack.getItem() instanceof TinyArrowItem ? stack.getItem() : TINY_ARROW_ITEM);
		PersistentProjectileEntity persistentProjectileEntity = arrowItem.createArrow(entity.world, stack, entity);
		persistentProjectileEntity.applyEnchantmentEffects(entity, damageModifier);
		return persistentProjectileEntity;
	}

	@Override
	protected ItemStack asItemStack() {
		return new ItemStack(TINY_ARROW_ITEM);
	}
}
