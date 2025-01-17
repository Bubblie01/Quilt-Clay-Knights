package io.github.Bubblie01.terracotta_knights.items;

import io.github.Bubblie01.terracotta_knights.TerracottaRegistry;
import io.github.Bubblie01.terracotta_knights.entities.TerracottaHorseEntity;
import io.github.Bubblie01.terracotta_knights.entities.TerracottaKnightEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.List;

//Code Created by Bubblie01 Under MPL 2.0 License
public class SignalDeviceItem extends Item {

	public SignalDeviceItem(Settings settings) {
		super(settings);
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		if(!world.isClient) {
			world.playSound(null, user.getBlockPos(), TerracottaRegistry.SIGNAL_SOUND_EVENT, SoundCategory.PLAYERS, 1.0f, 1.0f);
			Box killBox = user.getBoundingBox().expand(20,20,20);
			List<TerracottaKnightEntity> knights = world.getEntitiesByClass(TerracottaKnightEntity.class, killBox, knight -> knight != null);
			List<TerracottaHorseEntity> horses = world.getEntitiesByClass(TerracottaHorseEntity.class, killBox, horse -> horse != null);
			for (int i = 0; i < knights.size(); i++) {
				knights.get(i).kill();
			}
			for (int i = 0; i < horses.size(); i++) {
				horses.get(i).kill();
			}


		}
		return super.use(world, user, hand);
	}
}
