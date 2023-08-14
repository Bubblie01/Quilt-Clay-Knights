package io.github.Bubblie01.terracotta_knights;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import org.jetbrains.annotations.NotNull;
import org.quiltmc.loader.api.minecraft.ClientOnly;

import java.util.EnumMap;

//Code Created by Bubblie01 Under MPL 2.0 License
public class TinyArmorMaterial implements ArmorMaterial {

	public static final TinyArmorMaterial TINY_CHAINMAIL = new TinyArmorMaterial("tiny_chainmail", 15, Util.make(new EnumMap(ArmorItem.ArmorSlot.class), map -> {
		map.put(ArmorItem.ArmorSlot.BOOTS, 1);
		map.put(ArmorItem.ArmorSlot.LEGGINGS, 4);
		map.put(ArmorItem.ArmorSlot.CHESTPLATE, 5);
		map.put(ArmorItem.ArmorSlot.HELMET, 2);
	}), 12, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 0.0F, 0.0F, Ingredient.ofItems(Items.IRON_NUGGET));
	public static final TinyArmorMaterial TINY_IRON = new TinyArmorMaterial("tiny_iron", 15, Util.make(new EnumMap(ArmorItem.ArmorSlot.class), map -> {
		map.put(ArmorItem.ArmorSlot.BOOTS, 2);
		map.put(ArmorItem.ArmorSlot.LEGGINGS, 5);
		map.put(ArmorItem.ArmorSlot.CHESTPLATE, 6);
		map.put(ArmorItem.ArmorSlot.HELMET, 2);
	}), 9, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F, 0.0F, Ingredient.ofItems(Items.IRON_NUGGET));
	public static final TinyArmorMaterial TINY_GOLD = new TinyArmorMaterial("tiny_gold", 7, Util.make(new EnumMap(ArmorItem.ArmorSlot.class), map -> {
		map.put(ArmorItem.ArmorSlot.BOOTS, 1);
		map.put(ArmorItem.ArmorSlot.LEGGINGS, 3);
		map.put(ArmorItem.ArmorSlot.CHESTPLATE, 5);
		map.put(ArmorItem.ArmorSlot.HELMET, 2);
	}), 25, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 0.0F, 0.0F, Ingredient.ofItems(Items.GOLD_NUGGET));
	public static final TinyArmorMaterial TINY_DIAMOND = new TinyArmorMaterial("tiny_diamond", 33, Util.make(new EnumMap(ArmorItem.ArmorSlot.class), map -> {
		map.put(ArmorItem.ArmorSlot.BOOTS, 3);
		map.put(ArmorItem.ArmorSlot.LEGGINGS, 6);
		map.put(ArmorItem.ArmorSlot.CHESTPLATE, 8);
		map.put(ArmorItem.ArmorSlot.HELMET, 3);
	}), 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0.0F, 0.0F, Ingredient.ofItems(Items.DIAMOND));

	public static final TinyArmorMaterial TINY_NETHERITE = new TinyArmorMaterial("tiny_netherite", 37,  Util.make(new EnumMap(ArmorItem.ArmorSlot.class), map -> {
		map.put(ArmorItem.ArmorSlot.BOOTS, 3);
		map.put(ArmorItem.ArmorSlot.LEGGINGS, 6);
		map.put(ArmorItem.ArmorSlot.CHESTPLATE, 8);
		map.put(ArmorItem.ArmorSlot.HELMET, 3);
	}), 15, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 3.0F, 0.1F, Ingredient.ofItems(Items.NETHERITE_INGOT));


	private static final EnumMap<ArmorItem.ArmorSlot, Integer> BASE_DURABILITY_VALUES = Util.make(new EnumMap(ArmorItem.ArmorSlot.class), map -> {
		map.put(ArmorItem.ArmorSlot.BOOTS, 13);
		map.put(ArmorItem.ArmorSlot.LEGGINGS, 15);
		map.put(ArmorItem.ArmorSlot.CHESTPLATE, 16);
		map.put(ArmorItem.ArmorSlot.HELMET, 11);
	});
	private final String name;
	private final EnumMap<ArmorItem.ArmorSlot, Integer> slotProtections;
	private final int enchantability;
	private final SoundEvent equipSound;
	private final float toughness;
	private final float knockBackResistance;
	private final int durabilityMultiplier;
	private final Ingredient ingredient;

	private TinyArmorMaterial(String name, int durabilityMultiplier, EnumMap<ArmorItem.ArmorSlot, Integer> slotProtections, int enchantability, SoundEvent equipSound, float toughness, float knockbackResistance, Ingredient ingredient) {
		this.durabilityMultiplier = durabilityMultiplier;
		this.slotProtections = slotProtections;
		this.enchantability = enchantability;
		this.equipSound = equipSound;
		this.toughness = toughness;
		this.knockBackResistance = knockbackResistance;
		this.ingredient = ingredient;
		this.name = name;
	}

	@Override
	public int getDurability(ArmorItem.ArmorSlot slot) {
		return BASE_DURABILITY_VALUES.get(slot) * this.durabilityMultiplier;
	}

	@Override
	public int getProtection(ArmorItem.ArmorSlot slot) {
		return this.slotProtections.get(slot);
	}

	@Override
	public int getEnchantability() {
		return this.enchantability;
	}

	@Override
	public SoundEvent getEquipSound() {
		return this.equipSound;
	}

	@Override
	public Ingredient getRepairIngredient() {
		return ingredient;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public float getToughness() {
		return this.toughness;
	}

	@Override
	public float getKnockbackResistance() {
		return this.knockBackResistance;
	}

	@Override
	public @ClientOnly @NotNull Identifier getTexture() {
		return new Identifier(Main.MOD_ID, "textures/models/armor/" + this.name);
	}
}

