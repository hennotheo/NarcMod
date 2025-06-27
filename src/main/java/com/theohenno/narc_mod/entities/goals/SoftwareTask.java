package com.theohenno.narc_mod.entities.goals;

import net.minecraft.entity.mob.PathAwareEntity;

public abstract class SoftwareTask {
    public PathAwareEntity Entity;

    public void onStart() {
    }

    public void onFinish() {
    }

    public void onTick() {
    }

    public abstract boolean isFinished();
}