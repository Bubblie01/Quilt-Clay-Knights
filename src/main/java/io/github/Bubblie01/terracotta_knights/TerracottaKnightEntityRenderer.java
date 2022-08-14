package io.github.Bubblie01.terracotta_knights;

import net.minecraft.block.MapColor;
import net.minecraft.client.render.*;
import net.minecraft.client.render.entity.BipedEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.Quaternion;
import org.jetbrains.annotations.Nullable;

public class TerracottaKnightEntityRenderer extends BipedEntityRenderer<TerracottaKnightEntity, TerracottaKnightEntityModel<TerracottaKnightEntity>> {
	private Identifier textureIdentifier = new Identifier(Main.MOD_ID, "textures/entities/terracotta_knight.png");
	private String leocthChecker = "";
	public TerracottaKnightEntityRenderer(EntityRendererFactory.Context context) {
		super(context, new TerracottaKnightEntityModel(context.getPart(EntityModelLayers.PLAYER), false), 0.5f);
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
			if (mobEntity.getCustomName().equals(Text.of("leocth"))) {
				textureIdentifier = new Identifier(Main.MOD_ID, "textures/entities/leocth.png");
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
