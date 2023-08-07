package io.github.Bubblie01.terracotta_knights.mixin;

import io.github.Bubblie01.terracotta_knights.items.TerracottaItemFlag;
import io.github.Bubblie01.terracotta_knights.items.TinyArmorItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MobEntity.class)
public class MobEntityMixin {

	@Inject(method = "prefersNewEquipment", at = @At("HEAD"), cancellable = true)
	private void terracotta$_equipModificationMethod(ItemStack newStack, ItemStack oldStack, CallbackInfoReturnable<Boolean> cir) {
		System.out.println("No Balls");
		if(newStack.getItem() instanceof TerracottaItemFlag) {
			System.out.println("Balls");
			cir.setReturnValue(false);
		}
	}
}
