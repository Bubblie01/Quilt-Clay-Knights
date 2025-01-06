package io.github.Bubblie01.terracotta_knights.mixin;

import com.llamalad7.mixinextras.injector.WrapWithCondition;
import com.llamalad7.mixinextras.sugar.Local;
import io.github.Bubblie01.terracotta_knights.entities.TerracottaKnightEntity;
import io.github.Bubblie01.terracotta_knights.items.TinyArmorItem;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.HeadFeatureRenderer;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HeadFeatureRenderer.class)
public class HeadFeatureMixin {
	@Inject(method = "render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/entity/LivingEntity;FFFFFF)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/feature/HeadFeatureRenderer;translate(Lnet/minecraft/client/util/math/MatrixStack;Z)V"), cancellable = true)
	private void stopTranslate(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, LivingEntity entity, float f, float g, float h, float j, float k, float l, CallbackInfo ci) {
		if(entity instanceof TerracottaKnightEntity)
			if(entity.getEquippedStack(EquipmentSlot.HEAD).getItem() instanceof TinyArmorItem) {
				matrixStack.pop();
				ci.cancel();
			}
	}

}
