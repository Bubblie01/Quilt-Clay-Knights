package io.github.Bubblie01.terracotta_knights.entities;

import io.github.Bubblie01.terracotta_knights.*;
import io.github.Bubblie01.terracotta_knights.entities.ai.ItemPickupGoal;
import io.github.Bubblie01.terracotta_knights.entities.ai.RunAwayFromEntityGoal;
import io.github.Bubblie01.terracotta_knights.entities.ai.TerracottaKnightAttackGoal;
import io.github.Bubblie01.terracotta_knights.items.*;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.FleeEntityGoal;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

//Code Created by Bubblie01 Under MPL 2.0 License
public class TerracottaKnightEntity extends PathAwareEntity {
	//public static final EntityType<TerracottaKnightEntity> TERRACOTTA_KNIGHT = Registry.register(Registry.ENTITY_TYPE, new Identifier(Main.MOD_ID, "terracotta_knight_entity"),FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, TerracottaKnightEntity::new).dimensions(EntityDimensions.changing(0.5F, 1.2F)).build());
	public static final TrackedData<Integer> COLOR = DataTracker.registerData(TerracottaKnightEntity.class, TrackedDataHandlerRegistry.INTEGER);
	public static final TrackedData<Integer> INVENTORY = DataTracker.registerData(TerracottaKnightEntity.class, TrackedDataHandlerRegistry.INTEGER);
	public static final TrackedData<BlockPos> POS = DataTracker.registerData(TerracottaKnightEntity.class, TrackedDataHandlerRegistry.BLOCK_POS);
	private final SimpleInventory terracottaKnightInventory = new SimpleInventory(2);
	public DyeColor dyeColor;

