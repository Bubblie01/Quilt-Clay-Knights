package io.github.Bubblie01.terracotta_knights.client.renderer;

import com.mojang.blaze3d.vertex.VertexConsumer;
import io.github.Bubblie01.terracotta_knights.Main;
import io.github.Bubblie01.terracotta_knights.client.models.TerracottaHorseEntityModel;
import io.github.Bubblie01.terracotta_knights.entities.TerracottaHorseEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class TerracottaHorseEntityRenderer extends MobEntityRenderer<TerracottaHorseEntity, TerracottaHorseEntityModel<TerracottaHorseEntity>> {

	public TerracottaHorseEntityRenderer(EntityRendererFactory.Context context) {
		super(context, new TerracottaHorseEntityModel(context.getPart(EntityModelLayers.HORSE)), 0.4f);
	}

	@Override
	public void render(TerracottaHorseEntity mobEntity, float f, float g, MatrixStack matrices, VertexConsumerProvider vertexConsumerProvider, int i) {
		int color = mobEntity.getDataTracker().get(TerracottaHorseEntity.HCOLOR);
		float RGBColors[] = new float[3];
		RGBColors[0] = (float) (color >> 16 & 0xFF) / 255.0F;
		RGBColors[1] = (float) (color >> 8 & 0xFF) / 255.0F;
		RGBColors[2] = (float) (color & 0xFF) / 255.0F;
		//VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(this.getModel().getLayer(this.getTexture(mobEntity)));
		model.setColors(RGBColors[0], RGBColors[1], RGBColors[2]);
		super.render(mobEntity, f, g, matrices, vertexConsumerProvider, i);
	}

	@Override
	protected void scale(TerracottaHorseEntity entity, MatrixStack matrices, float amount) {
		matrices.scale(0.5f,0.5f,0.5f);
		super.scale(entity, matrices, amount);
	}

	@Override
	public Identifier getTexture(TerracottaHorseEntity entity) {
		return new Identifier(Main.MOD_ID, "textures/entities/terracotta_horse.png");
	}
}
