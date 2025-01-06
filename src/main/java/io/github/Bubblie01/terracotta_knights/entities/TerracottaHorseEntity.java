package io.github.Bubblie01.terracotta_knights.entities;

import io.github.Bubblie01.terracotta_knights.TerracottaRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.WanderAroundGoal;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class TerracottaHorseEntity extends PathAwareEntity {
	public int tailWagTicks;
	public static final TrackedData<Integer> HCOLOR = DataTracker.registerData(TerracottaHorseEntity.class, TrackedDataHandlerRegistry.INTEGER);
	public TerracottaHorseEntity(EntityType<? extends TerracottaHorseEntity> entityType, World world) {
		super(entityType, world);
	}

	public static void registerTerracottaHorseEntityAttributes() {
		FabricDefaultAttributeRegistry.register(TerracottaRegistry.TERRACOTTA_HORSE, TerracottaHorseEntity.createAttributes().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.7f).add(EntityAttributes.GENERIC_FOLLOW_RANGE, 5.0f).add(EntityAttributes.GENERIC_MAX_HEALTH, 12f));
	}

	@Override
	protected void initGoals() {
		this.goalSelector.add(1, new WanderAroundGoal(this, 0.7f));
		this.goalSelector.add(2, new LookAroundGoal(this));
		super.initGoals();
	}

	public int getColor() {
		return this.dataTracker.get(HCOLOR);
	}

	public void setColor(int color) {
		this.dataTracker.set(HCOLOR, color);
	}

	protected void initDataTracker() {
		super.initDataTracker();
		this.dataTracker.startTracking(HCOLOR, 0);
	}

	@Override
	public void writeCustomDataToNbt(NbtCompound nbt) {
		nbt.putInt("Color",this.dataTracker.get(HCOLOR));
		super.writeCustomDataToNbt(nbt);
	}

	@Override
	public void readCustomDataFromNbt(NbtCompound nbt) {
		setColor(nbt.getInt("Color"));
		super.readCustomDataFromNbt(nbt);
	}

	private void wagTail() {
		this.tailWagTicks = 1;
	}

	@Override
	public void tickMovement() {
		if (this.random.nextInt(200) == 0) {
			this.wagTail();
		}

		if (this.tailWagTicks > 0 && ++this.tailWagTicks > 8) {
			this.tailWagTicks = 0;
		}
		super.tickMovement();
	}

	@Override
	protected void updatePassengerPosition(Entity passenger, Entity.PositionUpdater positionUpdater) {
		super.updatePassengerPosition(passenger, positionUpdater);
		float f = MathHelper.sin(this.bodyYaw * (float) (Math.PI / 180.0));
		float g = MathHelper.cos(this.bodyYaw * (float) (Math.PI / 180.0));
		float h = 0.1F;
		float i = 0.0F;
		positionUpdater.accept(passenger, this.getX() + (double)(0.1F * f), this.getBodyY(0.5) + passenger.getHeightOffset() + 0.0, this.getZ() - (double)(0.1F * g));
		if (passenger instanceof LivingEntity) {
			((LivingEntity)passenger).bodyYaw = this.bodyYaw;
		}
	}
}
