package io.github.Bubblie01.terracotta_knights.mixin;

import io.github.Bubblie01.terracotta_knights.items.TerracottaItemFlag;
import io.github.Bubblie01.terracotta_knights.items.TinyArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.AnvilScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(AnvilScreenHandler.class)
public class AnvilScreenHandlerMixin {
	private ItemStack item;

	@Inject(method = "updateResult", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/inventory/Inventory;getStack(I)Lnet/minecraft/item/ItemStack;"), locals = LocalCapture.CAPTURE_FAILHARD)
	private void terracotta$getVariable(CallbackInfo ci, ItemStack itemStack) {
		this.item = itemStack;
	}

	@ModifyVariable(method = "updateResult", at = @At(value = "INVOKE", target = "Ljava/util/Map;keySet()Ljava/util/Set;"), ordinal = 3)
	private boolean terracotta$modifyBool(boolean bl4) {
		if(item.getItem() instanceof TerracottaItemFlag) {
			return false;
		}
		return true;
	}

}
