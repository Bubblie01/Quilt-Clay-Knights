package io.github.Bubblie01.terracotta_knights;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.quiltmc.loader.api.minecraft.ClientOnly;

public class TinyArmorMaterial implements ArmorMaterial {

	public static final TinyArmorMaterial TINY_IRON = new TinyArmorMaterial("tiny_iron", 15, new int[]{2, 5, 6, 2}, 9, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F, 0.0F, Ingredient.ofItems(Items.IRON_NUGGET));
	public static final TinyArmorMaterial TINY_GOLD = new TinyArmorMaterial("tiny_gold", 7, new int[]{1, 3, 5, 2}, 25, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 0.0F, 0.0F, Ingredient.ofItems(Items.GOLD_NUGGET));
	public static final TinyArmorMaterial TINY_DIAMOND = new TinyArmorMaterial("tiny_diamond", 33, new int[]{1, 3, 5, 2}, 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0.0F, 0.0F, Ingredient.ofItems(Items.DIAMOND));
	private static final int[] BASE_DURABILITY = new int[]{13, 15, 16, 11};
	private final String name;
	private final int[] protectionValues;
	private final int enchantability;
	private final SoundEvent equipSound;
	private final float toughness;
	private final float knockBackResistance;
	private final int durabilityMultiplier;
	private final Ingredient ingredient;

	private TinyArmorMaterial(String name, int durabilityMultiplier, int[] protectionValues, int enchantability, SoundEvent equipSound, float toughness, float knockbackResistance, Ingredient ingredient) {
		this.durabilityMultiplier = durabilityMultiplier;
		this.protectionValues = protectionValues;
		this.enchantability = enchantability;
		this.equipSound = equipSound;
		this.toughness = toughness;
		this.knockBackResistance = knockbackResistance;
		this.ingredient = ingredient;
		this.name = name;
	}

	@Override
	public int getDurability(EquipmentSlot slot) {
		return BASE_DURABILITY[slot.getEntitySlotId()] * this.durabilityMultiplier;
	}

	@Override
	public int getProtectionAmount(EquipmentSlot slot) {
		return this.protectionValues[(slot.getEntitySlotId())];
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

