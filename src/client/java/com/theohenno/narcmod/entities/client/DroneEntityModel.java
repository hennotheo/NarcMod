package com.theohenno.narcmod.entities.client;

import com.theohenno.narcmod.entities.DroneEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;

@Environment(EnvType.CLIENT)
public class DroneEntityModel extends EntityModel<DroneEntityRenderState> {
    private final ModelPart main;

    public DroneEntityModel(ModelPart root) {
        super(root);
        this.main = root.getChild("main");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData main = modelPartData
                .addChild("main", ModelPartBuilder.create().uv(0, 0)
                                .cuboid(-6.0F, -7.0F, -6.0F, 12.0F, 7.0F, 12.0F,
                                        new Dilation(0.0F)),
                        ModelTransform.origin(0.0F, 24.0F, 0.0F));

        return TexturedModelData.of(modelData, 64, 64);
    }
}