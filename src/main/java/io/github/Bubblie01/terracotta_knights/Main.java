package io.github.Bubblie01.terracotta_knights;

import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;

public class Main implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod name as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final String MOD_ID = "terracotta_knights";

	@Override
	public void onInitialize(ModContainer mod) {
		TerracottaKnightEntity.registerClayKnightEntityAttributes();
		TerracottaRegistry.registerItems();
		TerracottaRegistry.registerRecipies();
		TerracottaRegistry.registerColors();
	}
}
