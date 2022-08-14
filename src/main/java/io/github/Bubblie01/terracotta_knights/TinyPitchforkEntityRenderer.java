package io.github.Bubblie01.terracotta_knights;

import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.TridentEntityModel;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3f;

public class TinyPitchforkEntityRenderer extends EntityRenderer<TinyPitchforkEntity> {

	private final TinyPitchforkEntityModel model;
	public TinyPitchforkEntityRenderer(EntityRendererFactory.Context context) {
		super(context);
		this.model = new TinyPitchforkEntityModel(context.getPart(TerracottaRegistry.PITCHFORK));
	}

	@Override
	public void render(TinyPitchforkEntity pitchforkEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
		matrixStack.push();
		//matrixStack.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(MathHelper.lerp(g, pitchforkEntity.prevYaw, pitchforkEntity.getYaw()) - 90.0F));
		//matrixStack.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(MathHelper.lerp(g, pitchforkEntity.prevPitch, pitchforkEntity.getPitch()) + 90.0F));
		VertexConsumer vertexConsumer = ItemRenderer.getDirectItemGlintConsumer(
				vertexConsumerProvider, this.model.getLayer(this.getTexture(pitchforkEntity)), false, false);
		this.model.render(matrixStack,vertexConsumer , i, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
		matrixStack.pop();
		super.render(pitchforkEntity, f, g, matrixStack, vertexConsumerProvider, i);
	}

	@Override
	public Identifier getTexture(TinyPitchforkEntity entity) {
		return new Identifier(Main.MOD_ID, "textures/items/tiny_diamond_pitchfork_model_texture.png");
	}

}
