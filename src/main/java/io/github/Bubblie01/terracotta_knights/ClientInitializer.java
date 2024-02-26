package io.github.Bubblie01.terracotta_knights;

import io.github.Bubblie01.terracotta_knights.client.renderer.TerracottaHorseEntityRenderer;
import io.github.Bubblie01.terracotta_knights.client.renderer.TerracottaKnightEntityRenderer;
import io.github.Bubblie01.terracotta_knights.client.renderer.TinyArrowRenderer;
import io.github.Bubblie01.terracotta_knights.client.models.TinyPitchforkEntityModel;
import io.github.Bubblie01.terracotta_knights.client.renderer.TinyPitchforkEntityRenderer;
import io.github.Bubblie01.terracotta_knights.entities.TerracottaKnightEntity;
import io.github.Bubblie01.terracotta_knights.entities.TinyArrowEntity;
import io.github.Bubblie01.terracotta_knights.entities.TinyPitchforkEntity;
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.util.Identifier;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;


public class ClientInitializer implements ClientModInitializer {

	@Override
	public void onInitializeClient(ModContainer mod) {

		EntityRendererRegistry.register(TerracottaRegistry.TERRACOTTA_KNIGHT, ((context -> {
			return new TerracottaKnightEntityRenderer(context, EntityModelLayers.PLAYER_INNER_ARMOR, EntityModelLayers.PLAYER_OUTER_ARMOR);
		})));

		EntityRendererRegistry.register(TerracottaRegistry.TERRACOTTA_HORSE, ((context -> {
			return new TerracottaHorseEntityRenderer(context);
		})));

		ModelPredicateProviderRegistry.register(TerracottaRegistry.TINY_BOW_ITEM, new Identifier("pulling"),(stack, world, entity, seed) -> entity != null && entity.isUsingItem() && entity.getActiveItem() == stack ? 1.0F : 0.0F);

		ModelPredicateProviderRegistry.register(TerracottaRegistry.TINY_BOW_ITEM, new Identifier("pull"), (stack, world, entity, seed) -> {
			if (entity == null) {
				return 0.0F;
			} else {
				return entity.getActiveItem() != stack ? 0.0F : (float)(stack.getMaxUseTime() - entity.getItemUseTimeLeft()) / 20.0F;
			}
		});

		EntityRendererRegistry.register(TerracottaRegistry.TINY_ARROW, ((context -> {
			return new TinyArrowRenderer(context);
		})));

		EntityRendererRegistry.register(TinyPitchforkEntity.TINY_PITCHFORK, ((context -> {
			return new TinyPitchforkEntityRenderer(context);
		})));

		EntityModelLayerRegistry.registerModelLayer(TinyPitchforkEntityModel.PITCHFORK, TinyPitchforkEntityModel::getTexturedModelData);

		ModelLoadingRegistry.INSTANCE.registerModelProvider((manager, out) -> {
			out.accept(new ModelIdentifier(Main.MOD_ID, "tiny_diamond_pitchfork_item_model", "inventory"));
		});

		TerracottaRegistry.registerColors();



	}
}
