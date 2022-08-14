package io.github.Bubblie01.terracotta_knights.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(EntityType.class)
public interface EntityTypeAccessor {

    @Invoker
    static <T extends Entity> EntityType<T> callRegister(String id, EntityType.Builder<T> type) {
        throw new UnsupportedOperationException();
    }
}
