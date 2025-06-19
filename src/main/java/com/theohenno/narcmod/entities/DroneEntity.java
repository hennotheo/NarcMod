package com.theohenno.narcmod.entities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.world.World;

public class DroneEntity extends MobEntity {
    public DroneEntity(EntityType<? extends MobEntity> entityType, World world) {
        super(entityType, world);
    }
}