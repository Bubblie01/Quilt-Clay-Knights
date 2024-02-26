package io.github.Bubblie01.terracotta_knights.mixin;

import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Item.class)
public interface ItemAccessorMixin {

	@Mutable
	@Accessor("maxDamage")
	void setMaxDamage(int value);

	@Mutable
	@Accessor("maxCount")
	void setMaxCount(int value);
}
