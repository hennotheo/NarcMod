package com.theohenno.narc_mod.entities.goals;

import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.PathAwareEntity;

public abstract class SoftwareTask extends Goal {
    private final PathAwareEntity mob;

    protected SoftwareTask(PathAwareEntity mob) {
        super();

        this.mob = mob;
    }

    protected PathAwareEntity getMob() {
        return mob;
    }
}
