package com.theohenno.narc_mod.entities.goals;

import net.minecraft.util.math.Vec3d;

public class MoveToTask extends SoftwareTask {
    private final Vec3d target;
    private final double speed;

    public MoveToTask(Vec3d target, double speed) {
        super();

        this.target = target;
        this.speed = speed;
    }

    @Override
    public boolean canStart() {
        return Mob != null;
    }

    @Override
    public void start() {
        Mob.getNavigation().startMovingTo(target.x, target.y, target.z, speed);
    }

    @Override
    public boolean shouldContinue() {
        return !Mob.getNavigation().isIdle();
    }
}