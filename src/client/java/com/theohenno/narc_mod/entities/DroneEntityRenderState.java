package com.theohenno.narc_mod.entities;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;

@Environment(EnvType.CLIENT)
public class DroneEntityRenderState extends LivingEntityRenderState {
    public DroneEntityRenderState() {
        super();
        // Initialize any specific state for the drone entity here
    }
}