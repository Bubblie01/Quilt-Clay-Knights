package io.github.Bubblie01.terracotta_knights.items;

import io.github.Bubblie01.terracotta_knights.TerracottaRegistry;
import io.github.Bubblie01.terracotta_knights.entities.TerracottaKnightEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DyeableItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import org.apache.logging.log4j.core.jmx.Server;

public class TerracottaKnightItem extends Item implements DyeableItem {
	public TerracottaKnightItem(Settings settings) {
		super(settings);
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		ItemStack itemStack = user.getStackInHand(hand);
		HitResult hitResult = raycast(world, user, RaycastContext.FluidHandling.SOURCE_ONLY);
		BlockHitResult blockHitResult = (BlockHitResult)hitResult;
		EntityType<?> terracottaType = TerracottaRegistry.TERRACOTTA_KNIGHT;
		if(!world.isClient) {
			TerracottaKnightEntity knight = (TerracottaKnightEntity) terracottaType.create((ServerWorld) world, null, EntityType.createDefaultStackSpawnConfig((ServerWorld) world, itemStack, user), blockHitResult.getBlockPos(), SpawnReason.SPAWN_EGG, true, false);
			knight.setColor(this.getColor(user.getStackInHand(hand)));
			((ServerWorld)world).spawnEntityAndPassengers(knight);
		}
		if(!user.isCreative())
			itemStack.decrement(1);
		return super.use(world, user, hand);
	}


}
