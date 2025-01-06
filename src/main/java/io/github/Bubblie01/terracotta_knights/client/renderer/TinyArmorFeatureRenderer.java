package io.github.Bubblie01.terracotta_knights.client.renderer;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.VertexConsumer;
import io.github.Bubblie01.terracotta_knights.Main;
import io.github.Bubblie01.terracotta_knights.TinyArmorMaterial;
import io.github.Bubblie01.terracotta_knights.client.models.TerracottaKnightEntityModel;
import io.github.Bubblie01.terracotta_knights.entities.TerracottaKnightEntity;
import io.github.Bubblie01.terracotta_knights.items.TinyArmorItem;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.ModelWithArms;
import net.minecraft.client.render.model.BakedModelManager;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.*;
import net.minecraft.item.trim.ArmorTrimPermutation;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class TinyArmorFeatureRenderer <T extends TerracottaKnightEntity, M extends BipedEntityModel<T>, A extends BipedEntityModel<T>> extends FeatureRenderer<T, M> {
	//private static final Map<String, Identifier> ARMOR_TEXTURE_CACHE = Maps.newHashMap();
	private final A leggingsModel;
	private final A bodyModel;
	public TinyArmorFeatureRenderer(FeatureRendererContext<T, M> context, A leggingsModel, A bodyModel, BakedModelManager modelManager) {
		super(context);
        this.leggingsModel = leggingsModel;
        this.bodyModel = bodyModel;
    }

	@Override
	public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, T entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
		this.renderArmor(matrices, vertexConsumers, entity, EquipmentSlot.CHEST, light, this.getArmor(EquipmentSlot.CHEST));
		this.renderArmor(matrices, vertexConsumers, entity, EquipmentSlot.LEGS, light, this.getArmor(EquipmentSlot.LEGS));
		this.renderArmor(matrices, vertexConsumers, entity, EquipmentSlot.FEET, light, this.getArmor(EquipmentSlot.FEET));
		this.renderArmor(matrices, vertexConsumers, entity, EquipmentSlot.HEAD, light, this.getArmor(EquipmentSlot.HEAD));
	}

	private void renderArmor(MatrixStack matrices, VertexConsumerProvider vertexConsumers, T entity, EquipmentSlot armorSlot, int light, A model) {
		ItemStack itemStack = entity.getEquippedStack(armorSlot);
		Item bl = itemStack.getItem();
		if (bl instanceof TinyArmorItem armorItem) {
			if (armorItem.getPreferredSlot() == armorSlot) {
				this.setVisible(model, armorSlot);
				this.getContextModel().setAttributes((BipedEntityModel<T>) model);
				boolean blx = this.usesSecondLayer(armorSlot);
				this.renderArmorParts(matrices, vertexConsumers, light, armorItem, model, blx, 1.0F, 1.0F, 1.0F, null);
				if (itemStack.hasGlint()) {
					this.renderArmorGlint(matrices, vertexConsumers, light, model);
				}
			}
		}
	}

	protected void setVisible(A bipedModel, EquipmentSlot slot) {
		bipedModel.setVisible(false);
		switch(slot) {
			case HEAD:
				bipedModel.head.visible = true;
				bipedModel.hat.visible = true;
				break;
			case CHEST:
				bipedModel.body.visible = true;
				bipedModel.rightArm.visible = true;
				bipedModel.leftArm.visible = true;
				break;
			case LEGS:
				bipedModel.body.visible = true;
				bipedModel.rightLeg.visible = true;
				bipedModel.leftLeg.visible = true;
				break;
			case FEET:
				bipedModel.rightLeg.visible = true;
				bipedModel.leftLeg.visible = true;
		}
	}

	private void renderArmorParts(
		MatrixStack matrices,
		VertexConsumerProvider vertexConsumers,
		int light,
		TinyArmorItem item,
		A model,
		boolean usesSecondLayer,
		float red,
		float green,
		float blue,
		@Nullable String overlay
	) {
		VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getArmorCutoutNoCull(this.getArmorTexture(item, usesSecondLayer, overlay)));
		model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, red, green, blue, 1.0F);
	}

	private Identifier getArmorTexture(TinyArmorItem item, boolean legs, @Nullable String overlay) {
		String string = "textures/models/armor/" + item.getMaterial().getName() + "_layer_" + (legs ? 2 : 1) + (overlay == null ? "" : "_" + overlay) + ".png";
		return new Identifier(Main.MOD_ID, string);
	}

	private void renderArmorGlint(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, A model) {
		model.render(matrices, vertexConsumers.getBuffer(RenderLayer.getArmorEntityGlint()), light, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
	}

	private boolean usesSecondLayer(EquipmentSlot slot) {
		return slot == EquipmentSlot.LEGS;
	}

	private A getArmor(EquipmentSlot slot) {
		return (A)(this.usesSecondLayer(slot) ? this.leggingsModel : this.bodyModel);
	}
}
