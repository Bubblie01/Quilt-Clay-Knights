package io.github.Bubblie01.terracotta_knights.client.renderer;

import io.github.Bubblie01.terracotta_knights.entities.TinyArrowEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class TinyArrowRenderer extends ProjectileEntityRenderer<TinyArrowEntity> {

	public static final Identifier TEXTURE = new Identifier("textures/entity/projectiles/arrow.png");

	public TinyArrowRenderer(EntityRendererFactory.Context context) {
		super(context);
	}

	@Override
	public void render(TinyArrowEntity persistentProjectileEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
		matrixStack.scale(0.4f,0.4f,0.4f);
		super.render(persistentProjectileEntity, f, g, matrixStack, vertexConsumerProvider, i);
	}

	@Override
	public Identifier getTexture(TinyArrowEntity entity) {
		return TEXTURE;
	}
}
