package io.github.Bubblie01.terracotta_knights;

import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;

public class ClientInitializer implements ClientModInitializer {
	@Override
	public void onInitializeClient(ModContainer mod) {
		EntityRendererRegistry.register(TerracottaKnightEntity.CLAY_KNIGHT, ((context -> {
			return new TerracottaKnightEntityRenderer(context);
		})));
	}
}
