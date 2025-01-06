package io.github.Bubblie01.terracotta_knights.entities.ai;

import io.github.Bubblie01.terracotta_knights.entities.TerracottaHorseEntity;
import io.github.Bubblie01.terracotta_knights.entities.TerracottaKnightEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.TntEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.pathing.Path;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class RideAndFindGoal extends Goal {
	private final TerracottaKnightEntity knightEntity;
	private final float searchRange;
	private Box searchBox;
	private List<TerracottaHorseEntity> horseList = new ArrayList<TerracottaHorseEntity>();

	private int tickCounter = 0;
	public RideAndFindGoal(TerracottaKnightEntity knightEntity, float searchRange) {
		this.knightEntity = knightEntity;
		this.searchRange = searchRange;
		this.setControls(EnumSet.of(Goal.Control.MOVE, Goal.Control.LOOK));
	}
	@Override
	public boolean canStart() {
		this.searchBox = knightEntity.getBoundingBox().expand(this.searchRange, this.searchRange, this.searchRange);
		List<TerracottaHorseEntity> tempList = this.knightEntity.getWorld().getEntitiesByClass(TerracottaHorseEntity.class, searchBox, entity -> entity != null);
		if (tempList.size() > 0) {
			for (int i = 0; i < tempList.size(); i++) {
				TerracottaHorseEntity horseEntity = tempList.get(i);
				if(!horseEntity.hasPassengers() && !knightEntity.hasVehicle() && knightEntity.canSee(horseEntity))
					this.horseList.add(horseEntity);
			}

			if (horseList.size() != 0 && knightEntity.getTarget() == null) {
				int random = (int) (Math.random() * horseList.size());
				knightEntity.setTarget(horseList.get(random));
			}
			this.horseList.clear();
			return true;
		}

		return false;
	}
	@Override
	public void tick() {
		if (knightEntity != null && knightEntity.getTarget() != null && knightEntity.getTarget() instanceof TerracottaHorseEntity) {
				Path path = knightEntity.getNavigation().findPathTo(knightEntity.getTarget(), 0);
				BlockPos blockPos = knightEntity.getTarget().getBlockPos();
				if (knightEntity.getNavigation().isValidPosition(blockPos))
					knightEntity.getNavigation().startMovingAlong(path, 0.5f);

				if (blockPos.isCenterWithinDistance(knightEntity.getPos(), 1.0)) {
					knightEntity.startRiding(knightEntity.getTarget());
					knightEntity.setTarget(null);
				}
			if (tickCounter % 50 == 0) {
				knightEntity.getNavigation().recalculatePath();
			}
			if(tickCounter % 100 == 0)
				tickCounter = 0;
		}
		tickCounter++;
	}

}
