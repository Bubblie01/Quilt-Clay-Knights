package io.github.Bubblie01.terracotta_knights;

import com.mojang.blaze3d.vertex.VertexConsumer;
import io.github.Bubblie01.terracotta_knights.entities.TerracottaKnightEntity;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.CrossbowPosing;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.util.Arm;

public class TerracottaKnightEntityModel<T extends PathAwareEntity> extends PlayerEntityModel<TerracottaKnightEntity> {
	private float r, g, b;

	public TerracottaKnightEntityModel(ModelPart modelPart, boolean bl) {
		super(modelPart, bl);
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

	@Override
	public void setAngles(TerracottaKnightEntity knight, float f, float g, float h, float i, float j) {
		super.setAngles(knight, f, g, h, i, j);
		if(knight.isAttacking()) {
			if (knight.getMainHandStack().isEmpty()) {
				CrossbowPosing.swingArm(rightArm,h, 1.0f);
			}
		}
	}

	@Override
	public void animateModel(TerracottaKnightEntity knight, float f, float g, float h) {
		this.rightArmPose = BipedEntityModel.ArmPose.EMPTY;
		this.leftArmPose = BipedEntityModel.ArmPose.EMPTY;
		if (knight.getMainHandStack().isOf(TerracottaRegistry.TINY_BOW_ITEM)) {
			if (knight.getMainArm() == Arm.RIGHT) {
				this.rightArmPose = BipedEntityModel.ArmPose.BOW_AND_ARROW;
			} else {
				this.leftArmPose = BipedEntityModel.ArmPose.BOW_AND_ARROW;
			}
		}

		super.animateModel(knight, f, g, h);

	}
	@Override
	public void setArmAngle(Arm arm, MatrixStack matrices) {
		ModelPart modelPart = this.getArm(arm);
		modelPart.rotate(matrices);
	}
}
