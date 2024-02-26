package io.github.Bubblie01.terracotta_knights.client.models;

import com.mojang.blaze3d.vertex.VertexConsumer;
import io.github.Bubblie01.terracotta_knights.entities.TerracottaHorseEntity;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.HorseEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.passive.HorseBaseEntity;

public class TerracottaHorseEntityModel<T extends TerracottaHorseEntity> extends HorseEntityModel<T> {
	private float r, g, b;

	public TerracottaHorseEntityModel(ModelPart root) {
		super(root);
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
