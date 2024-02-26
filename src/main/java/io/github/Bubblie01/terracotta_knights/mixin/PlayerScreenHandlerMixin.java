package io.github.Bubblie01.terracotta_knights.mixin;

import com.llamalad7.mixinextras.injector.WrapWithCondition;
import io.github.Bubblie01.terracotta_knights.items.TinyArmorItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.PlayerScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PlayerScreenHandler.class)
public class PlayerScreenHandlerMixin {
	@WrapWithCondition(method = "Lnet/minecraft/screen/PlayerScreenHandler;onEquipItem(Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/entity/EquipmentSlot;Lnet/minecraft/item/ItemStack;Lnet/minecraft/item/ItemStack;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/PlayerScreenHandler;onEquipItem(Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/entity/EquipmentSlot;Lnet/minecraft/item/ItemStack;Lnet/minecraft/item/ItemStack;)V"))
	private boolean terracotta$onlyEquipIfNotTiny(PlayerScreenHandler instance, ItemStack stack) {
		System.out.println(stack.getItem());
		return !(stack.getItem() instanceof TinyArmorItem);
	}
}
