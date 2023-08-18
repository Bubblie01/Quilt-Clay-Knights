package io.github.Bubblie01.terracotta_knights.entities.ai;

import io.github.Bubblie01.terracotta_knights.TerracottaRegistry;
import io.github.Bubblie01.terracotta_knights.entities.TerracottaKnightEntity;
import io.github.Bubblie01.terracotta_knights.items.TerracottaItemFlag;
import io.github.Bubblie01.terracotta_knights.items.TinyArmorItem;
import io.github.Bubblie01.terracotta_knights.items.TinyBowItem;
import io.github.Bubblie01.terracotta_knights.items.TinySwordItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.pathing.Path;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//Code Created by Bubblie01 Under MPL 2.0 License
public class ItemPickupGoal extends Goal {

	private final TerracottaKnightEntity knightEntity;
	private boolean reached;
	private List<ItemEntity> itemList = new ArrayList<ItemEntity>();
	private Box searchBox;
	private Random random = new Random();
	private ItemEntity randomItem;
	private final float searchRange;

	public ItemPickupGoal(TerracottaKnightEntity knightEntity, float searchRange) {
		this.knightEntity = knightEntity;
		this.searchRange = searchRange;
	}
	@Override
	public boolean canStart() {
		searchBox = knightEntity.getBoundingBox().expand((double)searchRange,(double)searchRange,(double)searchRange);
		List<ItemEntity> tempList = knightEntity.getWorld().getEntitiesByClass(ItemEntity.class, searchBox, knightEntity -> knightEntity != null);
		if(tempList.size() > 0) {
			for (int i = 0; i < tempList.size(); i++) {
				ItemEntity item = tempList.get(i);
				if (this.knightEntity.getVisibilityCache().canSee(item)) {
					if (item.getStack().getItem() instanceof TinySwordItem) {
						if (item.getStack().getItem() != TerracottaRegistry.TINY_BOW_ITEM)
							if (this.knightEntity.prefersNewEquipment(item.getStack(), this.knightEntity.getEquippedStack(EquipmentSlot.MAINHAND)))
								itemList.add(item);

					} else if (item.getStack().getItem() instanceof TinyArmorItem) {
						if (this.knightEntity.prefersNewEquipment(item.getStack(), this.knightEntity.getEquippedStack(((TinyArmorItem) item.getStack().getItem()).getPreferredSlot())))
							itemList.add(item);
					} else if (item.getStack().getItem() instanceof TinyBowItem && knightEntity.getEquippedStack(EquipmentSlot.MAINHAND).getItem() == Items.AIR) {
						itemList.add(item);
					} else if (item.getStack().getItem() == Items.TNT && knightEntity.findItemInventory(Items.TNT).getCount() < 3) {
						itemList.add(item);
					} else {
						itemList.remove(item);
					}
				}
			}
			if(itemList.size() != 0 && knightEntity.getEntityTarget() == null) {
				int random = (int) (Math.random() * itemList.size());
				knightEntity.setEntityTarget(itemList.get(random));
			}
			itemList.clear();
			return true;
		}
		return false;
	}

	@Override
	public void start() {
		//System.out.println(itemList.size());
		//this.randomItem = itemList.get((int) (itemList.size()) + 1);
	}

	@Override
	public void tick() {
		if(knightEntity != null && knightEntity.getEntityTarget() != null) {
			Path path = knightEntity.getNavigation().findPathTo(knightEntity.getEntityTarget(), 0);
			knightEntity.getNavigation().startMovingAlong(path, 0.5f);
			BlockPos blockPos = knightEntity.getEntityTarget().getBlockPos();
				if (blockPos.isCenterWithinDistance(knightEntity.getPos(), 1.0))
					knightEntity.setEntityTarget(null);
				//itemList.remove(knightEntity.getEntityTarget());
			}
		}

	@Override
	public boolean canStop() {
		BlockPos pos = randomItem.getBlockPos();
		if(pos.isCenterWithinDistance(knightEntity.getPos(), 1.0)) {
			return true;
		}
		return false;
	}

	@Override
	public void stop() {
		knightEntity.getNavigation().stop();
	}
}
