package io.github.Bubblie01.terracotta_knights;

import io.github.Bubblie01.terracotta_knights.entities.TerracottaKnightEntity;
import io.github.Bubblie01.terracotta_knights.entities.TinyArrowEntity;
import io.github.Bubblie01.terracotta_knights.items.*;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.*;
import net.minecraft.recipe.SpecialRecipeSerializer;
import net.minecraft.registry.*;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import org.quiltmc.qsl.entity.api.QuiltEntityTypeBuilder;

public class TerracottaRegistry {

	//Item group
	//public static final RegistryKey<ItemGroup> TERRACOTTA_KNIGHT_ITEM_GROUP = RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier(Main.MOD_ID, "item"));
	public static final Identifier TERRACOTTA_KNIGHT_ITEM_GROUP = new Identifier(Main.MOD_ID, "terracotta_knights");
	//Items
	public static final TerracottaKnightItem TERRACOTTA_KNIGHT_ITEM = new TerracottaKnightItem(new Item.Settings());
	public static final ToolItem TINY_WOODEN_SWORD_ITEM = new TinySwordItem(ToolMaterials.WOOD,1, -2.4F, new Item.Settings());
	public static final ToolItem TINY_STONE_SWORD_ITEM = new TinySwordItem(ToolMaterials.STONE,1, -2.4F, new Item.Settings());
	public static final ToolItem TINY_IRON_SWORD_ITEM = new TinySwordItem(ToolMaterials.IRON,1, -2.4F, new Item.Settings());
	public static final ToolItem TINY_GOLD_SWORD_ITEM = new TinySwordItem(ToolMaterials.GOLD,1, -2.4F, new Item.Settings());
	public static final ToolItem TINY_DIAMOND_SWORD_ITEM = new TinySwordItem(ToolMaterials.DIAMOND,1, -2.4F, new Item.Settings());
	public static final ToolItem TINY_NETHERITE_SWORD_ITEM = new TinySwordItem(ToolMaterials.NETHERITE,1, -2.4F, new Item.Settings());
	public static final TinyBowItem TINY_BOW_ITEM = new TinyBowItem(new Item.Settings());
	public static final TinyArrowItem TINY_ARROW_ITEM = new TinyArrowItem(new Item.Settings());
	public static final SignalDeviceItem SIGNAL_DEVICE_ITEM = new SignalDeviceItem(new Item.Settings());
	public static final TinyPitchforkItem TINY_PITCHFORK_ITEM = new TinyPitchforkItem(new Item.Settings());

	public static final Item TINY_CHAINMAIL_HELMET = new TinyArmorItem(TinyArmorMaterial.TINY_CHAINMAIL, ArmorItem.ArmorSlot.HELMET, new Item.Settings());
	public static final Item TINY_CHAINMAIL_CHESTPLATE = new TinyArmorItem(TinyArmorMaterial.TINY_CHAINMAIL, ArmorItem.ArmorSlot.CHESTPLATE, new Item.Settings());
	public static final Item TINY_CHAINMAIL_LEGGINGS = new TinyArmorItem(TinyArmorMaterial.TINY_CHAINMAIL, ArmorItem.ArmorSlot.LEGGINGS, new Item.Settings());
	public static final Item TINY_CHAINMAIL_BOOTS = new TinyArmorItem(TinyArmorMaterial.TINY_CHAINMAIL, ArmorItem.ArmorSlot.BOOTS, new Item.Settings());

	public static final Item TINY_IRON_HELMET = new TinyArmorItem(TinyArmorMaterial.TINY_IRON, ArmorItem.ArmorSlot.HELMET, new Item.Settings());
	public static final Item TINY_IRON_CHESTPLATE = new TinyArmorItem(TinyArmorMaterial.TINY_IRON, ArmorItem.ArmorSlot.CHESTPLATE, new Item.Settings());
	public static final Item TINY_IRON_LEGGINGS = new TinyArmorItem(TinyArmorMaterial.TINY_IRON, ArmorItem.ArmorSlot.LEGGINGS, new Item.Settings());
	public static final Item TINY_IRON_BOOTS = new TinyArmorItem(TinyArmorMaterial.TINY_IRON, ArmorItem.ArmorSlot.BOOTS, new Item.Settings());


