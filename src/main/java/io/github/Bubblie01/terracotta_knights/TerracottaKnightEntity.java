package io.github.Bubblie01.terracotta_knights;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.block.MapColor;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.DyeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.packet.s2c.play.MobSpawnS2CPacket;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class TerracottaKnightEntity extends PathAwareEntity {
	public static final EntityType<TerracottaKnightEntity> CLAY_KNIGHT = Registry.register(Registry.ENTITY_TYPE, new Identifier("clay_knights", "clay_knight_entity"),FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, TerracottaKnightEntity::new).dimensions(EntityDimensions.changing(0.5F, 1.0F)).build());
	public static final TrackedData<Integer> COLOR = DataTracker.registerData(TerracottaKnightEntity.class, TrackedDataHandlerRegistry.INTEGER);
	public static final TrackedData<BlockPos> POS = DataTracker.registerData(TerracottaKnightEntity.class, TrackedDataHandlerRegistry.BLOCK_POS);
	public static final SimpleInventory terracottaKnightInventory = new SimpleInventory(5);
	public DyeColor dyeColor;
	protected TerracottaKnightEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
		super(entityType, world);
	}

	public static void registerClayKnightEntityAttributes() {
		FabricDefaultAttributeRegistry.register(CLAY_KNIGHT, TerracottaKnightEntity.createMobAttributes().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.5f).add(EntityAttributes.GENERIC_FOLLOW_RANGE, 5.0f).add(EntityAttributes.GENERIC_ATTACK_SPEED, 4.0f).add(EntityAttributes.GENERIC_ATTACK_DAMAGE,  1.0f));
	}

	@Override
	protected void initGoals() {
		this.goalSelector.add(0, new TerracottaKnightAttackGoal(this, 10.0f, 2.0f));
		this.goalSelector.add(1, new WanderAroundFarGoal(this,0.5f));
		this.goalSelector.add(0, new LookAroundGoal(this));
		super.initGoals();
	}

	@Override
	protected void initDataTracker() {
		super.initDataTracker();
		this.dataTracker.startTracking(COLOR, MapColor.BROWN.color);
	}

	public int getColor() {
		return this.dataTracker.get(COLOR);
	}

	public void setColor(int color) {
		this.dataTracker.set(COLOR, color);
	}

	@Override
	public NbtCompound writeNbt(NbtCompound nbt) {
		nbt.putInt("Color",this.dataTracker.get(COLOR));
		return super.writeNbt(nbt);
	}

	@Override
	public void readNbt(NbtCompound nbt) {
		if(nbt.getInt("Color") != 0) {
			setColor(nbt.getInt("Color"));
		}
		super.readNbt(nbt);
	}

	@Override
	protected ActionResult interactMob(PlayerEntity player, Hand hand) {
		ItemStack dye = player.getStackInHand(hand);
		if(dye.getItem() instanceof DyeItem) {
			dyeColor = ((DyeItem) dye.getItem()).getColor();
			this.dataTracker.set(COLOR, (dyeColor.getMapColor().color));
		}
		return super.interactMob(player, hand);
	}

	public void rangedAttack() {

	}



	@Override
	public void readFromPacket(MobSpawnS2CPacket packet) {

		super.readFromPacket(packet);
	}
}
