package io.github.Bubblie01.terracotta_knights;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.pathing.Path;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.BowItem;
import net.minecraft.item.Items;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class TerracottaKnightAttackGoal extends Goal{
	private TerracottaKnightEntity knightEntity;
	private PathAwareEntity targetEntity;
	private Path path;
	private Vec3d pos;
	private List<Entity> entityList;
	private List<TerracottaKnightEntity> enemyList = new ArrayList<TerracottaKnightEntity>();
	private Box searchBox;
	private float searchRange;
	private float attackRange;
	public TerracottaKnightAttackGoal(TerracottaKnightEntity knightEntity, float searchRange, float attackRange) {
		this.knightEntity = knightEntity;
		this.searchRange = searchRange;
		this.attackRange = attackRange;
		this.setControls(EnumSet.of(Goal.Control.MOVE, Goal.Control.LOOK));
	}


	@Override
	public boolean canStart() {
		searchBox = knightEntity.getBoundingBox().expand((double)searchRange,(double)searchRange,(double)searchRange);
		entityList = knightEntity.getWorld().getEntitiesByClass(Entity.class,searchBox, knightEntity -> knightEntity != null);
		for(int i = 0; i<entityList.size(); i++) {
			Entity entity = entityList.get(i);
			if(entity instanceof TerracottaKnightEntity) {
				int color = entity.getDataTracker().get(TerracottaKnightEntity.COLOR);
				if(color != knightEntity.getDataTracker().get(TerracottaKnightEntity.COLOR)) {
					enemyList.add((TerracottaKnightEntity) entity);
				}
				else {
					enemyList.remove(entity);
				}
			}
		}

		if((enemyList != null && enemyList.size() != 0)) {
			if((knightEntity.getTarget() == null || knightEntity.getTarget().isDead())) {
					int random = (int) (Math.random() * enemyList.size());
					knightEntity.setTarget(enemyList.get(random));

			}
			else {
				int knightColor =knightEntity.getDataTracker().get(TerracottaKnightEntity.COLOR);
				int enemyColor = knightEntity.getTarget().getDataTracker().get(TerracottaKnightEntity.COLOR);
				if(enemyColor == knightColor) {
					knightEntity.setTarget(null);
				}
			}
			enemyList.clear();
			return true;
		}

		return false;

	}

	@Override
	public void start() {
		knightEntity.setAttacking(true);
	}

	@Override
	public void tick() {
		if(knightEntity.getAttacker() instanceof TerracottaKnightEntity && knightEntity.getColor() != ((TerracottaKnightEntity) knightEntity.getAttacker()).getColor() && knightEntity.getAttacker() != null) {
			knightEntity.setTarget(knightEntity.getAttacker());
		}
		if(knightEntity.getTarget() !=null) {
				path = knightEntity.getNavigation().findPathTo(knightEntity.getTarget(), 0);
				knightEntity.getLookControl().lookAt(knightEntity.getTarget());
				knightEntity.getNavigation().startMovingAlong(path, 0.5f);
				if (knightEntity.squaredDistanceTo(knightEntity.getTarget()) <= this.getAttackDistance((PathAwareEntity) knightEntity.getTarget())) {
					if (knightEntity.getVisibilityCache().canSee(knightEntity.getTarget())) {
						knightEntity.tryAttack(knightEntity.getTarget());
						knightEntity.swingHand(Hand.MAIN_HAND);
					}
				}
				else if(knightEntity.isHolding(Items.BOW) && knightEntity.squaredDistanceTo(knightEntity.getTarget()) >= 15) {
					knightEntity.setCurrentHand(ProjectileUtil.getHandPossiblyHolding(knightEntity, Items.BOW));
					int useTime = knightEntity.getItemUseTime();
					if (useTime >= 20) {
						knightEntity.clearActiveItem();
						System.out.println(useTime);
						knightEntity.rangedAttack(knightEntity.getTarget(), BowItem.getPullProgress(useTime));

					}
				}
		}

		super.tick();
	}

	@Override
	public void stop() {
		knightEntity.setAttacking(false);
	}

	private double getAttackDistance(PathAwareEntity entity) {
		return (double)(knightEntity.getWidth() * attackRange * knightEntity.getWidth() * attackRange + entity.getWidth());
	}
}