	public static final Item TINY_GOLD_HELMET = new TinyArmorItem(TinyArmorMaterial.TINY_GOLD, ArmorItem.ArmorSlot.HELMET, new Item.Settings());
	public static final Item TINY_GOLD_CHESTPLATE = new TinyArmorItem(TinyArmorMaterial.TINY_GOLD, ArmorItem.ArmorSlot.CHESTPLATE, new Item.Settings());
	public static final Item TINY_GOLD_LEGGINGS = new TinyArmorItem(TinyArmorMaterial.TINY_GOLD, ArmorItem.ArmorSlot.LEGGINGS, new Item.Settings());
	public static final Item TINY_GOLD_BOOTS = new TinyArmorItem(TinyArmorMaterial.TINY_GOLD, ArmorItem.ArmorSlot.BOOTS, new Item.Settings());

	public static final Item TINY_DIAMOND_HELMET = new TinyArmorItem(TinyArmorMaterial.TINY_DIAMOND, ArmorItem.ArmorSlot.HELMET, new Item.Settings());
	public static final Item TINY_DIAMOND_CHESTPLATE = new TinyArmorItem(TinyArmorMaterial.TINY_DIAMOND, ArmorItem.ArmorSlot.CHESTPLATE, new Item.Settings());
	public static final Item TINY_DIAMOND_LEGGINGS = new TinyArmorItem(TinyArmorMaterial.TINY_DIAMOND, ArmorItem.ArmorSlot.LEGGINGS, new Item.Settings());
	public static final Item TINY_DIAMOND_BOOTS = new TinyArmorItem(TinyArmorMaterial.TINY_DIAMOND, ArmorItem.ArmorSlot.BOOTS, new Item.Settings().maxDamage(0));

	public static final Item TINY_NETHERITE_HELMET = new TinyArmorItem(TinyArmorMaterial.TINY_NETHERITE, ArmorItem.ArmorSlot.HELMET, new Item.Settings());
	public static final Item TINY_NETHERITE_CHESTPLATE = new TinyArmorItem(TinyArmorMaterial.TINY_NETHERITE, ArmorItem.ArmorSlot.CHESTPLATE, new Item.Settings());
	public static final Item TINY_NETHERITE_LEGGINGS = new TinyArmorItem(TinyArmorMaterial.TINY_NETHERITE, ArmorItem.ArmorSlot.LEGGINGS, new Item.Settings());
	public static final Item TINY_NETHERITE_BOOTS = new TinyArmorItem(TinyArmorMaterial.TINY_NETHERITE, ArmorItem.ArmorSlot.BOOTS, new Item.Settings());
	//recipies
	public static SpecialRecipeSerializer<TerracottaKnightRecipe> TERRACOTTA_KNIGHT_RECIPE;

	public static final EntityType<TinyArrowEntity> TINY_ARROW = Registry.register(Registries.ENTITY_TYPE, new Identifier(Main.MOD_ID, "tiny_arrow_entity"), QuiltEntityTypeBuilder.<TinyArrowEntity>create(SpawnGroup.MISC, TinyArrowEntity::new).build());
	public static final EntityType<TerracottaKnightEntity> TERRACOTTA_KNIGHT = Registry.register(Registries.ENTITY_TYPE, new Identifier(Main.MOD_ID, "terracotta_knight_entity"), QuiltEntityTypeBuilder.create(SpawnGroup.MONSTER, TerracottaKnightEntity::new).setDimensions(EntityDimensions.changing(0.5f,1.2f)).build());
	//sounds
	public static final Identifier SIGNAL_SOUND_ID = new Identifier(Main.MOD_ID, "signal_sound");
	public static final SoundEvent SIGNAL_SOUND_EVENT = SoundEvent.createVariableRangeEvent(SIGNAL_SOUND_ID);
	public static final Identifier SOUL_WHISPER_SOUND_ID = new Identifier(Main.MOD_ID, "soul_whisper");
	public static final SoundEvent SOUL_WHISPER_SOUND_EVENT = SoundEvent.createFixedRangeEvent(SOUL_WHISPER_SOUND_ID,0.5f);

	//model layer

