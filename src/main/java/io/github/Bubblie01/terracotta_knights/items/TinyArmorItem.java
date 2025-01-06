package io.github.Bubblie01.terracotta_knights.items;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import io.github.Bubblie01.terracotta_knights.TinyArmorMaterial;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.*;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Util;

import java.util.EnumMap;
import java.util.UUID;

//Code Created by Bubblie01 Under MPL 2.0 License
public class TinyArmorItem extends Item implements TerracottaItemFlag {


	private static final EnumMap<ArmorItem.ArmorSlot, UUID> SLOT_UUID_MODIFIERS = Util.make(new EnumMap(ArmorItem.ArmorSlot.class), map -> {
		map.put(ArmorItem.ArmorSlot.BOOTS, UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B"));
		map.put(ArmorItem.ArmorSlot.LEGGINGS, UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D"));
		map.put(ArmorItem.ArmorSlot.CHESTPLATE, UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E"));
		map.put(ArmorItem.ArmorSlot.HELMET, UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150"));
	});


	/*
	public static final DispenserBehavior DISPENSER_BEHAVIOR = new ItemDispenserBehavior() {
		@Override
		protected ItemStack dispenseSilently(BlockPointer pointer, ItemStack stack) {
			return TinyArmorItem.dispenseArmor(pointer, stack) ? stack : super.dispenseSilently(pointer, stack);
		}
	};

	 */
	protected final ArmorItem.ArmorSlot tinyArmorSlot;
	private final int protection;
	private final float toughness;
	protected final float knockbackResistance;
	protected final TinyArmorMaterial type;
	private final Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;

	/*
	public static boolean dispenseArmor(BlockPointer pointer, ItemStack armor) {
		BlockPos blockPos = pointer.getPos().offset(pointer.getBlockState().get(DispenserBlock.FACING));
		List<LivingEntity> list = pointer.getWorld()
			.getEntitiesByClass(LivingEntity.class, new Box(blockPos), EntityPredicates.EXCEPT_SPECTATOR.and(new EntityPredicates.Equippable(armor)));
		if (list.isEmpty()) {
			return false;
		} else {
			LivingEntity livingEntity = (LivingEntity)list.get(0);
			EquipmentSlot equipmentSlot = MobEntity.getPreferredEquipmentSlot(armor);
			ItemStack itemStack = armor.split(1);
			livingEntity.equipStack(equipmentSlot, itemStack);
			if (livingEntity instanceof MobEntity) {
				((MobEntity)livingEntity).setEquipmentDropChance(equipmentSlot, 2.0F);
				((MobEntity)livingEntity).setPersistent();
			}

			return true;
		}
	}

	 */

	public TinyArmorItem(TinyArmorMaterial material, ArmorItem.ArmorSlot slot, Item.Settings settings) {
        super(settings.maxCount(64));
        this.type = material;
		this.tinyArmorSlot = slot;
		this.protection = material.getProtection(slot);
		this.toughness = material.getToughness();
		this.knockbackResistance = material.getKnockbackResistance();
		//DispenserBlock.registerBehavior(this, DISPENSER_BEHAVIOR);

		ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
		UUID uUID = (UUID)SLOT_UUID_MODIFIERS.get(slot);
		builder.put(
			EntityAttributes.GENERIC_ARMOR, new EntityAttributeModifier(uUID, "Armor modifier", (double)this.protection, EntityAttributeModifier.Operation.ADDITION)
		);
		builder.put(
			EntityAttributes.GENERIC_ARMOR_TOUGHNESS,
			new EntityAttributeModifier(uUID, "Armor toughness", (double)this.toughness, EntityAttributeModifier.Operation.ADDITION)
		);
		if (material == TinyArmorMaterial.TINY_NETHERITE) {
			builder.put(
				EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE,
				new EntityAttributeModifier(uUID, "Armor knockback resistance", (double)this.knockbackResistance, EntityAttributeModifier.Operation.ADDITION)
			);
		}

		this.attributeModifiers = builder.build();


	}

	public ArmorItem.ArmorSlot getArmorSlot() {
		return this.tinyArmorSlot;
	}

	public int getEnchantability() {
		return this.type.getEnchantability();
	}

	public TinyArmorMaterial getMaterial() {
		return this.type;
	}


	public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
		return slot == this.tinyArmorSlot.getEquipmentSlot() ? this.attributeModifiers : super.getAttributeModifiers(slot);
	}

	public int getProtection() {
		return this.protection;
	}

	public float getToughness() {
		return this.toughness;
	}

	public EquipmentSlot getPreferredSlot() {
		return this.tinyArmorSlot.getEquipmentSlot();
	}

	public SoundEvent getEquipSound() {
		return this.getMaterial().getEquipSound();
	}

	/*
	public static enum ArmorSlot {
		HELMET(EquipmentSlot.HEAD, "helmet"),
		CHESTPLATE(EquipmentSlot.CHEST, "chestplate"),
		LEGGINGS(EquipmentSlot.LEGS, "leggings"),
		BOOTS(EquipmentSlot.FEET, "boots");

		private final EquipmentSlot equipmentSlot;
		private final String translationKey;

		private ArmorSlot(EquipmentSlot equipmentSlot, String translationKey) {
			this.equipmentSlot = equipmentSlot;
			this.translationKey = translationKey;
		}

		public EquipmentSlot getEquipmentSlot() {
			return this.equipmentSlot;
		}

		public String getTranslationKey() {
			return this.translationKey;
		}
	}

	 */
}
