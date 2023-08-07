package io.github.Bubblie01.terracotta_knights.entities.ai;

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
import net.minecraft.util.math.Box;

import java.util.ArrayList;
import java.util.List;

public class ItemPickupGoal extends Goal {

	private final TerracottaKnightEntity knightEntity;

	private List<ItemEntity> itemList = new ArrayList<ItemEntity>();
	private Box searchBox;
	private final float searchRange;

	public ItemPickupGoal(TerracottaKnightEntity knightEntity, float searchRange) {
		this.knightEntity = knightEntity;
		this.searchRange = searchRange;
	}
	@Override
	public boolean canStart() {
		searchBox = knightEntity.getBoundingBox().expand((double)searchRange,(double)searchRange,(double)searchRange);
		List<ItemEntity> tempList = knightEntity.getWorld().getEntitiesByClass(ItemEntity.class, searchBox, knightEntity -> knightEntity != null);
		for(int i = 0; i < tempList.size(); i++) {
			ItemEntity item = tempList.get(i);
			if(this.knightEntity.getVisibilityCache().canSee(item)) {
				if(item.getStack().getItem() instanceof TinySwordItem) {
					if(this.knightEntity.prefersNewEquipment(item.getStack(), this.knightEntity.getEquippedStack(EquipmentSlot.MAINHAND)))
						itemList.add(item);

				}
				else if(item.getStack().getItem() instanceof TinyArmorItem) {
					if (this.knightEntity.prefersNewEquipment(item.getStack(), this.knightEntity.getEquippedStack(((TinyArmorItem) item.getStack().getItem()).getPreferredSlot())))
						itemList.add(item);
				}
				else if(item.getStack().getItem() instanceof TinyBowItem) {
					itemList.add(item);
				}
				else {
					itemList.remove(item);
				}
			}
		}
		for(int i = 0; i < itemList.size(); i++) {
			System.out.println(itemList.get(i).getName());
		}
		return true;
	}

	@Override
	public void tick() {
		if(knightEntity != null && itemList.size() > 0) {
				for(int i = 0; i < itemList.size(); i++) {
					Path path = knightEntity.getNavigation().findPathTo(itemList.get(i), 0);
					knightEntity.getNavigation().startMovingAlong(path, 0.5f);
					itemList.remove(i);
				}
		}
		super.tick();
	}

	@Override
	public boolean canStop() {
		return super.canStop();
	}
}