	private Entity entityTarget;
	public TerracottaKnightEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
		super(entityType, world);
	}

	public static void registerClayKnightEntityAttributes() {
		FabricDefaultAttributeRegistry.register(TerracottaRegistry.TERRACOTTA_KNIGHT, TerracottaKnightEntity.createAttributes().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.5f).add(EntityAttributes.GENERIC_FOLLOW_RANGE, 5.0f).add(EntityAttributes.GENERIC_ATTACK_SPEED, 4.0f).add(EntityAttributes.GENERIC_ATTACK_DAMAGE,  0.5f).add(EntityAttributes.GENERIC_MAX_HEALTH, 12f));
	}

	@Override
	protected void initGoals() {
		this.goalSelector.add(0, new TerracottaKnightAttackGoal(this, 30.0f, 2.0f));
		this.goalSelector.add(1, new WanderAroundFarGoal(this,0.5f));
		this.goalSelector.add(2, new LookAroundGoal(this));
		this.goalSelector.add(1, new ItemPickupGoal(this, 7.0f));
		this.goalSelector.add(2, new RunAwayFromEntityGoal(this, 15.0f));
		super.initGoals();
	}

	@Override
	public boolean canUseRangedWeapon(RangedWeaponItem weapon) {
		return true;
	}

	@Override
	protected void loot(ItemEntity item) {
		ItemStack itemStack = item.getStack();
		ItemStack itemStack2 = this.tryEquip(itemStack.copy());

		if(!itemStack2.isEmpty() && itemStack2.getItem() != Items.TNT) {
			this.triggerItemPickedUpByEntityCriteria(item);
			this.sendPickup(item, itemStack2.getCount());
			itemStack.decrement(itemStack2.getCount());
			if (itemStack.isEmpty()) {
				item.discard();
			}
		}
		if(itemStack2.getItem() == Items.TNT) {
			this.triggerItemPickedUpByEntityCriteria(item);
			int i = itemStack.getCount();
			ItemStack itemStack3 = terracottaKnightInventory.addStack(itemStack);
			this.sendPickup(item, i - itemStack3.getCount());
			if (itemStack3.isEmpty()) {
				item.discard();
			} else {
				itemStack.setCount(itemStack3.getCount());
			}
			if(this.findItemInventory(Items.TNT).getCount() >= 3) {
				int temp = this.findItemInventory(Items.TNT).getCount();
				int temp2 = temp - 3;
				this.terracottaKnightInventory.removeItem(Items.TNT, temp2);
				ItemStack itemStack4 = Items.TNT.getDefaultStack();
				itemStack4.setCount(temp2);
				this.dropStack(itemStack4);
				}
			this.setTntCount(this.findItemInventory(Items.TNT).getCount());
			}
		}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundEvents.BLOCK_STONE_PLACE;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.BLOCK_DECORATED_POT_BREAK;
	}

	@Override
	public ItemStack tryEquip(ItemStack equipment) {
		EquipmentSlot equipmentSlot = getPreferredEquipmentSlot(equipment);
		ItemStack itemStack = this.getEquippedStack(equipmentSlot);
		boolean bl = this.prefersNewEquipment(equipment, itemStack);
		if(equipment.getItem() == Items.TNT) {
			ItemStack itemStack2 = equipment.copy();
			return itemStack2;
		}
		if (bl && this.canPickupItem(equipment)) {
			double d = (double)this.getDropChance(equipmentSlot);
			if (!itemStack.isEmpty() && (double)Math.max(this.random.nextFloat() - 0.1F, 0.0F) < d) {
				this.dropStack(itemStack);
			}

			if (equipmentSlot.isArmorSlot() && equipment.getCount() > 1) {
				ItemStack itemStack2 = equipment.withCount(1);
				this.equipStack(equipmentSlot, itemStack2);
				return itemStack2;
			}
			else if(equipment.getItem() == TerracottaRegistry.TINY_BOW_ITEM && equipment.getCount() > 1) {
				ItemStack itemStack2 = equipment.withCount(1);
				this.equipLootStack(equipmentSlot, itemStack2);
				return itemStack2;
			}
			else {
				this.equipLootStack(equipmentSlot, equipment);
				return equipment;
			}
		}
		else {
			return ItemStack.EMPTY;
		}


	}

	public Entity getEntityTarget() {
		return entityTarget;
	}

	public void setEntityTarget(Entity entityTarget) {
		this.entityTarget = entityTarget;
	}

	public ItemStack findItemInventory(Item item) {
		for(int i = terracottaKnightInventory.size() - 1; i >= 0; --i) {
			ItemStack itemStack = terracottaKnightInventory.getStack(i);
			if(itemStack.getItem().equals(item)) {
				return itemStack;
			}
		}
		return ItemStack.EMPTY;
	}

	public SimpleInventory getInventory() {
		return terracottaKnightInventory;
	}
	@Override
	public void onDeath(DamageSource source) {
		super.onDeath(source);
		if(this.getWorld().getGameRules().get(GameRules.DO_ENTITY_DROPS).get()) {
			TerracottaKnightItem item = TerracottaRegistry.TERRACOTTA_KNIGHT_ITEM;
			ItemStack stack = item.getDefaultStack();
			stack.setCustomName(this.getCustomName());
			item.setColor(stack, this.getColor());
			this.dropStack(stack);
		}
		for(int i = 0; i < terracottaKnightInventory.size(); i++) {
			this.dropStack(terracottaKnightInventory.getStack(i));
		}

	}

	public boolean prefersNewEquipment(ItemStack newStack, ItemStack oldStack) {
			if (oldStack.isEmpty()) {
				return true;
			} else if (newStack.getItem() instanceof SwordItem) {
				if (!(oldStack.getItem() instanceof SwordItem)) {
					return false;
				} else {
					SwordItem swordItem = (SwordItem)newStack.getItem();
					SwordItem swordItem2 = (SwordItem)oldStack.getItem();
					if (swordItem.getAttackDamage() != swordItem2.getAttackDamage()) {
						return swordItem.getAttackDamage() > swordItem2.getAttackDamage();
					} else {
						return this.prefersNewDamageableItem(newStack, oldStack);
					}
				}
			} else if (newStack.getItem() instanceof BowItem && oldStack.getItem() instanceof BowItem) {
				return this.prefersNewDamageableItem(newStack, oldStack);
			} else if (newStack.getItem() instanceof CrossbowItem && oldStack.getItem() instanceof CrossbowItem) {
				return this.prefersNewDamageableItem(newStack, oldStack);
			} else if (newStack.getItem() instanceof ArmorItem) {
				if (EnchantmentHelper.hasBindingCurse(oldStack)) {
					return false;
				} else if (!(oldStack.getItem() instanceof ArmorItem)) {
					return true;
				} else {
					ArmorItem armorItem = (ArmorItem)newStack.getItem();
					ArmorItem armorItem2 = (ArmorItem)oldStack.getItem();
					if (armorItem.getProtection() != armorItem2.getProtection()) {
						return armorItem.getProtection() > armorItem2.getProtection();
					} else if (armorItem.getToughness() != armorItem2.getToughness()) {
						return armorItem.getToughness() > armorItem2.getToughness();
					} else {
						return this.prefersNewDamageableItem(newStack, oldStack);
					}
				}
			} else {
				if (newStack.getItem() instanceof MiningToolItem) {
					if (oldStack.getItem() instanceof BlockItem) {
						return true;
					}

					if (oldStack.getItem() instanceof MiningToolItem) {
						MiningToolItem miningToolItem = (MiningToolItem)newStack.getItem();
						MiningToolItem miningToolItem2 = (MiningToolItem)oldStack.getItem();
						if (miningToolItem.getAttackDamage() != miningToolItem2.getAttackDamage()) {
							return miningToolItem.getAttackDamage() > miningToolItem2.getAttackDamage();
						}

						return this.prefersNewDamageableItem(newStack, oldStack);
					}
				}
				return false;
			}
		}

	@Override
	public boolean canPickupItem(ItemStack stack) {
		if(stack.getItem() instanceof TerracottaItemFlag) {
			return true;
		}
		else if(stack.getItem() == Items.TNT) {
			if(findItemInventory(Items.TNT).getCount() < 3) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void tick() {
		super.tick();
	}

	@Override
	public void tickMovement() {
		this.tickHandSwing();
		super.tickMovement();
	}

	@Override
	public boolean canPickUpLoot() {
		return true;
	}

	@Override
	protected void initDataTracker() {
		super.initDataTracker();
		this.dataTracker.startTracking(COLOR, 0);
		this.dataTracker.startTracking(INVENTORY, 0);
	}


	public int getColor() {
		return this.dataTracker.get(COLOR);
	}

	public void setColor(int color) {
		this.dataTracker.set(COLOR, color);
	}

	public void setTntCount(int count) {
		this.dataTracker.set(INVENTORY, count);
	}

	public int getTntCount() {
		return this.dataTracker.get(INVENTORY);
	}

	@Override
	protected void mobTick() {
		super.mobTick();
	}

	@Override
	public NbtCompound writeNbt(NbtCompound nbt) {
		nbt.putInt("Color",this.dataTracker.get(COLOR));
		nbt.put("Inventory", terracottaKnightInventory.toNbtList());

		return super.writeNbt(nbt);
	}

	@Override
	public void readNbt(NbtCompound nbt) {
		if(nbt.getInt("Color") != 0) {
			setColor(nbt.getInt("Color"));
		}
		if (nbt.contains("Inventory", NbtElement.LIST_TYPE)) {
			terracottaKnightInventory.readNbtList(nbt.getList("Inventory", NbtElement.COMPOUND_TYPE));
			this.dataTracker.set(INVENTORY, this.findItemInventory(Items.TNT).getCount());
		}

		super.readNbt(nbt);
	}

	@Override
	protected ActionResult interactMob(PlayerEntity player, Hand hand) {
		ItemStack item = player.getStackInHand(hand);
		ItemStack itemCopy = item.copy();
		if(itemCopy.getItem() instanceof DyeItem) {
			dyeColor = ((DyeItem) itemCopy.getItem()).getColor();
			this.dataTracker.set(COLOR, (dyeColor.getMapColor().color));
			if(!player.isCreative())
				item.decrement(1);
		}

		if(itemCopy.getItem() instanceof TinySwordItem) {
			this.dropStack(this.getEquippedStack(EquipmentSlot.MAINHAND));
			//this.equipStack(EquipmentSlot.MAINHAND, Items.AIR.getDefaultStack());
			this.equipLootStack(EquipmentSlot.MAINHAND, itemCopy);
			if(!player.isCreative())
				item.decrement(1);
		}

		if(itemCopy.getItem() instanceof TinyBowItem) {
			itemCopy.setCount(1);
			this.dropStack(this.getEquippedStack(EquipmentSlot.MAINHAND));
			//this.equipStack(EquipmentSlot.MAINHAND, Items.AIR.getDefaultStack());
			this.equipLootStack(EquipmentSlot.MAINHAND, itemCopy);
			if(!player.isCreative())
				item.decrement(1);
		}

		if(itemCopy.getItem() == Items.TNT) {
			if (this.findItemInventory(Items.TNT).getCount() < 3) {
				if(!this.getWorld().isClient) {
					itemCopy.setCount(1);
					if (terracottaKnightInventory.canInsert(itemCopy)) {
						terracottaKnightInventory.addStack(itemCopy);
						this.setTntCount(this.findItemInventory(Items.TNT).getCount());
						if (!player.isCreative())
							item.decrement(1);
					}
				}
				else {
					player.swingHand(hand);
				}
			}
		}

		if(itemCopy.getItem() instanceof TinyArmorItem) {
			ItemStack itemStack = this.getEquippedStack(((TinyArmorItem) itemCopy.getItem()).getPreferredSlot());
			this.dropStack(itemStack);
			this.equipLootStack(((TinyArmorItem) itemCopy.getItem()).getPreferredSlot(), itemCopy);
			if(!player.isCreative())
				item.decrement(1);
		}

		//System.out.println(terracottaKnightInventory.stacks);

		this.swingHand(Hand.MAIN_HAND);
		return super.interactMob(player, hand);
	}

	public void rangedAttack(LivingEntity target, float pullProgress) {
			ItemStack itemStack = this.getArrowType(this.getStackInHand(ProjectileUtil.getHandPossiblyHolding(this, Items.BOW)));
			PersistentProjectileEntity persistentProjectileEntity = TinyArrowEntity.createArrowProjectile(this,itemStack,pullProgress);

			double d = target.getX() - this.getX();
			double e = target.getBodyY(0.1) - persistentProjectileEntity.getY();
			double f = target.getZ() - this.getZ();
			double g = Math.sqrt(d * d + f * f);
			persistentProjectileEntity.setVelocity(d, e + g * 0.2F, f, 1.6F, 0);
			this.playSound(SoundEvents.ENTITY_SKELETON_SHOOT, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
			this.getWorld().spawnEntity(persistentProjectileEntity);
	}


}
