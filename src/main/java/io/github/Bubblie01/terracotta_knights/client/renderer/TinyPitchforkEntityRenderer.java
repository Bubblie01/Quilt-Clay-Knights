package io.github.Bubblie01.terracotta_knights.client.renderer;

import com.mojang.blaze3d.vertex.VertexConsumer;
import io.github.Bubblie01.terracotta_knights.Main;
import io.github.Bubblie01.terracotta_knights.TerracottaRegistry;
import io.github.Bubblie01.terracotta_knights.client.models.TinyPitchforkEntityModel;
import io.github.Bubblie01.terracotta_knights.client.models.TinyPitchforkModel;
import io.github.Bubblie01.terracotta_knights.entities.TinyPitchforkEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

//Code Created by Bubblie01 Under MPL 2.0 License
public class TinyPitchforkEntityRenderer extends EntityRenderer<TinyPitchforkEntity> {

	private final TinyPitchforkEntityModel model;
	public TinyPitchforkEntityRenderer(EntityRendererFactory.Context context) {
		super(context);
		this.model = new TinyPitchforkEntityModel(context.getPart(TinyPitchforkEntityModel.PITCHFORK));
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
