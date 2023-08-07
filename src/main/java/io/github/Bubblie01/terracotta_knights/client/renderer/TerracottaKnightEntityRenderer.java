package io.github.Bubblie01.terracotta_knights.client.renderer;

import com.mojang.blaze3d.vertex.VertexConsumer;
import io.github.Bubblie01.terracotta_knights.Main;
import io.github.Bubblie01.terracotta_knights.client.models.TerracottaKnightEntityModel;
import io.github.Bubblie01.terracotta_knights.entities.TerracottaKnightEntity;
import net.minecraft.client.render.*;
import net.minecraft.client.render.entity.BipedEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

public class TerracottaKnightEntityRenderer extends BipedEntityRenderer<TerracottaKnightEntity, TerracottaKnightEntityModel<TerracottaKnightEntity>> {
	private Identifier textureIdentifier = new Identifier(Main.MOD_ID, "textures/entities/terracotta_knight.png");
	private String leocthChecker = "";
	public TerracottaKnightEntityRenderer(EntityRendererFactory.Context context, EntityModelLayer innerArmorLayer, EntityModelLayer outerArmorLayer) {
		super(context, new TerracottaKnightEntityModel(context.getPart(EntityModelLayers.PLAYER), false), 0.3f);
		this.addFeature(new ArmorFeatureRenderer(this, new BipedEntityModel(context.getPart(innerArmorLayer)), new BipedEntityModel(context.getPart(outerArmorLayer)), context.getModelManager()));
	}


	@Override
	protected void scale(TerracottaKnightEntity entity, MatrixStack matrices, float amount) {
		matrices.scale(0.5f,0.5f,0.5f);
		super.scale(entity, matrices, amount);
	}

	@Nullable
	@Override
	protected RenderLayer getRenderLayer(TerracottaKnightEntity entity, boolean showBody, boolean translucent, boolean showOutline) {
		return super.getRenderLayer(entity, showBody, translucent, showOutline);
	}

	@Override
	public void render(TerracottaKnightEntity mobEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
		if(mobEntity.hasCustomName()) {
			if (mobEntity.getCustomName().equals(Text.of("fusion"))) {
				textureIdentifier = new Identifier(Main.MOD_ID, "textures/entities/fusion_flux.png");
			}
		}
		else {
			textureIdentifier =	new Identifier(Main.MOD_ID, "textures/entities/terracotta_knight.png");
		}

		int color = mobEntity.getDataTracker().get(TerracottaKnightEntity.COLOR);
		float RGBColors[] = new float[3];
		RGBColors[0] = (float)(color >> 16 & 0xFF) / 255.0F;
		RGBColors[1] = (float)(color >> 8 & 0xFF) / 255.0F;
		RGBColors[2] = (float)(color & 0xFF) / 255.0F;

		VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(this.getModel().getLayer(this.getTexture(mobEntity)));
		model.setColors(RGBColors[0],RGBColors[1], RGBColors[2]);
		leocthChecker = " ";
		super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);

	}



	@Override
	public Identifier getTexture(TerracottaKnightEntity mobEntity) {
		return textureIdentifier;
	}
}
