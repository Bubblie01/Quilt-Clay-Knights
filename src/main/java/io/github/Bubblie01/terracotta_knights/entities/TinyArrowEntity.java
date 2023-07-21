package io.github.Bubblie01.terracotta_knights.entities;

import io.github.Bubblie01.terracotta_knights.Main;
import io.github.Bubblie01.terracotta_knights.items.TinyArrowItem;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.quiltmc.qsl.entity.api.QuiltEntityTypeBuilder;

import static io.github.Bubblie01.terracotta_knights.TerracottaRegistry.TINY_ARROW_ITEM;

public class TinyArrowEntity extends PersistentProjectileEntity {
	//public static final EntityType<TinyArrowEntity> TINY_ARROW = EntityTypeAccessor.callRegister("tiny_arrow", EntityType.Builder.create(TinyArrowEntity::new, SpawnGroup.MISC));

	public static final EntityType<TinyArrowEntity> TINY_ARROW = Registry.register(Registries.ENTITY_TYPE, new Identifier(Main.MOD_ID, "tiny_arrow_entity"),QuiltEntityTypeBuilder.<TinyArrowEntity>create(SpawnGroup.MISC, TinyArrowEntity::new).build());

	public TinyArrowEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
		super(entityType, world);
	}

	public TinyArrowEntity(EntityType<? extends PersistentProjectileEntity> entityType, double d, double e, double f, World world) {
		super(entityType, d, e, f, world);
	}

	public TinyArrowEntity(EntityType<? extends PersistentProjectileEntity> entityType, LivingEntity livingEntity, World world) {
		super(entityType, livingEntity, world);
	}

	public static PersistentProjectileEntity createArrowProjectile(LivingEntity entity, ItemStack stack, float damageModifier) {
		TinyArrowItem arrowItem = (TinyArrowItem) (stack.getItem() instanceof TinyArrowItem ? stack.getItem() : TINY_ARROW_ITEM);
		PersistentProjectileEntity persistentProjectileEntity = arrowItem.createArrow(entity.getWorld(), stack, entity);
		persistentProjectileEntity.applyEnchantmentEffects(entity, damageModifier);
		return persistentProjectileEntity;
	}

	@Override
	protected ItemStack asItemStack() {
		return new ItemStack(TINY_ARROW_ITEM);
	}
}
