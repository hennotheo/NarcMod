package com.theohenno.narc_mod.entities;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.EntityModel;

@Environment(EnvType.CLIENT)
public class DroneEntityModel extends EntityModel<DroneEntityRenderState> {

    public DroneEntityModel(ModelPart root) {
        super(root);
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();

        return TexturedModelData.of(modelData, 64, 64);
    }
}