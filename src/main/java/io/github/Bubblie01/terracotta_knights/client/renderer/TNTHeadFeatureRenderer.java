package io.github.Bubblie01.terracotta_knights.client.renderer;

import io.github.Bubblie01.terracotta_knights.entities.TerracottaKnightEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.ModelWithArms;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.Axis;

public class TNTHeadFeatureRenderer <T extends TerracottaKnightEntity, M extends EntityModel<T> & ModelWithArms> extends FeatureRenderer<T, M> {
	private ItemRenderer itemRenderer;
	public TNTHeadFeatureRenderer(FeatureRendererContext<T, M> context, ItemRenderer itemRenderer) {
		super(context);
		this.itemRenderer = itemRenderer;
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, T mobEntity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
			matrices.push();
			ItemStack itemStack = Items.TNT.getDefaultStack();
			itemStack.setCount(mobEntity.getTntCount());
				BakedModel model = this.itemRenderer.getHeldItemModel(itemStack, mobEntity.getWorld(), mobEntity, mobEntity.getId());
				//matrices.multiply(Axis.Y_POSITIVE.rotationDegrees(180.0F));
				matrices.multiply(Axis.X_POSITIVE.rotationDegrees(-180F));
				for (int j = 0; j < itemStack.getCount(); j++) {
					if(itemStack.getCount() == 1)
						matrices.translate(0, 1f, 0);
					else
						matrices.translate(0, (itemStack.getCount()) / 2, 0);
					this.itemRenderer.renderItem(itemStack, ModelTransformationMode.HEAD, false, matrices, vertexConsumers, light, OverlayTexture.DEFAULT_UV, model);
				}
			matrices.pop();
	}
}
