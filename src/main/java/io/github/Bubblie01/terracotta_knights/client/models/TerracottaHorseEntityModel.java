package io.github.Bubblie01.terracotta_knights.client.models;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.VertexConsumer;
import io.github.Bubblie01.terracotta_knights.entities.TerracottaHorseEntity;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.AnimalModel;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.client.render.entity.model.HorseEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.passive.HorseBaseEntity;
import net.minecraft.util.math.MathHelper;
import org.joml.Vector3f;

public class TerracottaHorseEntityModel<T extends TerracottaHorseEntity> extends AnimalModel<T> {
	private float r, g, b;
	protected final ModelPart body;
	protected final ModelPart head;
	private final ModelPart rightHindLeg;
	private final ModelPart leftHindLeg;
	private final ModelPart rightFrontLeg;
	private final ModelPart leftFrontLeg;
	private final ModelPart tail;

	public void setColors(float r, float g, float b) {
		this.r = r;
		this.g = g;
		this.b = b;
	}

	public TerracottaHorseEntityModel(ModelPart root) {
		this.body = root.getChild(EntityModelPartNames.BODY);
		this.head = root.getChild("head_parts");
		this.rightHindLeg = root.getChild(EntityModelPartNames.RIGHT_HIND_LEG);
		this.leftHindLeg = root.getChild(EntityModelPartNames.LEFT_HIND_LEG);
		this.rightFrontLeg = root.getChild(EntityModelPartNames.RIGHT_FRONT_LEG);
		this.leftFrontLeg = root.getChild(EntityModelPartNames.LEFT_FRONT_LEG);
		this.tail = this.body.getChild(EntityModelPartNames.TAIL);
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
		super.render(matrices, vertices, light, overlay, r, g, b, alpha);
	}

	@Override
	public Iterable<ModelPart> getHeadParts() {
		return ImmutableList.<ModelPart>of(this.head);
	}

	@Override
	protected Iterable<ModelPart> getBodyParts() {
		return ImmutableList.<ModelPart>of(
			this.body,
			this.rightHindLeg,
			this.leftHindLeg,
			this.rightFrontLeg,
			this.leftFrontLeg
		);
	}

	@Override
	public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
		this.body.pivotY = 11.0F;
		this.leftFrontLeg.pivotZ = -10.1f;
		this.rightFrontLeg.pivotZ = -10.1f;
	}

	@Override
	public void animateModel(T entity, float limbAngle, float limbDistance, float tickDelta) {
		super.animateModel(entity, limbAngle, limbDistance, tickDelta);
		float i = MathHelper.lerpAngleDegrees(tickDelta, entity.prevBodyYaw, entity.bodyYaw);
		float j = MathHelper.lerpAngleDegrees(tickDelta, entity.prevHeadYaw, entity.headYaw);
		float k = MathHelper.lerp(tickDelta, entity.prevPitch, entity.getPitch());
		float l = j - i;
		float m = k * (float) (Math.PI / 180.0);
		if (l > 20.0F) {
			l = 20.0F;
		}

		if (l < -20.0F) {
			l = -20.0F;
		}

		if (g > 0.2F) {
			m += MathHelper.cos(limbAngle * 0.8F) * 0.15F * limbDistance;
		}
		float p = 1.0F;
		boolean bl = entity.tailWagTicks != 0;
		float r = (float)entity.age + tickDelta;
		this.head.pivotY = 4.0F;
		this.head.pivotZ = -12.0F;
		this.body.pitch = 0.0F;
		this.head.pitch = (float) (Math.PI / 6) + m;
		this.head.yaw = l * (float) (Math.PI / 180.0);
		float s = entity.isTouchingWater() ? 0.2F : 1.0F;
		float t = MathHelper.cos(s * limbAngle * 0.6662F + (float) Math.PI);
		float u = t * 0.8F * limbDistance;
		float w = (float) (Math.PI / 12);
		float x = MathHelper.cos(r * 0.6F + (float) Math.PI);
		this.rightFrontLeg.pivotY = this.leftFrontLeg.pivotY;
		this.rightFrontLeg.pivotZ = this.leftFrontLeg.pivotZ;
		float y = ((float) u * p);
		float z = ((float) - u * p);
		this.leftHindLeg.pitch = - t * 0.5F * limbDistance * p;
		this.rightHindLeg.pitch =  t * 0.5F * limbDistance * p;
		this.leftFrontLeg.pitch = y;
		this.rightFrontLeg.pitch = z;
		this.tail.pitch = (float) (Math.PI / 6) + limbDistance * 0.75F;
		this.tail.pivotY = -5.0F + limbDistance;
		this.tail.pivotZ = 2.0F + limbDistance * 2.0F;
		if (bl) {
			this.tail.yaw = MathHelper.cos(r * 0.7F);
		} else {
			this.tail.yaw = 0.0F;
		}
	}
}
