package io.github.Bubblie01.terracotta_knights;

import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.item.*;
import net.minecraft.recipe.ArmorDyeRecipe;
import net.minecraft.recipe.SpecialRecipeSerializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class TerracottaRegistry {

	public static final TerracottaKnightItem TERRACOTTA_KNIGHT_ITEM = new TerracottaKnightItem(new Item.Settings().group(ItemGroup.MISC));
	public static final ToolItem TINY_IRON_SWORD_ITEM = new TinySwordItem(ToolMaterials.IRON,3, -2.4F, new Item.Settings().group(ItemGroup.COMBAT));
	public static SpecialRecipeSerializer<TerracottaKnightRecipe> TERRACOTTA_KNIGHT_RECIPE;

	public static void registerItems() {
		Registry.register(Registry.ITEM, new Identifier(Main.MOD_ID, "terracotta_knight"), TERRACOTTA_KNIGHT_ITEM);
		Registry.register(Registry.ITEM, new Identifier(Main.MOD_ID, "tiny_iron_sword_item"), TINY_IRON_SWORD_ITEM);
	}

	public static void registerRecipies() {
		TERRACOTTA_KNIGHT_RECIPE = Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(Main.MOD_ID, "crafting_special_terracotta_knight_recipe") , new SpecialRecipeSerializer<>(TerracottaKnightRecipe::new));
	}

	public static void registerColors() {
		ColorProviderRegistry.ITEM.register((stack, tintIndex) -> ((DyeableItem)stack.getItem()).getColor(stack), TERRACOTTA_KNIGHT_ITEM);
	}
}


