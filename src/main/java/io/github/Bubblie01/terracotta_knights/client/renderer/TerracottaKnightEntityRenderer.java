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
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Axis;
import org.jetbrains.annotations.Nullable;

//Code Created by Bubblie01 Under MPL 2.0 License
public class TerracottaKnightEntityRenderer extends BipedEntityRenderer<TerracottaKnightEntity, TerracottaKnightEntityModel<TerracottaKnightEntity>> {

	private ItemRenderer itemRenderer;

	public TerracottaKnightEntityRenderer(EntityRendererFactory.Context context, EntityModelLayer innerArmorLayer, EntityModelLayer outerArmorLayer) {
		super(context, new TerracottaKnightEntityModel(context.getPart(EntityModelLayers.PLAYER), false), 0.3f);
		this.addFeature(new ArmorFeatureRenderer(this, new BipedEntityModel(context.getPart(innerArmorLayer)), new BipedEntityModel(context.getPart(outerArmorLayer)), context.getModelManager()));
		this.addFeature(new TNTHeadFeatureRenderer<>(this, context.getItemRenderer()));
		itemRenderer = context.getItemRenderer();
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
	public void render(TerracottaKnightEntity mobEntity, float f, float g, MatrixStack matrices, VertexConsumerProvider vertexConsumerProvider, int i) {
			int color = mobEntity.getDataTracker().get(TerracottaKnightEntity.COLOR);
			float RGBColors[] = new float[3];
			RGBColors[0] = (float) (color >> 16 & 0xFF) / 255.0F;
			RGBColors[1] = (float) (color >> 8 & 0xFF) / 255.0F;
			RGBColors[2] = (float) (color & 0xFF) / 255.0F;
			VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(this.getModel().getLayer(this.getTexture(mobEntity)));
			model.setColors(RGBColors[0], RGBColors[1], RGBColors[2]);
			super.render(mobEntity, f, g, matrices, vertexConsumerProvider, i);
		}



	@Override
	public Identifier getTexture(TerracottaKnightEntity mobEntity) {
		Identifier textureIdentifier = new Identifier(Main.MOD_ID, "textures/entities/terracotta_knight.png");
		if(mobEntity.hasCustomName()) {
			if (mobEntity.getCustomName().equals(Text.of("fusion")) || mobEntity.getCustomName().equals(Text.of("ennui")) || mobEntity.getCustomName().equals(Text.of("boba")) || mobEntity.getCustomName().equals(Text.of("maximum")) || mobEntity.getCustomName().equals(Text.of("harpsi")) || mobEntity.getCustomName().equals(Text.of("sonatiine")) || mobEntity.getCustomName().equals(Text.of("shard"))) {
				textureIdentifier = new Identifier(Main.MOD_ID, "textures/entities/" + mobEntity.getCustomName().getString() + ".png");
			} else {
				textureIdentifier = new Identifier(Main.MOD_ID, "textures/entities/terracotta_knight.png");
			}
		}
		return textureIdentifier;
	}
}
