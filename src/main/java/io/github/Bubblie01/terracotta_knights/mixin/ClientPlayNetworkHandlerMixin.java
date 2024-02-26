package io.github.Bubblie01.terracotta_knights.mixin;

import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.s2c.play.ItemPickupAnimationS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayNetworkHandlerMixin {
	@Inject(at=@At(value="FIELD", target="net/minecraft/client/MinecraftClient.player:Lnet/minecraft/client/network/ClientPlayerEntity;"), method="onItemPickupAnimation", cancellable=true)
	public void terracotta$preventGlitchyPickup(ItemPickupAnimationS2CPacket pkt, CallbackInfo ci) {
		ci.cancel();
	}

}
