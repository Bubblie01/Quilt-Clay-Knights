package io.github.Bubblie01.terracotta_knights;

import io.github.Bubblie01.terracotta_knights.entities.TerracottaKnightEntity;
import io.github.Bubblie01.terracotta_knights.items.TerracottaKnightItem;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.DispenserBehavior;
import net.minecraft.block.dispenser.ItemDispenserBehavior;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.Direction;
import net.minecraft.world.event.GameEvent;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;

public class Main implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod name as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final String MOD_ID = "terracotta_knights";

	@Override
	public void onInitialize(ModContainer mod) {
		TerracottaKnightEntity.registerClayKnightEntityAttributes();
		TerracottaRegistry.registerItems();
		TerracottaRegistry.registerRecipies();
		TerracottaRegistry.registerSounds();
		ItemDispenserBehavior itemDispenserBehavior = new ItemDispenserBehavior() {
			@Override
			public ItemStack dispenseSilently(BlockPointer pointer, ItemStack stack) {
				Direction direction = pointer.getBlockState().get(DispenserBlock.FACING);
				EntityType<?> entityType = TerracottaRegistry.TERRACOTTA_KNIGHT;
				TerracottaKnightItem item = (TerracottaKnightItem)stack.getItem();
				try {
					((TerracottaKnightEntity)(entityType.spawnFromItemStack(pointer.getWorld(), stack, null, pointer.getPos().offset(direction), SpawnReason.DISPENSER, direction != Direction.UP, false))).setColor(item.getColor(stack));
				} catch (Exception var6) {
					LOGGER.error("Error while dispensing spawn egg from dispenser at {}", pointer.getPos(), var6);
					return ItemStack.EMPTY;
				}

				stack.decrement(1);
				pointer.getWorld().emitGameEvent(null, GameEvent.ENTITY_PLACE, pointer.getPos());
				return stack;
			}
		};
		DispenserBlock.registerBehavior(TerracottaRegistry.TERRACOTTA_KNIGHT_ITEM, itemDispenserBehavior);
	}
}
