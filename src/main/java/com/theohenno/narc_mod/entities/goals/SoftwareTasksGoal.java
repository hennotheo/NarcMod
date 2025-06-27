package com.theohenno.narc_mod.entities.goals;

import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.PathAwareEntity;

import java.util.EnumSet;
import java.util.Queue;

public class SoftwareTasksGoal extends Goal {
    private PathAwareEntity entity;

    private Queue<SoftwareTask> tasks;

    private SoftwareTask currentTask;

    public SoftwareTasksGoal(PathAwareEntity entity) {
        super();

        this.setControls(EnumSet.of(Control.MOVE, Control.LOOK));
        this.tasks = new java.util.LinkedList<>();
        this.currentTask = null;
        this.entity = entity;
    }

    public void addTask(SoftwareTask task) {
        if (task == null) {
            return;
        }

        tasks.add(task);

        if (currentTask == null) {
            setNewTaskAsCurrent();
        }
    }

    @Override
    public boolean shouldContinue() {
        return currentTask != null && !currentTask.isFinished();
    }

    @Override
    public boolean canStart() {
        return !tasks.isEmpty();
    }

    @Override
    public void start() {
        setNewTaskAsCurrent();
    }

    @Override
    public void tick() {
        if (currentTask == null) {
            setNewTaskAsCurrent();
            return;
        }

        currentTask.onTick();

        if (currentTask.isFinished()) {
            finishCurrentTask();
        }
    }

    private void finishCurrentTask() {
        currentTask.onFinish();
        setNewTaskAsCurrent();
    }

    private void setNewTaskAsCurrent() {
        currentTask = tasks.poll();
        if (currentTask != null) {
            currentTask.Entity = entity;
            currentTask.onStart();
        }
    }
}
