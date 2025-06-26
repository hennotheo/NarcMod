package com.theohenno.narc_mod.entities.goals;

import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.util.math.Vec3d;

public class MoveToTask extends SoftwareTask {
    private final Vec3d target;
    private final double speed;

    public MoveToTask(PathAwareEntity mob, Vec3d target, double speed) {
        super(mob);

        this.target = target;
        this.speed = speed;
    }

    @Override
    public boolean canStart() {
        return true;
    }

    @Override
    public void start() {
        getMob().getNavigation().startMovingTo(target.x, target.y, target.z, speed);
    }

    @Override
    public boolean shouldContinue() {
        return !getMob().getNavigation().isIdle();
    }
}