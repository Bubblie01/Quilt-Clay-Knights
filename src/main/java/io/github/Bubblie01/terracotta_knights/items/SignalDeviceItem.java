package io.github.Bubblie01.terracotta_knights.items;

import io.github.Bubblie01.terracotta_knights.TerracottaRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class SignalDeviceItem extends Item {

	public SignalDeviceItem(Settings settings) {
		super(settings);
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

		if(!world.isClient)
		world.playSound(null,user.getBlockPos(), TerracottaRegistry.SIGNAL_SOUND_EVENT, SoundCategory.PLAYERS,1.0f, 1.0f);
		return super.use(world, user, hand);
	}
}
