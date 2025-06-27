package com.theohenno.narc_mod.entities.goals;

import com.theohenno.narc_mod.NarcMod;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class MoveToTask extends SoftwareTask {
    private final Vec3d target;

    public MoveToTask(Vec3d target) {
        super();

        this.target = target;
    }

    @Override
    public boolean isFinished() {
        BlockPos targetPos = Entity.getNavigation().getTargetPos();

        if (targetPos == null) {
            return true;
        }

        boolean isAtTarget = targetPos.isWithinDistance(target, 0.1);
        boolean isIdle = Entity.getNavigation().isIdle();

        return isAtTarget && isIdle;
    }

    @Override
    public void onTick() {
        // No specific actions needed during the tick for this task
        NarcMod.LOGGER.info("MoveToTask: Ticking towards target: {}", target);
        Entity.getNavigation().startMovingTo(target.x, target.y, target.z, 1.0);
    }

    @Override
    public void onStart() {
        Entity.getNavigation().startMovingTo(target.x, target.y, target.z, 1.0);
    }
}