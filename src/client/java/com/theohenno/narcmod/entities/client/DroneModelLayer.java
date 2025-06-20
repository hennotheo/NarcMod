package com.theohenno.narcmod.entities.client;

import com.google.common.collect.Sets;
import com.theohenno.narcmod.NarcMod;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

import java.util.Set;

public class DroneModelLayer {
    private static final Set<EntityModelLayer> LAYERS = Sets.newHashSet();
    public static final EntityModelLayer DRONE = registerMain("drone");

    private static EntityModelLayer create(String name, String layer) {
        return new EntityModelLayer(Identifier.of(NarcMod.MOD_ID, name), layer);
    }

    private static EntityModelLayer register(String name, String layer) {
        EntityModelLayer entityModelLayer = create(name, layer);
        if (!LAYERS.add(entityModelLayer)) {
            throw new IllegalStateException("Duplicate registration for " + String.valueOf(entityModelLayer));
        } else {
            return entityModelLayer;
        }
    }

    private static EntityModelLayer registerMain(String name) {
        return register(name, "main");
    }
}

