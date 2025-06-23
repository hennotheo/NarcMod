package com.theohenno.narc_mod.entities;

import com.theohenno.narc_mod.NarcMod;
import com.theohenno.narc_mod.entities.screen_handler.DroneScreenHandler;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<DroneEntity> DRONE = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(NarcMod.MOD_ID, DroneEntity.ID),
            EntityType.Builder.create(DroneEntity::new, SpawnGroup.CREATURE)
                    .dimensions(0.75f, 0.75f)
                    .build(RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(NarcMod.MOD_ID, DroneEntity.ID)))
    );

    public static final ScreenHandlerType<DroneScreenHandler> DRONE_SCREEN_HANDLER = new ScreenHandlerType<>(DroneScreenHandler::new, FeatureFlags.VANILLA_FEATURES);

    public static void initialize() {
        FabricDefaultAttributeRegistry.register(DRONE, DroneEntity.createDroneAttributes());
        Registry.register(Registries.SCREEN_HANDLER, Identifier.of(NarcMod.MOD_ID, "drone_screen"), DRONE_SCREEN_HANDLER);
    }

    private ModEntities() {
    }
}