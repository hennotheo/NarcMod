package com.theohenno.narcmod.entities;

import com.theohenno.narcmod.NarcMod;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

public class DroneEntity extends PathAwareEntity {
    public DroneEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);

        Vec3d target = new Vec3d(0, 0, 0);
        getNavigation().startMovingTo(target.x, target.y, target.z, speed);
    }

    public static DefaultAttributeContainer.Builder createDroneAttributes() {
        return AnimalEntity.createAnimalAttributes()
                .add(EntityAttributes.MAX_HEALTH, 10.0)
                .add(EntityAttributes.MOVEMENT_SPEED, 0.25);
    }

    @Override
    protected void updateGoalControls() {
        super.updateGoalControls();
    }


    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new MoveToPointGoal(this, new Vec3d(0, 0, 0), 1.0));
//        this.goalSelector.add(1, new FlyGoal(this, 2));
//        this.goalSelector.add(2, new LookAroundGoal(this));
    }
}