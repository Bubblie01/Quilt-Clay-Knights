package io.github.Bubblie01.terracotta_knights.entities;

import io.github.Bubblie01.terracotta_knights.mixin.EntityTypeAccessor;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import static io.github.Bubblie01.terracotta_knights.TerracottaRegistry.TINY_PITCHFORK_ITEM;

public class TinyPitchforkEntity extends PersistentProjectileEntity {

	public static final EntityType<TinyPitchforkEntity> TINY_PITCHFORK = EntityTypeAccessor.callRegister("tiny_pitchfork", EntityType.Builder.<TinyPitchforkEntity>create(TinyPitchforkEntity::new, SpawnGroup.MISC).setDimensions(0.5F, 0.8F).maxTrackingRange(4).trackingTickInterval(20));

	protected TinyPitchforkEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
		super(entityType, world);
	}

	public TinyPitchforkEntity(World world, LivingEntity user, ItemStack stack) {
		super(TINY_PITCHFORK, user, world);
	}


	@Override
	protected ItemStack asItemStack() {
		return new ItemStack(TINY_PITCHFORK_ITEM);
	}
}
