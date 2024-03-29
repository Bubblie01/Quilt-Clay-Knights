package io.github.Bubblie01.terracotta_knights.items;

import io.github.Bubblie01.terracotta_knights.entities.TinyPitchforkEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Vanishable;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

//Code Created by Bubblie01 Under MPL 2.0 License
public class TinyPitchforkItem extends Item implements Vanishable {

	public TinyPitchforkItem(Settings settings) {
		super(settings);
	}


	@Override
	public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
		PlayerEntity playerEntity = (PlayerEntity) user;
		if (!world.isClient) {
			stack.damage(1, user, p -> p.sendToolBreakStatus(user.getActiveHand()));
				TinyPitchforkEntity pitchforkEntity = new TinyPitchforkEntity(world, user, stack);

				pitchforkEntity.setProperties(user, user.getPitch(), user.getYaw(), 0.0F, 2.5F, 1.0F);
				if (playerEntity.getAbilities().creativeMode) {
					pitchforkEntity.pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;
				}
				world.spawnEntity(pitchforkEntity);
				world.playSoundFromEntity(null, pitchforkEntity, SoundEvents.ITEM_TRIDENT_THROW, SoundCategory.PLAYERS, 1.0F, 1.0F);
				if (!playerEntity.getAbilities().creativeMode) {
					playerEntity.getInventory().removeOne(stack);
				}
		}
		super.onStoppedUsing(stack, world, user, remainingUseTicks);
	}

	@Override
	public UseAction getUseAction(ItemStack stack) {
		return UseAction.SPEAR;
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		ItemStack itemStack = user.getStackInHand(hand);
		user.setCurrentHand(hand);
		return TypedActionResult.consume(itemStack);
	}
}
