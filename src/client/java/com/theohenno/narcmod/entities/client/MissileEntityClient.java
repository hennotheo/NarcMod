package com.theohenno.narcmod.entities.client;

import com.theohenno.narcmod.NarcMod;
import com.theohenno.narcmod.entities.ModEntities;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class MissileEntityClient {
    public static final EntityModelLayer MODEL_MISSILE_LAYER = new EntityModelLayer(
            Identifier.of(NarcMod.MOD_ID, "missile"), "main");

    public static void initialize() {
        EntityModelLayerRegistry.registerModelLayer(MODEL_MISSILE_LAYER, MissileEntityModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.MISSILE, MissileEntityRenderer::new);
    }
}