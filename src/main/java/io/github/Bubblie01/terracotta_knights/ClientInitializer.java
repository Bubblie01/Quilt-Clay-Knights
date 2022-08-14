package io.github.Bubblie01.terracotta_knights;

import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;

import static io.github.Bubblie01.terracotta_knights.TerracottaRegistry.PITCHFORK;

public class ClientInitializer implements ClientModInitializer {

	@Override
	public void onInitializeClient(ModContainer mod) {

		EntityRendererRegistry.register(TerracottaKnightEntity.TERRACOTTA_KNIGHT, ((context -> {
			return new TerracottaKnightEntityRenderer(context);
		})));
		ModelPredicateProviderRegistry.register(TerracottaRegistry.TINY_BOW_ITEM, new Identifier("pulling"),(stack, world, entity, seed) -> entity != null && entity.isUsingItem() && entity.getActiveItem() == stack ? 1.0F : 0.0F);

		ModelPredicateProviderRegistry.register(TerracottaRegistry.TINY_BOW_ITEM, new Identifier("pull"), (stack, world, entity, seed) -> {
			if (entity == null) {
				return 0.0F;
			} else {
				return entity.getActiveItem() != stack ? 0.0F : (float)(stack.getMaxUseTime() - entity.getItemUseTimeLeft()) / 20.0F;
			}
		});

		EntityRendererRegistry.register(TinyArrowEntity.TINY_ARROW, ((context -> {
			return new TinyArrowRenderer(context);
		})));

		EntityRendererRegistry.register(TinyPitchforkEntity.TINY_PITCHFORK, ((context -> {
			return new TinyPitchforkEntityRenderer(context);
		})));

		EntityModelLayerRegistry.registerModelLayer(PITCHFORK, TinyPitchforkEntityModel::getTexturedModelData);

		ModelLoadingRegistry.INSTANCE.registerModelProvider((manager, out) -> {
			out.accept(new ModelIdentifier("terracotta_knights:tiny_diamond_pitchfork_item_model#inventory"));
		});



	}
}
