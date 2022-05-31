package io.github.Bubblie01.terracotta_knights;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.mob.PathAwareEntity;

public class TerracottaKnightEntityModel<C extends PathAwareEntity> extends BipedEntityModel<TerracottaKnightEntity> {
	private float r, g, b;
	public TerracottaKnightEntityModel(ModelPart modelPart) {
		super(modelPart);
	}
	public void setColors(float r, float g, float b) {
		this.r = r;
		this.g = g;
		this.b = b;
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
		super.render(matrices, vertices, light, overlay, r, g, b, alpha);
	}
}
