package com.theohenno.narc_mod.entities;

import com.theohenno.narc_mod.NarcMod;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class DroneEntityClient {
    public static final EntityModelLayer MODEL_DRONE_LAYER = new EntityModelLayer(
            Identifier.of(NarcMod.MOD_ID, "drone"), "main");

    public static void initialize() {
        EntityModelLayerRegistry.registerModelLayer(MODEL_DRONE_LAYER, DroneEntityModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.DRONE, DroneEntityRenderer::new);
    }

    private DroneEntityClient() {
    }
}