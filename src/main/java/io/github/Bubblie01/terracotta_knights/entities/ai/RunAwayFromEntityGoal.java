package io.github.Bubblie01.terracotta_knights.entities.ai;

import io.github.Bubblie01.terracotta_knights.entities.TerracottaKnightEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.TntEntity;
import net.minecraft.entity.ai.FuzzyTargeting;
import net.minecraft.entity.ai.NoPenaltyTargeting;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.pathing.Path;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class RunAwayFromEntityGoal extends Goal {

	private TerracottaKnightEntity knightEntity;
	private float distance;
	private float searchRange;
	private Box searchBox;
	private List<TntEntity> tntList = new ArrayList<TntEntity>();

	private Vec3d vec3d;

	public RunAwayFromEntityGoal(TerracottaKnightEntity knightEntity, float searchRange) {
		this.knightEntity = knightEntity;
		//this.distance = distance;
		this.searchRange = searchRange;
		this.setControls(EnumSet.of(Goal.Control.MOVE));
	}

	@Override
	public boolean canStart() {
		searchBox = knightEntity.getBoundingBox().expand((double)searchRange,(double)searchRange,(double)searchRange);
		tntList = this.knightEntity.getWorld().getEntitiesByClass(TntEntity.class, searchBox, tntEntity -> tntEntity != null);
		if(tntList != null) {
			vec3d = NoPenaltyTargeting.find(this.knightEntity, 16, 7, this.knightEntity.getPos());
			return true;
		}
		return false;
	}

	@Override
	public void start() {

	}

	@Override
	public void tick() {
		if(knightEntity != null && tntList.size() > 0) {
			for(int i = 0; i < tntList.size(); i++) {
				if (vec3d != null) {
					Path path = this.knightEntity.getNavigation().findPathTo(vec3d.x + knightEntity.getRandom().nextDouble(), vec3d.y, vec3d.z + knightEntity.getRandom().nextDouble(), 0);
					this.knightEntity.getNavigation().startMovingAlong(path, 0.55f);
					tntList.remove(i);
				}
			}
		}
	}
}
