package io.github.Bubblie01.terracotta_knights.mixin;

import io.github.Bubblie01.terracotta_knights.entities.TerracottaKnightEntity;
import net.minecraft.entity.*;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(TntEntity.class)
public abstract class TntEntityMixin implements Ownable {

	@Shadow
	public abstract @Nullable LivingEntity getOwner();

	@ModifyArgs(method = "Lnet/minecraft/entity/TntEntity;explode()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;createExplosion(Lnet/minecraft/entity/Entity;DDDFLnet/minecraft/world/World$ExplosionSourceType;)Lnet/minecraft/world/explosion/Explosion;"))
	private void terracotta$_modifyTntPower(Args args) {
		if(this.getOwner() instanceof TerracottaKnightEntity) {
			args.set(5, World.ExplosionSourceType.MOB);
			args.set(4, 2.0f);
		}
	}
}
