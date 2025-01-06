package io.github.Bubblie01.terracotta_knights.mixin;

import com.llamalad7.mixinextras.injector.WrapWithCondition;
import io.github.Bubblie01.terracotta_knights.items.TinyArmorItem;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Equippable;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.PlayerScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerScreenHandler.class)
public class PlayerScreenHandlerMixin {
	@WrapWithCondition(method = "onEquipItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;onEquipItem(Lnet/minecraft/entity/EquipmentSlot;Lnet/minecraft/item/ItemStack;Lnet/minecraft/item/ItemStack;)V"))
	private static boolean terracotta$onlyEquipIfNotTiny(PlayerEntity instance, EquipmentSlot equipmentSlot, ItemStack itemStack1, ItemStack itemStack2) {
		if(itemStack2.getItem() instanceof TinyArmorItem) {
			System.out.println("TRUE!");
			return false;
		}
		return true;
	}

}
