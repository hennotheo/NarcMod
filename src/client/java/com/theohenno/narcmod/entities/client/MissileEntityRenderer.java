package com.theohenno.narcmod.entities.client;

import com.theohenno.narcmod.NarcMod;
import com.theohenno.narcmod.entities.MissileEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class MissileEntityRenderer extends MobEntityRenderer<MissileEntity, MissileEntityRenderState, MissileEntityModel> {
    public MissileEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new MissileEntityModel(context.getPart(MissileModelLayer.MISSILE)), 0.5f);
    }

    @Override
    public Identifier getTexture(MissileEntityRenderState state) {
        return Identifier.of(NarcMod.MOD_ID, "textures/entity/missile/missile.png");
    }

    @Override
    public MissileEntityRenderState createRenderState() {
        return new MissileEntityRenderState();
    }
}