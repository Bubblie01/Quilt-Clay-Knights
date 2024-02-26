package io.github.Bubblie01.terracotta_knights.entities;

import io.github.Bubblie01.terracotta_knights.TerracottaRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.passive.HorseBaseEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.EntityView;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.quiltmc.qsl.entity.effect.api.StatusEffectRemovalReason;

public class TerracottaHorseEntity extends HorseBaseEntity {
	public static final TrackedData<Integer> COLOR = DataTracker.registerData(TerracottaKnightEntity.class, TrackedDataHandlerRegistry.INTEGER);
	public TerracottaHorseEntity(EntityType<? extends HorseBaseEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	public EntityView getEntityView() {
		return null;
	}

	@Nullable
	@Override
	public LivingEntity getOwner() {
		return super.getOwner();
	}

	@Override
	public int getJumpCooldown() {
		return super.getJumpCooldown();
	}

	@Override
	public SoundEvent getSaddleSound() {
		return super.getSaddleSound();
	}

	@Override
	public boolean cannotBeSilenced() {
		return super.cannotBeSilenced();
	}

	@Override
	public boolean removeStatusEffect(@NotNull StatusEffect type, @NotNull StatusEffectRemovalReason reason) {
		return super.removeStatusEffect(type, reason);
	}

	@Override
	public int clearStatusEffects(@NotNull StatusEffectRemovalReason reason) {
		return super.clearStatusEffects(reason);
	}

	@Override
	public void onStatusEffectRemoved(@NotNull StatusEffectInstance effect, @NotNull StatusEffectRemovalReason reason) {
		super.onStatusEffectRemoved(effect, reason);
	}

	public static void registerTerracottaHorseEntityAttributes() {
		FabricDefaultAttributeRegistry.register(TerracottaRegistry.TERRACOTTA_HORSE, TerracottaKnightEntity.createAttributes().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.5f).add(EntityAttributes.GENERIC_FOLLOW_RANGE, 5.0f).add(EntityAttributes.GENERIC_MAX_HEALTH, 12f));
	}

	public int getColor() {
		return this.dataTracker.get(COLOR);
	}

	public void setColor(int color) {
		this.dataTracker.set(COLOR, color);
	}

	protected void initDataTracker() {
		super.initDataTracker();
		this.dataTracker.startTracking(COLOR, 0);
	}

	@Override
	public NbtCompound writeNbt(NbtCompound nbt) {
		nbt.putInt("Color",this.dataTracker.get(COLOR));
		return super.writeNbt(nbt);
	}

	@Override
	public void readNbt(NbtCompound nbt) {
		if (nbt.getInt("Color") != 0) {
			setColor(nbt.getInt("Color"));
		}
	}
}
