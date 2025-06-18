package com.theohenno.narcmod.entities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.world.World;

public class MissileEntity extends MobEntity {
    public MissileEntity(EntityType<? extends MobEntity> entityType, World world) {
        super(entityType, world);
    }
}