	public static void registerItems() {
		Registry.register(Registries.ITEM_GROUP, TERRACOTTA_KNIGHT_ITEM_GROUP, FabricItemGroup.builder().name(Text.translatable(Util.createTranslationKey("itemGroup", new Identifier(Main.MOD_ID, "terracotta_knights"))))
				.icon(() -> new ItemStack(TERRACOTTA_KNIGHT_ITEM))
				.entries((display, entries) -> {
					entries.addItem(TERRACOTTA_KNIGHT_ITEM);
					entries.addItem(SIGNAL_DEVICE_ITEM);
					entries.addItem(TINY_BOW_ITEM);
					entries.addItem(TINY_WOODEN_SWORD_ITEM);
					entries.addItem(TINY_STONE_SWORD_ITEM);
					entries.addItem(TINY_IRON_SWORD_ITEM);
					entries.addItem(TINY_GOLD_SWORD_ITEM);
					entries.addItem(TINY_DIAMOND_SWORD_ITEM);
					entries.addItem(TINY_NETHERITE_SWORD_ITEM);
					entries.addItem(TINY_CHAINMAIL_BOOTS);
					entries.addItem(TINY_CHAINMAIL_LEGGINGS);
					entries.addItem(TINY_CHAINMAIL_CHESTPLATE);
					entries.addItem(TINY_CHAINMAIL_HELMET);
					entries.addItem(TINY_IRON_BOOTS);
					entries.addItem(TINY_IRON_LEGGINGS);
					entries.addItem(TINY_IRON_CHESTPLATE);
					entries.addItem(TINY_IRON_HELMET);
					entries.addItem(TINY_GOLD_BOOTS);
					entries.addItem(TINY_GOLD_LEGGINGS);
					entries.addItem(TINY_GOLD_CHESTPLATE);
					entries.addItem(TINY_GOLD_HELMET);
					entries.addItem(TINY_DIAMOND_BOOTS);
					entries.addItem(TINY_DIAMOND_LEGGINGS);
					entries.addItem(TINY_DIAMOND_CHESTPLATE);
					entries.addItem(TINY_DIAMOND_HELMET);
					entries.addItem(TINY_NETHERITE_BOOTS);
					entries.addItem(TINY_NETHERITE_LEGGINGS);
					entries.addItem(TINY_NETHERITE_CHESTPLATE);
					entries.addItem(TINY_NETHERITE_HELMET);
				}).build());


		Registry.register(Registries.ITEM, new Identifier(Main.MOD_ID, "terracotta_knight"), TERRACOTTA_KNIGHT_ITEM);

		Registry.register(Registries.ITEM, new Identifier(Main.MOD_ID, "tiny_wooden_sword_item"), TINY_WOODEN_SWORD_ITEM);
		Registry.register(Registries.ITEM, new Identifier(Main.MOD_ID, "tiny_stone_sword_item"), TINY_STONE_SWORD_ITEM);
		Registry.register(Registries.ITEM, new Identifier(Main.MOD_ID, "tiny_iron_sword_item"), TINY_IRON_SWORD_ITEM);
		Registry.register(Registries.ITEM, new Identifier(Main.MOD_ID, "tiny_gold_sword_item"), TINY_GOLD_SWORD_ITEM);
		Registry.register(Registries.ITEM, new Identifier(Main.MOD_ID, "tiny_diamond_sword_item"), TINY_DIAMOND_SWORD_ITEM);
		Registry.register(Registries.ITEM, new Identifier(Main.MOD_ID, "tiny_netherite_sword_item"), TINY_NETHERITE_SWORD_ITEM);

		Registry.register(Registries.ITEM, new Identifier(Main.MOD_ID, "tiny_bow"), TINY_BOW_ITEM);
		Registry.register(Registries.ITEM, new Identifier(Main.MOD_ID, "signal_device_item"), SIGNAL_DEVICE_ITEM);
		Registry.register(Registries.ITEM, new Identifier(Main.MOD_ID, "tiny_arrow"), TINY_ARROW_ITEM);

		//Pitchforks
		Registry.register(Registries.ITEM, new Identifier(Main.MOD_ID, "tiny_diamond_pitchfork_item"), TINY_PITCHFORK_ITEM);

		//Chainmail Armor
		Registry.register(Registries.ITEM, new Identifier(Main.MOD_ID, "tiny_chainmail_helmet"), TINY_CHAINMAIL_HELMET);
		Registry.register(Registries.ITEM, new Identifier(Main.MOD_ID, "tiny_chainmail_chestplate"), TINY_CHAINMAIL_CHESTPLATE);
		Registry.register(Registries.ITEM, new Identifier(Main.MOD_ID, "tiny_chainmail_leggings"), TINY_CHAINMAIL_LEGGINGS);
		Registry.register(Registries.ITEM, new Identifier(Main.MOD_ID, "tiny_chainmail_boots"), TINY_CHAINMAIL_BOOTS);
		//Iron Armor
		Registry.register(Registries.ITEM, new Identifier(Main.MOD_ID, "tiny_iron_helmet"), TINY_IRON_HELMET);
		Registry.register(Registries.ITEM, new Identifier(Main.MOD_ID, "tiny_iron_chestplate"), TINY_IRON_CHESTPLATE);
		Registry.register(Registries.ITEM, new Identifier(Main.MOD_ID, "tiny_iron_leggings"), TINY_IRON_LEGGINGS);
		Registry.register(Registries.ITEM, new Identifier(Main.MOD_ID, "tiny_iron_boots"), TINY_IRON_BOOTS);
		//Gold Armor
		Registry.register(Registries.ITEM, new Identifier(Main.MOD_ID, "tiny_gold_helmet"), TINY_GOLD_HELMET);
		Registry.register(Registries.ITEM, new Identifier(Main.MOD_ID, "tiny_gold_chestplate"), TINY_GOLD_CHESTPLATE);
		Registry.register(Registries.ITEM, new Identifier(Main.MOD_ID, "tiny_gold_leggings"), TINY_GOLD_LEGGINGS);
		Registry.register(Registries.ITEM, new Identifier(Main.MOD_ID, "tiny_gold_boots"), TINY_GOLD_BOOTS);
		//Diamond Armor
		Registry.register(Registries.ITEM, new Identifier(Main.MOD_ID, "tiny_diamond_helmet"), TINY_DIAMOND_HELMET);
		Registry.register(Registries.ITEM, new Identifier(Main.MOD_ID, "tiny_diamond_chestplate"), TINY_DIAMOND_CHESTPLATE);
		Registry.register(Registries.ITEM, new Identifier(Main.MOD_ID, "tiny_diamond_leggings"), TINY_DIAMOND_LEGGINGS);
		Registry.register(Registries.ITEM, new Identifier(Main.MOD_ID, "tiny_diamond_boots"), TINY_DIAMOND_BOOTS);
		//Netherite Armor
		Registry.register(Registries.ITEM, new Identifier(Main.MOD_ID, "tiny_netherite_helmet"), TINY_NETHERITE_HELMET);
		Registry.register(Registries.ITEM, new Identifier(Main.MOD_ID, "tiny_netherite_chestplate"), TINY_NETHERITE_CHESTPLATE);
		Registry.register(Registries.ITEM, new Identifier(Main.MOD_ID, "tiny_netherite_leggings"), TINY_NETHERITE_LEGGINGS);
		Registry.register(Registries.ITEM, new Identifier(Main.MOD_ID, "tiny_netherite_boots"), TINY_NETHERITE_BOOTS);

	}

	public static void registerRecipies() {
		TERRACOTTA_KNIGHT_RECIPE = Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(Main.MOD_ID, "crafting_special_terracotta_knight_recipe") , new SpecialRecipeSerializer<>(TerracottaKnightRecipe::new));
	}

	public static void registerColors() {
		ColorProviderRegistry.ITEM.register((stack, tintIndex) -> ((DyeableItem)stack.getItem()).getColor(stack), TERRACOTTA_KNIGHT_ITEM);
	}

	public static void registerSounds() {
		Registry.register(Registries.SOUND_EVENT, SIGNAL_SOUND_ID, SIGNAL_SOUND_EVENT);
		Registry.register(Registries.SOUND_EVENT, SOUL_WHISPER_SOUND_ID, SOUL_WHISPER_SOUND_EVENT);
	}
}


