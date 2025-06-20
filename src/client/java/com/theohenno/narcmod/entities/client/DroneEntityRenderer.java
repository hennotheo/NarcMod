package com.theohenno.narcmod.entities.client;

import com.theohenno.narcmod.NarcMod;
import com.theohenno.narcmod.entities.DroneEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class DroneEntityRenderer extends MobEntityRenderer<DroneEntity, DroneEntityRenderState, DroneEntityModel> {
    public DroneEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new DroneEntityModel(context.getPart(DroneModelLayer.DRONE)), 0.5f);
    }

    @Override
    public Identifier getTexture(DroneEntityRenderState state) {
        return Identifier.of(NarcMod.MOD_ID, "textures/entity/drone/drone.png");
    }

    @Override
    public DroneEntityRenderState createRenderState() {
        return new DroneEntityRenderState();
    }
}