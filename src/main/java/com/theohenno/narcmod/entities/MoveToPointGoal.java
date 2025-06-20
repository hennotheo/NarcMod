package com.theohenno.narcmod.entities;

import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.util.math.Vec3d;

public class MoveToPointGoal extends Goal {
    private final PathAwareEntity mob;
    private final Vec3d target;
    private final double speed;

    public MoveToPointGoal(PathAwareEntity mob, Vec3d target, double speed) {
        this.mob = mob;
        this.target = target;
        this.speed = speed;
    }

    @Override
    public boolean canStart() {
        return true;
    }

    @Override
    public void start() {
        mob.getNavigation().startMovingTo(target.x, target.y, target.z, speed);
    }

    @Override
    public boolean shouldContinue() {
        // continue tant que le chemin n'est pas fini
        return !mob.getNavigation().isIdle();
    }
}