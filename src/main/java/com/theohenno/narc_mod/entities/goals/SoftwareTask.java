package com.theohenno.narc_mod.entities.goals;

import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.PathAwareEntity;

public abstract class SoftwareTask extends Goal {
    public PathAwareEntity Mob;

    protected SoftwareTask() {
        super();
    }
}
